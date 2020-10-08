package com.afi.minby.home.homemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.core.VerticalSpaceDecoration
import com.afi.minby.model.IdeaTemplateImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    @Inject
    lateinit var template: IdeaTemplateImpl

    private val viewModel: HomeViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.addHomeMenuItems()
        observeHomeMenuItems()
    }

    private fun observeHomeMenuItems() {
        viewModel.gethomeMenuItem_.observe(viewLifecycleOwner, Observer {
            with(recyclerView) {
                addItemDecoration(VerticalSpaceDecoration())
                adapter = HomeMenuAdapter(
                    it,
                    object : AdapterCallback {
                        override fun <T> onItemClicked(item: T) {
                            val ideaType = (item as HomeMenuItem).title
                            val updatedTemplate =
                                template.ideaTemplate.copy(ideaType = requireContext().getString(ideaType))
                            template.update(updatedTemplate)
                            NavHostFragment.findNavController(hostFragment)
                                .navigate(R.id.sendIdeaActivity)
                        }
                    })
            }
        })
    }
}

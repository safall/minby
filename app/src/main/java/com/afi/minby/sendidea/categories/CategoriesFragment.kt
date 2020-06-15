package com.afi.minby.sendidea.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.core.CategoriesItemDecoration
import com.afi.minby.home.AdapterCallback
import com.afi.minby.sendidea.categories.CategoriesAdapter.Companion.VIEW_HOLDER_ANOTHER_CATEGORY
import com.afi.minby.sendidea.categories.CategoriesAdapter.Companion.VIEW_HOLDER_CATEGORY
import com.afi.minby.sendidea.categories.model.CategoriesList
import kotlinx.android.synthetic.main.activity_send_idea.*
import kotlinx.android.synthetic.main.categories_fragment.*

class CategoriesFragment : Fragment(R.layout.categories_fragment) {

    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoriesAdapter = CategoriesAdapter(
            CategoriesList.getCategories(requireContext()),
            object : AdapterCallback {
                override fun <T> onItemClicked(item: T) {
                   NavHostFragment.findNavController(host_fragment).navigate(R.id.categoriesFragmentToSelectLocationFragment)
                }

            })
        with(recyclerView) {
            adapter = categoriesAdapter
            layoutManager = getGridLayoutManager()
            addItemDecoration(CategoriesItemDecoration())
        }
    }

    private fun getGridLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (categoriesAdapter.getItemViewType(position)) {
                        VIEW_HOLDER_ANOTHER_CATEGORY -> 2
                        VIEW_HOLDER_CATEGORY -> 1
                        else -> 1
                    }
                }
            }
        }
    }
}

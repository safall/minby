package com.afi.minby.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.categories.CategoriesAdapter.Companion.VIEW_HOLDER_ANOTHER_CATEGORY
import com.afi.minby.categories.CategoriesAdapter.Companion.VIEW_HOLDER_CATEGORY
import com.afi.minby.categories.CategoriesAdapter.Companion.VIEW_HOLDER_SUBMIT_BUTTON
import com.afi.minby.categories.model.CategoriesEnum
import com.afi.minby.categories.model.Category
import com.afi.minby.core.ArtworkUtils
import com.afi.minby.core.CategoriesItemDecoration
import kotlinx.android.synthetic.main.categories_fragment.*

class CategoriesFragment : Fragment(R.layout.categories_fragment) {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artworkSize: Int = ArtworkUtils.getSize(
            R.dimen.size_screen_width,
            R.dimen.size_103dp,
            view.context
        ) / 3

        categoriesAdapter = CategoriesAdapter(artworkSize)
        with(recyclerView) {
            adapter = categoriesAdapter
            layoutManager = getGridLayoutManager()
            addItemDecoration(CategoriesItemDecoration())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        addItems()
    }

    private fun getGridLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 3).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (categoriesAdapter.getItemViewType(position)) {
                        VIEW_HOLDER_SUBMIT_BUTTON,
                        VIEW_HOLDER_ANOTHER_CATEGORY -> 3
                        VIEW_HOLDER_CATEGORY -> 1
                        else -> 1
                    }
                }
            }
        }
    }

    private fun addItems() {
        val items = arrayListOf<Category>()
        items.apply {
            add(
                0,
                Category(
                    "Social",
                    CategoriesEnum.SOCIAL
                )
            )
            add(
                1,
                Category(
                    "Building and construction",
                    CategoriesEnum.BUILDING
                )
            )
            add(
                2,
                Category(
                    "Nature and green",
                    CategoriesEnum.NATURE

                )
            )
            add(
                3,
                Category(
                    "Activities and training",
                    CategoriesEnum.ACTIVITIES

                )
            )
            add(
                4,
                Category(
                    "Technology",
                    CategoriesEnum.TECHNOLOGY

                )
            )
            add(
                5,
                Category(
                    "Ogranization",
                    CategoriesEnum.ORGANISATION

                )
            )
            add(
                6,
                Category(
                    "Annet",
                    null
                )
            )
            add(
                7,
                Category(
                    "Submit",
                    null
                )
            )
        }
        categoriesAdapter.items = items
        categoriesAdapter.notifyDataSetChanged()
    }
}

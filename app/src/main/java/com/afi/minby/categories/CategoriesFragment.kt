package com.afi.minby.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.categories.CategoriesAdapter.Companion.VIEW_HOLDER_ANOTHER_CATEGORY
import com.afi.minby.categories.CategoriesAdapter.Companion.VIEW_HOLDER_CATEGORY
import com.afi.minby.categories.model.CategoriesList
import com.afi.minby.core.CategoriesItemDecoration
import kotlinx.android.synthetic.main.categories_fragment.*

class CategoriesFragment : Fragment(R.layout.categories_fragment) {

    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoriesAdapter = CategoriesAdapter(CategoriesList.getCategories(requireContext()))
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

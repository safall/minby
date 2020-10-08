package com.afi.minby.home.settings.subpage

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.core.inflate
import com.afi.minby.home.settings.subpage.model.Terms
import kotlinx.android.synthetic.main.privacy_item.view.*

class PrivacyAdapter(var items: List<Terms>) :
    RecyclerView.Adapter<PrivacyAdapter.TermsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermsViewHolder {
        return TermsViewHolder(parent.context.inflate(R.layout.privacy_item, parent, false))
    }

    override fun onBindViewHolder(holder: TermsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class TermsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Terms) {
            with(itemView) {
                title.text = item.title
                desc.text = item.desc
            }
        }
    }
}

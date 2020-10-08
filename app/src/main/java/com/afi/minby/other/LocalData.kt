package com.afi.minby.other

import com.afi.minby.R
import com.afi.minby.home.homemenu.HomeMenuItem

object LocalData {
    fun getDummyData(): List<HomeMenuItem> {
        return arrayListOf<HomeMenuItem>()
            .apply {
                add(
                    0,
                    HomeMenuItem(
                        R.string.send_us_idea,
                        R.string.desc_home_menu,
                        R.drawable.ic_idea
                    )
                )
                add(
                    1,
                    HomeMenuItem(
                        R.string.send_us_review,
                        R.string.desc_home_menu,
                        R.drawable.ic_review
                    )
                )
                add(
                    2,
                    HomeMenuItem(
                        R.string.send_us_report,
                        R.string.desc_home_menu,
                        R.drawable.ic_interview
                    )
                )
            }
    }
}

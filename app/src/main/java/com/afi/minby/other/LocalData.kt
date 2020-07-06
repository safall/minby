package com.afi.minby.other

import com.afi.minby.R
import com.afi.minby.home.HomeMenuItem

object LocalData {
    fun getDummyData(): List<HomeMenuItem> {
        return arrayListOf<HomeMenuItem>()
            .apply {
                add(
                    0,
                    HomeMenuItem(
                        "Send oss en ide",
                        "Lorem ipsum dolor sit amet consectetur adipiscing elit. praesent bibendum massa lectus, vitae",
                        R.drawable.ic_idea
                    )
                )
                add(
                    1,
                    HomeMenuItem(
                        "Send oss en vurdering",
                        "Lorem ipsum dolor sit amet consectetur adipiscing elit. praesent bibendum massa lectus, vitae",
                        R.drawable.ic_interview
                    )
                )
                add(
                    2,
                    HomeMenuItem(
                        "Send oss en intervju",
                        "Lorem ipsum dolor sit amet consectetur adipiscing elit. praesent bibendum massa lectus, vitae",
                        R.drawable.ic_interview
                    )
                )
            }
    }
}

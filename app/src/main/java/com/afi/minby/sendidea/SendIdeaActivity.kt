package com.afi.minby.sendidea

import androidx.appcompat.app.AppCompatActivity
import com.afi.minby.R
import com.afi.minby.model.IdeaTemplateImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SendIdeaActivity : AppCompatActivity(R.layout.activity_send_idea) {

    @Inject
    lateinit var template: IdeaTemplateImpl

    override fun onDestroy() {
        super.onDestroy()
        template.clear()
    }
}

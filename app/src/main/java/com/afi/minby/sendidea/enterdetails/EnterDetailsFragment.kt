package com.afi.minby.sendidea.enterdetails

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.model.IdeaTemplateImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_send_idea.*
import kotlinx.android.synthetic.main.enter_details_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class EnterDetailsFragment : Fragment(R.layout.enter_details_fragment) {

    companion object {
        private const val VOICE_RECOGNITION_REQUEST_CODE = 200
    }

    @Inject
    lateinit var template: IdeaTemplateImpl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (template.ideaTemplate.description.isNotBlank()) {
            description.setText(template.ideaTemplate.description)
        }
        voiceIcon.setOnClickListener { speak() }
        nextButton.setOnClickListener {
            val desc = description.text.toString()
            val updatedTempl = template.ideaTemplate.copy(description = desc)
            template.update(updatedTempl)
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.enterDetailsFragmentToSummaryDetailsFragment)
        }
        backIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }
        description.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                nextButton.isEnabled = s.isNotBlank()
            }
        })
    }

    private fun speak() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, javaClass.getPackage()!!.name)
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.message_hint))
        }
        this.startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            val text =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
            description.setText(text[0])
        }
    }
}

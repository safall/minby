package com.afi.minby.sendidea.enterdetails

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.sendidea.enterdetails.viewmodel.EnterDetailsViewModel
import kotlinx.android.synthetic.main.activity_send_idea.*
import kotlinx.android.synthetic.main.enter_details_fragment.*

class EnterDetailsFragment : Fragment(R.layout.enter_details_fragment) {

    companion object {
        private const val VOICE_RECOGNITION_REQUEST_CODE = 200
    }

    private val viewModel: EnterDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        voiceIcon.setOnClickListener { speak() }
        nextButton.setOnClickListener {
            val bundle = Bundle().apply {
                arguments?.putString("ARG_DESCRIPTION", description.text.toString())
            }
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.enterDetailsFragmentToSummaryDetailsFragment, bundle)
        }
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

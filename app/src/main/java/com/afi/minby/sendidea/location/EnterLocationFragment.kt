package com.afi.minby.sendidea.location

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.model.IdeaTemplateImpl
import com.afi.minby.sendidea.IdeaTemplate
import com.afi.minby.sendidea.categories.model.Category
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_send_idea.*
import kotlinx.android.synthetic.main.enter_location_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class EnterLocationFragment : Fragment(R.layout.enter_location_fragment), OnMapReadyCallback {

    @Inject
    lateinit var template: IdeaTemplateImpl

    private lateinit var googleMap: GoogleMap

    private var currentLatLng = LatLng(59.9139, 10.7522)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("location fragment", template.ideaTemplate.toString())
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        nextButton.setOnClickListener {
            val updatedTempl  = template.ideaTemplate.copy(latlng = currentLatLng)
            template.update(updatedTempl)
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.enterLocationFragmentToEnterDetailsFragment)
        }
        backIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map ?: return
        with(googleMap) {
            animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13f))
            setOnMapClickListener {
                clear()
                animateCamera(CameraUpdateFactory.newLatLngZoom(it, 13f))
                addMarker(MarkerOptions().apply {
                    position(it)
                    title(it.latitude.toString() + " : " + it.longitude.toString())
                })
                currentLatLng = it
                nextButton.isEnabled = true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }
}

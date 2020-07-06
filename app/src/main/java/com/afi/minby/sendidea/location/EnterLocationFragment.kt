package com.afi.minby.sendidea.location

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_send_idea.*
import kotlinx.android.synthetic.main.enter_location_fragment.*


class EnterLocationFragment : Fragment(R.layout.enter_location_fragment), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    private val osloLatLng = LatLng(59.9139, 10.7522)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        nextButton.setOnClickListener {
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.enterLocationFragmentToEnterDetailsFragment)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map ?: return
        with(googleMap) {
            animateCamera(CameraUpdateFactory.newLatLngZoom(osloLatLng, 13f))
            setOnMapClickListener {
                val markerOptions = MarkerOptions().apply {
                    position(it)
                    title(it.latitude.toString() + " : " + it.longitude)
                }

                apply {
                    clear()
                    animateCamera(CameraUpdateFactory.newLatLngZoom(it, 13f))
                    addMarker(markerOptions)
                }
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

package com.afi.minby.sendidea.location

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_send_idea.*
import kotlinx.android.synthetic.main.enter_location_fragment.*

class EnterLocationFragment : Fragment(R.layout.enter_location_fragment) {

    private lateinit var googleMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        val sydney = LatLng(59.9139, 10.7522)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Oslo"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        nextButton.setOnClickListener {
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.enterLocationFragmentToEnterDetailsFragment)
        }
    }
}

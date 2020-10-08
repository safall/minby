package com.afi.minby.home.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.afi.minby.R
import com.afi.minby.model.MyClusterItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.enter_location_fragment.*
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream
import java.util.*

@AndroidEntryPoint
class MapUnifiedFragment : Fragment(R.layout.fragment_map_unified), OnMapReadyCallback {

    companion object {
        private const val REGEX_INPUT_BOUNDARY_BEGINNING = "\\A"
    }

    private var clusterManager: ClusterManager<MyClusterItem>? = null

    private lateinit var googleMap: GoogleMap

    private var currentLatLng = LatLng(59.9695, 10.6209)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map ?: return
        with(googleMap) {
            animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13f))
        }

        clusterManager = ClusterManager(requireContext(), googleMap)
        googleMap.setOnCameraIdleListener(clusterManager)
        try {
            readItems()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    @Throws(JSONException::class)
    private fun readItems() {
        val inputStream = resources.openRawResource(R.raw.search)
        val items: List<MyClusterItem> = read(inputStream)
        for (i in 0..9) {
            val offset = i / 11.1
            for (item in items) {
                val position = item.position
                val lat = position.latitude + offset
                val lng = position.longitude + offset
                val offsetItem =
                    MyClusterItem(lat, lng, LatLng(lat, lng), "item title", "item snippet")
                clusterManager?.addItem(offsetItem)
            }
        }
    }

    @Throws(JSONException::class)
    fun read(inputStream: InputStream?): List<MyClusterItem> {
        val items: MutableList<MyClusterItem> = arrayListOf<MyClusterItem>()
        val json: String = Scanner(inputStream).useDelimiter(REGEX_INPUT_BOUNDARY_BEGINNING).next()
        val array = JSONArray(json)
        for (i in 0 until array.length()) {
            var title: String? = null
            var snippet: String? = null
            val jsonObj = array.getJSONObject(i)
            val lat = jsonObj.getDouble("lat")
            val lng = jsonObj.getDouble("lng")
            if (!jsonObj.isNull("title")) {
                title = jsonObj.getString("title")
            }
            if (!jsonObj.isNull("snippet")) {
                snippet = jsonObj.getString("snippet")
            }
            items.add(
                MyClusterItem(
                    lat,
                    lng,
                    LatLng(lat, lng),
                    title ?: "item title",
                    snippet ?: "item snippet"
                )
            )
        }
        return items
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

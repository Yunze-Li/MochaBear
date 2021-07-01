package com.arctos.mochabear.mapdemo

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.arctos.mochabear.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.maps.android.SphericalUtil
import com.google.maps.android.ui.IconGenerator


class GoogleMapDemoActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup UI
        setContentView(R.layout.google_map_demo)
        val demoButton1 = findViewById<AppCompatButton>(R.id.location_button_1)
        val demoButton2 = findViewById<AppCompatButton>(R.id.location_button_2)
        val demoButton3 = findViewById<AppCompatButton>(R.id.location_button_3)
        val demoButton4 = findViewById<AppCompatButton>(R.id.location_button_4)

        demoButton1.setOnClickListener {
            val beijingMarker = MapMarker(
                LatLng(39.916668, 116.383331),
                "This is Beijing",
                "The capital of China, where I live."
            )
            showMarkerOnMap(beijingMarker)
            moveCameraToMarker(beijingMarker)
        }
        demoButton2.setOnClickListener {
            val seattleMarker = MapMarker(
                LatLng(47.608013, -122.335167),
                "This is Seattle",
                "Where Amazon headquarter is."
            )
            showMarkerOnMap(seattleMarker)
            moveCameraToMarker(seattleMarker)
        }
        demoButton3.setOnClickListener {
            val dubaiMarker = MapMarker(
                LatLng(25.276987, 55.296249),
                "This is Dubai",
                "Currently I stay in here."
            )
            showMarkerOnMap(dubaiMarker)
            moveCameraToMarker(dubaiMarker)
        }
        demoButton4.setOnClickListener {
            val place1 = MapMarker(LatLng(39.920628, 116.395179), "长春宫")
            val place2 = MapMarker(LatLng(39.919567, 116.399118), "奉先殿")
            val place3 = MapMarker(LatLng(39.913762, 116.397145), "午门", "斩首的地方")
            showMultipleMarkersAndMoveCamera(listOf(place1, place2, place3))
        }

        val bottomSheet = findViewById<ConstraintLayout>(R.id.google_map_bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // no ops
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // no ops
            }
        })

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.google_map_fragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }

    private fun showMarkerOnMap(marker: MapMarker) {
        marker.title?.let {
            val customMarkerView = bindCustomMarkerInfo(marker.title, marker.description)
            val iconBitMap = makeIconBitmap(customMarkerView)
            addMarker(marker.location, iconBitMap)
        }

        googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(p0: Marker): View {
                // in here it will using the entire info window including frame
                val view: View = layoutInflater.inflate(R.layout.google_map_custom_infowindow, null)
                val titleView = view.findViewById<View>(R.id.infowindow_title) as TextView
                val descriptionView =
                    view.findViewById<View>(R.id.infowindow_description) as TextView
                titleView.text = marker.title
                descriptionView.text = marker.description
                return view
            }

            override fun getInfoContents(p0: Marker): View? {
                // in here it will show custom content but using default info window frame and background
                return null
            }

        })
    }

    private fun moveCameraToMarker(marker: MapMarker, zoomLevel: Float = 12.0f) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.location, zoomLevel))
    }

    private fun showMultipleMarkersAndMoveCamera(markers: List<MapMarker>) {
        val builder = LatLngBounds.Builder()
        for (marker in markers) {
            showMarkerOnMap(marker)
            val markerNorthEastBorder = marker.location.let {
                SphericalUtil.computeOffset(it, MARKER_OFFSET_IN_METERS, HEADING_NORTH)
                SphericalUtil.computeOffset(it, MARKER_OFFSET_IN_METERS, HEADING_EAST)
            }
            val markerSouthWestBorder = marker.location.let {
                SphericalUtil.computeOffset(it, MARKER_OFFSET_IN_METERS, HEADING_SOUTH)
                SphericalUtil.computeOffset(it, MARKER_OFFSET_IN_METERS, HEADING_WEST)
            }
            builder.include(markerNorthEastBorder)
            builder.include(markerSouthWestBorder)
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 0))
    }

    private fun bindCustomMarkerInfo(title: String, description: String?): View {
        val customMarkerView = layoutInflater.inflate(R.layout.google_map_custom_marker, null)
        customMarkerView.findViewById<TextView>(R.id.custom_marker_title)?.apply { text = title }
        customMarkerView.findViewById<TextView>(R.id.customer_marker_description)
            ?.apply { description?.let { text = it } }
        return customMarkerView
    }

    private fun makeIconBitmap(markerView: View): Bitmap {
        val iconGenerator = IconGenerator(this)
        iconGenerator.setContentView(markerView)
        iconGenerator.setBackground(null)
        return iconGenerator.makeIcon()
    }

    private fun addMarker(location: LatLng, icon: Bitmap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
        )
    }

    companion object {
        private const val MARKER_OFFSET_IN_METERS = 500.0
        private const val HEADING_NORTH = 0.0
        private const val HEADING_EAST = 90.0
        private const val HEADING_SOUTH = 180.0
        private const val HEADING_WEST = 270.0
    }
}
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
            val beijingLatLng = LatLng(39.916668, 116.383331)
            addMarkerAndMoveCamera(beijingLatLng, "This is beijing", "The Capital of China.")
        }
        demoButton2.setOnClickListener {
            val seattleLatLng = LatLng(47.608013, -122.335167)
            addMarkerAndMoveCamera(seattleLatLng, "This is Seattle", "Where Amazon headquarter is.")
        }
        demoButton3.setOnClickListener {
            val dubaiLatLng = LatLng(25.276987, 55.296249)
            addMarkerAndMoveCamera(dubaiLatLng, "This is Dubai", "Currently I stay in here.")
        }
        demoButton4.setOnClickListener {
            val place1 = LatLng(39.920628, 116.395179)
            val place2 = LatLng(39.919567, 116.399118)
            val sw = LatLng(39.907935, 116.392183)
            val ne = LatLng(39.923541, 116.402244)
            showMultipleMarkersAndMoveCamera(sw, ne, place1, place2, "长春宫", "奉先殿")
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

    private fun addMarkerAndMoveCamera(destination: LatLng, title: String, description: String) {
        val customMarkerView = bindCustomMarkerInfo(title, description)
        val iconBitMap = makeIconBitmap(customMarkerView)

        addMarker(destination, iconBitMap)

        googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(p0: Marker): View? {
                // in here it will using the entire info window including frame
                return null
            }

            override fun getInfoContents(p0: Marker): View? {
                // in here it will show custom content but using default info window frame and background
                return null
            }

        })
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 12.0f))
    }

    private fun showMultipleMarkersAndMoveCamera(
        sw: LatLng,
        ne: LatLng,
        place1: LatLng,
        place2: LatLng,
        title1: String,
        title2: String
    ) {
        val markerView1 = bindCustomMarkerInfo(title1, null)
        val markerView2 = bindCustomMarkerInfo(title2, null)
        val iconBitMap1 = makeIconBitmap(markerView1)
        val iconBitMap2 = makeIconBitmap(markerView2)

        addMarker(place1, iconBitMap1)
        addMarker(place2, iconBitMap2)

        val latLngBounds = LatLngBounds(sw, ne)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 30))
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
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
        )
    }
}
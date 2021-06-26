package com.arctos.mochabear.mapdemo

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

        demoButton1.setOnClickListener {
            val beijingLatLng = LatLng(39.916668, 116.383331)
            addMarkedAndMoveCamera(beijingLatLng, "This is beijing", "The Capital of China.")
        }
        demoButton2.setOnClickListener {
            val seattleLatLng = LatLng(47.608013, -122.335167)
            addMarkedAndMoveCamera(seattleLatLng, "This is Seattle", "Where Amazon headquarter is.")
        }
        demoButton3.setOnClickListener {
            val dubaiLatLng = LatLng(25.276987, 55.296249)
            addMarkedAndMoveCamera(dubaiLatLng, "This is Dubai", "Currently I stay in here.")
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

    private fun addMarkedAndMoveCamera(destination: LatLng, title: String, description: String) {
        val customMarkerView = bindCustomMarkerInfo(title, description)
        val iconGenerator = IconGenerator(this)
        iconGenerator.setContentView(customMarkerView)
        iconGenerator.setBackground(null)
        val iconBitMap = iconGenerator.makeIcon()

        googleMap.addMarker(
            MarkerOptions()
                .position(destination)
                .icon(BitmapDescriptorFactory.fromBitmap(iconBitMap))
        )
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

    private fun bindCustomMarkerInfo(title: String, description: String): View {
        val customMarkerView = layoutInflater.inflate(R.layout.google_map_custom_marker, null)
        customMarkerView.findViewById<TextView>(R.id.custom_marker_title)?.apply { text = title }
        customMarkerView.findViewById<TextView>(R.id.customer_marker_description)
            ?.apply { text = description }
        return customMarkerView
    }
}
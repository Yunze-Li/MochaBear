package com.arctos.mochabear.mapdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.arctos.mochabear.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior

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
            addMarkedAndMoveCamera(beijingLatLng, "This is beijing")
        }
        demoButton2.setOnClickListener {
            val seattleLatLng = LatLng(47.608013, -122.335167)
            addMarkedAndMoveCamera(seattleLatLng, "This is Seattle")
        }
        demoButton3.setOnClickListener {
            val dubaiLatLng = LatLng(25.276987, 55.296249)
            addMarkedAndMoveCamera(dubaiLatLng, "This is Dubai")
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
        val sydney = LatLng(-33.852, 151.211)
        this.googleMap = googleMap
    }

    private fun addMarkedAndMoveCamera(destination: LatLng, title: String) {
        googleMap.addMarker(
            MarkerOptions()
                .position(destination)
                .title(title)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(destination))
    }
}
package com.arctos.mochabear.mapdemo

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.arctos.mochabear.R
import com.arctos.mochabear.mapdemo.model.CustomInfoWindowMarker
import com.arctos.mochabear.mapdemo.model.CustomLayoutMarker
import com.arctos.mochabear.mapdemo.model.MapMarker
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

    private val markerSet = mutableSetOf<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup UI
        setContentView(R.layout.google_map_demo)
        val demoButton1 = findViewById<AppCompatButton>(R.id.location_button_1)
        val demoButton2 = findViewById<AppCompatButton>(R.id.location_button_2)
        val demoButton3 = findViewById<AppCompatButton>(R.id.location_button_3)
        val demoButton4 = findViewById<AppCompatButton>(R.id.location_button_4)

        demoButton1.setOnClickListener {
            val beijingMarker = CustomLayoutMarker(
                LatLng(39.916668, 116.383331),
                "This is Beijing",
                "The capital of China."
            )
            showCustomLayoutMarkerOnMap(beijingMarker)
            moveCameraToMarker(beijingMarker)
        }
        demoButton2.setOnClickListener {
            val seattleMarker = CustomLayoutMarker(
                LatLng(47.608013, -122.335167),
                "This is Seattle",
                "Where Amazon headquarter is."
            )
            showCustomLayoutMarkerOnMap(seattleMarker)
            moveCameraToMarker(seattleMarker)
        }
        demoButton3.setOnClickListener {
            val dubaiMarker = CustomLayoutMarker(
                LatLng(25.276987, 55.296249),
                "This is Dubai",
                "Currently I stay in here."
            )
            showCustomLayoutMarkerOnMap(dubaiMarker)
            moveCameraToMarker(dubaiMarker)
        }
        demoButton4.setOnClickListener {
            val place1 = CustomInfoWindowMarker(LatLng(39.920628, 116.395179), "长春宫", "也叫永宁宫")
            val place2 = CustomInfoWindowMarker(LatLng(39.919567, 116.399118), "奉先殿", "祭祀祖先用的家庙")
            val place3 = CustomInfoWindowMarker(LatLng(39.913762, 116.397145), "午门", "推出去斩首的地方")
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
        googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter(layoutInflater, markerSet))
    }

    private fun showCustomLayoutMarkerOnMap(marker: CustomLayoutMarker) {
        val customLayoutMarkerView = bindCustomLayoutMarkerInfo(marker.title, marker.description)
        val iconBitMap = makeIconBitmap(customLayoutMarkerView)
        addCustomLayoutMarker(marker.location, iconBitMap)
    }

    private fun showInfoWindowMarkerOnMap(marker: CustomInfoWindowMarker) {
        addCustomInfoWindowMarker(marker)?.also { markerSet.add(it) }
    }

    private fun moveCameraToMarker(marker: MapMarker, zoomLevel: Float = 12.0f) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.location, zoomLevel))
    }

    private fun showMultipleMarkersAndMoveCamera(markers: List<MapMarker>) {
        val builder = LatLngBounds.Builder()
        for (marker in markers) {
            when (marker) {
                is CustomLayoutMarker -> showCustomLayoutMarkerOnMap(marker)
                is CustomInfoWindowMarker -> showInfoWindowMarkerOnMap(marker)
            }
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

    private fun bindCustomLayoutMarkerInfo(title: String, description: String?): View {
        val customLayoutMarkerView = layoutInflater.inflate(R.layout.google_map_custom_marker, null)
        customLayoutMarkerView.findViewById<TextView>(R.id.custom_marker_title)
            ?.apply { text = title }
        customLayoutMarkerView.findViewById<TextView>(R.id.custom_marker_description)?.apply {
            description?.let {
                text = it
                this.visibility = VISIBLE
            }
        }
        return customLayoutMarkerView
    }

    private fun makeIconBitmap(markerView: View): Bitmap {
        val iconGenerator = IconGenerator(this)
        iconGenerator.setContentView(markerView)
        iconGenerator.setBackground(null)
        return iconGenerator.makeIcon()
    }

    private fun addCustomLayoutMarker(location: LatLng, icon: Bitmap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
        )
    }

    private fun addCustomInfoWindowMarker(marker: CustomInfoWindowMarker): Marker? {
        val markerOptions = MarkerOptions().position(marker.location).title(marker.infoWindowTitle)
        marker.infoWindowDescription?.also {
            markerOptions.snippet(it)
        }
        return googleMap.addMarker(markerOptions)
    }

    companion object {
        private const val MARKER_OFFSET_IN_METERS = 500.0
        private const val HEADING_NORTH = 0.0
        private const val HEADING_EAST = 90.0
        private const val HEADING_SOUTH = 180.0
        private const val HEADING_WEST = 270.0
    }
}
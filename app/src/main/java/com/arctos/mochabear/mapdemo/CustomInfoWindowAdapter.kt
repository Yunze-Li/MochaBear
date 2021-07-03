package com.arctos.mochabear.mapdemo

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.arctos.mochabear.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(
    private val layoutInflater: LayoutInflater,
    private val markerSet: Set<Marker>
) : GoogleMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker): View? {
        // in here it will using the entire info window including frame
        if (marker in markerSet) {
            val view: View = layoutInflater.inflate(R.layout.google_map_custom_infowindow, null)
            val titleView = view.findViewById<View>(R.id.infowindow_title) as TextView
            val descriptionView = view.findViewById<View>(R.id.infowindow_description) as TextView
            titleView.text = marker.title
            marker.snippet?.let {
                descriptionView.text = it
                descriptionView.visibility = View.VISIBLE
            }
            return view
        }
        return null
    }

    override fun getInfoContents(p0: Marker): View? {
        // in here it will show custom content but using default info window frame and background
        return null
    }
}
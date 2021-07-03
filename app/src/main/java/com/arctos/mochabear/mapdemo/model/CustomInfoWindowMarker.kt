package com.arctos.mochabear.mapdemo.model

import com.google.android.gms.maps.model.LatLng

data class CustomInfoWindowMarker(
    override val location: LatLng,
    val infoWindowTitle: String,
    val infoWindowDescription: String? = null
) : MapMarker
package com.arctos.mochabear.mapdemo

import com.google.android.gms.maps.model.LatLng

data class MapMarker(
    val location: LatLng,
    val title: String? = null,
    val description: String? = null
)
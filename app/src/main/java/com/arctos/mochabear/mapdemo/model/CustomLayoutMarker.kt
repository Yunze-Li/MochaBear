package com.arctos.mochabear.mapdemo.model

import com.google.android.gms.maps.model.LatLng

data class CustomLayoutMarker(
    override val location: LatLng,
    val title: String,
    val description: String? = null
) : MapMarker
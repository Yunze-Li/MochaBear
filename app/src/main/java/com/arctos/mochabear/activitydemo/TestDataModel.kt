package com.arctos.mochabear.activitydemo

import android.app.Activity

class TestDataModel {
    val activityList = mutableListOf<Activity>()

    companion object {
        private var instance: TestDataModel? = null
            get() {
                if (field == null) {
                    field = TestDataModel()
                }
                return field
            }

        fun get(): TestDataModel {
            return instance!!
        }
    }
}
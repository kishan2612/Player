package com.devbrackets.android.exomediademo.data

import java.util.*

object Samples {



    /**
     * A container for the information associated with a
     * sample media item.
     */
    class Sample(
            val title: String,
            val mediaUrl: String,
            val artworkUrl: String? = null
    )
}

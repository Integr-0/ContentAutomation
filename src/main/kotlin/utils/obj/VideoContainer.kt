package utils.obj

import com.google.gson.GsonBuilder

/**
 * **Author: Integr**
 * - **What:** Contains videos
 * - **How:** Using the args
 * - **Why:** Essential function of the bot
 */
@Suppress("Unused")
class VideoContainer(var contents: List<VideoObject>) {
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

    companion object {
        fun fromJson(json: String): VideoContainer {
            return GsonBuilder().create().fromJson(json, VideoContainer::class.java)
        }
    }
}
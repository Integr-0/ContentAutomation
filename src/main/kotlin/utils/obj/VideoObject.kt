package utils.obj

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.reflect.typeOf

/**
 * **Author: Integr**
 * - **What:** Video data
 * - **How:** Using the args
 * - **Why:** Essential function of the bot
 */
data class VideoObject(
    var title: String = "",
    var series: String = "",
    var parts: List<String> = listOf(),
    var outro: String = "",
    var ttsFile: String = ""
)
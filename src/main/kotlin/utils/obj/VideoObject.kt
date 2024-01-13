package utils.obj

/**
 * **Author: Integr**
 * - **What:** Video data
 * - **How:** Using the args
 * - **Why:** Essential function of the bot
 */
data class VideoObject(
    var parts: List<String> = listOf(),
    var outro: String = "",
    var ttsFile: String = ""
)
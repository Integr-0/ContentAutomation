/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package utils.video

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
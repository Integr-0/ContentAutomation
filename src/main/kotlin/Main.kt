/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

import execution.Runner
import execution.settings.SettingsBuilder

/**
 * # ContentAutomation
 * Authors: [Integr](https://github.com/Integr-0) with Reddit-getter by [Julian](https://github.com/Julian-Mostbauer)
 * - **What:** Creates short-form videos
 * - **How:** FFmpeg, Whisper-AI, Edge-TTS and various APIs
 * - **Why:** To automatically create videos
 */
fun main() {
    /* Settings */
    val settings = SettingsBuilder()
        .venvPath("C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\venv\\Scripts\\python.exe")
        .ffmpegPath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffmpeg.exe")
        .probePath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffprobe.exe")
        .videoAmount(1)
        .perVideo(7)
        .outro("Follow us for more!")
        .backVids(listOf("Air_Parkour", "Hypixel_Parkour", "Spiral_Parkour", "Scenic_Parkour"))
        .randomSource()
        .randomColor()
        .build()

    /* Optionally download a background utils.video */
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=Pt5_GSKIWQM", "Hypixel_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=2VpG0WS4uCo", "Spiral_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=JlPEb6WNuDI", "Scenic_Parkour")

    /* Run the Generator */
    Runner.run(settings)
}







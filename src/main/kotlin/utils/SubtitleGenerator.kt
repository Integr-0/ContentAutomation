package utils

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

/**
 * **Author: Integr**
 * - **What:** Generates subtitles to the given .mp3 file
 * - **How:** Using a python script with openai-whisper
 * - **Why:** To generate subtitles from TTS
 */
class SubtitleGenerator {
    companion object {
        fun genSub(filename: String, venvPath: String, color: String) {
            val dir = System.getProperty("user.dir")

            println("|| Generating subtitle .ass file")
            val p: Process =
                Runtime.getRuntime().exec(String.format("$venvPath $dir\\SubtitleGeneration\\main.py $filename $color"));

            val stdInput = BufferedReader(InputStreamReader(p.inputStream))
            val stdError = BufferedReader(InputStreamReader(p.errorStream))

            try {
                while (stdInput.readLine() != null) {
                    // Wait
                }

                while (stdError.readLine() != null) {
                    // Wait
                }
            } catch (e: NullPointerException) {
                // Nope
            }
        }
    }
}
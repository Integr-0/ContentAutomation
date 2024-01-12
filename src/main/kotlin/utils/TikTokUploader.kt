package utils

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * **Author: Integr**
 * - **What:** Uploads a given file to TikTok
 * - **How:** Using a python script with tiktok-uploader
 * - **Why:** To auto upload a video
 */
class TikTokUploader {
    companion object {

        //TODO: !!!!!! Dont use !!!!!!!
        fun upload(filename: String, venvPath: String, title: String, cookieLoc: String) {
            val dir = System.getProperty("user.dir")

            println("|| Uploading Video")
            val p: Process =
                Runtime.getRuntime().exec(String.format("$venvPath $dir\\TikTokUploader\\main.py $filename $title $cookieLoc"));

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
package utils

import com.github.javafaker.Faker
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import utils.obj.RedditGetter
import utils.obj.VideoContainer
import utils.obj.VideoObject
import java.net.URL
import java.time.LocalDate
import java.time.LocalTime

/**
 * **Author: Integr**
 * - **What:** Generates video data
 * - **How:** APIs and user settings
 * - **Why:** Essential to generate TTS, video and content
 */
class Generator {
    companion object {
        fun genVideoData(
            amount: Int,
            perVideo: Int,
            outro: String,
            series: String,
            firstPartNum: Int,
            genFun: (Int) -> String
        ): VideoContainer {
            println("Generating video content")

            var pn = firstPartNum

            val name = (
                LocalDate.now().dayOfMonth.toString()
                + "-" + LocalDate.now().monthValue.toString()
                + "-" + LocalDate.now().year.toString()
                + "-" + LocalTime.now().second.toString()
                + "-" + LocalTime.now().minute.toString()
                + "-" + LocalTime.now().hour.toString()
            )

            val videos: MutableList<VideoObject> = mutableListOf()

            for (i in (1..amount)) {
                var ttsText = ""

                val contents: MutableList<String> = mutableListOf()
                println("|| Reading API: $i/$amount")
                for (it in (1..perVideo)) {
                    val obj = genFun(it)
                    ttsText += " $obj "
                    contents += obj
                }

                ttsText += " $outro"
                println("|| Generating TTS: $i/$amount")
                TTSGen.genTTS(ttsText, i, name)
                videos += VideoObject("$series Part $pn", series, contents, outro, name + "_" + i + ".mp3")
                pn++
            }

            return VideoContainer(videos)
        }

        fun readProgrammerJokeAPI(): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://jokeapi.dev/joke/Any?format=txt&type=single&blacklistFlags=nsfw%2Cracist%2Csexist&lang=en")
                .get()
                .build()

            val response = client.newCall(request).execute()

            return response.body().string()
        }

        fun readChuckNorisQuoteAPI(): String {
            return Faker.instance().chuckNorris().fact()
        }

        fun readRedditAPI(currentIndex: Int, url: String, postIndex: Int): String {
            val posts = RedditGetter.getPosts(URL("$url.json"), postIndex, currentIndex)

            return if (currentIndex == 1) posts[postIndex].question
            else posts[postIndex].responses[currentIndex] + "."
        }

        fun readJokeAPI(): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://v2.jokeapi.dev/joke/Miscellaneous,Dark,Pun,Spooky,Christmas?blacklistFlags=nsfw,racist,sexist&format=txt&type=single")
                .get()
                .build()

            val response = client.newCall(request).execute()

            return response.body().string()
        }

        fun readDarkJokeAPI(): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://v2.jokeapi.dev/joke/Dark?blacklistFlags=nsfw,racist,sexist&format=txt&type=single")
                .get()
                .build()

            val response = client.newCall(request).execute()

            return response.body().string()
        }
    }
}
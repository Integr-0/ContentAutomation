/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package generators

import com.github.javafaker.Faker
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import utils.video.VideoContainer
import utils.video.VideoObject
import generators.reddit.RedditGetter
import java.net.URL
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

/**
 * **Author: Integr**
 * - **What:** Generates utils.video data
 * - **How:** APIs and user execution.settings
 * - **Why:** Essential to generate TTS, utils.video and content
 */
@Suppress("Unused", "MemberVisibilityCanBePrivate")
class Generator {
    companion object {
        fun genVideoData(
            amount: Int,
            perVideo: Int,
            outro: String,
            genFun: (Int) -> String
        ): VideoContainer {
            println("Generating video content")

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
                TTSGenerator.genTTS(ttsText, i, name)
                videos += VideoObject(contents, outro, name + "_" + i + ".mp3")
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

            return response.body().string().replace("\n", " ")
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
                .addHeader("User-Agent", "ContentGen/1.0")
                .build()

            val response = client.newCall(request).execute()

            return response.body().string().replace("\n", " ")
        }

        fun readDarkJokeAPI(): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://v2.jokeapi.dev/joke/Dark?blacklistFlags=nsfw,racist,sexist&format=txt&type=single")
                .get()
                .addHeader("User-Agent", "ContentGen/1.0")
                .build()

            val response = client.newCall(request).execute()

            return response.body().string().replace("\n", " ")
        }

        fun readDadJokeAPI(): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://icanhazdadjoke.com/")
                .get()
                .addHeader("Accept", "text/plain")
                .addHeader("User-Agent", "ContentGen/1.0")
                .build()

            val response = client.newCall(request).execute()

            return response.body().string().replace("\n", " ")
        }

        fun readGeekJokeAPI(): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://geek-jokes.sameerkumar.website/api?format=txt")
                .get()
                .addHeader("User-Agent", "ContentGen/1.0")
                .build()

            val response = client.newCall(request).execute()

            return response.body().string().replace("\n", " ").removePrefix("\"").removeSuffix("\"")
        }

        fun readOfficialJokeAPI(): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://official-joke-api.appspot.com/random_joke")
                .get()
                .addHeader("User-Agent", "ContentGen/1.0")
                .build()

            val response = client.newCall(request).execute()

            val rString = response.body().string().replace("\n", " ")

            val setup = rString.substringAfter("\"setup\":\"").substringBefore("\",\"punchline\"")
            val punchline = rString.substringAfter("\"punchline\":\"").substringBefore("\",\"id\"")
            return "$setup $punchline"
        }

        fun getRandomJokeAPI(): ((Int) -> String) {

            val apis: List<((Int) -> String)> = listOf(
                { readJokeAPI() },
                { readProgrammerJokeAPI() },
                { readDadJokeAPI() },
                { readOfficialJokeAPI() },
                { readGeekJokeAPI() },
                { readDarkJokeAPI() },
                { readChuckNorisQuoteAPI() }
            )

            return apis[Random(LocalTime.now().second*System.currentTimeMillis()).nextInt(0, apis.size-1)]
        }
    }
}
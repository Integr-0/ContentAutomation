package utils

import com.github.javafaker.Faker
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import utils.obj.VideoContainer
import utils.obj.VideoObject
import java.time.LocalDate
import java.time.LocalTime

class Generator {
    companion object {
        fun genVideoDataJson(
            amount: Int,
            perVideo: Int,
            outro: String,
            series: String,
            firstPartNum: Int,
            genFun: () -> String
        ): Pair<String, String> {
            val name = (
                    LocalDate.now().dayOfMonth.toString()
                            + "-" + LocalDate.now().monthValue.toString()
                            + "-" + LocalDate.now().year.toString()
                            + "-" + LocalTime.now().second.toString()
                            + "-" + LocalTime.now().minute.toString()
                            + "-" + LocalTime.now().hour.toString()
                    )

            return Pair(genVideoData(amount, perVideo, outro, series, firstPartNum, genFun).toJson(), name)
        }

        fun genVideoData(
            amount: Int,
            perVideo: Int,
            outro: String,
            series: String,
            firstPartNum: Int,
            genFun: () -> String
        ): VideoContainer {
            println("Generating video content")

            var pn = firstPartNum

            val name =
                (LocalDate.now().dayOfMonth.toString()
                        + "-" + LocalDate.now().monthValue.toString()
                        + "-" + LocalDate.now().year.toString()
                        + "-" + LocalTime.now().second.toString()
                        + "-" + LocalTime.now().minute.toString()
                        + "-" + LocalTime.now().hour.toString())

            val videos: MutableList<VideoObject> = mutableListOf()

            for (i in (1..amount)) {
                var ttsText = ""

                val contents: MutableList<String> = mutableListOf()
                for (it in (1..perVideo)) {
                    val obj = genFun()
                    ttsText += " $obj "
                    contents += obj
                }

                ttsText += " $outro"
                println("|| Generating TTS: $i/$amount")
                TTSGen.genTTS(ttsText, i, name)
                videos += VideoObject(
                    "$series Part $pn",
                    series,
                    contents,
                    outro,
                    name + "_" + i + ".mp3"
                )
                pn++
            }

            return VideoContainer(videos)
        }

        fun readJokeAPI(): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://jokeapi.dev/joke/Any?format=txt&type=single&blacklistFlags=nsfw%2Cracist%2Csexist&lang=en")
                .get()
                .build()

            val response = client.newCall(request).execute()

            return response.body().string()
        }

        fun readChuckNorisAPI(): String {
            return Faker.instance().chuckNorris().fact()
        }
    }
}
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
        fun genVideoJson(amount: Int, perVideo: Int): Pair<String, String> {
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

                val jokes: MutableList<String> = mutableListOf()
                for (it in (1..perVideo)) {
                    val joke = readAPI().filter { c -> c != '\n' && c != '"' }
                    ttsText += "$joke "
                    jokes += joke
                }
                TTSGen.genTTS(ttsText, i, name)
                videos += VideoObject("Jokes Part $i", "Jokes", jokes, "Follow us for more!", name+"_"+i+".mp3")
            }

            return Pair(VideoContainer(videos).toJson(), name)
        }

        private fun readAPI(): String {
            return if (Faker.instance().number().numberBetween(0, 3) == 0) {
                Faker.instance().chuckNorris().fact()
            } else {
                val client = OkHttpClient()

                val request = Request.Builder()
                    .url("https://jokeapi.dev/joke/Any?format=txt&type=single&blacklistFlags=nsfw%2Cracist%2Csexist&lang=en")
                    .get()
                    .build()

                val response = client.newCall(request).execute()

                response.body().string()
            }
        }
    }
}
import com.github.javafaker.Faker
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import utils.FFMPEGUtils
import utils.FileUtils
import utils.Generator
import utils.TTSGen
import utils.obj.VideoContainer
import utils.obj.VideoObject
import java.time.LocalDate
import java.time.LocalTime

fun main(args: Array<String>) {
    val videos = args[0].toInt()
    val perVideo = args[1].toInt()

    //val v = Generator.genVideoJson(videos, perVideo)
    //FileUtils.dump(v.first, v.second)
    //FileUtils.downloadYT("https://www.youtube.com/watch?v=intRX7BRA90", "Parkour_Background")
    FFMPEGUtils.edit(
        "C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\entries\\vids\\Parkour_Background.mp4",
        "C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\entries\\saves\\Subtitles_1.mp3",
        30
    )
}

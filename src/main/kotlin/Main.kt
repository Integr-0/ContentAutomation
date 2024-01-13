import utils.*
import utils.obj.SettingsBuilder
import utils.obj.asyncruntime.*

fun main() {
    /* Settings */
    val settings = SettingsBuilder()
        .venvPath("C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\venv\\Scripts\\python.exe")
        .ffmpegPath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffmpeg.exe")
        .probePath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffprobe.exe")
        .videoAmount(1)
        .perVideo(5)
        .outro("Follow us for more!")
        .backVids(listOf("Air_Parkour", "Hypixel_Parkour", "Spiral_Parkour", "Scenic_Parkour"))
        .contentSource {
            Generator.readRandomJokeAPI()
        }
        .randomColor()
        .build()

    /* Optionally download a background video */
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=Pt5_GSKIWQM", "Hypixel_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=2VpG0WS4uCo", "Spiral_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=JlPEb6WNuDI", "Scenic_Parkour")

    /* Run the Generator */
    Runner.run(settings)
}







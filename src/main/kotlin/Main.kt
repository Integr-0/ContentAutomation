import utils.*
import utils.obj.SettingsBuilder

fun main(args: Array<String>) {
    /* Settings */
    val settings = SettingsBuilder()
        .videoAmount(1)
        .perVideo(5)
        .outro("Follow us for more!")
        .series("Jokes")
        .backVids(listOf("Air_Parkour", "Hypixel_Parkour", "Spiral_Parkour", "Scenic_Parkour"))
        .continueOn(1)
        .contentSource { Generator.readJokeAPI() }
        .venvPath("C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\venv\\Scripts\\python")
        .ffmpegPath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffmpeg.exe")
        .probePath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffprobe.exe")
        .build()

    /* Optionally download a background Video */
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=Pt5_GSKIWQM", "Hypixel_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=2VpG0WS4uCo", "Spiral_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=JlPEb6WNuDI", "Scenic_Parkour")

    /* Run the Generator */
    Runner.run(settings)
}

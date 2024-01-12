import utils.*
import utils.obj.SettingsBuilder

fun main(args: Array<String>) {
    /* Settings */
    val settings = SettingsBuilder()
        .videoAmount(1)
        .perVideo(5)
        .outro("Follow us for more!")
        .series("Reddit")
        .backVids(listOf("Air_Parkour", "Hypixel_Parkour", "Spiral_Parkour", "Scenic_Parkour"))
        .continueOn(1)
        .contentSource {
            Generator.readRedditAPI(
                it,
                "https://www.reddit.com/r/askreddit",
                4
            )
        }
        .venvPath("C:\\users\\erikr\\Desktop\\Misc\\JokeAPI\\venv\\Scripts\\python.exe")
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

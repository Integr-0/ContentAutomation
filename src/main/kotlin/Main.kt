import utils.*
import utils.obj.SettingsBuilder

fun main(args: Array<String>) {
    /* Settings */
    val settings = SettingsBuilder()
        .venvPath("C:\\users\\erikr\\Desktop\\Misc\\JokeAPI\\venv\\Scripts\\python.exe")
        .ffmpegPath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffmpeg.exe")
        .probePath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffprobe.exe")
        .videoAmount(1)
        .perVideo(5)
        .outro("Follow us for more!")
        .series("Reddit")
        .continueOn(1)
        .backVids(listOf("Air_Parkour", "Hypixel_Parkour", "Spiral_Parkour", "Scenic_Parkour"))
        .contentSource {
            Generator.readRedditAPI(
                it,
                "https://www.reddit.com/r/askreddit",
                2
            )
        }
        //TODO: Cookie Path -> .autoUpload("", listOf("#fyp", "#foryou", "#jokes", "#funny", "#fun", "#facts", "#joke", "#meme"))
        .build()

    /* Optionally download a background video */
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=Pt5_GSKIWQM", "Hypixel_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=2VpG0WS4uCo", "Spiral_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=JlPEb6WNuDI", "Scenic_Parkour")

    /* Run the Generator */
    Runner.run(settings)
}

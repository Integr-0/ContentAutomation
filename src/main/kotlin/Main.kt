import utils.*
import utils.obj.SettingsBuilder

fun main(args: Array<String>) {
    /* Settings */
    val settings = SettingsBuilder()
        .videoAmount(5)
        .perVideo(5)
        .outro("Follow us for more!")
        .series("Jokes")
        .backVids(listOf("Air_Parkour", "Hypixel_Parkour", "Spiral_Parkour", "Scenic_Parkour"))
        .continueOn(1)
        .contentSource { Generator.readJokeAPI() }
        .build()

    /* Optionally download a background Video */
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=Pt5_GSKIWQM", "Hypixel_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=2VpG0WS4uCo", "Spiral_Parkour")
    // FileUtils.downloadYT("https://www.youtube.com/watch?v=JlPEb6WNuDI", "Scenic_Parkour")

    /* Run the Generator */
    Runner.run(settings)
}

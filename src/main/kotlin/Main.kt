import utils.*

fun main(args: Array<String>) {
    /* Settings */
    val videos = 1
    val perVideo = 5
    val outro = "Follow us for more!"
    val series = "Jokes"
    val backVid = "Parkour_Background.mp4"
    val continueOn = 1
    val contentSource = { Generator.readJokeAPI() }

    /* Optionally download a background Video */
    //FileUtils.downloadYT("<Video>", "<Name>")

    /* Run the Generator */
    Runner.run(videos, perVideo, outro, series, backVid, continueOn, contentSource)
}

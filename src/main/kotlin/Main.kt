import utils.*

fun main(args: Array<String>) {
    val videos = 1
    val perVideo = 5
    val backVid = "Parkour_Background.mp4"

    /*
        Optionally download a background Video
        FileUtils.downloadYT("https://www.youtube.com/watch?v=intRX7BRA90", "Parkour_Background")
     */

    val v = Generator.genVideoData(videos, perVideo)
    val dir = System.getProperty("user.dir")

    println()

    for (vid in v.contents) {
        println("Generating Video: ${v.contents.indexOf(vid)+1}/$videos")

        val name = vid.ttsFile.substringBefore(".mp3")

        FFmpegUtils.edit(
            "$dir\\entries\\download\\$backVid",
            "$dir\\entries\\vids\\${vid.ttsFile}",
            name
        )

        SubtitleGenerator.genSub("$dir\\entries\\vids\\$name-cut.mp4")
        FFmpegUtils.addSubtitles("$dir\\entries\\vids\\$name-cut.mp4", FileUtils.fixPathForSubAdd("$dir\\entries\\vids\\$name-cut-sub.ass"), "$name-final")
        FileUtils.cleanUp(name)

        println()
        println(" => Outputted ${v.contents.indexOf(vid) + 1}/$videos as: $name-final.mp4")
        println()
    }
}

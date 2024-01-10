import utils.*

fun main(args: Array<String>) {
    val videos = 5
    val perVideo = 3

    /*
        Optionally download a background Video
        FileUtils.downloadYT("https://www.youtube.com/watch?v=intRX7BRA90", "Parkour_Background")
     */

    val v = Generator.genVideoData(videos, perVideo)

    println()

    for (vid in v.contents) {
        println("Generating Video: ${v.contents.indexOf(vid)+1}/$videos")

        val name = vid.ttsFile.substringBefore(".mp3")

        FFMPEGUtils.edit(
            "C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\entries\\download\\Parkour_Background.mp4",
            "C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\entries\\vids\\${vid.ttsFile}",
            name
        )

        SubtitleGenerator.genSub("C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\entries\\vids\\$name-cut.mp4")
        FFMPEGUtils.addSubtitles("C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\entries\\vids\\$name-cut.mp4", FileUtils.fixPathForSubAdd("C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\entries\\vids\\$name-cut-sub.ass"), "$name-final")
        FileUtils.cleanUp(name)

        println()
        println(" => Outputted ${v.contents.indexOf(vid) + 1}/$videos as: $name-final.mp4")
        println()
    }
}

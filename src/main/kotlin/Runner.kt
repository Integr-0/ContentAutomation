import utils.FFmpegUtils
import utils.FileUtils
import utils.Generator
import utils.SubtitleGenerator

class Runner {
    companion object {
        fun run(
            videos: Int,
            perVideo: Int,
            outro: String,
            series: String,
            backVid: String,
            continueOn: Int,
            jokeSource: () -> String
        ) {
            val v = Generator.genVideoData(
                videos,
                perVideo,
                outro,
                series,
                continueOn,
                jokeSource
            )

            val dir = System.getProperty("user.dir")
            val startTime = System.currentTimeMillis()

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
                println(" => Outputted ${v.contents.indexOf(vid) + 1}/$videos after ${String.format("%.1f", (System.currentTimeMillis()-startTime)*0.001)} Seconds as: $name-final.mp4")
                println()
            }

            println("==> Finished Generation of $videos Video/s in ${String.format("%.1f", (System.currentTimeMillis()-startTime)*0.001)} Seconds <==")
        }
    }
}
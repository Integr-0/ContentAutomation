package utils

import net.bramp.ffmpeg.FFmpeg
import net.bramp.ffmpeg.FFmpegExecutor
import net.bramp.ffmpeg.FFprobe
import net.bramp.ffmpeg.builder.FFmpegBuilder

/**
 * **Author: Integr**
 * - **What:** Handles FFmpeg functions
 * - **How:** using ffmpeg
 * - **Why:** Essential for cutting videos
 */
class FFmpegUtils {
    companion object {
        var ffmpeg: FFmpeg? = null
        var ffprobe: FFprobe? = null

        fun edit(inputVid: String, inputSub: String, name: String) {
            val dir = System.getProperty("user.dir")

            println("|| Cutting utils.video to length")
            val builder = FFmpegBuilder()
                .addInput(ffprobe!!.probe(inputVid))
                .addInput(ffprobe!!.probe(inputSub))
                .overrideOutputFiles(true)
                .addOutput("$dir\\entries\\vids\\$name-cut.mp4")
                .addExtraArgs("-vf", "crop=ih/16*9:ih, scale=w=1080:h=1920:flags=lanczos")
                .addExtraArgs("-crf", "23")
                .addExtraArgs("-map", "0:v")
                .addExtraArgs("-map", "1:a")
                .addExtraArgs("-shortest")
                .done()

            val executor = FFmpegExecutor(ffmpeg, ffprobe)

            executor.createJob(builder).run()
        }

        fun addSubtitles(inputVid: String, inputSub: String, outPutName: String) {
            val dir = System.getProperty("user.dir")

            println("|| Adding subtitle .ass file to utils.video")
            val builder = FFmpegBuilder()
                .addInput(ffprobe!!.probe(inputVid))
                .overrideOutputFiles(true)
                .addOutput("$dir\\entries\\saves\\$outPutName.mp4")
                .addExtraArgs("-vf", "ass=$inputSub")
                .done()

            val executor = FFmpegExecutor(ffmpeg, ffprobe)

            executor.createJob(builder).run()
        }
    }
}
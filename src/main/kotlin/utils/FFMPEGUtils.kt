package utils

import net.bramp.ffmpeg.FFmpeg
import net.bramp.ffmpeg.FFmpegExecutor
import net.bramp.ffmpeg.FFprobe
import net.bramp.ffmpeg.builder.FFmpegBuilder
import java.util.concurrent.TimeUnit


class FFMPEGUtils {
    companion object {
        val ffmpeg = FFmpeg("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffmpeg.exe")
        val ffprobe = FFprobe("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffprobe.exe")

        fun edit(inputVid: String, inputSub: String, durationSecs: Int) {
            val dir = System.getProperty("user.dir")
            val builder = FFmpegBuilder()
                .addInput(ffprobe.probe(inputVid))
                .addInput(ffprobe.probe(inputSub))
                .overrideOutputFiles(true)
                .addOutput("$dir\\entries\\vids\\${inputVid.substringBefore(".mp4").substringAfter("\\")}.mp4")
                .addExtraArgs("-map", "0:v")
                .addExtraArgs("-map", "1:a")
                .setDuration(durationSecs.toLong(), TimeUnit.SECONDS)
                .done()

            val executor = FFmpegExecutor(ffmpeg, ffprobe)

            executor.createJob(builder).run()
        }

        fun addSubtitles(inputVid: String, inputSub: String, outPutName: String) {
            val dir = System.getProperty("user.dir")
            val builder = FFmpegBuilder()
                .addInput(ffprobe.probe(inputVid))
                .overrideOutputFiles(true)
                .addOutput("$dir\\entries\\saves\\$outPutName.mp4")
                .addExtraArgs("-vf", "ass=$inputSub")
                .done()

            val executor = FFmpegExecutor(ffmpeg, ffprobe)

            executor.createJob(builder).run()
        }
    }
}
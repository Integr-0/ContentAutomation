package utils

import net.bramp.ffmpeg.FFmpeg
import net.bramp.ffmpeg.FFmpegExecutor
import net.bramp.ffmpeg.FFprobe
import net.bramp.ffmpeg.builder.FFmpegBuilder
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit


class FFMPEGUtils {
    companion object {
        fun edit(inputVid: String, inputSub: String, durationSecs: Int) {
            val ffmpeg = FFmpeg("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffmpeg.exe")
            val ffprobe = FFprobe("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffprobe.exe")

            val builder = FFmpegBuilder()
                .addInput(ffprobe.probe(inputVid))
                .addInput(ffprobe.probe(inputSub))
                .overrideOutputFiles(true)
                .addOutput("${inputVid.substringBefore(".mp4")}-out.mp4")
                .addExtraArgs("-map 0:v", "-map 1:a") //FIXME: ???
                .disableSubtitle()
                .setDuration(durationSecs.toLong(), TimeUnit.SECONDS)
                .done()

            val executor = FFmpegExecutor(ffmpeg, ffprobe)

            executor.createJob(builder).run()
        }
    }
}
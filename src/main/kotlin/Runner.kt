import com.github.javafaker.Faker
import net.bramp.ffmpeg.FFmpeg
import net.bramp.ffmpeg.FFprobe
import utils.*
import utils.obj.NotBuiltException
import utils.obj.SettingsBuilder
import kotlin.random.Random

/**
 * **Author: Integr**
 * - **What:** Runs the bot
 * - **How:** Combining all things
 * - **Why:** To make the bot work
 */
class Runner {
    companion object {
        fun run(settings: SettingsBuilder) {
            if (!settings.isBuilt()) throw NotBuiltException("Given SettingsBuilder is not built!")

            val v = Generator.genVideoData(
                settings.videos!!,
                settings.perVideo!!,
                settings.outro!!,
                settings.series!!,
                settings.continueOn!!,
                settings.contentSource!!
            )

            FFmpegUtils.ffmpeg = FFmpeg(settings.ffmpegPath!!)
            FFmpegUtils.ffprobe = FFprobe(settings.probePath!!)

            val dir = System.getProperty("user.dir")
            val startTime = System.currentTimeMillis()

            println()

            for (vid in v.contents) {
                println("Generating Video: ${v.contents.indexOf(vid) + 1}/${settings.videos!!}")

                val name = vid.ttsFile.substringBefore(".mp3")

                FFmpegUtils.edit(
                    "$dir\\entries\\download\\${randomBackVid(settings.backVids!!)}.mp4",
                    "$dir\\entries\\vids\\${vid.ttsFile}",
                    name
                )

                SubtitleGenerator.genSub("$dir\\entries\\vids\\$name-cut.mp4", settings.venvPath!!)
                FFmpegUtils.addSubtitles(
                    "$dir\\entries\\vids\\$name-cut.mp4",
                    FileUtils.fixPathForSubAdd("$dir\\entries\\vids\\$name-cut-sub.ass"),
                    "$name-final"
                )
                FileUtils.cleanUp(name)

                if (settings.cookiePath != null) {
                    //TODO: Cookies for TikTok were set -> Upload
                    //TODO: Potentially AUTO-UPLOAD Here

                    // TODO DISABLED: TikTokUploader.upload("$dir\\entries\\saves\\$name-final.mp4", settings.venvPath!!, settings.aalTags!!.joinToString(" "), settings.cookiePath!!)
                }

                println()
                println(
                    " => Outputted ${v.contents.indexOf(vid) + 1}/${settings.videos!!} after ${
                        String.format(
                            "%.1f",
                            (System.currentTimeMillis() - startTime) * 0.001
                        )
                    } Seconds as: $name-final.mp4"
                )
                println()
            }

            println(
                "==> Finished Generation of ${settings.videos!!} Video/s in ${
                    String.format(
                        "%.1f",
                        (System.currentTimeMillis() - startTime) * 0.001
                    )
                } Seconds <=="
            )
        }

        private fun randomBackVid(vids: List<String>) = vids[Faker.instance().number().numberBetween(0, vids.size - 1)]

    }


}
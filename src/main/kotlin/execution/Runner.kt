/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package execution

import generators.Generator
import generators.SubtitleGenerator
import net.bramp.ffmpeg.FFmpeg
import net.bramp.ffmpeg.FFprobe
import utils.*
import execution.settings.NotBuiltException
import execution.settings.SettingsBuilder
import java.time.LocalTime
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

                SubtitleGenerator.genSub("$dir\\entries\\vids\\$name-cut.mp4", settings.venvPath!!, randomCol(settings.colorIsRandom!!))
                FFmpegUtils.addSubtitles(
                    "$dir\\entries\\vids\\$name-cut.mp4",
                    FileUtils.fixPathForSubAdd("$dir\\entries\\vids\\$name-cut-sub.ass"),
                    "$name-final"
                )
                FileUtils.cleanUp(name)

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


        private fun randomCol(isRandom: Boolean): String {
            if (!isRandom) return "36f7ca"

            val colors = listOf("36f7ca", "36f749", "c80eed", "f56416", "f20736", "eef207", "1b07f2", "f28b05", "f00c9c", "0c86f0")
            return colors[Random(LocalTime.now().second*System.currentTimeMillis()).nextInt(0, colors.size-1)]
        }
        private fun randomBackVid(vids: List<String>) = vids[Random(LocalTime.now().second*System.currentTimeMillis()).nextInt(0, vids.size-1)]
    }
}
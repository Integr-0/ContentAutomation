/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package execution.settings

import generators.Generator

/**
 * **Author: Integr**
 * - **What:** Settings for the runner
 * - **How:** Using the builder functions to set values
 * - **Why:** To make configuring the bot easier
 */
@Suppress("Unused")
class SettingsBuilder {
    var videos: Int? = null
    var perVideo: Int? = null
    var outro: String? = null
    var backVids: List<String>? = null
    var contentSource: ((Int) -> String)? = null
    var venvPath: String? = null
    var ffmpegPath: String? = null
    var probePath: String? = null
    var rcol: Boolean? = false
    private var isBuilt = false

    fun videoAmount(videos: Int): SettingsBuilder {
        this.videos = videos
        return this
    }

    fun perVideo(perVideo: Int): SettingsBuilder {
        this.perVideo = perVideo
        return this
    }

    fun outro(outro: String): SettingsBuilder {
        this.outro = outro
        return this
    }

    fun backVids(backVids: List<String>): SettingsBuilder {
        this.backVids = backVids
        return this
    }

    fun contentSource(contentSource: (Int) -> String): SettingsBuilder {
        this.contentSource = contentSource
        return this
    }

    fun randomSource(): SettingsBuilder {
        this.contentSource = Generator.getRandomJokeAPI()
        return this
    }

    fun venvPath(path: String): SettingsBuilder {
        this.venvPath = path.substringBefore(".exe")
        return this
    }

    fun ffmpegPath(path: String): SettingsBuilder {
        this.ffmpegPath = path
        return this
    }

    fun probePath(path: String): SettingsBuilder {
        this.probePath = path
        return this
    }

    fun randomColor(): SettingsBuilder {
        this.rcol = true
        return this
    }

    fun build(): SettingsBuilder {
        isBuilt = true

        if (videos == null) throw NotSetException("No utils.video count given! - Use .videoAmount() to set it!")
        if (perVideo == null) throw NotSetException("No content per utils.video count given! - Use .perVideo() to set it!")
        if (outro == null) throw NotSetException("No outro given! - Use .outro() to set it!")
        if (backVids == null) throw NotSetException("No background videos count given! - Use .backVids() to set it!")
        if (contentSource == null) throw NotSetException("No content source function given! - Use .contentSource() to set it!")
        if (venvPath == null) throw NotSetException("No virtual env path given! - Use .venvPath() to set it!")
        if (ffmpegPath == null) throw NotSetException("No Ffmpeg path given! - Use .ffmpegPath() to set it!")
        if (probePath == null) throw NotSetException("No Ffmpeg probe path given! - Use .probePath() to set it!")

        return this
    }

    fun isBuilt() = isBuilt
}
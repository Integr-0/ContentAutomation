/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package execution.settings

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SettingsBuilderTest {
    @Test
    fun `SettingsBuilder Built`() {
        val settings = SettingsBuilder()
            .venvPath("x")
            .ffmpegPath("x")
            .probePath("x")
            .videoAmount(1)
            .perVideo(1)
            .outro("x")
            .backVids(listOf("x", "y", "z"))
            .randomSource()
            .randomColor()
            .build()

        assert(settings.isBuilt())
    }

    @Test
    fun `SettingsBuilder Throws`() {
        val settings = SettingsBuilder()
            .venvPath("x")
            .ffmpegPath("x")
            .videoAmount(1)
            .perVideo(1)
            .outro("x")
            .backVids(listOf("x", "y", "z"))
            .randomSource()
            .randomColor()

        assertThrows(NotSetException::class.java, settings::build)
    }
}
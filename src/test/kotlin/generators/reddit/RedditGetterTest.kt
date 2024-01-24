/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package generators.reddit

import org.junit.jupiter.api.Test
import java.net.URL

class RedditGetterTest {
    @Test
    fun `RedditGetter Empty`() {
        val out = RedditGetter.getPosts(URL("https://www.reddit.com/r/askreddit/.json"), 1, 3)

        assert(out.isNotEmpty())
        assert(out[0].responses.isNotEmpty())
    }

    @Test
    fun `RedditGetter Blank`() {
        val out = RedditGetter.getPosts(URL("https://www.reddit.com/r/askreddit/.json"), 1, 3)

        assert(out[0].question.isNotBlank())
        assert(out[0].responses[0].isNotBlank())
    }
}
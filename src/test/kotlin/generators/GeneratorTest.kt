package generators

import org.junit.jupiter.api.Test

class GeneratorTest {
    @Test
    fun `Generator Joke`() {
        val out = Generator.readJokeAPI()

        assert(out.isNotBlank())
        assert(out != " ")
        assert(!out.contains("\n"))
    }

    @Test
    fun `Generator Dark Joke`() {
        val out = Generator.readDarkJokeAPI()

        assert(out.isNotBlank())
        assert(out != " ")
        assert(!out.contains("\n"))
    }

    @Test
    fun `Generator Geek Joke`() {
        val out = Generator.readGeekJokeAPI()

        assert(out.isNotBlank())
        assert(out != " ")
        assert(!out.contains("\n"))
    }

    @Test
    fun `Generator Dad Joke`() {
        val out = Generator.readDadJokeAPI()

        assert(out.isNotBlank())
        assert(out != " ")
        assert(!out.contains("\n"))
    }

    @Test
    fun `Generator Official Joke`() {
        val out = Generator.readOfficialJokeAPI()

        assert(out.isNotBlank())
        assert(out != " ")
        assert(!out.contains("\n"))
    }

    @Test
    fun `Generator Chuck Quote`() {
        val out = Generator.readChuckNorisQuoteAPI()

        assert(out.isNotBlank())
        assert(out != " ")
        assert(!out.contains("\n"))
    }

    @Test
    fun `Generator Programmer Joke`() {
        val out = Generator.readProgrammerJokeAPI()

        assert(out.isNotBlank())
        assert(out != " ")
        assert(!out.contains("\n"))
    }

    @Test
    fun `Generator Random API`() {
        val out = Generator.getRandomJokeAPI()
        val executed = out(0)

        assert(executed.isNotBlank())
        assert(executed != " ")
        assert(!executed.contains("\n"))
    }
}
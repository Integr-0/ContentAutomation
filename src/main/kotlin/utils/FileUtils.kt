package utils

import java.io.BufferedReader
import java.io.Console
import java.io.InputStreamReader
import java.nio.file.Files
import kotlin.io.path.Path


class FileUtils {
    companion object {
        fun dump(json: String, name: String) {
            Files.createDirectories(Path("./entries/saves/"))
            Files.write(
                Path(
                    "./entries/saves/$name.json"
                ),
                json.toByteArray()
            )
        }

        fun downloadYT(url: String, name: String) {
            val dir = System.getProperty("user.dir")
            Files.createDirectories(Path("$dir/entries/vids/"))

            val p: Process =
                Runtime.getRuntime().exec(String.format("yt-dlp -o \"$dir\\entries\\vids\\$name.mp4\" $url"));

            val stdInput = BufferedReader(InputStreamReader(p.inputStream))
            val stdError = BufferedReader(InputStreamReader(p.errorStream))

            var s = ""
            while (stdInput.readLine() != null && (stdInput.readLine().also { s = it }) != null) {
                println(s)
            }

            while (stdError.readLine() != null && (stdError.readLine().also { s = it }) != null) {
                println(s)
            }
        }
    }
}
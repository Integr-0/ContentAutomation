package utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.name


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
                        Files.createDirectories(Path("$dir/entries/download/"))

                        val p: Process =
                                Runtime.getRuntime()
                                        .exec(String.format("yt-dlp -f mp4 -o \"$dir\\entries\\download\\$name.mp4\" $url"));

                        val stdInput = BufferedReader(InputStreamReader(p.inputStream))
                        val stdError = BufferedReader(InputStreamReader(p.errorStream))

                        var s = ""
                        try {
                                while (stdInput.readLine() != null && (stdInput.readLine().also { s = it }) != null) {
                                        if (s.startsWith("[download]")) println(s)
                                }

                                while (stdError.readLine() != null && (stdError.readLine().also { s = it }) != null) {
                                        if (s.startsWith("[download]")) println(s)
                                }
                        } catch (e: NullPointerException) {
                                // Do Nothing
                        }

                }

                fun cleanUp(name: String) {
                        println("|| Cleaning up temp files")
                        val dir = System.getProperty("user.dir")
                        for (f in Files.list(Path("$dir\\entries\\vids\\"))) {
                                if (f.name.startsWith(name)) {
                                        Files.delete(f.toAbsolutePath())
                                }
                        }
                }

                fun fixPathForSubAdd(string: String): String {
                        return string.replace("\\", "\\\\\\\\").replace("C:", "C\\\\\\:")
                }
        }
}
import jdk.jpackage.internal.IOUtils
import java.io.Console
import java.nio.file.Files
import java.util.logging.ConsoleHandler
import java.util.logging.StreamHandler
import kotlin.io.path.Path


class Dumper {
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
            //print("yt-dlp -o \"$dir\\entries\\vids\\$name.mp4\"")

            val p: Process = Runtime.getRuntime().exec(String.format("yt-dlp -o \"$dir\\entries\\vids\\$name.mp4\" $url"));

            while (p.isAlive) {

            }
        }
    }
}
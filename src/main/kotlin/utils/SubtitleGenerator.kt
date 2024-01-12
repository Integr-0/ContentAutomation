package utils

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class SubtitleGenerator {
        companion object {
                fun genSub(filename: String, venvPath: String) {
                        val dir = System.getProperty("user.dir")

                        println("|| Generating subtitle .ass file")
                        val p: Process =
                                Runtime.getRuntime()
                                        .exec(String.format("$dir\\venv\\Scripts\\python $dir\\SubtitleGeneration\\main.py $filename"));

                        val stdInput = BufferedReader(InputStreamReader(p.inputStream))
                        val stdError = BufferedReader(InputStreamReader(p.errorStream))

                        try {
                                while (stdInput.readLine() != null) {
                                        // Wait
                                }

                                while (stdError.readLine() != null) {
                                        // Wait
                                }
                        } catch (e: NullPointerException) {
                                // Nope
                        }
                }
        }
}
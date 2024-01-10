package utils

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class SubtitleGenerator {
    companion object {
        fun genSub(filename: String) {
            val dir = System.getProperty("user.dir")

            val p: Process =
                Runtime.getRuntime().exec(String.format("$dir/venv/Scripts/python $dir/SubtitleGeneration/main.py $filename"));

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
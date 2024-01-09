import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.reflect.typeOf

data class VideoObject(
    var title: String = "",
    var series: String = "",
    var parts: List<String> = listOf(),
    var outro: String = "",
    var ttsFile: String = ""
)
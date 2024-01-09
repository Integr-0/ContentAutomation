import com.google.gson.GsonBuilder

class VideoContainer(var contents: List<VideoObject>) {
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

    companion object {
        fun fromJson(json: String): VideoContainer {
            return GsonBuilder().create().fromJson(json, VideoContainer::class.java)
        }
    }
}
package utils.obj

class SettingsBuilder {
    var videos: Int? = null
    var perVideo: Int? = null
    var outro: String? = null
    var series: String? = null
    var backVids: List<String>? = null
    var continueOn: Int? = null
    var contentSource: (() -> String)? = null
    private var isBuilt = false;

    fun videoAmount(videos: Int): SettingsBuilder {
        this.videos = videos
        return this
    }
    fun perVideo(perVideo: Int): SettingsBuilder {
        this.perVideo = perVideo
        return this
    }
    fun outro(outro: String): SettingsBuilder {
        this.outro = outro
        return this
    }
    fun series(series: String): SettingsBuilder {
        this.series = series
        return this
    }
    fun backVids(backVids: List<String>): SettingsBuilder {
        this.backVids = backVids
        return this
    }
    fun continueOn(continueOn: Int): SettingsBuilder {
        this.continueOn = continueOn
        return this
    }
    fun contentSource(contentSource: () -> String): SettingsBuilder {
        this.contentSource = contentSource
        return this
    }

    fun build(): SettingsBuilder {
        isBuilt = true

        if (videos == null) throw NullPointerException("No video count given! - Use .videoAmount() to set it!")
        if (perVideo == null) throw NullPointerException("No content per video count given! - Use .perVideo() to set it!")
        if (outro == null) throw NullPointerException("No outro given! - Use .outro() to set it!")
        if (series == null) throw NullPointerException("No series given! - Use .series() to set it!")
        if (backVids == null) throw NullPointerException("No background videos count given! - Use .backVids() to set it!")
        if (continueOn == null) throw NullPointerException("No series continuation given! - Use .continueOn() to set it!")
        if (contentSource == null) throw NullPointerException("No content source function given! - Use .contentSource() to set it!")

        return this
    }

    fun isBuilt() = isBuilt
}

class NotBuiltException(message: String?) : Exception(message)
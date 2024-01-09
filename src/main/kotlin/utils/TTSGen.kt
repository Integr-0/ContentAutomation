package utils

import io.ikfly.constant.OutputFormat
import io.ikfly.constant.VoiceEnum
import io.ikfly.model.SSML
import io.ikfly.service.TTSService


class TTSGen {
    companion object {
        fun genTTS(text: String, partNum: Int, name: String) {
            val ts = TTSService()
            val ssml = SSML.builder()
                .outputFormat(OutputFormat.audio_24khz_48kbitrate_mono_mp3)
                .synthesisText(text)
                .outputFileName("./entries/saves/${name}_$partNum")
                .voice(VoiceEnum.en_US_RogerNeural)
                .build()

            ts.sendText(ssml)

            ts.close()
        }
    }
}
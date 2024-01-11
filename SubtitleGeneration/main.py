from pathlib import Path

import torch
import stable_whisper as whisper
import argparse

argParser = argparse.ArgumentParser()
argParser.add_argument("file")

args = argParser.parse_args()


def srt_create(filename: str):
    absolute_ass_path = Path(filename.removesuffix(".mp4")+"-sub.ass")

    word_dict = {
        'Fontname': 'Arial',
        'Alignment': 5,
        'BorderStyle': '1',
        'Outline': '1',
        'Shadow': '2',
        'Blur': '21',
        'Fontsize': 13,
        'MarginL': '0',
        'MarginR': '0',
    }

    transcribe = whisper.load_model("base").transcribe(
        filename,
        regroup=True,
        fp16=torch.cuda.is_available()
    )

    transcribe.split_by_gap(0.5).split_by_length(38).merge_by_gap(0.15, max_words=2)

    transcribe.to_ass(
        str(absolute_ass_path),
        word_level=True,
        font_size=13,
        highlight_color="05f030",
        **word_dict
    )


srt_create(args.file)

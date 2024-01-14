#     This file is part of the Content automation project by Integr
#     Copyright (c) Integr.
#
#     Full copyright found in LICENSE.md

from pathlib import Path

import torch
import stable_whisper as whisper
import argparse

argParser = argparse.ArgumentParser()
argParser.add_argument("file")
argParser.add_argument("col")

args = argParser.parse_args()


def generate(filename: str, color: str):
    path = Path(filename.removesuffix(".mp4")+"-sub.ass")

    settings = {
        'Fontname': 'Arial',
        'Alignment': '5',
        'BorderStyle': '1',
        'Outline': '1',
        'Shadow': '2',
        'Blur': '21',
        'Fontsize': '13',
        'MarginL': '0',
        'MarginR': '0',
    }

    transcription = whisper.load_model("base").transcribe(
        filename,
        regroup=True,
        fp16=torch.cuda.is_available()
    )

    transcription.split_by_gap(0.5).split_by_length(38).merge_by_gap(0.15, max_words=2)

    transcription.to_ass(
        str(path),
        word_level=True,
        font_size=13,
        highlight_color=color,
        **settings
    )


generate(args.file, args.col)

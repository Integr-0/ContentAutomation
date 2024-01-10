## About

### Project

Automatically generates clips of a length of around 30 seconds. Fills those clips with Chuck Norris jokes, and jokes
from a joke-api. Uses background Videos in -mp4 format. Adds subtitles automatically. Perfect for platforms like TikTok
and YouTube-shorts.

### Me

I'm a student from Austria. I like to code random projects like this in my free time.

## Requirements

### External

- [PYTHON 3.9](https://www.python.org/downloads/release/python-390/)
- [FFMPEG](https://ffmpeg.org/) (Chocolatey -> ```choco install ffmpeg-full```)
- [YT-DLP](https://github.com/yt-dlp/yt-dlp)

### Python-Lib

- stable-ts (pip -> ```pip install stable-ts```)
- openai-whisper (pip -> ```pip install openai-whisper```)
- torch (pip -> ```pip install torch```)

## Getting started

Clone this repo and create a virtual environment (or use your own Python 3.9).
Open the Main.kt file and edit the settings.
````kotlin
val videos = 5 // Amount og videos to generate per run
val perVideo = 3 // Amount of jokes to package into one video
val backVid = "Parkour_Background.mp4" // Filename of the background video (Origin in "./entries/download")
````
Run the ``main()`` method (via Gradle)
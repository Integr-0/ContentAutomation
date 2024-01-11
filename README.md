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

### Configuring the Runner and running it

Clone this repo and create a virtual environment.
Open the Main.kt file and edit the settings.

````kotlin
val settings = SettingsBuilder()
    .videoAmount(5)
    .perVideo(5)
    .outro("Follow us for more!")
    .series("Jokes")
    .backVids(listOf("Air_Parkour", "Hypixel_Parkour", "Spiral_Parkour", "Scenic_Parkour"))
    .continueOn(1)
    .contentSource { Generator.readJokeAPI() }
    .build()
````

Run the ``main()`` method (via Gradle)

### Downloading external content

````kotlin
FileUtils.downloadYT("https://www.youtube.com/watch?v=Pt5_GSKIWQM", "Hypixel_Parkour")
FileUtils.downloadYT("https://www.youtube.com/watch?v=2VpG0WS4uCo", "Spiral_Parkour")
FileUtils.downloadYT("https://www.youtube.com/watch?v=JlPEb6WNuDI", "Scenic_Parkour")
````

These are 3 examples of downloading a YouTube video and saving is under a name.
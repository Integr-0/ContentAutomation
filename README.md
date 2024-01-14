<!--suppress CheckImageSize -->
<img alt="YT_TRANS.png" height="200" src="YT_TRANS.png" width="200"/>

# Content Automation

## Table of Contents
<!-- TOC -->
* [Content Automation](#content-automation)
  * [Table of Contents](#table-of-contents)
  * [About](#about)
    * [Project](#project)
    * [Me](#me)
  * [Requirements](#requirements)
    * [External](#external)
    * [Python-Lib](#python-lib)
  * [Getting started](#getting-started)
    * [Setting up the virtual environment](#setting-up-the-virtual-environment)
    * [Configuring the Runner and running it](#configuring-the-runner-and-running-it)
    * [Downloading external content](#downloading-external-content)
    * [Grabbing the finished product](#grabbing-the-finished-product)
  * [Dependencies](#dependencies)
    * [Kotlin](#kotlin)
    * [Python](#python)
    * [External](#external-1)
<!-- TOC -->

## About

### Project

Automatically generates clips of a length of around 30 seconds. Fills those clips with Chuck Norris jokes, and jokes
from a joke-api. Uses background Videos in -mp4 format. Adds subtitles automatically. Perfect for platforms like TikTok
and YouTube-shorts.

### Me

I'm a student from Austria. I like to code random projects like this in my free time.

## Requirements

### External

> [!NOTE]
> There is an installation of FFMPEG via WinGet, although I couldn't get it to work.

- [PYTHON 3.9](https://www.python.org/downloads/release/python-390/)
- [FFMPEG](https://ffmpeg.org/) (Chocolatey -> ```choco install ffmpeg-full```)
- [YT-DLP](https://github.com/yt-dlp/yt-dlp)

### Python-Lib

- stable-ts (pip -> ```pip install stable-ts```)
- openai-whisper (pip -> ```pip install openai-whisper```)
- torch (pip -> ```pip install torch```)

## Getting started

### Setting up the virtual environment

> [!CAUTION]
> Python 3.9.0 is Required

Create a new Python venv (or use your own Python) and get the path to the "python.exe" it uses. Add it in the .venvPath()

### Configuring the Runner and running it

> [!CAUTION]
> Make sure you have all tools installed, and u have the correct paths

Clone this repo and open the Main.kt file and edit the execution.settings.

````kotlin
val settings = SettingsBuilder()
  .venvPath("C:\\Users\\erikr\\Desktop\\Projects\\ContentAutomation\\venv\\Scripts\\python.exe")
  .ffmpegPath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffmpeg.exe")
  .probePath("C:\\ProgramData\\chocolatey\\lib\\ffmpeg-full\\tools\\ffmpeg\\bin\\ffprobe.exe")
  .videoAmount(1)
  .perVideo(5)
  .outro("Follow us for more!")
  .backVids(listOf("Air_Parkour", "Hypixel_Parkour", "Spiral_Parkour", "Scenic_Parkour"))
  .contentSource {
    Generator.readJokeAPI()
  }
  .randomColor()
  .build()

````

Run the ``main()`` method (via Intellij or your IDE)

### Downloading external content

````kotlin
FileUtils.downloadYT("https://www.youtube.com/watch?v=Pt5_GSKIWQM", "Hypixel_Parkour")
FileUtils.downloadYT("https://www.youtube.com/watch?v=2VpG0WS4uCo", "Spiral_Parkour")
FileUtils.downloadYT("https://www.youtube.com/watch?v=JlPEb6WNuDI", "Scenic_Parkour")
````

These are 3 examples of downloading a YouTube video and saving is under a name.

### Grabbing the finished product

The output directory of the bot is: ````./entries/saves````
Outputs are in the following format: 
````
<day>-<month>-<year>-<second>-<minute>-<hour>_<video_group_index>-final.mp4
````
For example:
````
5-2-2024-16-22-8_2-final.mp4
````
This means that:
- Day: 5th
- Month: 2nd
- Year: 2024
- Second: 16
- Minute: 22
- Hour: 8
- Index of Group: 2

## Dependencies

### Kotlin

- [OkHTTP](https://github.com/square/okhttp)
  - Apache License, Version 2.0
- [Gson](https://github.com/google/gson)
  - Apache License, Version 2.0
- [JavaFaker](https://github.com/HannnnXiao/javafaker)
  - Apache License, Version 2.0
- [Java-TTS](https://github.com/ikfly/java-tts)
  - None
- [Ffmpeg wrapper](https://github.com/bramp/ffmpeg-cli-wrapper)
  - BSD 2-Clause "Simplified" License
- [Slf4j](https://www.slf4j.org/)
  - MIT License

### Python

- [PyTorch](https://pytorch.org/)
  - [Special](https://github.com/pytorch/pytorch/blob/main/LICENSE)
- [OpenAI-Whisper](https://github.com/openai/whisper)
  - MIT License

### External

- [PYTHON 3.9](https://www.python.org/downloads/release/python-390/)
  - [Special](https://docs.python.org/3/license.html)
- [FFMPEG](https://ffmpeg.org/)
  - GNU Lesser General Public License (LGPL) version 2.1
- [YT-DLP](https://github.com/yt-dlp/yt-dlp)
  - The UnLicense
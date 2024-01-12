import os

from tiktok_uploader.upload import upload_video
import argparse

argParser = argparse.ArgumentParser()
argParser.add_argument("file")
argParser.add_argument("bio")
argParser.add_argument("cookie")

args = argParser.parse_args()


def upload_tiktok(file, title: str, cookie_file: str):
    if os.path.isfile(cookie_file):
        try:
            upload_video(file, description=title, cookies=cookie_file, comment=True, stitch=False, duet=False, headless=False)
        except Exception as e:
            print(e)


upload_tiktok(args.file, args.bio, args.cookie)

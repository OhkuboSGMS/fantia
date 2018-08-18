# fantia
fantiaの画像をログインして取得する

seleniumを使用したfantiaにログインしてpost/xxxxの画像を取得するスクレイピングです。

某氏の画像がzipで纏めてられなかったのでそれを取得する必要があるかなと思ったんですけど。あとで見たらzipで纏められていたんでダメですね（笑）

#実行環境
macOS HighSieera

JetBrains Idea

kotlin


#依存関係
* selenium
* fuel
* kotlin-coroutine


build.gradle

```buildscript {
    ext.kotlin_version = '1.2.51'

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}

plugins {
    id 'java'
}

group 'UnityAssetScraping'
version '1.0-SNAPSHOT'

apply plugin: 'kotlin'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile 'org.jetbrains.kotlinx:kotlinx-coroutines-core:0.24.0'
    compile "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
    compile 'com.github.kittinunf.fuel:fuel:1.15.0' //for JVM
    compile 'com.github.kittinunf.fuel:fuel-coroutines:1.15.0' //for Kotlin Coroutines support
    compile "org.seleniumhq.selenium:selenium-java:3.5.3"
    testCompile group: 'junit', name: 'junit', version: '4.12'
} 

```


# 実行
```

mail address:xxxx@mail.com
password:XXXXX
post_id:63867 ←欲しい画像のpostの値 (https://fantia.jp/posts/63867)
directory name:保存するディレクトリの名前
(以下実行時のログ)

Starting ChromeDriver 2.41.578706 (5f725d1b4f0a4acbf5259df887244095596231db) on port 47438
Only local connections are allowed.
8 19, 2018 3:14:47 午前 org.openqa.selenium.remote.ProtocolHandshake createSession
情報: Detected dialect: OSS
set account info
login complete
wait until page loaded
image loading need more wait(for ajax)
click 大きく表示
page loaded start click
img-url:https://c.fantia.jp/uploads/fanclub/icon_image/6939/thumb_4649e26d-39db-4024-9f33-12dc440ecb5c.jpg
img-url:/images/fallback/user/default_4.png
img-url:/images/fallback/user/thumb_default.png
img-url:/images/fallback/user/thumb_default.png
img-url:/images/fallback/user/thumb_default.png
.
.
.
download url:https://cc.fantia.jp/uploads/post_content_photo/file/214447/main_6680ba56_rkgk59cb.jpg?Key-Pair-Id=APKAIOCKYZS7WKBB6G7A&Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9jYy5mYW50aWEuanAvdXBsb2Fkcy9wb3N0X2NvbnRlbnRfcGhvdG8vZmlsZS8yMTQ0NDcvbWFpbl82NjgwYmE1Nl9ya2drNTljYi5qcGciLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE1MzQ2MTY2OTd9fX1dfQ__&Signature=dBpKl9%7EJqBN4dLOqmc%7EUCmQghl8aLJMnwlwqxxnIzP-94G18qzA0dDXUOKkcD654AJ8PC7CRGO0pzFaka2HHCtzHQvcbiaGX2C8uJJFoBlleOCcfectGiUCB1%7ElVOOGpvO-WGrM-iNyayq4rdC2hILZ%7EeNnANuoANewH-KrC0jIBQ1wAQk%7EgbLGN3L8AcbELxcBgdcB02r-ZbF825-LOeyN%7ErwJYanfH2A4zh7589n4hTSfyq7Co1IlzbaSFEv1BKljvHaayeSljz16xZX7p7g6jiuFKFi6iOheOPG5fRk6b25wH4Sa0xM9U0nrCa8sbQ01hXn3DJRihjGVhWr5s0Q__
download url:https://cc.fantia.jp/uploads/post_content_photo/file/214448/main_ed0c7406_uza03.jpg?Key-Pair-Id=APKAIOCKYZS7WKBB6G7A&Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9jYy5mYW50aWEuanAvdXBsb2Fkcy9wb3N0X2NvbnRlbnRfcGhvdG8vZmlsZS8yMTQ0NDgvbWFpbl9lZDBjNzQwNl91emEwMy5qcGciLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE1MzQ2MTY2OTd9fX1dfQ__&Signature=147DuYHNiXBhUZi-s6MaR6HCpnx1F1JWfrqePxJXpih4wJ2rpWLMLJMwqjTBPQUBL%7EVeri6pKbXZdG7WXiI4ypcKgQOloPDQo7NPoKnKNwONxgos-EupXDsXqc8cYKDwk-5xUPpMZ0dpxsdE1owf2OIn4%7EczZsSwK3d2VUv5O-kpVvjN0OuVvKzuRaoSEhS9bk7Hf5C58j6FBU8%7Ew50E2mMSbqmnqu0PS%7E3FHzo9DiX5iCZIOpdv8JwBbf2ebc9Mc91ygQ34xLevXSzBb2f8BOZH9yjOSI8A2SKltHjAUqOZ5yrhPWqX6i95gRnOkhf6UQ0juOJ9K0ppOM%7EWUZA6MA__
download url:https://cc.fantia.jp/uploads/post_content_photo/file/214449/main_b67b6fb8_oob7a.jpg?Key-Pair-Id=APKAIOCKYZS7WKBB6G7A&Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9jYy5mYW50aWEuanAvdXBsb2Fkcy9wb3N0X2NvbnRlbnRfcGhvdG8vZmlsZS8yMTQ0NDkvbWFpbl9iNjdiNmZiOF9vb2I3YS5qcGciLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE1MzQ2MTY2OTd9fX1dfQ__&Signature=nxJqWD7TkXaEkvjpBzzIFOr3SRDsdECyIqb4QG6SVmPWOvmKFlcmxPIkAonuc1ZxTr8u-BLabllSWmpl7GeDnFNXgf7LuU3R4bGPaqs9EduYFvSK%7Etbqf39rrKEH48-EWYa-AFNojmTJfiy-pt4Qx6Dx6Q4yuSa2xBtZv3UaORCvlWxZGP-qMVHzM-vvviI-dmNfYFA364sTcChH8ifogE4c2mDV-Y2DDU%7EYI4RE55xFr52Py4CepXIG56ZbTEJ0Hh3q8NzUYxHPVzsBA52qv16Hy1eVM0fYOP-tkRFfbbvTrf-DyjExLKFo%7EK%7EbiNK3Pcmao2s0LAE23-2jx4nqlg__

```
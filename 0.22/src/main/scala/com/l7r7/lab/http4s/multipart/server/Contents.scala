package com.l7r7.lab.http4s.multipart.server

import java.util.UUID

object Contents {
  def single(page: Int): String =
    s"""--gc0p4Jq0M2Yt08jU534c0p
       |Operation-Type: http-equiv=PUT
       |Content-Disposition: inline
       |Content-Transfer-Encoding: 8bit
       |Content-Type: application/vnd.example+json
       |Last-Modified: Fri, 8 Feb 2019 18:37:25 GMT
       |Content-Length: 83
       |ETag: 1986283643
       |Page-Number: $page
       |Content-ID: <${UUID.randomUUID()}>
       |
       |
       |[{
       |  "created_at": "Thu Jun 22 21:00:00 +0000 2017",
       |  "id": 877994604561387500,
       |  "id_str": "877994604561387520",
       |  "text": "Creating a Grocery List Manager Using Angular, Part 1: Add &amp; Display Items https://t.co/xFox78juL1 #Angular",
       |  "truncated": false,
       |  "entities": {
       |    "hashtags": [{
       |      "text": "Angular",
       |      "indices": [103, 111]
       |    }],
       |    "symbols": [],
       |    "user_mentions": [],
       |    "urls": [{
       |      "url": "https://t.co/xFox78juL1",
       |      "expanded_url": "http://buff.ly/2sr60pf",
       |      "display_url": "buff.ly/2sr60pf",
       |      "indices": [79, 102]
       |    }]
       |  },
       |  "source": "<a href=\"http://bufferapp.com\" rel=\"nofollow\">Buffer</a>",
       |  "user": {
       |    "id": 772682964,
       |    "id_str": "772682964",
       |    "name": "SitePoint JavaScript",
       |    "screen_name": "SitePointJS",
       |    "location": "Melbourne, Australia",
       |    "description": "Keep up with JavaScript tutorials, tips, tricks and articles at SitePoint.",
       |    "url": "http://t.co/cCH13gqeUK",
       |    "entities": {
       |      "url": {
       |        "urls": [{
       |          "url": "http://t.co/cCH13gqeUK",
       |          "expanded_url": "http://sitepoint.com/javascript",
       |          "display_url": "sitepoint.com/javascript",
       |          "indices": [0, 22]
       |        }]
       |      },
       |      "description": {
       |        "urls": []
       |      }
       |    },
       |    "protected": false,
       |    "followers_count": 2145,
       |    "friends_count": 18,
       |    "listed_count": 328,
       |    "created_at": "Wed Aug 22 02:06:33 +0000 2012",
       |    "favourites_count": 57,
       |    "utc_offset": 43200,
       |    "time_zone": "Wellington",
       |  },
       |}]
       |--gc0p4Jq0M2Yt08jU534c0p
       |Operation-Type: http-equiv=PUT
       |Content-Disposition: inline
       |Content-Transfer-Encoding: 8bit
       |Content-Type: application/vnd.example2+json
       |Last-Modified: Sat, 17 Apr 2021 20:59:37 GMT
       |Content-Length: 98
       |ETag: 1140044744
       |Content-ID: <1821d84c-5f5d-4879-ae99-73da984637fe>
       |
       |
       |{"foo":"066c18f7-7449-4529-a5df-b0aa07907bbe","bar":"2021-04-17T20:58:18.625318Z"}
       |
       |--gc0p4Jq0M2Yt08jU534c0p
       |Operation-Type: http-equiv=PUT
       |Content-Disposition: inline
       |Content-Transfer-Encoding: 8bit
       |Content-Type: application/vnd.example2+json
       |Last-Modified: Sat, 17 Apr 2021 20:59:37 GMT
       |Content-Length: 98
       |ETag: 1140044744
       |Content-ID: <1821d84c-5f5d-4879-ae99-73da984637fe>
       |
       |
       |{"foo":"066c18f7-7449-4529-a5df-b0aa07907bbe","bar":"2021-04-17T20:58:18.625318Z"}
       |
       |--gc0p4Jq0M2Yt08jU534c0p
       |Operation-Type: http-equiv=PUT
       |Content-Disposition: inline
       |Content-Transfer-Encoding: 8bit
       |Content-Type: application/vnd.example2+json
       |Last-Modified: Sat, 17 Apr 2021 20:59:37 GMT
       |Content-Length: 98
       |ETag: 1140044744
       |Content-ID: <1821d84c-5f5d-4879-ae99-73da984637fe>
       |
       |
       |{"foo":"066c18f7-7449-4529-a5df-b0aa07907bbe","bar":"2021-04-17T20:58:18.625318Z"}
       |
       |--gc0p4Jq0M2Yt08jU534c0p--""".stripMargin.replace("\n", "\r\n")

}

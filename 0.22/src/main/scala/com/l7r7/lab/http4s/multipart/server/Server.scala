package com.l7r7.lab.http4s.multipart.server

import cats.arrow.FunctionK
import cats.data.Kleisli
import cats.effect._
import org.http4s.dsl.io._
import org.http4s.headers._
import org.http4s.implicits._
import org.http4s.server.blaze._
import org.http4s.server.middleware.Logger
import org.http4s._

object Server extends IOApp {

   private val contentType = `Content-Type`(MediaType.multipartType("package", Some("gc0p4Jq0M2Yt08jU534c0p")))

  val routes: Kleisli[IO, Request[IO], Response[IO]] =
    HttpRoutes.of[IO] {
      case _ -> Root / "test-endpoint" / "latest" =>
        Ok(Contents.single(100), contentType)
          .map(_.putHeaders(
            `Last-Modified`(HttpDate.unsafeFromEpochSecond(1568040401)),
            Link(LinkValue(Uri.unsafeFromString("/test-endpoint/3"), rel = Some("prev"))),
            Link(LinkValue(Uri.unsafeFromString("/test-endpoint/4"), rel = Some("self")))
          ))
    }.orNotFound

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(Logger(logHeaders = true, logBody = false, FunctionK.id[IO])(routes))
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
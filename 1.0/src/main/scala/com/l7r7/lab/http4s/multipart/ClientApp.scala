package com.l7r7.lab.http4s.multipart

import cats.effect.{ExitCode, IO, IOApp}
import fs2.{Pipe, Stream}
import org.http4s.Method.GET
import org.http4s._
import org.http4s.client.Client
import org.http4s.client.blaze._
import org.http4s.client.middleware.Logger
import org.http4s.headers.`Content-Type`
import org.http4s.multipart.{Boundary, MultipartParser, Part}
import org.typelevel.ci.CIString

import scala.concurrent.ExecutionContext

object ClientApp extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {

    import ExecutionContext.Implicits.global

    BlazeClientBuilder[IO](ExecutionContext.global).resource.use { client: Client[IO] =>
      implicit val httpApp: HttpApp[IO] = Logger(logHeaders = true, logBody = false)(client).toHttpApp

      read
        .evalMap(part => IO.delay(println(part)))
        .compile
        .drain
        .as(ExitCode.Success)
    }
  }

  def read(implicit ec: ExecutionContext, httpApp: HttpApp[IO]): Stream[IO, Part[IO]] =
    Stream.emit(createRequest)
      .through(doRequest)
      .through(responseToParts)

  val createRequest: Request[IO] =
    Request[IO](GET, Uri.unsafeFromString(s"http://localhost:8080/test-endpoint/latest"))

  def doRequest(implicit httpApp: HttpApp[IO]): Pipe[IO, Request[IO], Response[IO]] =
    _.evalMap { request =>
      for {
        response <- httpApp.run(request)
        _ <- response.body.compile.drain
      } yield response
    }

  def responseToParts: Pipe[IO, Response[IO], Part[IO]] = _.flatMap { response =>
    findBoundary(response.headers) match {
      case Some(boundary) => response.body.unchunk.through(MultipartParser.parseToPartsStream(boundary))
      case None => Stream.raiseError[IO](new IllegalStateException("no boundary found"))
    }
  }

  def findBoundary(headers: Headers): Option[Boundary] =
    headers.headers
      .filter(raw => raw.name == CIString("Content-Type"))
      .map(raw => `Content-Type`.parse(raw.value))
      .flatMap(_.toOption)
      .headOption
      .flatMap(_.mediaType.extensions.get("boundary"))
      .map(Boundary(_))
}

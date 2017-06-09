package com.md.saklia

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import com.md.saklia.data.{Film, FilmRepository}
import de.choffmeister.microserviceutils.http.{HttpServerBase, PagingDirective}
import de.choffmeister.microserviceutils.json._

import scala.concurrent.{ExecutionContext, Future}

class HttpServer(repository: FilmRepository)(override implicit val system: ActorSystem, implicit val executor: ExecutionContext)
  extends HttpServerBase with JsonProtocol with PagingDirective {
  override def home = HttpServer.jsonHome
  override def routes =
    pathPrefix("api") {
      pathEnd {
        complete(HttpServer.jsonHome)
      } ~
      path("films") {
        get {
          pathEnd {
            paging(defaultLength = 20) { page =>
              val filmsPage = repository.findAll(page)
              val filmResourcesFuture: Future[Vector[HalResource[Film]]] = filmsPage.map(_.map(HalResource(_)).toVector)
              complete(filmResourcesFuture.map(HalResource(PageWrapper(page)).writeEmbedded("films", _)))
            }
          }
        }
      }
    }
}

object HttpServer {
  val docsBaseUrl = "http://akka-http-quill-async-mysql"

  val greetRel = s"$docsBaseUrl/rel/greet"

  val jsonHome = JsonHome(Map(
    greetRel -> JsonHomeSingleResource("/greet")
  ))
}

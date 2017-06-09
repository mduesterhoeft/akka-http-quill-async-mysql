package com.md.saklia

import akka.actor.ActorSystem
import com.md.saklia.data.FilmRepository
import de.choffmeister.microserviceutils.ServiceBase
import io.getquill.{MysqlAsyncContext, SnakeCase}

import scala.concurrent.ExecutionContext

class Service(implicit val system: ActorSystem, val executor: ExecutionContext) extends ServiceBase {
  lazy val ctx: MysqlAsyncContext[SnakeCase] = new MysqlAsyncContext[SnakeCase]("db")

  val httpServer = new HttpServer(new FilmRepository(ctx))
}

package com.md.saklia

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import de.choffmeister.microserviceutils.http.Page
import de.choffmeister.microserviceutils.json._
import spray.json._

case class PageWrapper(page:Page)

trait JsonProtocol extends DefaultJsonProtocol
    with SprayJsonSupport
    with JsonHomeJsonProtocol
    with HalResourceJsonProtocol
    with UUIDJsonProtocol
    with InstantJsonProtocol {

  implicit val nameFormat = jsonFormat1(Name)
  implicit val greetingFormat = jsonFormat1(Greeting)
  implicit val filmFormat = jsonFormat6(Film)
  implicit val pageWrapperFormat = jsonFormat1(PageWrapper)
}

object JsonProtocol extends JsonProtocol

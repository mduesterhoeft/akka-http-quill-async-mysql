package com.md.saklia.data

import com.md.saklia
import de.choffmeister.microserviceutils.http.Page
import io.getquill._

import scala.concurrent.{ExecutionContext, Future}

class FilmRepository(ctx: MysqlAsyncContext[SnakeCase])(implicit  ec: ExecutionContext) {

  import ctx._

  def findByTitle(name: String): Future[List[Film]] = {
    val q = quote(query[Film].filter(_.title == lift(name)))
    run(q)
  }

  def findAll(page: Page): Future[List[saklia.Film]] = {
    val offset = page.length * (page.number + 1)
    val size = page.length
    val q = quote{
      for {
        f <- query[Film].drop(lift(offset)).take(lift(size))
        fc <- query[FilmCategory] if f.filmId == fc.filmId
        c <- query[Category] if fc.categoryId == c.categoryId
      } yield (f, c)
    }
    run(q).map(list => list.map{ case(f:Film, c:Category) => saklia.Film(
      id = f.filmId,
      title = f.title,
      description = f.description,
      releaseYear = f.releaseYear,
      rentalRate = f.rentalRate,
      category = c.name
    )})
  }
}

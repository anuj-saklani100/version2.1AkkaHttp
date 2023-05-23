package mainPackage.routesPack
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import mainPackage.allHandlers.ExistingFetch.existingUserFetch
import mainPackage.allHandlers.IdFetcher._
import mainPackage.Routes._


object GetRoute {
  def Getter(): server.Route = {
    get {
      parameter('id.as[Int]) {
        (a) => {
          val res1 = getUserById(a)
          complete(res1)
        }
      } ~
        parameter("username") {
          (a) =>
            get {
              val res1 = existingUserFetch(a)
              if (res1.isEmpty) {
                complete(StatusCodes.NotAcceptable)
              } else complete(res1)
            }
        }

    }
  }
}

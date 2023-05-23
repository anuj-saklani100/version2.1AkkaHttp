package mainPackage.routesPack
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import mainPackage.UserBase
import mainPackage.allHandlers.UserUpdaterr.updateUser
import mainPackage.Routes._
object PatchRoute {
  def Patcher(): server.Route = {
    patch {
      entity(as[UserBase]) {
        (a) => {
          parameter('id.as[Int]) {
            (b) => {
              val res1 = updateUser(b, a)
              if (res1) {
                complete(StatusCodes.OK)
              } else {
                complete(StatusCodes.NotImplemented)
              }
            }
          }
        }
      }
    }
  }
}

package mainPackage.RoutesPack

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import mainPackage.UserBase
import mainPackage.allHandlers.userAdder.addUser
import mainPackage.routes._
object postRoute {
def Poster():server.Route={
  post {
    entity(as[UserBase]) {
      (a) => {
        val res1 = addUser(a)
        if (res1 == None) complete(StatusCodes.NotImplemented)
        else complete(res1)
      }
    }
  }
}
}

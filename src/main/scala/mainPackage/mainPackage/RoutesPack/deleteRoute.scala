package mainPackage.RoutesPack

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import mainPackage.allHandlers.DelBy._
import mainPackage.routes._
object deleteRoute {
def Deleter():server.Route={
  delete{
    parameter('id.as[Int]){
      (a)=>{

        val res1=deleteById(a)
        if(res1){
          println("User deleted successfully")
          complete(StatusCodes.OK)
        }else{
          complete(StatusCodes.BadRequest)
        }
      }
    }
  }
}
}

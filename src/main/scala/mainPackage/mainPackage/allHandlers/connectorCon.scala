package mainPackage.allHandlers
import mainPackage.Credentials.{password, url, username}

import java.sql.{Connection, DriverManager}
object connectorCon {
  def getConnection(): Connection = {
    DriverManager.getConnection(url, username, password)
  }
}

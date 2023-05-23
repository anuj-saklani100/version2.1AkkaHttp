package mainpackage

import java.sql.{Connection, DriverManager}

object DatabaseConnection {
  val url = "jdbc:mysql://localhost:3306/megaBase"
  val username = "root"
  val password = "Tellius@12345"

  def getConnection(): Connection = {
    DriverManager.getConnection(url, username, password)
  }
}

package mainpackage.allhandlers

import mainpackage.DatabaseConnection.getConnection
import mainpackage.UserBase
import mainpackage.allhandlers.ResultParserRequestHandler.parseResultSet

object AllUserListerRequestHandler {
  def getAllUsers: List[UserBase] = {
    val connection = getConnection()
    val statement = connection.createStatement()
    val query = "SELECT * FROM Person"
    val resultSet = statement.executeQuery(query)
    val res_User = parseResultSet(resultSet)
    resultSet.close()
    statement.close()
    connection.close()
    res_User
  }
}

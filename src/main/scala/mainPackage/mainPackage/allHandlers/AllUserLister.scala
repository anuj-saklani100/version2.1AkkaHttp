package mainPackage.allHandlers

import mainPackage.UserBase
import mainPackage.allHandlers.ConnectorCon.getConnection
import mainPackage.allHandlers.ParserOfResult.parseResultSet

object AllUserLister {
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

package mainPackage.allHandlers

import mainPackage.UserBase
import mainPackage.allHandlers.connectorCon.getConnection
import mainPackage.allHandlers.parserOfResult.parseResultSet

object allUserLister {
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

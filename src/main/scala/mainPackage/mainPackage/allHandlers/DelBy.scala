package mainPackage.allHandlers

import mainPackage.allHandlers.ConnectorCon.getConnection
object DelBy {
  def deleteById(ids: Int): Boolean = {
    val connection = getConnection()
    val statement = connection.createStatement()
    val query = s"DELETE FROM Person WHERE id=?"
    val preparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, ids)
    val rowAffected = preparedStatement.executeUpdate()
    if (rowAffected > 0) {
      statement.close()
      connection.close()
      true
    } else {
      statement.close()
      connection.close()
      false
    }
  }
}

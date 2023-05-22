package mainPackage.allHandlers
import mainPackage.UserBase

import java.sql.ResultSet

object parserOfResult {
   def parseResultSet(resultSet: ResultSet): List[UserBase] = {

    def parseRow(resultSet: ResultSet): Option[UserBase] = {
      if (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val startTime = resultSet.getString("startTime")
        val create_at = resultSet.getString("create_at")
        val password = resultSet.getString("password")
        Some(UserBase(id, name, startTime, create_at, password))
      } else None
    }

    // recursive call to parseRow until it hits to the None
    Iterator.continually(parseRow(resultSet))
      .takeWhile(_.isDefined)
      .flatten
      .toList


  }
}

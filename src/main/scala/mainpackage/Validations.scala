package mainpackage

import scala.util.matching.Regex



trait ValidationTrait {
  // ------------------------- password validation mechanism --------------------
  def isValidate(pass: String):Boolean
}


object MinLength extends ValidationTrait{
  val minLength = 8
  override def isValidate(pass: String): Boolean = pass.length>=minLength
}

object HasDigitRegex extends ValidationTrait{
  val hasDigitRegex: Regex = ".*\\d.*".r
  override def isValidate(pass: String): Boolean = hasDigitRegex.findFirstMatchIn(pass).isDefined
}

object  HasCharRegex extends ValidationTrait{
  val hasCharRegex: Regex = ".*[a-zA-Z].*".r
  override def isValidate(pass: String): Boolean = hasCharRegex.findFirstMatchIn(pass).isDefined
}

object HasSpecialCharRegex extends ValidationTrait{
  val hasSpecialCharRegex: Regex = ".*[^a-zA-Z0-9].*".r
  override def isValidate(pass: String): Boolean = hasSpecialCharRegex.findFirstMatchIn(pass).isDefined
}
object Validations extends ValidationTrait{
 override  def isValidate(pass: String): Boolean = {
  val list:List[ValidationTrait]=List(MinLength,HasDigitRegex,HasCharRegex,HasSpecialCharRegex)
   list.map(validation=> validation.isValidate(pass)).forall(_ == true)
  }
}




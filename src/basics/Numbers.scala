package basics

object Numbers extends App{
  /*
  INTRO:
  In Scala, all the numeric types are objects including Bytes, Char, Double, Float, Int, Long, & Short
  All of these seven numeric types extend the AnyVal trait as do the Unit & Boolean class[Both Unit & Boolean are non numeric types]
   */

  /*
  PARSING A NUMBER FROM A STRING
  convert a String to one of the Scala's numeric types
   */
  val str = "100"
  val intValue = str.toInt
  println(intValue)
  val dobValue = str.toDouble
  println(dobValue)
  val longValue = str.toLong
  println(longValue)
  val shortVal = str.toShort
  println(shortVal)
  val byteVal = str.toByte
  println(byteVal)

  // BigDecimal and BigInt should be imported from scala.math package
  // Below conversion might will NumberFormatException
  val bigIntVal = BigInt(str)
  println(bigIntVal)
  val bigDecimalVal = BigDecimal(str)
  println(bigDecimalVal)

  // illegal activity to convert string to numerical value will result in NumberFormatException
  val str1 = "Scala"
  // println(str1.toInt)    // NumberFormatException

  /*
   NOTE: SCALA DOESNOT HAVE CHECKED EXCEPTIONS
   */
  // Using Option/Some/None to handle checked exceptions
  def toInt(s: String): Option[Int] = {
    try{
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }
  println(toInt("s"))
  // Another way to handle is using getOrElse()
  val st1 = "11000"
  val st2 = "Dancing Baby"
  val firstVal = toInt(st1).getOrElse(0)
  val secondVal = toInt(st2).getOrElse(0)
  println(s"The values using .getOrElse for $st1 = ${firstVal} and for $st2 = $secondVal")
  // Another approach is to use the match expression
  val x = toInt("Elvis") match {
    case Some(x) => println(x)
    case None => println("Bing Bang that's not a number")
  }

  /*
  CONVERTING BETWEEN NUMERIC TYPES (CASTING)
   */
  // 'to' methods are used to do the casting
  val a = 19.95
  println(a.toInt)
  println(a.toFloat)
  println(a.toLong)
  println(a.toByte)
  // validity of casting can be ensured using "isValid" method
  val b = 1000L
  println(b.isValidByte)
  println(s"checking if $b ${b.getClass.getName} is valid int with isValidInt: ${b.isValidInt}")
  println(s"checking if $b ${b.getClass.getName} is valid char with isValidChar: ${b.isValidChar}")

  /*
  OVERRIDING THE DEFAULT NUMERIC TYPE
  because of Type Inference Scala automatically assigns types to numeric values when we assign them
   */
  // says to override simple numeric types
  val c = 1d
  println(1)
  val d = 1f
  println(d)
  val e = 100L
  println(e)
  // another approach
  val f: Byte = 0
  println(f)
  val g: Int = 100
  println(g)
  val h: Short = 32453
  println(h)
  // HEX VALUES
  val i = 0x20
  println(i)
  val j = 0x20L
  println(j)
  // type ascription is basically type upcasting
  val k: String = "Bose"
  println(s"The data type of $k is ${k.getClass.getName}")
  val upCastK = k : Object
  println(s"The data type of upcasted $k is ${upCastK.getClass.getName}")

  /*
  REPLACEMENTS FOR ++ AND --
  we want to increment or decrement numbers using ++/-- operators but Scala doesn't have these operators
   */
  // only var is mutable not val use +=, -= , *= , /= instead of ++ or --
  var l = 20
  l += 1
  println(l)
  l -= 1
  println(l)
  l /= 5
  println(l)
  l *= 10
  println(l)

  /*
  COMPARING FLOATING-POINT NUMBERS
   */
  def ~=(x: Double, y: Double, precision: Double) = {
    if((x - y).abs < precision) true else false
  }
  val firstDob = 0.5f
  val secondDob = 0.3f + 0.2f
  println(~=(firstDob, secondDob, 0.0001))
  val y = 0.3
  println(y)
  val z = 0.2d + 0.1d
  println(z)
  println(y == z)
  // calling MathUtils ~= method
  println(MathUtils.~=(firstDob, secondDob, 0.001))

  /*
  HANDLING VERY LARGE NUMBERS
  if we need a very large integer or decimal numbers
   */
  // use scala BigInt or BigDecimal classes
}

object MathUtils {
  def ~=(x: Double, y: Double, precision: Double) = {
    if((x-y).abs < precision) true else false
  }
}

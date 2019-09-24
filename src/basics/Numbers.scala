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
  var largeNum1 = BigInt(1878945698)
  println(s"The number $largeNum1 is of type ${largeNum1.getClass.getName}")
  println(largeNum1.isValidInt)
  println(if(largeNum1.isValidLong) largeNum1.toLong)
  var largeDecimal1: BigDecimal = 123456.789
  println(s"The number $largeDecimal1 is of type ${largeDecimal1.getClass.getName}")
  println(largeDecimal1 + largeDecimal1)
  println(largeDecimal1.isValidInt)
  println(largeDecimal1.toDouble)
  println(if(largeDecimal1.isValidInt) largeDecimal1.toInt else largeDecimal1.toLong)
  // maximum value check
  println(s"The min value of Byte is ${Byte.MinValue} & the max value is ${Byte.MaxValue}")
  println(s"The min value of Short is ${Short.MinValue} & the max value is ${Short.MaxValue}")
  println(s"The min value of Int is ${Int.MinValue} & the max value is ${Int.MaxValue}")
  println(s"The min value of Long is ${Long.MinValue} & the max value is ${Long.MaxValue}")
  println(s"The min value of Float is ${Float.MinValue} & the max value is ${Float.MaxValue}")
  println(s"The min value of Double is ${Double.MinValue} & the max value is ${Double.MaxValue}")
  // using +ve infinity and -ve infinity
  println(s"${Double.NegativeInfinity}")
  println(s"${Double.PositiveInfinity}")
  println(Double.MaxValue > Double.PositiveInfinity)

  /*
  GENERATING RANDOM NUMBERS
  creating random numbers, such as when testing an application, performing a simulation, and many situations
   */
  // generating random numbers with "scala.util.Random" class
  val rand1 = scala.util.Random
  println(rand1.getClass.getName)
  println(rand1.nextInt)
  // limiting the random numbers to a maximum value
  println(rand1.nextInt(100))         // returns number between (0 inclusive and 100 exclusive] so 0-99
  // creating random float numbers
  println(rand1.nextFloat())          // generates random float number between 0.0 and 1.0
  // creating random double numbers
  println(rand1.nextDouble())         // same value as above
  // we can seed value using and Int or Long while creating Random object
  val ri = new scala.util.Random(100)    // (0 - 100]
  println(ri.nextInt())
  // seeding Random object after it is created
  ri.setSeed(1000L)
  println(ri.nextInt())
  // creating Random characters
  val randChar = new scala.util.Random()
  println(randChar.nextPrintableChar())
  // creating a random-length range of numbers which is useful for testing
  // create a random length range
  val range1 = 0 to ri.nextInt(10)
  range1.foreach(println)
  val range2 = 0 to ri.nextInt(10)
  range2.foreach(print)

  /*
  CREATING A RANGE, LIST, OR ARRAY OF NUMBERS
   */
  // creating a Range with "to" method of the Int class elements
  val rInt1 = 1 to 10
  println(rInt1.getClass.getName)
  rInt1.foreach(print)
  println()
  // using "by" method we can create a step
  val rInt2 = 1 to 10 by 2
  rInt2.foreach(print)
  println()
  // ranges are commonly used in for loops
  for(x <- 0 to 5) println(x)         // to and until method is using ".to" and ".until" in the background
  // we can also use "until" method instead of "to"
  for(x <-50 until 60 by 2) if(x % 5 == 0) println(x)
  val rangeToVector = for(i <- 2 to 5) yield i*i
  println(rangeToVector.getClass.getName)
  rangeToVector.foreach(println)
  // Range can be easily converted to other data types like array or lists
  val rangeToArray = (98 to 100).toArray
  rangeToArray.foreach(println)
  val rangeToList = (3 to 5).toList
  rangeToList.foreach(println)
  // range of Random numbers
  val randRange = 100 to scala.util.Random.nextInt(105)

  /*
  FORMATTING NUMBERS AND CURRENCY
   */
  // basic number formatting
  val PI = scala.math.Pi
  println(s"Default value of PI: $PI")
  println(f"After formatting value of PI: $PI%1.5f")
  println(f"Formatting PI value to three significant digits: $PI%1.2f")
  println(f"Ensuring data is properly formatted to 5 digits: $PI%07.3f")
  // getting commas in your numbers when Int
  val formatter1 = java.text.NumberFormat.getIntegerInstance()
  println(formatter1.format(100000000))
  // getting commas in your Double numbers
  val formatter2 = java.text.NumberFormat.getInstance()
  println(formatter2.format(10000000.366))
  // handling currency output
  val formatter3 = java.text.NumberFormat.getCurrencyInstance
  println(formatter3.format(123123122.8953))

}

object MathUtils {
  def ~=(x: Double, y: Double, precision: Double) = {
    if((x-y).abs < precision) true else false
  }
}

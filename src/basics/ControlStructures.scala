package basics

import scala.annotation.switch

object ControlStructures extends App {
  /*
  LOOPING WITH 'FOR' AND 'FOREACH'
  to iterate over the elements in a collection, either to operate on each element in the collection,
  or to create a new collection from the existing collection
   */
  // there are many ways to loop ever scala collections, including for loops, while loops and collection,
  // methods like foreach, map, flatMap, and more
  val arr1 = Array("silver","bronze","gold","platinum")
  for(x <- arr1) println(x)
  // changing into uppercase
  for(x <- arr1) {
    val s = x.toUpperCase
    println(s)
  } // doesn't return array
  // returning values from a for loop giving a array back
  // for yield returns value, so in this case, the array newArray contains uppercase version in the initial array
  val newArr1 = for(x <- arr1) yield x.toUpperCase
  println(newArr1.getClass.getName)
  // returning new array with multiple line code
  val newArr2 = for(x <- arr1) yield {
    val y = x.substring(0,3)
    val z = y.toUpperCase
    z   // z is of type Array[java.lang.String]
  }
  for(x <- newArr2) println(x)
  // to access a counter inside for loop
  for(c <- 0 until arr1.length) {
    println(s"$c is ${arr1(c)}")
  }
  // zipWithIndex method can be used to created a loop counter
  val daysArray = Array("sunday","monday","tuesday","wednesday","thursday","friday","saturday")
  for((x, count) <- daysArray.zipWithIndex) {
    println(s"element at index $count is $x")
    // generators and guards
    // range to execute a loop three times, unlike Range below is a Generator
  }
  for (i <- 5 to 10) println(i)   // scala.collection.immutable.Range.inclusive
  // using guards
  for (x <- 1 to 5 if x < 3) println(x)
  // looping over maps
  val states = Map("Texas" -> "TX", "California" -> "CA", "Arkansas" -> "AR")
  for((k,v) <- states) println(s"key: $k, value: $v")
  // foreach will not generate collection
  val res1 = daysArray.foreach(x => x.toUpperCase)
  println(res1.getClass.getName)


  /*
  USING FOR LOOPS WITH MULTIPLE COUNTERS
  creating loop with multiple counters, such as weh iterating over a multidimensional array
   */
  for(x <- 1 to 4; y <- 1 to 2) println(s"x = $x, y = $y")
  // three counters with multiline for loop
  for {
    x <- 1 to 4
    y <- 1 to 3
    z <- 1 to 2
  } println(s"x = $x, y = $y, z = $z")
  // multidimensional array
  val multiDimArray = Array.ofDim[Int](2,2)
  multiDimArray(0)(0) = 1
  multiDimArray(0)(1) = 2
  multiDimArray(1)(0) = 3
  multiDimArray(1)(1) = 4
  // iterating over our 2D array
  for {
    // Ranges created with <- symbol in for loops are referred as generators and multiple generators are allowed in one loop
    x <- 0 to 1
    y <- 0 to 1
  } println(s"($x)($y) = ${multiDimArray(x)(y)}")

  /*
  USING A 'FOR' LOOP WITH EMBEDDED 'IF' STATEMENT
  add one or more conditions to a for loop to filter out some elements in a collection while working on the others
   */
  // adding if statement after generator "if" are referred as filters, filter expressions or guards
  for(x <- 1 to 10 if x % 2 == 0) println(x)  // printing all even numbers
  // multiple guards
  for{
    x <- 1 to 10
    if x > 3
    if x < 9
    if x % 2 != 0
  } println(x)        // printing all odd numbers between 3 and 9


  /*
  CREATING A FOR COMPREHENSION (FOR/YIELD COMBINATION)
  creating new collection by applying an algorithm to each element in the original collection
   */
  // for/yield OR for comprehension will create a collection unlike foreach or for
  val orgColl1 = Array("january","february", "March")
  val capNamesColl1 = for(x <- orgColl1) yield x.toUpperCase()
  // multiline algo for old collection to work on and build new collection
  val southStates = Array("virginia", "  texas", "arkansas", "florida", "louisiana", " north carolina ", "south carolina ")
  val modifiedSouthStates = for (x <- southStates) yield {
    val a = x.trim
    val b = a.toUpperCase()
    b
  }
  for (x <- modifiedSouthStates) println(x)
  // for/yield expression without a guard is like calling map method on collection
  val out1 = for(x <- orgColl1) yield x.substring(0,3).toUpperCase
  for(y <- out1) println(y)
  val out2 = out1.map(_.substring(0,2).toUpperCase)
  for(z <- out2) println(z)
  // val absValue = if(x < 0) -x else x   // scala does not have ternary operator like java use if/else expression


  /*
  USING A MATCH EXPRESSION LIKE A SWITCH STATEMENT
  use case of Int maps to a result
   */
  // Scala has 'match' expression like Java 'switch' statement
  val num1 = 22
  (num1: @switch) match {
    case 20 => println("Can't consume alcoholic beverages.")
    case 21 => println("barely legal")
    case 22 => println("You're ready now")
    case _  => println("Please verify")
  }
  // more functional approach
  val d:Int = 6
  // annotation provides a waring if the expression cannot be compiled to a tableswitch or lookupswitch
  val day = (d: @switch) match {   // ensures branch table is used rather than decision tree
    case 1 => "Sunday"
    case 2 => "Monday"
    case 3 => "Tuesday"
    case 4 => "Wednesday"
    case 5 => "Thursday"
    case 6 => "Friday"
    case 7 => "Saturday"
    case _ => "Are you nuts!"
  }
  println(day)
  // match error is generated if default is not handled
  def getClassAsString(x: Any): String = x match {
    case s: String => s + " is a String"
    case i: Int => "Int"
    case f: Float => "Float"
    case l: List[_] => "List"
    case _ => "Unknown"
  }
  // switch statement can be replaced by the Map function
  val daysNumberToName = Map(
    1 -> "Sunday",
    2 -> "Monday",
    3 -> "Tuesday",
    4 -> "Wednesday",
    5 -> "Thursday",
    6 -> "Friday",
    7 -> "Saturday"
  )
  val dayName = daysNumberToName(2)
  println(dayName)


}
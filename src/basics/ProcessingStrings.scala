package basics

object ProcessingStrings extends App {

  // PROCESSING A STRING ONE CHARACTER AT A TIME
  val str1 = "The pessimist sees difficulty in every opportunity, the optimist sees opportunity in every difficulty"
  val pStr1 = str1.map(x => x.toUpper)
  println(pStr1)
  // magic of underscore _
  println(str1.toLowerCase())
  // chaining collection methods to filter and convert characters to uppercase
  val str2 = "don't lets yesterday takes up too much of todays"
  println(str2.filter(_ != 's').map(_.toUpper))
  // using foreach for same result
  val result = for {
    x <- str2
    if x != 's'
  } yield x.toUpper
  println(result)
  // map or for/yield approaches are used to transform one collection into another, the foreach method is typically
  // used to operate on each element without returning a result
  println("Sairam".map(c => (c.toByte+32).toChar))
  println("sairam".map {      // here map method is called on a String, and map treats a String as a sequential collection
    c =>                      // of Char elements. Map method has an implicit loop and it passes on Char at a time to the algorithm
      (c.toByte+32).toChar
  })
  // operating map on defined function
  def up(c: Char): Char = (c.toUpper).toChar
  println("Sol".map(up))

  // FINDING PATTERNS IN STRING
  val numPattern = "[0-9]+".r
  val addr = "701 Jacksonville Florida, 92715"
  // findFirstIn finds the first match
  val match1 = numPattern.findFirstIn(addr)  // returns Option[String]
  println(match1)
  // handling Option[String], method defined to return an Option[String] will either return a Some(String) or a None
  // Option is a container that holds 0 or 1 values, in success returns Some() if fails returns None()
  // for multiple matches use findAllIn
  val match2 = numPattern.findAllIn(addr)   // returns Iterator if nothing is found returns an empty iterator
  match2.foreach(println)
  // .getOrElse is used to specify a default value or what to return if failed
  val res = numPattern.findFirstIn(addr).getOrElse("No Match Found!")
  // we can use match expression to make it more readable
  match1 match {
    case Some(s) => println(s"Found: $s")
    case None => println("No match found ")
  }

  // REPLACING PATTERNS IN STRINGS
  /*
  search for regex patterns in a string and replace them
   */
  // to replace all match
  val addr1 = "123 Main Street, SSN: 10101010".replaceAll("[0-9]","x")
  println(addr1)
  // to replace the first match
  val addr2 = "123 Main Street, 95405".replaceFirst("[0-9]", "#")
  println(addr2)

  // EXTRACTING PARTS OF A STRING THAT MATCH PATTERNS
  /*
  extract one or more parts of a string that match the regex pattern
   */
  // regex groups can be made using parens
  val patt1 = "([0-9]+) ([A-Za-z]+)".r
  val patt1(zip_code, state) = "76145 AR"
  println(zip_code, state )
  // movie search
  // match "movies 80301"
  val MoviesZipRE = "movies (\\d{5})".r
  // match "movies near city, state"
  val MoviesNearCityStateRE = "movies near ([a-zA-Z]+), ([a-zA-Z]{2})".r

  // ACCESSING A CHARACTER IN A STRING
  /*
  get a specific char at specific position in a string
   */
  // we can use java chatAt but more preferred way is to use Scala's Array notation
  val gm = "Good Morning"
  println(gm.charAt(1))
  println(gm(1))    // more scala way, behind the scene it is using gm.apply(1) the helper function Syntactic Sugar`

}

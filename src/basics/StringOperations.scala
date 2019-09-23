package basics

object StringOperations extends App {

  // TESTING STRING EQUALITY
  val str1 = "Hello, world!"
  println(str1)
  println(str1.getClass())
  // Scala String is a Java String, so we can use all the normal Java string methods
  println(str1.getClass().getName())
  println(str1.length())
  println("Hello"+ " world!")

  // IMPLICIT CONVERSION OF String as StringOps which extends StringLike
  // Scala offers the magic of implicit conversions, String instances also have access to all the methods of the StringOps class
  // StringOps class lets us treat String instance as a sequence of characters
  val str2 = "SCALA"
  str2.foreach(println)
  // can treat a String of a sequence of characters in a for loop
  for (c <- str2) println(c)
  // we can also treat String as sequence of bytes
  str2.getBytes.foreach(println)
  // since many methods are available on sequential collections, we can use functional methods on it
  val str3 = "Pick an award! you are winner!"
  val result = str3.filter(_ != '!')
  println(result)
  // Scala String have both String and Collection features
  val str4 = "pentatonic"
  println(str4.drop(5).take(5).capitalize) // "drop" and "take" are from collection whereas "capitalize" is from StringOps
  // Testing String Equality
  val s1 = "Pneumonoultramicroscopicsilicovolcanoconiosis"
  val s2 = "Pneumonoultramicroscopicsilicovolcanoconiosis"
  val s3 = "Pneumonoultramicroscopic" + "silicovolcanoconiosis"
  /*
   == method defined in the AnyRef class first checks for null values and then calls the equals method
   on the first object i.e.,this. So we dont have to check for null values while comparing strings
   */
  println(s1 == s2)
  println(s1 == s3)
  // benefit of == method is that it doesn't throw a NullPointerException on a basic test if a String is null
  val s4: String = null   // never use null values instead opt in for Option instead
  println(s3 == s4)  // it will not through NullPointerException thanks to == method
  /* point to be noted is that calling method on a 'null' string can throw a NullPointerException
  println(s4.toUpperCase)      // throws java.lang.NullPointerException since we called method on a 'null'
   */
  // comparing two string in a case-insensitive manner can be achieved using either uppercase or lowercase
  val st1: String = "Pentatonic"
  val st2: String = "pentaTonic"
  println(st1.toUpperCase == st2.toUpperCase)
  println(st2.toLowerCase == st1.toLowerCase)
  // we can also fall back and use equalIgnoreCase of the Java String class to compare strings ignoring cases
  println(st1.equalsIgnoreCase(str2))

  // CREATING MULTILINE STRINGS

  val multiSt1 =
    """My new
      |multiline String
      |is now initiated
      |""".stripMargin
  println(multiSt1)
  // multiline strings are created with three double quotes
  // stripMargin with pipe symbol at first lines will give cleaner output
  // if we want to avoid pipe symbol we can mention delimiter in stripMargin
  val multiSt2 = """A quick brown fox
                    #jumps over the
                    #lazy dog """.stripMargin('#')
  println(multiSt2)
  // we can also convert the multiline string into a single line using replaceAll and passing delimiters
  // ensure we use "\n" in Linux "\r" mac and "\r\n" in windows
  val rhymes = """
                 %The wheels on the bus goes
                 %round and round
                 %round and round""".stripMargin('%').replaceAll("\r\n", "")

  println(rhymes)

  // SPLITTING STRINGS
  // split method splits based on regex pattern provided and spits back the array of Strings
  val csvStr1 = "Rain Rain Go Away!"
  // returns an array of String elements which can be treated as a normal Scala Array
  val splittedArray = csvStr1.split(" ")
  splittedArray.foreach(println)
  val gStr = "eggs, milk, butter, Coco Puffs "
  val arraygStr= gStr.split(",")    // first task is to split the elements into array of strings
  val cleangStr = arraygStr.map(_.trim)   // map to call trim on each string in the array
  cleangStr.foreach(println)
  // split with a String args results in output as Array[java.lang.String]
  // split with char args results in output as Array[String]
  // one liner approach and using multiple delimiters
  val myRhy = "Ba Ba Black Sheep, Have you any Wool?, Yes Sir Yes Sir, Three bags full"
  myRhy.split("(,|\\W+)").map(_.trim).foreach(println)

  // SUBSTITUTING VARIABLES INTO STRING ['s' WHICH IS A METHOD]
  // string interpolation in scala with the letter s preceded by $ character
  val name: String  = "Harry"
  val age: Int = 20
  val weight: Double = 200.00
  println(s"$name is $age years old, and weighs $weight pounds.")
  // any arbitrary expression can be embedded in ${}
  println(s"${name+" Porter"} is ${age+5} years old, and weighs ${weight-40} pounds.")
  // we can also use expressions inside the curly braces
  println(s"${name} can grab a beer: ${age == 21}")
  // we need to use curly braces when printing object fields
  case class Employee(name: String, performance_score: Double)  // defining case class
  val emp1 = Employee("Gengis", 10.00)
  println(s"${emp1.name} has topped the company with performance score of ${emp1.performance_score}")

  // F STRING INTERPOLATOR
  // lets you use printf style formatting specifiers inside strings
  println(f"$name is $age years old, and weighs $weight%.2f pounds.")
  println(f"$name is $age years old, and weighs $weight%.0f pounds.")

  // THE RAW INTERPOLATOR
  // 'raw' interpolator performs no escaping of literals within the string.
  val rawStr1 = raw"This string is \n raw string with raw"
  println(rawStr1)
  // NOTE: Scala s,f,raw interpolators does not work with pattern matching

}

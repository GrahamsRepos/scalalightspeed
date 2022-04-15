package com.rockthejvm

object FunctionalProgramming extends App{

  // Scala is OO
  class Person(name: String){
    def apply(age:Int): Unit = println(s"I have aged $age years")
  }
  val bob = new Person("bob")
  bob.apply(43)
  bob(43) // same as calling the apply method
  // Scala creates functions from a function trait - FunctionX[arg,return]
  val simpleIncrementer = new Function1[Int, Int]{
    override def apply(arg:Int):Int = arg + 1
  }
  // can be invoked like a function because it has an apply method
  simpleIncrementer(23)
  val stringConcatenator = new Function2[String,String, String]{
    override def apply(arg:String, arg2:String):String = arg + arg
  }
  stringConcatenator("a","b")

  // syntax sugars
  val doubler: Function1[Int,Int] = (x:Int) => 2 * x
  /* equivalent to
  new Function1[Int, Int] {
    override def apply(x:Int) = 2 * x
  }
   */
  val doubler2: Int => Int = (x:Int) => 2 * x
  // You can ommit the funtion type altogether ... compiler will infer the type
  var doubler3 = (x:Int) => 2 * x

  // Higher order functions
  val aMappedList = List(1,2,3).map((x)=>x+1)
  println(aMappedList)
  // flatmap generates a new list as a concatenation of the mapped lists
  val aFlatMappedList = List(1,2,3).flatMap((x)=>List(x, 2 * x))
  println(aFlatMappedList) // List(1,2,2,4,3,6)
  //Filtered lists
  val aFilteredList = List(1,2,3,4,5).filter(x=>x > 2)
  // Shorter filter syntax
  val aFilteredList2 = List(1,2,3,4,5).filter(_>2) // _ <=2 is equivalent to  x => x <= 2
  // Chaining map ,flatmap , map and filter
  val myList:List[Int] = List(1,2,3,4,5).filter(_>=1).map(_*2).flatMap(x=>List(x, x*2))
  val allPairs = List(1,2,3).flatMap(number => List("a","b","c").map(letter=>s"$number - $letter"))
  println(allPairs)
  // for comprehensions
  val alternativePairs = for {
      number <- List(1,2,3)
      letter <- List('a','b','c')
  } yield s"$number-$letter" // equivalent to the map flatmap chain above

  // Collections

  // lists
  val aList = List(1,2,3,4)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // List(0,1,2,3,4)
  val anExtendedList = 0 +: aList :+ 5 // List(0,1,2,3,4,5)
  println(aPrependedList)
  println(anExtendedList)

  // sequences
  val aSequence: Seq[Int] = Seq(1,2,3)
  val accessedElement = aSequence(0) // returns Int(1)

  //vectors - fast access sequence
  val aVector = Vector(1,2,3)
  val accessedVectorElement = aVector(0) // returns Int(1)

  // sets - A list with no duplicates
  val aSet = Set(1,2,3,4,1,2,3) // returns Set(1,2,3,4)
  val setHas5 = aSet.contains(5)// Check if set has value
  val aListWithDuplicates = List(1,2,3,4,5,1,2,3,4)
  val listNoDuplicates = aListWithDuplicates.toSet.toList.sorted
  val addedSet = aSet + 6 // Set(1,2,3,4,5,6)

  // Ranges -
  val aRange = Range.inclusive(1, 1000)
  val aRange2 = 1 to 1000
  val twoByTwo = aRange.map(x => 2 * x).toList // List(2,4,6,8,....2000)

  // Tuples = groups of values under the same value
  val aTuple = ("Bon Jovi","Rock",1982)
  val aTuple2 = "Bon Jovi" -> 1982 // two member tuple

  // maps - key , value
  val aPhoneBook: Map[String, Int] = Map(
    ("Graham",12345678),
    "SomeOneElse" -> 12345897
  )



}

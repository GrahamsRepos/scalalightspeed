package com.rockthejvm

object PatternMatching extends App{
  // switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger+"th" // default case
  }
  println(order)

  // Deconstructing objects in case classes
  case class Person(name:String,age:Int)
  val graham = Person("Graham",32)
  val personGreeting = graham match {
    case Person(name, age) => s"Hello $name"
  }
  println(personGreeting)

  // Deconstructing Tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match{
    case (a, b) => s"$a is a $b band"
  }
  println(bandDescription)

  var matchTest = (myTuple:Any) => {
    myTuple match{
      case (name:String, genre:String, year:Int) => s"$name is a $genre band started in $year"
      case (name:String, genre:String) => s"$name is a $genre band, and we have no idea when they stared"
    }
  }
  println(matchTest(("Bon Jovi","Rock")))
  println(matchTest(("Bon Jovi","Rock", 2020)))

  // Deconstructing Lists in pattern match
  val aList = List(1,2,3)
  val listDescription = aList match {
    case  List(_,2,_) => "List containing 2 at the second position"
  }

  var matchByType = (argument:Any) =>{
    argument match {
      case List(_:String,_:Int,_:String) => "Matched a list with string, integer, string"
      case List(_:Int,_:Int,_:Int) => "Matched a list with three integer members"
      case _ => "Not matching anything"
    }
  }
  println(matchByType(List("abc",3,"cde")))
  println(matchByType(List(1,2,3)))

}

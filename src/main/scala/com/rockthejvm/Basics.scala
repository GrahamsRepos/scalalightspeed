package com.rockthejvm

//Extending the object using the App trait makes it executable
object Basics extends App {
// defining a value
  val meaningOfLife: Int = 42 // This is the same as a const
  val aBoolean = false // Scala can infer types
  val aString = "I love Scala"
  val aComposedString = "I" + " "+ "love" + " " + "scala"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

}

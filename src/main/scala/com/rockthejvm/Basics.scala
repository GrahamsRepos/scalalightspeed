package com.rockthejvm

//Extending the object using the App trait makes it executable
object Basics extends App {
// defining a value
  val meaningOfLife: Int = 42 // This is the same as a const
  val aBoolean = false // Scala can infer types
  val aString = "I love Scala"
  val aComposedString = "I" + " "+ "love" + " " + "scala"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"
  val ifExpression = if (meaningOfLife > 43) 56 else 999
  val chainedIfExpression =
    if (meaningOfLife > 43) 56
    else if (meaningOfLife  < 0 ) -2
    else if(meaningOfLife > 999) 78
    else 0
  val aCodeBlock = {
    val aLocalValue = 67
    aLocalValue + 3 // This is returned
  }
  def myFunction(x: Int, y: String): String = {
    x+" "+y
  }
  def factorial(n: Int):Int = {
    if(n < 1) n
    else n * factorial(n - 1)
  }
  def forRecursive(current: Int, end:Int): Unit={
    if(current <= end){
      println(current)
      forRecursive(current + 1, end)
    }
  }
  forRecursive(0, 20)
}

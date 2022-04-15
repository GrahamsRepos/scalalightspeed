package com.rockthejvm

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

object Advanced extends App{

  // lazy evaluation
  lazy val aLazyValue = 2
  val valueWithSideEffect = {
    println("I am not lazy") // side effect
    43
  } // <--- the line is printed because value is evaluated at runtime
  // vs.
  lazy val lazyValueWithSideEffect = {
    println("I am very lazy") // side effect - not executed until the first use
    43
  }
  println("This is printed")
  lazyValueWithSideEffect.toString // Only now the lazyVal is used

  // pseudo-collections: Option , Try
  def methodWhichCanReturnNull():String = if (Math.random() > 0.5) "Hello, scala" else null
  val range = 1 to 50
  range.foreach(_=>{
    if (methodWhichCanReturnNull()== null){
      println("was null")
      // some defensive code in case the method returns null
    } else {
      println("not null")
    }
    // using the option type
    val anOption = Option(methodWhichCanReturnNull()) // this returns Some(value) or None if the value is null
  })
  range.foreach(_=> {
    Option(methodWhichCanReturnNull()) match {
      case Some(number) => println("some number was returned")
      case None => println("a null was returned")
    }
  })

    // Try pseudo collection - guards against exceptions
    def aMethodThatCanThrow():String = if (Math.random() > 0.5) "Hello, scala" else throw new Exception
    // normal try catch
    range.foreach(_=>{
      try{
        aMethodThatCanThrow()
        println("No exception throws")
      }catch{
        case e:Exception => println("Exception thrown")
      }
    })
    // The Try collection
    range.foreach(_=>{
      Try(aMethodThatCanThrow()) match{
        case Success(value) => "No exception thrown"
        case Failure(exception) => "Exception was thrown"
      }
    })

    // Asynchronous programming
    // Future pseudo collection
    val aFuture = Future{
      println("loading....")
      Thread.sleep(1000) // future thread
      println("I have computed a value")
      67
    }
    Thread.sleep(500) // main thread - if Thread.sleep(500) then main thread will terminate before furture is finished
    // Future is a collection that contains a value once it's evaluated
    // a future is composable with map , flatmap and filter
    val waitingForFuture:(Future[Any])=>Unit = (future:Future[Any]) => {
      Thread.sleep(100)
      future.value match {
        case Some(value) => println(s"The future has resolved to $value")
        case _ => {
          Thread.sleep(100)
          println("Waiting for the future to resolve to a value")
          waitingForFuture(future)
        }
      }
    }
    waitingForFuture(aFuture)

  // Implicits basics
  // # 1. implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplicitInt = 46
  println(aMethodWithImplicitArgs) // You now don't have to pass a value , the compiler tries to find an implicit val of the type in the context (object)
  // 2. implicit conversions
  implicit class MyRichInteger(n: Int){
    def isEven() = n % 2 == 0
  }
  println(42.isEven()) // Compiler now looks for an implicit class with the method isEven given an int
  // The compiler basically figures out that this has to be new MyRichInteger(42).isEven()
}

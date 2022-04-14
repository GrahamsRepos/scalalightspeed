package com.rockthejvm

object ObjectOrientation extends App {
  class Animal {
    val age: Int = 0

    def eat() = println("I am eating")
  }

  val aAnimal = new Animal

  class Dog(val name: String) extends Animal {
  }

  val aDog = new Dog("Lassie")
  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat()

  // abstract class
  abstract class WalkingAnimal {
    protected val hasLegs = true

    def walk(): Unit
  }

  // interface equivalent
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit = println(s"I was thinking $thought") // ?! is a valid method name in scala
  }

  // single class multi trait inheritance
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal) = println("I am eating you animal")
  }

  val aCroc = new Crocodile;
  aCroc.eat(aDog)
  // Methods with 1x argument can be called using infix notation -> object method argument
  aCroc eat aDog
  aCroc ?! "What if we could fly?"
  // Operstors in scala are actually methods
  val basicMath = 1 + 2 // The + is a method belonging to the type Int
  val anotherBasicMath = 1.+(2) // Is equivalent to new Int(1).+(2)
  val anotherBasicMath2 = new Int().+(2)

  // Anonymous class for implementing traits on the spot
  class dinosaur extends Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat anything")
  }

  // Can be implemented like
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat anything")
  }

  object MySingleton {
    val mySpecialValue = 123

    def mySpecialMethod(): Int = 20

    def apply(x: Int): Int = x + 1
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  // The apply method can be called directly like so
  MySingleton(65) // equivalent to calling MySingleton.apply(..)

  object Animal{ // companion object
    //companions can access each others private methods and fields
    // singleton Animal and Instances of Animal are different things
  val canLiveIndefinitely = false
  }
  val animalsCanLiveForever = Animal.canLiveIndefinitely // static field

  /* case classes -> lightweight  with some boilerplate
    - sensible equals and hash code
    - serialization
    - companion with apply
   */
  case class Person(name:String, age: Int)
  // may be constructed without the new keyword
  val bob = Person("Bob",54)  // equivalent to Person.apply("Bob",54)

  // exceptions
  try{
    // code that can throw
    val x: String = null
    x.length // throws error
  }catch{
    case e: Exception => "Some faulty error message" // cases can match different exception types
  } finally{
    // I execute some code no matter what
  }
  // Generics - works for classes , abstract classes traits
  abstract class MyList[T]{
    def head: T
    def tail : MyList[T]
  }
  val aList: List[Int] = List(1,2,3) // is the same as List.apply(1,2,3)
  val first = aList.head // Infers type as Int from the generic
  val rest = aList.tail // Infers type tail as List[Int]
  val aStringList: List[String] = List("a","b","c")
  val fistString = aStringList.head // Infers type as String
  val restOfStrings = aList.tail // Infers type as List[String]
  // Immutability
  val myList: List[String] = List("a","b","c")
  val reversedList = myList.reverse // New copy of the object
}


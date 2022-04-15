## Part 2 - Basics
- [Code](src/main/scala/com/rockthejvm/Basics.scala)
- An app trait can be used to quickly turn objects into executable programs 
- defining a value (same as const => no reassignment) <br/>
```scala
val meaningOfLife: Int = 42
```
- defining a variable
```scala
var meaningOfLife: Int = 20
```
- Types can be inferred
```scala
val aBoolean = true
```
- Standard scala types
    * Int
    * Boolean
    * Char
    * Double
    * Float
    * String
- strings can be composed using the + operator
```scala
val aComposedString = "I" + " " + "love" + " " +"Scala"
```
- Interpolated strings
```scala
val meaningOfLife: Int = 42
val interpolatedString = s"The meaning of life is $meaningOfLife"
```
- Expressions -> structures that can be reduced to a value
```scala
val anExpression = 2 + 3
```
- Scala is based on composition not instructions , everything resolves to a value
- Even if statements in scala resolve to a value (example similar to ternary operator)
```scala
val ifExpression = if (meaningOfLife > 43) 56 else 999 // Same as meaningOfLife > 43 ? 56 : 999 in JS
```
- If expressions can be chained (Note we are not executing code based on a condition , we are resolving to a value based on condition)
```scala
  val chainedIfExpression = 
    if (meaningOfLife > 43) 56
    else if (meaningOfLife  < 0 ) -2
    else if(meaningOfLife > 999) 78
    else 0
```
- There are also code block expressions defined between {} tags
 * Inside a code block you can add "local definitions"
 * The value of the last expression in a code block is what's returned
 * The compiler can infer the type of the code-block
```scala
  val aCodeBlock = {
    val aLocalValue = 67
    aLocalValue + 3 // This is returned
  }
```
- Functions , are defined by the def keyword: 
```scala
  def myFunction(x: Int, y: String): String = {
    x+" "+y
  }
```
- Recursive scala functions can depend on their own type
```scala
  def factorial(n: Int):Int = {
    if(n < 1) n
    else n * factorial(n - 1)
  }
// factorial(5) -> 5 * factorial(4) -> onto stack
// factorial(4) -> 4 * factorial(3) -> onto stack
// factorial(3) -> 3 * factorial(2) -> onto stack
// factorial(2) -> 2 * factorial(1) -> onto stack
// factorial(1) -> 1 -> end of stack
```
- Loops are discouraged in scala, recursion is preferred 
```scala
  def forRecursive(current: Int, end:Int): Unit={
  if(current <= end){
    println(current)
    forRecursive(current + 1, end)
  }
}
```
- In scala the Unit type = "no meaningful value" and is equivalent to void
- The unit type is the type for any "side effects"
```scala
println("some value") // The return type of this is "Unit"
```

## Part 3 - Object orientated programming
- [Code](src/main/scala/com/rockthejvm/ObjectOrientation.scala)
- Scala classes allow you to create an instance in memory using the new keyword
```scala
class Animal
val aAnimal = new Animal
```
- Classes can also use inheritance like with other languages, the subclass inherits vals and methods
```scala
class Animal {
  val age: Int = 0
  def eat() = println("I am eating")
}
class Dog extends Animal {
}
```
- Passing arguments to a class constructor
- NB constructor arguments are not fields
```scala
  class Animal {
    val age: Int = 0
    def eat() = println("I am eating")
  }
  val aAnimal = new Animal
  class Dog(name: String) extends Animal {
  }
  val aDog = new Dog("Lassie")
  // Note: a constructor argument is not the same as a field you can't say aDog.name <-- error
```
- In order for the constructure to evaluate the constructor argument to a field , add val before it
```scala
  class Dog(val name: String) extends Animal {
  }
  val aDog = new Dog("Lassie")
```
- Subtype polymorphism - The most derived method will be called at runtime
```scala
  class Dog(val name: String) extends Animal {
}
val aDog = new Dog("Lassie")
val aDeclaredAnimal: Animal = new Dog("Hachi")
aDeclaredAnimal.eat() // eat will be called from Animal , unless Dog overrides it
```
- Abstract Class - A class that can only be derived from , not all methods or fields need to have implementations 
```scala
  abstract class WalkingAnimal {
    val hasLegs = true
    def walk(): Unit // Not implemented , whatever class extends this will need to implement it
  }
```
- All methods and values are public by default
  * Protected or Private keyword can be used to make class methods and values private
```scala
  abstract class WalkingAnimal {
    private val hasLegs = true
    def walk(): Unit // Not implemented , whatever class extends this will need to implement it
  }
```
- Interfaces - "ultimate abstract type => can leave all methods unimplemented" , can be implemented using traits
```scala
  trait Carnivore{
    def eat(animal: Animal): unit
  }
```
- Scala offers single class and multi trait inheritance
```scala
  trait Carnivore{
    def eat(animal: Animal): unit
  }
// single class multi trait inheritance
class Crocodile extends Animal with Carnivore{
  override def eat(animal:Animal) = println("I am eating you animal") // Note override keyword because the method is also on the Animal supertype
}
```
- Methods with a single argument can be called using infix notation -> object method argument
```scala
  val aCroc = new Crocodile;
  aCroc.eat(aDog)
  aCroc eat aDog // infix notation
```
- Scala is very permissive w.r.t method naming , example ?! is a valid method name
```scala
  trait Philosopher{
    def ?!(though:String):Unit // ?! is a valid method name in scala (often used as method name in Akka)
  }
```
- Operators in scala , are actually methods
```scala
  val basicMath = 1 + 2 // The + is a method belonging to the type Int
  val anotherBasicMath = 1.+(2) // Equivalent to above Calls the + method on the type Int
```
- Anonymous classes , a trait and abstract class can't be instantiated , anonymous class allows you to implement the trait on the spot
```scala
trait Carnivore{
    def eat(animal: Animal): Unit
  }
// Anonymous class for implementing traits on the spot
// Can be implemented like - Creating an instance on the fly
val dinosaur = new Carnivore{
  override def eat(animal:Animal):Unit = println("I am a dinosaur so I can eat anything")
}
// This does the following:
class Carnivore_Anonymous_35728 extends Carnivore{
  override def eat(animal:Animal):Unit = println("I am a dinosaur so I can eat anything")
}
val dinosaur1 = new Carnivore_Anonymous_35728
```
- Singleton objects -> will only ever have one instance
  * Scala has a special class method called apply that can be called as MyInstance(...)
  * The precence of the apply method allows instances of a class to be invoked like functions
```scala
object MySingleton{
  val mySpecialValue = 123
  def mySpecialMethod():Int = 20
  def apply(x:Int):Int = x + 1
}
MySingleton.mySpecialMethod()
MySingleton.apply(65)
// The apply method can be called directly like so
MySingleton(65) // equivalent to calling MySingleton.apply(..)
```
- Scala companion objects
  * If a class / trait and a singleton object with the same name are defined in the same file they are called companions
  * Companions can access each other's private fields / methods
  * The instances of the class and the singleton of the companion object are different things
  * You would normally use the Animal Singleton companion to access things that do not depend on the Animal instances
```scala
  class Animal {
    val age: Int = 0
    def eat() = println("I am eating")
  }
  object Animal{ // companion object
    //companions can access each others private methods and fields
    // singleton Animal and Instances of Animal are different things
  val canLiveIndefinitely = false
  }
  val animalsCanLiveForever = Animal.canLiveIndefinitely // generally used to access static properties
```
- Case classes -> Lightweight datastructures with some boilerplate
- The compiler automatically generates the following when a case class is defined
  * sensible equals and hash code
  * serialization - serialised case classed are often sent over the network (Akka)
  * companion with apply
  * May be constructed without the new keyword
  * pattern matching - see later course notes
```scala
case class Person(name:String, age: Int)
// may be constructed without the new keyword
val bob = Person("Bob",54)  // equivalent to Person.apply("Bob",54)
```
- Exceptions
  * Case can be used to match different exception types
```scala
  try{
    // code that can throw
    val x: String = null
    x.length // throws error
  }catch{
    case e: Exception => "Some faulty error message" // cases can match different exception types
  } finally{
    // I execute some code no matter what
  }
```
- Generics , example with List object
  * Classes
  * Abstract Classes
  * Traits
```scala
  // Generics - works for classes , abstract classes and traits
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
```
- Immutability  -> In Scala we normally operate on immutable objects to return new objects
  * Benefits:
    1. Immutability is great for multithreaded environments
    2. makes code easier to reason about
  * Scala is closest to the OO ideal -> All code is always part of a class or an object
```scala
val myList: List[String] = List("a","b","c")
val reversedList = myList.reverse // New copy of the object
```
- Extending using the App object
 * Inherits the static main method of the App trait object

## Part 4 - Functional Programming
- [Code](src/main/scala/com/rockthejvm/FunctionalProgramming.scala)
- Apply method allows an instance of a class to be invoked as a function
```scala
  class Person(name: String){
    def apply(age:Int): Unit = println(s"I have aged $age years")
  }
  val bob = new Person("bob")
  bob.apply(43)
  bob(43) // same as calling apply
```
- The JVM doesn't know what a function is as a first class citizen
- Functional Programming, we want to
  * All functions are instances of the FunctionX type trait
  * compose functions
  * pass functions as arguments
  * return functions as results
  - Scala models functions using a trait
  * Scala has traits called FunctionX from 1 - 22 (22 max arguments)
- def is used to define class methods
```scala
  //Function1[argumentType, ReturnType]
  val simpleIncrementer = new Function1[Int, Int]{
    override def apply(arg:Int):Int = arg + 1
  }
```
### Syntactic sugars
- Arrow functions
```scala
  val doubler: Function1[Int,Int] = (x:Int) => 2 * x
  // equivalent to:
  val doubler1  = new Function1[Int, Int] {
    override def apply(x:Int) = 2 * x
  }
 ```
- Function[Int, Int] type can be written as Int => Int
```scala
  val doubler : Int => Int = (x:Int) => 2 * x
```
- You can omit the function type altogether
```scala
 val doubler = (x:Int) => 2 * x // compiler infers type of function
```
### Higher order functions
- Allows you to
  * Take functions as arguments 
  * return functions as results
- map is a higher order method
```scala
  val aMappedList = List(1,2,3).map((x)=>x+1)
```
- flatMapped lists
```scala
  val aFlatMappedList = List(1,2,3).flatMap((x)=>List(x, 2 * x))
  println(aFlatMappedList) // List(1,2,2,4,3,6) // Concatenates the lists generated for each map result
```
- Alternative Map syntax
```scala
 val aMappedList = List(1,2,3).map{x => 
  x+1
}
```
- Filtered Lists
```scala
  val aFilteredList = List(1,2,3,4,5).filter(x=>x <= 2) // returns List(3,4,5)
```
- Underscore shorthand notation
```scala
  val aFilteredList2 = List(1,2,3,4,5).filter(_<=2) // _ <=2 is equivalent to  (x) => x <= 2
```
- Map , Flatmap and Filter can be chained because the all return lists
```scala
  val myList:List[Int] = List(1,2,3,4,5).filter(_>=1).map(_*2).flatMap(x=>List(x, x*2))
  val allPairs = List(1,2,3).flatMap(number => List("a","b","c").map(letter=>s"$number - $letter"))
  println(allPairs)
```
- For comprehensions (More human readable chains)
  * it is not a for loop , it is an expression
  * a for comprehension is deconstructed to a chain of flatmaps and maps
```scala
  val alternativePairs = for {
      number <- List(1,2,3)
      letter <- List('a','b','c')
  } yield s"$number-$letter" // equivalent to the map flatmap chain above
  // resolves to List(1-a , 1-b , 1-c , 2-a, 2-b , 2-c) .....
```
### Collections
- List prepend and append operators
  * prepend use :: or +:
  * append use List :+
```scala
  val aList = List(1,2,3,4)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // List(0,1,2,3,4)
  val anExtendedList = 0 +: aList :+ 5 // List(0,1,2,3,4,5)
```  
- Sequences
  * Allows you to access an element at an index
```scala
  val aSequence: Seq[Int] = Seq(1,2,3)
  val accessedElement = aSequence(0) // returns Int(1)
```
- Vectors - same as sequence but with very fast access time
```scala
  val aVector = Vector(1,2,3)
  val accessedVectorElement = aVector(0) // returns Int(1)
```
- Sets
  * A list with no duplicates
  * Main usecase of a set -> check if it contains value
  * Elements can be added to a set using + and removed using minus
  * Order is not maintained in sets
```scala
  val aSet = Set(1,2,3,4,1,2,3) // returns Set(1,2,3,4)
  val setHas5 = aSet.contains(5) // Check if set has value -> true or false
  val aListWithDuplicates = List(1,2,3,4,5,1,2,3,4)
  val listNoDuplicates = aListWithDuplicates.toSet.toList.sorted
  val addedSet = aSet + 6 // Set(1,2,3,4,5,6) // same as aSet.+(6)
  val removedSet = aSet - 6
```
- Ranges
  * Fictitious list for a range
```scala
  val aRange = Range.inclusive(1, 1000)
  val aRange2 = 1 to 1000 // Same as inclusive
  val twoByTwo = aRange.map(x => 2 * x).toList // List(2,4,6,8,....2000)
```
- Tuples
  * Useful for grouping info under on var
```scala
  val aTuple = ("Bon Jovi","Rock",1982)
  val aTuple2 = "Bon Jovi" -> 1982 // two member tuple
```
- Maps
  * A map stores tuples
  * A tuple can be constructed with val1 -> val2 = (val1, val2)
```scala
  val aPhoneBook: Map[String, Int] = Map(
  ("Graham",12345678),
  "SomeOneElse" -> 12345897 // two member tuple shorthand
)
```

## Part 5 - Pattern Matching
- [Code](src/main/scala/com/rockthejvm/PatternMatching.scala)
- Switch expression
  * Some value match {case ...}
  * Default case denoted by _
  * Pattern match is an expression
```scala
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger+"th" // default case
  }
```
- match can deconstruct case classes
  * a case doesn't have to match against a definitive value
  * It matches against a structure (case class)
  * Only available for case classes (for now)
```scala
  case class Person(name:String,age:Int)
  val graham = Person("Graham",32)
  val personGreeting = graham match {
    case Person(name, age) => s"Hello $name"
    case _ => "Not a person"
  }
```
- Deconstructing tuples in pattern match
```scala
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match{
    case (a, b) => s"$a is a $b band"
  }
  println(bandDescription)

  var matchTest = (myTuple:Any) => {
    myTuple match{
      case (name:String, genre:String, year:Int) => s"$name is a $genre band started in $year"
      case (name:String, genre:String) => s"$name is a $genre band, and we have no idea when they stared"
      case _ => "I have no idea what you are talking about"
    }
  }
```
- Deconstructing lists in pattern match
  * use _ as placeholder for "anything"
```scala
  val aList = List(1,2,3)
  val listDescription = aList match {
    case  List(_,2,_) => "List containing 2 at the second position"
    case _ => "No match"
  }
```
- If there is no default case _ , an error will be thrown
- Types can also be used in pattern matching
```scala
var matchByType = (argument:Any) =>{
    argument match {
      case List(_:String,_:Int,_:String) => "Matched a list with string, integer, string"
      case List(_:Int,_:Int,_:Int) => "Matched a list with three integer members"
      case _ => "Not matching anything"
    }
  }
  println(matchByType(List("abc",3,"cde")))
  println(matchByType(List(1,2,3)))
```



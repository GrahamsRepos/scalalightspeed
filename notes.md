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

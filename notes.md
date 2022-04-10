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
- Continue from 6:04
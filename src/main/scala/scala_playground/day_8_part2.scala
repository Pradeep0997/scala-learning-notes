package scala_playground

object day_8_part2 {
  def main(args: Array[String]):Unit={
     println("--Pattern Matching--")
      // match (similar to java switch statement which only works on integers,enums or strings)
      // but in scala, we can match anything: types, structures, conditions .. more

    val x: Any = "Scala"

    x match {
      case 1 => println("It's one")
      case "Scala" => println("It's Scala")
      case true => println("It's true")
      case _ => println("It's something else") // the 'default ('_' wild entry) catch-all

    }

    // matching case class (destructuring)
    // we "unpack" an object to get the data inside it without need of .getName etc
    case class Employee(name: String, role:String, salary:Int)

    val emp = Employee("Pradeep","Developer",50000)

    emp match{
      case Employee("Pradeep","CEO",_) => println("Hey, you are CEO")
      case Employee(n,"Developer",s) => println(s"Developer $n earns $s")
      case Employee(_,_,_) => println("Some generic employee")

    }

    // pattern matching on lists (Useful in functional programming)
    val nums = List(1,2,3,4,5)

    nums match {
      case List(1,2) => println("Exact match 1,2")
      // matches a list with exactly two elements: 1 and 2

      // matches a list which starts with 1, followed by anything
      case 1 :: tail => println(s"Starts with 1, rest is $tail")
      // tail becomes List(2,3,4,5)

      // matches an empty list
      case Nil => println("Empty List")

      //matches anything else
      case _ => println("something else")
    }

    // Checking types (Typed Patterns)

    val unknown: Any = 45
    unknown match {
      case s: String => println(s"It's a string of length ${s.length}") // 's' is automatically a String here
      case i: Int=> println(s"It's an integer plus one: ${i + 1}") // 'i' is automatically an Int here
      case _ => println("Unknown type")
    }

    // Guard Clauses
    // adding if statement directly into the case

    case class Order(amount:Int)

     val order1 = Order(500)

    order1 match {
      case Order(amt) if amt > 1000 => println("Large Order")
      case Order(amt) if amt > 100 => println("Standard Order")

      case Order(amt) => println("Small Orders")
    }

    // Tuple Matching
    // Pattern matching makes unpacking them instant

    val res = ("Success",200)
    res match {
      case ("Success", code) => println(s"Operation OK: $code")
      case ("Error",code) => println(s"Operation Failed: $code")
      case _ => println("Invalid Operation")
    }
  }
}

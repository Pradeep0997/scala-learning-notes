package scala_playground

object day_8 {
  def main(args : Array[String]): Unit={
    println("-- Day 8 --")

    println("\n Trait")
    // trait = Interface + Abstract class + Multiple Inheritance (safer than Java)

    /**
     * Java Interface
     * -----
     * interface Logger {
     *    void log(String msg);
     * }
     * Only method declaration.
     *
     * Java Abstract Class
     * -----
     * abstract class Logger {
     *  void info() {
     *    System.out.println("Info");
     *  }
     *  abstract void log(String msg);
     * }
     * Partial implementation.
     */
    // Scala trait = Both in one
    trait traitEx {
      def log(msg:String):Unit  //abstract =  members/classes define a contract (declaration) without providing the full implementation
      def info(): Unit = println("this is info about trait") // concrete = members/classes provide a full, usable implementation
    }


    val s = new Service
    s.log("This is basic trait")

    val s2 = new NoService
    s2.log("Hi, trait")

    val multService = new ServiceMultiple
    multService.start()

    val d = new Dog
    d.eat()
    d.sound()

    val cnt = new MyCounter
    cnt.inc()
    cnt.inc()
    println("Accessing trait variable: "+ cnt.count)

    // Creating instance for Value Class which extends Universal Trait
    val valueInstance = new Meter(5.5)
    valueInstance.printMe() // Calling the universal trait method
    println(valueInstance.toCentimeter)
    // Main use of Value Class: At runtime, the JVM sees 'double primitive'. No 'Meter' object exists!

    println("\n sealed trait")
    val p1 = CreditCard("1234-5678")
    val p2 = Cash()
    println(process(p1))
    println(process(p2))

  }
  // Outside main method
  // basic
  trait Logger {
    def log(msg: String): Unit
  }
  // class using the above trait
  class Service extends Logger {
    def log(msg: String):Unit ={
      println("LOG: "+msg)
    }
  }

  // trait with default implementation
  trait Logger2 {
    def log(msg: String):Unit = {
      println("Logging: "+ msg)
    }
  }
  // now class doesn't need to implement it
  class NoService extends Logger2
  // inherited automatically and println of trait will be executed

  // Multiple traits (multiple inheritance via traits)

  trait Auth {
    def authenticate(): Boolean = true
  }

  trait Database {
    def connect(): Unit = println("DB Connected")
  }

  // class using all three traits (Logger2, Auth, Database)
  class ServiceMultiple extends Logger2 with Auth with Database{
    def start(): Unit = {
      if(authenticate()){
        connect()
        log("Service Started!")
      }
    }
  }

  // Abstract class
  abstract class Animal { // Parent class
    def eat() : Unit = {
      println("Eating")
    }
    def sound() : Unit // abstract
  }
   class Dog extends Animal { // Child class
     def sound(): Unit = {
       println("Bark")
     }
   }

  /**
   * |                      | Trait      | Abstract Class |
   * | -------------------- | ---------- | -------------- |
   * | Multiple inheritance |  Yes      |  No           |
   * | Constructor params   | (mostly) no | yes            |
   * | Mix-in               | yes          | no            |
   */

  // trait with fields (variables)
  // Note: use carefully(mutable)
  trait Counter {
    var count=0

    def inc(): Unit ={
      count+=1
    }
  }
  class MyCounter extends Counter


  //Value Classes : exist at compile time for safety but vanish at runtime
  // as creating millions of tiny objects kills our RAM and slows down GC (garbage collector)

  /**
   * Value Class Rules:
   *  - Must extend "AnyVal"
   *  - Must have exactly "one" val parameter
   *  - Cannot hold state (no "var" inside)
   */

  //Universal Trait (Must extend "Any", methods only, no fields)
  // Note: Only Universal Traits can be mixed into Value Classes

  // Universal Trait exists because these traits can be used
  // Universally—meaning they work on both Value Classes (primitives) and Reference Classes (normal objects).
  trait Printer extends Any {
    def printMe(): Unit = println(this)
  }

  class Meter(val value: Double) extends AnyVal with Printer {
    def toCentimeter: Double = value*100
  }


  // Sealed Traits
  // when we seal a trait, all its children must be in the same file.
  // this allows the compiler to know every subtype -> we write a "match"
  // statement and forget one children, the compiler shouts at you(Error). This prevents bugs before code runs

  sealed trait Payment
  case class CreditCard(num: String) extends Payment
  case class PayPal(email : String) extends Payment
  case class Cash() extends Payment
  // similarly we can add 'case class otherClassName()' etc

  def process(p: Payment) : String = p match {
    case CreditCard(num) => s"Processing Card: $num"
    case PayPal(email) => s"Processing PayPal for $email"
     case Cash() => "Processing Cash Payment..."
  }
  // As we created p2 = Cash() and called process(p2) ->
  // error after commenting case Cash() => in process Exception in thread "main" scala.MatchError: Cash() (of class scala_playground.day_8$Cash)
  // This is the super-power. In java or c++, we wouldn't know we missed
  // a case until the program crashed in production.
  // But in scala, the sealed trait tells us immediately

}

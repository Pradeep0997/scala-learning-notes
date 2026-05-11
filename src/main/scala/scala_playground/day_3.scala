package scala_playground

object day_3 {
  def main(args : Array[String]): Unit = {

    println("-- Class & Object --")
    // Classes & Objects
    // Primary Constructor is defined right here only
    class Student(val name: String, private var loggedIn: Boolean) {

      println(s"Student $name registered")


      def login(): Unit = {
        loggedIn = true
        println(s"$name logged in")
      }

      def logout(): Unit = {
        loggedIn = false
        println(s"$name logged out")
      }
    }

    object LoginSystem {

      def start(): Unit = {
        println("Login system started")
      }

      def apply(user: String): String = {
        s"$user successfully connected"
      }
    }



    val s1 = new Student("Pradeep", false)

    s1.login()

    LoginSystem.start()

    val msg = LoginSystem("Pradeep")

    println(msg)
    s1.logout()

    // Sets (unique items)
    println("\n-- Sets --")

    val items = Set("Pen", "Book", "Pen", "Pencil")

    println("Items: " + items)

    val newItems = items + "Eraser"

    println("After Adding(Eraser): " + newItems)

    val removeItems = items - "Pen"
    println("After removing (Pen): " + removeItems)

    println("Contains Book: " + items.contains("Book"))

    // Case classes
    println("\n---- Case Class ----")

    case class Book(title: String, pages: Int, available: Boolean)
    // no need to use new keyword
    val b1 = Book("Scala Basics", 200, true)

    println("Book1: " + b1)

    // exact b1 values copied into b2 except (available variable)
    val b2 = b1.copy(available = false)

    println("Book2: " + b2)

    val b3 = Book("Scala Basics", 200, true)

    // Checks whether both b1 and b3 contains same values or not
    println("b1 == b3 : " + (b1 == b3))

    // Access Modifiers
    println("\n-- Access Modifiers --")


    // PRIVATE
    class ATM {

      private var pin = 1234

      def checkPin(input: Int): Boolean = {
        input == pin
      }
    }

    val atm = new ATM

    println("Pin correct: " + atm.checkPin(1234))
    //atm.pin = 5432; // pin is inaccessible error (because pin is private var)

    // PROTECTED
    class Vehicle {
      protected def protected_val = 12;
      protected def startEngine(): Unit = {
        println("Engine Started")
      }
    }

    //println(protected_val) // Cannot resolve symbol protected_val error
    // as we can't access protected val outside only accessed in parent or child classes(inheritance)

    class Bike extends Vehicle {

      def ride(): Unit = {
        startEngine()
        println("Accessing protected var value from child: "+ protected_val)
        println("Bike Running")
      }
    }

    val bike = new Bike
    bike.ride()


    class Car {
      def ride(): Unit = {
        //startEngine() // unlike java we can't access protected things even from the same class
        // so protected vars,methods only accessible to defined class and child classes only
        println("Car Engine not started!")
      }
    }

    val car = new Car
    car.ride()


    // private package

    // private[pkg] = accessible only inside that package (Anywhere inside the same package only)
    /* Ex:
      package school.system

      private[system] def prepareQP(): Unit = {
      println("Question paper prepared")
      }

     // prepareQP() method can accessed  anywhere in the package "system"

     */




  }



}

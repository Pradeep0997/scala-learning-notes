package scala_playground

import scala.annotation.tailrec

object day_5 {
  def main(args: Array[String]): Unit = {
    // while
    println("---Day 5---");
    var attempts = 3;
    while(attempts>0){
      println("Try Password(no. of attempts remaining): "+ attempts);
      attempts-=1;
    }

    println("Account Locked");

    // do while
    var choice = "yes";
    do{
      println("do while loop executes atleast once!")
      choice="no";

    }while(choice=="yes");

    println("nested for loop (using one \"for\" statement)")
    for(i<- 1 to 5; j<- 2 to 4){
      println(s"$i - $j")
    }

    println("nested for loop (using two \"for\" statements)")
    for(i<-1 to 5){
      for(j<-2 to 4){
        println(s"$i - $j")
      }
    }

    println("until (excludes end)")
    for(i<- 1 until 5)
      println(i)

    println("for loop with a condition: ")
    print("Odd numbers: ")
    for(i<- 1 until 6 if i%2!=0){ // if inside for ==> filter
      print(i+" ")
    }
    println()

    // functions

    // basic function
    def add(a: Int, b:Int):Int = {
      a+b
    }
    println("add(2,4): "+ add(2,4))

    // short form
    def square(x:Int) = x*x  // return type inferred (automatically inferred by the compiler no need to mention)
    println("square(5) using short form function: "+square(5))


    // default parameters
    def greet(name: String ="Guest") : String = {
      "Hello "+name
    }
    println("greet() default parameter applied: "+ greet())
    println("greet(\"Guru\") here default params not applied: "+ greet("Guru"))

    // variable args (varargs)

    def total(marks: Int*): Int = { // here * means many arguments
      marks.sum
    }

    println("total(80,90,70) using variable args (Int*): "+ total(80,90,40))


    // nested functions (function inside function)

    def calcultr (x:Int, y:Int) : Int = {
      def add() = x+y
      def sub() = x-y

      add() // Inner functions uses outer variables
     // sub()
    }
    println("outer: calcultr(2,4) - Inner fun:  add: "+ calcultr(2,4))





    // Recursion
    println("Factorial(5): "+ factorial(5))

    //println("Factorial(10000): "+ factorial(10000)) // stack overflow error as n is large

    // Tail Recursion
    println("Factorial(5) using tail recursive function: "+ tailFactorial(5,1))

    println("Factorial(10000) using tail recursive function: : "+ tailFactorial(10000,1))



    // Higher Order functions (funs that takes another fun as i/p or returns a fun as o/p)

    def calculator(a:Int, f: Int => Int): Int = {
      f(a)
    }
    // same calculator function is used for implementation of different operations
    // so instead of writing so many functions seperately we can use Higher Order function which takes function as i/p
    // and also it can able to return fun as o/p if required

    val double = calculator(10, x=> x*2)
    println("double(num, double_implement_fun): "+ double)

    val square_ = calculator(10, x=> x*x)
    println("square(num, square_implement_fun):  "+square_)



    // built-in ex:
    val nums = List(1,2,3,4)
    val doubled = nums.map(_ * 2) // map applies functoin to each element
    println("Doubled list using built-in fun: "+ doubled)




  }

  def factorial(n: Int): BigInt ={
    if(n<=1) 1
    else n*factorial(n-1)
  }

  @tailrec
  def tailFactorial(n: Int, accumulator: BigInt): BigInt = {
    if(n<=1) accumulator
    else tailFactorial(n-1, n*accumulator)

  }
}

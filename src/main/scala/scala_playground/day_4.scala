package scala_playground

object day_4 {
  def main(args: Array[String]): Unit = {
    /**
     * Documentation Comment
     *
     *
     */
    val i=0;
    val j=0;
    for(i <- 1 to 3 ; j <- 1 until 3){ // nested loop
      println(s"$i - $j"); // String Interpolation
    }





    println("i: "+i)
    println("j: "+j)

    // yield results collection
    val collection = for{
      i <- 1 to 3
      j <- 1 until 4
    } yield List(i,j);
    println("Collection: "+ collection)


    val num = 14;
    println("(num>>1): "+ (num>>1));
    println("(num>>>1) [right shift with zero extension always results +ve big num for only -ve num]: "+ (num>>>1));

    println("(num<<1): "+ (num<<1));

    // Literals = Fixed values written directly in code
    // Integer (Int) default - 32 bit
    val _age = 20 // variables names can start with only alphabets or _
    val year3 = 2026 // variable structure [a-z _ A-Z]+ [1,..9]* [az_AZ]*

    val price = 99.99      // Double (default) if we don't mention anything for decimals by default it consider it as double
    val temp = 36.5f       // Float (add f)

    // Escape sequences (special chars)
    //  \n - new line , \t - tab , \" - double quotes, \ - single backslash
    println("Hello\nScala")
    println("Name:\tPradeep")
    println("He said: \"Hi\"")


  // type inference (scala automatically detects type) no need to mention : Data_type explicitly
    // NOTE: Compiler assigns type automatically

    // variable scope (where variable is accessible )
    if(true){
      val x=10
      println(x) // 10 as we are accessing within the var scope
    }
    //println(x)  // Error (Cannot resolve symbol x)



    // Conditional Statements

    // scala uses if as an expression, not just statement
    //if else
    val age = 19
    if(age >= 18){
      println("U can vote")
    }else{
      println("U can't vote")
    }


    // if - else if
    val marks = 82

    if (marks >= 90)
      println("A")
    else if (marks >= 75)
      println("B")
    else
      println("C")

    // OPERATORS
    //Scala treats operators as methods
    // a+b == a.+(b)

    // +, -, *, /, % (Arithmetic)
    // gives integer division if both are Int
    val num1 = 10;
    val num2 = 10
    println("a==b: "+ (num1==num2)); // scala '==' checks values not memory references(java)
    // Priority -> Decreasing order: (), *, /, %, +, -, <, >, &&, ||


    // Control Structures
    // while loop
    var loop = 1

    while (loop <= 5) {
      println(loop)
      loop += 1
    }

    // do while similar to java code (Executes atleast once)

    // for loop

    for(i <- 1 to 4){ // when we use  'to' (both 1 and 4 inclusive)
      println(i)      // if we use 'until' (only 1 inclusive 4 exclusive)
    }

    // for loop with condition (Guard)
    println("Printing even numbers using for loop(Guard)")
    for(i <- 1 to 10 if i%2==0){
      println(i)
    }

    // nested for loop with two 'for'

    for(i<- 1 to 5){
      for(j<- 2 until 4 ){
//        if(i==2){
//          break; // scala has no direct break, continue
//        }
        print(s"$i - $j , ")
      }
    }
    println()

    // if as expression (if return a value)
    // single if without else (if 'if' condition false then result= () => Unit type )
    val a = 10;
    val b = 20;

    val max = if(a>b) a
    println("max: "+max) // if a>b then result will be 'a' otherwise result = ()

    val max2 = if(a>b) a else b
    println("max2: "+ max2)

    // multi-line if expression
    val salary = 30000

    val bonus = if (salary > 25000) {
      println("Good Performance")
      salary * 0.10
    } else {
      salary * 0.05
    }

    println("Bonus: " + bonus)


  }
}

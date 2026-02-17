package scala_playground

object day_6 {
  def main(args: Array[String]): Unit = {
    println("-- Day 6 --")

    println("\n-- Closures --")
    // Closures:  a closure is a fun, whose return value depends on
    // 1 or more variables declared outside the fun
    println("multiplier1 value = "+ multiplier(2))
    println("multiplier2 value = "+ multiplier(4))

    println("\n-- String --")
    // Strings
    // String creation
    val s1 = "Hello" // = val s1:String = "Hello"  we can also defined
    val s2 = new String("Hi")
    println("s1: "+ s1)
    println("s2: "+s2)

    // String is IMMUTABLE
    val s = "Hi"
    val ss = s+" World" // s is unchanged
    // every change will create new String Object

    /**
     * If many concatenations:
     * Instead of using this approach it is better to use StringBuilder
     * var s = ""
     * for(i <- 1 to 1000)
     * s += i
     */

    // StringBuilder
    val sb = new StringBuilder
    for(i <- 1 to 1000)
      sb.append(i)

    val result = sb.toString
    println(result)


    println("\nSmart String Operations")

    val str : String = "Hello, I am learning Scala"

    println("str: "+ str)
    println("str.charAt(2): "+ str.charAt(2)) // similar to java
    println("str.substring(start,end): "+ str.substring(7,11)) // start(inclusive) but end(excluded)
    println("str.spilt(delimiter like \" \" or \"\\n\" ): "+ str.split(" ").toList) // after splitting we have to store
    // splitted strings in a List or any similar data structure
    // If we omit .List for split() then result: [Ljava.lang.String;@f5f2bb7
    println("str.startsWith(\"Hello\"): "+ str.startsWith("Hello"))// results either true or false
    println("str.endsWith(\"Scala\"): "+ str.endsWith("Scala"))
    println("str.replace(\" \"(space), \"  \"(2 spaces)): "+ str.replace(" ", "  ")) // original str remains unchanged only copy of str will changed

    println("Original str: "+ str)

    println("str.toLowerCase(): "+ str.toLowerCase())
    println("str.length : "+ str.length)


    val aNumStr = "2"
    val aNum = aNumStr.toInt // converts string into Int
    println('a' +: aNumStr :+ 'z') // Prepend: to add at front of the string use = 'char_added' +: str
    // Append: to add at end of str = str :+ 'char_added'
    println("abc" +: aNumStr :+ "yz")  // results different way : Vector(abc, 2, yz)

    println("str.reverse : "+ "madam".reverse) // reverses the string
    println("str.take(3): "+str.take(3)) // results first 3 chars as output

    println("\n-- String Interpolators --")
    // Scala - specific: String Interpolators

    // s-interpolators
    val name = "Pradeep"
    val age = 20
    val greeting = s"Hello, my name is $name and I am $age years old"
    println("greeting using s\"$var_name\": "+ greeting)
    val anOtherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years by next year"
    println(s"greeting using s\"{}\" used for expressions like age+1: "+ anOtherGreeting)

    // f-interpolators (like C programming printf() or Java printf )

    val speed = 5.2f
    val myth = f"$name can drive $speed%1.2f per minute"  // %[min_width].[precision][type]
    // ex: num = 5.2 then %1.2f means the num should be atleast 1 digit width ( here "5.20" = 4 chars ('5','.','2','0') so > width 1 satisifed so it is omitted)
    println(myth)

    val a = 2.3
    val b = 12.3
    val c = 123.3

    println(f"$a%6.2f") // here 2.30 as decimals are fixed to 2 digits and 2, '.' all together is 4 chars but formatting requires 6 chars as minimum so 2 spaces added at front(left) o/p: i.e, "  2.30"
    println(f"$b%6.2f") // here 12.30 as decimals are fixed to 2 digits and 1,2, '.' all together is 5 chars but formatting requires 6 chars as minimum so 1 space added at front i.e, " 2.30"
    println(f"$c%6.2f") // here 123.30 as decimals are fixed to 2 digits and 1,2,3, '.' all together is 6 chars = formatting  6 chars as minimum so no spaces required i.e, "123.30"

    // here spaces are added by JVM

    // to add spaces at right i.e, end instead of front we have to '-'
    println(f"$a%-6.2f") // now spaces are added at end i.e, o/p: "2.30  "

    // use of '-' adding spaces at right (logs, billing)
    //ex:
    val name2 = "Guest"
    println(f"Name: $name%-10s | Speed: $speed%6.2f")
    println(f"Name: $name2%-10s | Speed: $c%6.2f") // This Provides Perfect alignment


    // common format types
    /**
     *    %d - Int
     *    %f - Float/ Double
     *    %s - String
     *    %c - Char
     *    %b - Boolean
     */
    val isTrue:Boolean = false
    println(f"Boolean result: $isTrue%7b") // aleast 6 chars as here f,a,l,s,e : 5 chars so 2 spaces added at front
    println(f"Boolean result: ${!isTrue}%7b")
    println(f"Boolean result: $isTrue%-7b")
    println("(using - for adding spaces at right)")

    // zero padding
    println(f"Zero Padding: $a%05.2f") // adding zero at front
    println(f"Show Sign: $a%+5.2f") // sign representation

    //JVM automatically shows:
    // - for negative numbers
    // + for positive numbers (only if you use + flag)
    val neg = -2.3f
    println(f"Show Sign for negative (with +): $neg%+06.2f") // both results same output
    println(f"Show Sign for negative (without +): $neg%-6.2f")

    /**
     * Order doesn’t matter much:
     *    %-+6.2f  both shows sign and right alignment if width<6
     *    %+-6.2f
     *
     *    If we use $neg%+-06.2f (zero padding) then
     *    result: Exception in thread "main" java.util.IllegalFormatFlagsException: Flags = '-+0'
     *    this error occurs because when we use '-' which is using for Left alignment means adding spaces at right end
     *    and '0' padding which means adding 0 at left so both are conflicting and results error
     *
     *
     *   JVM applies flags in this order:
     *    (left align) → strongest
     *    (zero pad) → ignored if - exists
     *    (sign)
     *
     */

    val x = 1.1f
    // val num = f"$x%3d" //type mismatch then results error.
    //
    /**
     * So be aware of data type
     * found   : Float
     * required: Int, Long, Byte, Short, BigInt
     * val num = f"$x%3d"
     */


    // raw interpolator
    println(raw"This statment has \n without escape chars but using raw formatting")

    val escaped =  "This statment has \n without escape chars but using raw formatting"
    // here the raw injected variables don't print \n as they escaped when defining the string
    println(raw"$escaped")



  }
  var factor = 3
  //  function references factor and reads its current value each time.
  // here multiplier is Anonymous function as strictly speaking closures are created for anonymous functions only
  // if we consider def fun_name():data_type={} this is treated as method not a function so it can access class variables
  // so the automatic closure applied for "def" defined functions.

  val multiplier = (i:Int) => i*factor

}

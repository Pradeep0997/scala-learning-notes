package scala_playground

object day_7 {
  def main(args: Array[String]) : Unit={
    println("-- Day 7 --")
    println("\n Scala Collections")

    // scala collections are immutable by default

    /**
     *  main types
     *  List - Ordered and immutable
     *  Set - Unique elements
     *  Map - (Key -> Value) pairs
     *  Tuple - Fixed-size (allows mixed types)
     *
      */
    // List
    val list = List(1,2,3,4) // 1 -> 2 -> 3 -> 4 -> Nil
    // Linked List (fast at head related operations, slow at tail related operations)
    println("List: "+ list)
    //operations
    println("List head: "+ list.head)
    println("List tail(except head all other elements of list): "+ list.tail)
    println("Check whether list is empty? "+ list.isEmpty)

    // adding element at end (slow)
    val new_list = list :+ 5
    println("After adding element at end (list :+ 5): "+new_list)
    // as List is immutable if we add elements or remove then existing list will remain unchanged
    // a new list object must be created to access the updated list

    val new_list2 = 0 +: new_list // front adding (fast)
    println("After adding element at front (0 +: list): "+new_list2)

    val merged_list = list ++ List(9,10)
    println("Merged List (list ++ List(9,10)): "+ merged_list)


    val listEmpty = List()
    println("ListEmpty: "+ listEmpty)
    println("Is list empty? "+ listEmpty.isEmpty)
    //println("List head accessing: "+ listEmpty.head) // if we use this approach when list is empty then
    // Exception in thread "main" java.util.NoSuchElementException: head of empty list
    //	at scala.collection.immutable.Nil$.head error occur so use below one to gracefully fix the errors

    println("List head accessing: "+ listEmpty.headOption)

    // Remove Last element (tail end) => slow as it takes O(n)
    val removedLast = list.init
    println("Removed last element(using list.init): "+ removedLast)

//    val remove = listEmpty.init // when trying to delete last element of empty list
//    println(remove) // error

    // safer way
    val remove = listEmpty.dropRight(1)
    println("Trying to delete last element of empty list: "+ remove)

    // Note: For every operation a New List will created. Each and every operation

    // to remove N elements from start
    // use list.drop(n)

    // to remove N elements from end
    // use list.dropRight(n)

    // to delete specific element by value
    // use list.filterNot(_ == 2) // removes all 2's from the list

    // to remove only first occurrence (by value)
    // list.diff(List(2)) // removes only first occurrence of 2 and all other 2's will be there

    // Remove by index
    // list.patch(1,Nil,1) // removes 1 element at index 1
    // list.patch(1,Nil,2) // removes 2 elements from index 1 i.e, index 1, index 2


    // Remove using condition
    // list.filter(_ % 2==0) // keeps only even elements

    // REmove Duplicates
    // list.distinct

    // Splitting list at specific index: List(1,2,3,4)
    // val (a,b) = list.splitAt(2)
    // a = List(1,2) and b = List(3,4)

    // map/ filter/ reduce (Core Functional)
    val doubleList = list.map( x => x*2) // we can also use list.map(_ * 2)
    println("Doubled every element of list: "+ doubleList)

    val filteredList = list.filter(x => x>2) // also = list.filter(_>2)
    println("Filtered List: "+ filteredList)

    println("List elements sum: "+ list.sum)

    // groupBy -> grouping for ex: result looks like: HashMap(false -> List(1, 3), true -> List(2, 4))
    val groupby = list.groupBy(_%2==0)
    println("list.groupBy(_%2==0): "+ groupby)

    println("\n Set")
    // Set (unique and fast lookup)
    // here order of elements is not fixed so do not rely on index
    val s = Set(1,2,2,3,4,4,4)
    println("Set: "+ s)

    println("s.contains(4): "+ s.contains(4)) // true or false (whether element presents in the list)

    val addS = s+5 // adding new element into set
    println("After adding a element into set: "+ addS)
    val removeS = s-1 // removing existing element from set
    println("After removing a element from the set: "+ removeS)
    // to adds multiple elements into a set
    val mulAddS = s ++ Set(40,50)
    println("Added multiple elements using ++ :"+ mulAddS)

    val removes = s-10
    println(removes)
    val mulRemS = s -- Set(1,4)
    println("Deleted multiple elements using -- : "+ mulRemS )
    // to remove multiple elements

    val a = Set(1,2,3,4)
    val b = Set(3,4,5)

    println("set.contains(element): "+ a.contains(3))
    // or we can simply use a(3) also both are equivalent

    val aUb = a union b // also we can use a++b both are same
    println("a union b :" + aUb )

    val a_intersect_b = a.intersect(b) // = a intersect b = a intersect(b)
    // also we can use a&b
    println("a intersect b :"+ a_intersect_b)

    val aDiffb = a -- b
    println("a -- b :"+ aDiffb)

    println("set.size : "+ a.size)
    println("set.isEmpty : "+ a.isEmpty)

    println("Checking whether a set is subsetOf another: ")
    println(Set(1,2).subsetOf(Set(1,2,3))) // true

    // set.toList to convert set into List
    // set.toArray to convert set into Array


    println("\n Map")
    // Map (Key -> Value)

    val marks = Map(
      "Rohit" -> 90,
      "Virat" -> 100,
    )
    // access
    println("marks(\"Virat\"): "+ marks("Virat")) // 100 (may crash if key missing)
    //println("marks(\"Ro\"): "+ marks("Ro")) // .NoSuchElementException error
    println("marks.get(\"Virat\"): "+ marks.get("Virat"))
    println("marks.get(\"Ro\"): "+ marks.get("Ro")) // return None (better to use : Safer way)

    println(marks("Rohit"))

    /**
     * Other safer way
     * marks.get("Rohit") match {
     * case Some(v) => v
     * case None => 0
     * }
     */

    // or
    // marks.getOrElse("Ro",0) // results 0 if "Ro" key doesn't exists

    // update
    val newMap = marks + ("Abhi" -> 55 )
    // original will remain unchanged
    println("New map :"+ newMap)

    val newMap2 = marks - "Rohit"
    println("After removing \"Rohit\" from map: "+ newMap2)
    println("using map.contains() to check whether specified key present or not: "+ marks.contains("Virat"))
    println("Accessing all keys of map: "+ marks.keys)
    println("Accessing all values of map: "+ marks.values)

    // Iterating map using foreach
    println("Iterating the map using foreach loop: ")
    marks.foreach{
      case(k,v) => println(k+ " -> "+v)
    }
    val filterdMap =  marks.filter(_._2 > 98) // here we are filtering the map by checking whether element 2 (values of map) > 98
    val filterdMap2 = marks.filter(_._1 == "Virat")
    println("filterdMap using filter() : "+ filterdMap)

    // merge Maps
    val m1 = Map("a"->1)
    val m2 = Map("b"->2)
    val merged = m1 ++ m2
    println("Merging two maps: "+ merged) // we merged two maps

    // Assigning default value for map so even if we try to access a key which doesn't in the map return default value
    val mp = Map().withDefaultValue(0)

    //println("Accessing an unknown key from map: "+ mp("x"))

    // never use map(key) without checking -> results NoSuchElementException error

    println("\nTuple")
    // Tuple (stores different types)
    val t = (1,"Pradeep", true)
    println("Tuple :"+ t)
    println("t._1 (accessing first element of tuple: )"+t._1)
    println("t._1 (accessing second element of tuple: )"+t._2)
    println(t._3)
    //println(t._4) // Results error as tuple only consists of 3 elements:
    // value _4 is not a member of (Int, String, Boolean)
    //did you mean _1, _2, or _3?
    //println(t._4)

    // Destructuring
    val (id, name, isIntern) = t
    println(id +" "+ name +" "+ isIntern )
    // if we try to access less number of more number than original number of elements then
    // errors occurred => Pattern mismatch

    // swap 2 variables
    val t2 = (10,20)
    println("t2.swap : "+ t2.swap)

  }
}

package fpInScala.exercises

object Exercise3Point8 {

  // See what happens when you pass Nil and Cons themselves to foldRight, like this:
  //
  // foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_))
  //
  // What do you think this says about the relationship between foldRight and the data constructors of List?

  // You get back the list you input.
  // This is because of how foldRight performs "constructor replacement".
  // If you replace f with Cons and Nil with z, and since foldRight takes the list and replaces Cons with f
  // and Nil with z, you essentially get a transformation which produces a result identical to the original list.
}
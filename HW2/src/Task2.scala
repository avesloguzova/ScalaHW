/**
 * Created by av on 10/4/15.
 */
class Task2 {

  //  trait A {
  //    def foo(): Int = 1
  //    def goo(): Int
  //  }
  //  class B extends A {
  //    override def goo(): Int = 2
  //  }
  //  object C {
  //    def m(): Int = 1
  //  }
  abstract class A {
    def foo(): Int

    def goo(): Int
  }

  class AImpl {
    def foo(a: A): Int = 1
  }

  class B extends A {
    override def foo(): Int = new AImpl().foo(this)

    override def goo(): Int = 2
  }

  class C$ {
    def m(): Int = 1
  }

  object C$ {
    val INSTANCE = new C$()
  }

  object C {
    def m() = C$.INSTANCE.m()
  }

}

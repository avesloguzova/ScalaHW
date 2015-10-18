/**
 * Created by av on 10/18/15.
 */
object Implicits {

  class A

  class B

  class C

  implicit def `A to B`(a: A): B = new B

  implicit def `B to C`[T](b: T)(implicit fromTtoB: T => B): C = new C

  def main(args: Array[String]) {
    val a = new A
    val c: C = a
  }
}

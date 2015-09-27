import scala.annotation.tailrec

def egcd(a: Int, b: Int): (Int, Int, Int) = {
  @tailrec
  def egcd(a: Int, b: Int, x1: Int, x2: Int, y1: Int, y2: Int): (Int, Int, Int) = {
    if (b <= 0) {
      (a, x2, y2)
    } else {
      val q: Int = a / b
      val r: Int = a % b
      val x = x2 - q * x1
      val y = y2 - q * y1
      egcd(b, r,x, x1, y, y1)
    }
  }
  egcd(a, b, 0, 1, 1, 0)
}

def assertEquals(expected: Any, actual: Any): Unit = {
  assert(expected == actual, s"Objects don't equal: expected [ $expected ]; actual [ $actual ]")
}
assertEquals((1, 1, -1), egcd(3,2))
assertEquals((2, 0, 1), egcd(6, 2))


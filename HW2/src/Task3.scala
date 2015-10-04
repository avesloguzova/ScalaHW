/**
 * Created by av on 10/4/15.
 */
object Task3 {
  def main(args: Array[String]): Unit = {
    val counts = (1 to 1000).map(countRepeated)
    val n = counts.indexOf(counts.max) + 1
    println(n)
  }

  def countRepeated(n: Int): Int =
    (1 to 1000).dropWhile(i => BigInt(10).pow(i) % n != 1).headOption.getOrElse(0)
}


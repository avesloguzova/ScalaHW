import scala.math._

def square(points: Seq[(Double, Double)]): Double = {
  val shiftedPoints = points.tail :+ points.head
  points.zip(shiftedPoints).map {
    case ((x1, y1), (x2, y2)) => (x2 - x1) * (y2 + y1) / 2
  }.sum
}

def perimeter(points: Seq[(Double, Double)]): Double = {
  val shiftedPoints = points.tail :+ points.head
  points.zip(shiftedPoints).map {
    case ((x1, y1), (x2, y2)) => sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2))
  }.sum
}
square(Seq((0.0, 0.0), (0.0, 1.0), (1.0, 1.0), (1.0, 0.0)))
perimeter(Seq((0.0, 0.0), (0.0, 1.0), (1.0, 1.0), (1.0, 0.0)))

square(Seq((-3.0, -2.0), (-4.0, 9.0), (3.0, 10.0), (6.0, 1.0), (-1.0, 4.0)))
perimeter(Seq((-3.0, -2.0), (-4.0, 9.0), (3.0, 10.0), (6.0, 1.0), (-1.0, 4.0)))

square(Seq((0.6, 2.1), (1.8, 3.6), (2.2, 2.3), (3.6, 2.4), (3.1, 0.5)))
perimeter(Seq((0.6, 2.1), (1.8, 3.6), (2.2, 2.3), (3.6, 2.4), (3.1, 0.5)))


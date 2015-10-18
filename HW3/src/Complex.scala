/**
 * Created by av on 10/18/15.
 */
class Complex(real: Double, im: Double) {
  override def toString: String = {
    s"($real,$im)"
  }

  override def equals(obj: scala.Any): Boolean = obj match {
    case other: Complex => toPair.equals(other.toPair)
    case _ => false
  }

  override def hashCode(): Int = toPair.hashCode()

  def Re: Double = real

  def Im: Double = im

  def conjugate: Complex = Complex(real, -im)

  def abs: Double = math.sqrt(norm)

  def arg: Double = math.atan2(im, real)

  def +(operand: Complex): Complex = Complex(Re + operand.Re, Im + operand.Im)

  def -(operand: Complex): Complex = Complex(Re - operand.Re, Im - operand.Im)

  def *(operand: Complex): Complex = Complex(Re * operand.Re - Im * operand.Im, Re * operand.Im + operand.Re * Im)

  def /(operand: Complex): Complex = {
    (this * operand.conjugate) / (operand * operand.conjugate).Re
  }

  def unary_- : Complex = Complex(-real, -im)

  def unary_+ : Complex = this

  def /(operand: Double): Complex = Complex(real / operand, im / operand)

  def ^(exponent: Complex): Complex = {
    (exponent * log).exp
  }

  private def toPair: (Double, Double) = (real, im)

  def sqrt: Complex = {
    (0.5 * log).exp
  }

  private def norm: Double = {
    real * real + im * im
  }

  private def exp: Complex = {
    val e = math.exp(real)
    Complex(e * math.cos(im), e * math.sin(im))
  }

  private def log: Complex = Complex(0.5 * math.log(norm), arg)
}

object Complex {
  def apply(real: Double, im: Double): Complex = {
    new Complex(real, im)
  }

  def apply(number: String): Complex = {
    val doublePattern = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"
    val complexPattern = s"\\(($doublePattern),($doublePattern)\\)".r
    number match {
      case complexPattern(real, im) => new Complex(real.toDouble, im.toDouble)
    }
  }

  def unapply(number: Complex): Option[(Double, Double)] = {
    Some(number.Re, number.Im)
  }

  implicit def `double to complex`(double: Double): Complex = Complex(double, 0.0)

  implicit def `int to complex`(int: Int): Complex = Complex(int, 0.0)
}

object Test {
  def main(args: Array[String]) {
    val a = Complex(1, 2.0)
    a match {
      case Complex(re, im) if re == 1 =>
        println("YAY!")
    }
  }
}
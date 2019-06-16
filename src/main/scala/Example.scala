import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

// To use this example:
//
// 1. run `sbt`
// 2. run the `console` command within `sbt`
// 3. enter `Example.image.draw`
object Example {

  // ********************* Examples of simple figures *********************
  val image1 = circle(10) fillColor Color.black on
              circle(20) fillColor Color.cornflowerBlue on
              circle(30) fillColor Color.white on
              circle(40) fillColor Color.darkBlue

  val image2 = circle(50) fillColor Color.hsl(0.degrees, 0.8.normalized, 0.6.normalized)

  val image3 = ((circle(100) fillColor Color.red) beside
    (circle(100) fillColor Color.red.spin(15.degrees)) beside
    (circle(100) fillColor Color.red.spin(30.degrees))).lineWidth(5.0)

  val image4 = ((circle(10) fillColor (Color.red.alpha(0.5.normalized))) beside
    (circle(10) fillColor (Color.blue.alpha(0.5.normalized))) on
    (circle(10) fillColor (Color.green.alpha(0.5.normalized))))

  val image5 = (triangle(30,30) fillColor Color.darkSlateBlue.spin(0.degrees) beside
               triangle(30,30) fillColor Color.darkSlateBlue.spin(15.degrees)) below
               triangle(30,30) fillColor Color.darkSlateBlue.spin(30.degrees)

  val image6 = circle(10) fillColor Color.red on
               circle(20) fillColor Color.white on
               circle(30) fillColor Color.red

  val image7 = circle(10) fillColor Color.red on
               circle(20) fillColor Color.white on
               circle(30) fillColor Color.red above
               rectangle(6, 20) fillColor Color.white above
               rectangle(20, 6)  fillColor Color.brown above
               rectangle(80, 20)  fillColor Color.green

  val house = triangle(40,30).
                fillColor(Color.brown).
                lineColor(Color.brown) above
              rectangle(40,10).
                lineColor(Color.red).
                fillColor(Color.red) above
             (rectangle(10,30).
                lineColor(Color.black).
                fillColor(Color.black) on
              rectangle(40,30).
                lineColor(Color.red).
                fillColor(Color.red))

  val tree = circle(20) fillColor(Color.green) above
            rectangle(5,30) fillColor(Color.brown)

  val basic = (rectangle(20,5).fillColor(Color.yellow) above
             rectangle(20,10).fillColor(Color.black))

  val street = basic beside
              rectangle(15,15).fillColor(Color.black) beside
              basic beside
              rectangle(15,15).fillColor(Color.black) beside
              basic beside
              rectangle(15,15).fillColor(Color.black)

  val aBox = Image.rectangle(20, 20).fillColor(Color.royalBlue)

  // ********************* Making figures by recursion *********************

  def boxes(count: Int): Image = count match {
    case 0 => Image.empty
    case n => aBox beside boxes(n-1)
  }


  def cross(count: Int): Image = count match {
    case 0 => circle(10)
    case n => circle(10) above
              (circle(10) beside cross(n-1) beside circle(10)) above
              circle(10)
  }


  def chessboard(count: Int): Image = {
    def s_square(color: Color): Image =
      square(10).fillColor(color)

    val chess_unit = ((s_square(Color.red) beside s_square(Color.black) ) above
      (s_square(Color.black) beside s_square(Color.red) )) on
      square(20).lineColor(Color.black)

    count match{
      case 0 => chess_unit
      case n =>
        val unit = chessboard(n-1)
        (unit beside unit) above (unit beside unit)
    }
  }

  //The Sierpinski triangle is a famous fractal
  def sierpinski(count: Int): Image =
    count match {
      case 0 => triangle(10,8).lineColor(Color.red)
      case n =>
        val unit = sierpinski(n-1)
        unit above (unit beside unit)
    }




//  def stack_units(count: Int): Image = count match{
//    case 0 => chess_unit
//    case n => chess_unit above (stack_units(n-1))
//  }
//
//  def append_units(count: Int): Image = count match {
//    case 0 => Image.empty
//    case n => chess_unit beside append_units(n-1)
//  }
  def main(args: Array[String]): Unit = {
    //(house beside tree above
    //cross(3).draw
    //chessboard(5).draw
    sierpinski(6).draw
  }
}

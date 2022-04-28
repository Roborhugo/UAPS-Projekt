import java.io.FileWriter
import java.io.File
@main
def main(n: Int, randomOut: String, partialOut: String): Unit =
  val random: Vector[Int] = randomVector(n)
  val partial: Vector[Int] = partiallySorted(random)
  writeFile(randomOut, random)
  writeFile(partialOut, partial)
  

def randomVector(n: Int): Vector[Int] =
  (for i <- 1 to n yield (Math.random * n * 100 + 1).toInt).toVector

def partiallySorted(vector: Vector[Int]): Vector[Int] =
  var array = vector.sorted.toArray
  for i <- 0 until array.length - 4 do
    val v = scala.util.Random.shuffle(Vector(array(i), array(i+1), array(i+2), array(i+3), array(i+4)))
    for j <- 0 until 5 do
      array(i + j) = v(j)
  array.toVector

def writeFile(filename: String, vector: Vector[Int]): Unit =
  val writer = new FileWriter(filename)
  vector.foreach(i => writer.write(s"$i\n"))
  writer.close

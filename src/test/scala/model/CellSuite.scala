package model
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CellSuite extends FunSuite {

  test("Cell distance with itself is Null") {
    val c0 = Cell(0, 0)

    val dist = c0.dist2(c0)
    assert(dist === 0)
  }

  test("Cell distance another cell") {
    val c0 = Cell(0, 0)
    val c1 = Cell(-1, 1)

    val dist = c0.dist2(c1)
    assert(dist === 2)
  }

  test("Distance is refexive") {
    val c0 = Cell(0, 0)
    val c1 = Cell(-1, 1)

    val dist01 = c0.dist2(c1)
    val dist10 = c1.dist2(c0)
    assert(dist01 === dist10)
  }

  test("Cell is not a neighbor itself") {
    val c = Cell(0, 0)

    assert(!c.isNeighbor(c))
  }

  test("Cell is neighbor") {
    val c0 = Cell(0, 0)
    val c1 = Cell(-1, 1)

    assert(c0.isNeighbor(c1))
  }

  test("Cell is not neighbor") {
    val c0 = Cell(0, 0)
    val c1 = Cell(0, 2)

    assert(!c0.isNeighbor(c1))
  }

}
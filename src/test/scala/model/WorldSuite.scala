package model
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class WorldSuite extends FunSuite {

  test("Not exists in World") {
    val w = new World();
    assert(!w.exists(0, 0))
  }

  test("Exists in World") {
    val w = new World();
    w.addCell(0, 0)
    w.addCell(0, 1)
    w.addCell(-1, -1)

    assert(w.exists(0, 0))
    assert(w.exists(0, 1))
    assert(w.exists(-1, -1))
  }

  test("Test toString") {
    val w = World()
    w.addCell(0, 0)
    w.addCell(0, 1)
    w.addCell(-1, -1)

    val s = w.toString

    val expected = ".o\n" +
      ".o\n" +
      "o.\n"
    assert(s === expected)
  }

  test("Test create from String") {
    val s = """
..o
.o.
o.o
"""
    val w = World(s)
    assert(w.exists(0, 0))
    assert(w.exists(1, 1))
    assert(w.exists(2, 2))
    assert(w.exists(2, 0))
    assert(!w.exists(0, 2))
  }

  test("Neighbors of Cell 1") {
    val w = World()
    w.addCell(0, 0)
    w.addCell(0, 1)
    w.addCell(-1, -1)
    w.addCell(5, 7)

    val neighbors: Set[Cell] = w.getExistingNeighbors(0, 0)
    assert(neighbors != null);
    assert(neighbors.size == 2);
  }

  test("Neighbors of Cell 2") {
    val w = World()
    w.addCell(0, 1)
    w.addCell(1, 0)
    w.addCell(1, 1)
    w.addCell(-1, -1)
    w.addCell(5, 7)

    val neighbors: Set[Cell] = w.getExistingNeighbors(0, 0)
    assert(neighbors != null);
    assert(neighbors.size == 4);
  }

  test("Test Rule Breeding 1 ") {
    val w = World()
    w.addCell(0, 0)
    w.addCell(0, 1)
    w.addCell(-1, -1)
    w.addCell(5, 7)

    assert(w.existNext(0, 0))
  }

  test("Test Rule Breeding 2 ") {
    val w = World()
    w.addCell(0, 1)
    w.addCell(1, 0)
    w.addCell(-1, -1)
    w.addCell(5, 7)

    assert(w.existNext(0, 0))
  }

  test("Test Rule Overpopulation 1") {
    val w = World()
    w.addCell(0, 0)
    w.addCell(0, 1)
    w.addCell(-1, -1)
    w.addCell(5, 7)

    assert(w.existNext(0, 0))
  }

  test("Test Rule Overpopulation 2 ") {
    val w = World()
    w.addCell(0, 1)
    w.addCell(1, 0)
    w.addCell(1, 1)
    w.addCell(-1, -1)
    w.addCell(5, 7)

    assert(!w.existNext(0, 0))
  }

  test("Test next") {
    var w = World()
    w.addCell(0, 1)
    w.addCell(1, 0)
    w.addCell(-1, -1)

    w = w.next

    assert(w.exists(0, 0))
    assert(!w.exists(0, 1))
    assert(!w.exists(1, 1))
    assert(!w.exists(-1, -1))
  }

}
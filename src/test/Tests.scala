package test

import scala.reflect.Function
import model.World
import model.Cell

object Tests {

  def main(args: Array[String]) {
    testExists
    testNeighbors
    testCellDist
    testExistsNext
  }

  def testExists() {
    val w = new World();
    w.addCell(0, 0)
    assertTrue(_ => w.exists(0, 0));
    assertFalse(_ => w.exists(0, 1));
  }

  def testNeighbors() {
    val w = World()
    w.addCell(0, 1)
    w.addCell(-1, -1)
    w.addCell(5, 7)

    val neighbors: Set[Cell] = w.getExistingNeighbors(0, 0)
    assertTrue(_ => (neighbors != null));
    assertTrue(_ => (neighbors.size == 2));
  }
  
  def testExistsNext() {
    val w = World()
    w.addCell(0, 1)
    w.addCell(-1, -1)
    w.addCell(5, 7)

    assertTrue(_ => (w.existNext(0, 0) ))
  }
  
  

  def testCellDist {
    val c0 = Cell(0, 0)
    val c1 = Cell(-1, -1)

    assertTrue(_ => (c0.dist2(c0) == 0));

    assertTrue(_ => (c0.dist2(c1) == 2));
    assertTrue(_ => (c1.dist2(c0) == 2));
  }

  def assertTrue(fun: Function1[Any, Boolean]) {
    println(if (fun.apply()) "OK" else "Fail")
  }

  def assertFalse(fun: Function1[Any, Boolean]) {
    println(if (!fun.apply()) "OK" else "Fail")
  }
}
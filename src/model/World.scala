package model

import scala.collection.immutable.ListSet

/**
 * The World
 */
case class World {

  /** All Living cell. */
  var cells: ListSet[Cell] = ListSet();

  /**
   * Adds a cell.
   *
   * @param x the x
   * @param y the y
   */
  def addCell(x: Int, y: Int) {
    cells = cells + Cell(x, y);
  }

  /**
   * If a living cell exists.
   *
   * @param x the x
   * @param y the y
   * @return true, if living
   */
  def exists(x: Int, y: Int): Boolean = {
    cells.contains(Cell(x, y))
  }

  /**
   * Gets the Living Neighbors.
   *
   * @param x the x
   * @param y the y
   * @return the existing neighbors
   */
  def getExistingNeighbors(x: Int, y: Int) = {
    val c = Cell(x, y);
    val fun: Function1[Cell, Boolean] = isNeighbor(c);
    cells.filter(fun)
  }

  /**
   * Checks if is neighbor.
   *
   * @param init the initial cell
   * @param c the c
   * @return true, if is neighbor
   */
  // TODO test
  def isNeighbor(init: Cell)(c: Cell): Boolean = {
    (init.dist2(c)) <= 2
  }
  
  /**
   * Rules for a living cell next step.
   *
   * @param x the x
   * @param y the y
   * @return true, if living
   */
  def existNext(x: Int, y: Int): Boolean = {
    getExistingNeighbors(x, y).size match {
      case 2 => !exists(x, y)
      case 3 => true
      case _ => false
    }
  }

  /**
   * The new World after iteration.
   *
   * @return the world
   */
  // TODO Test
  def next: World = {
    val xList = cells.map(c => c.x)
    val yList = cells.map(c => c.y)

    // New world
    val w = World()
    for (x <- xList.min - 1 to xList.max + 1) {
      for (y <- yList.min - 1 to yList.max + 1) {
        if (existNext(x, y)) {
          w.addCell(x, y)
        }
      }
    }
    return w
  }

  // TODO display

}
package model

import scala.collection.immutable.ListSet
import scala.collection.mutable.StringBuilder

/**
 * The World
 */
class World {

  /** All Living cell. */
  var cells: ListSet[Cell] = ListSet();

  /**
   * Adds a cell.
   *
   * @param x the x
   * @param y the y
   */
  def addCell(x: Int, y: Int) {
    // Append on Set/List is '+' 
    // Note: The list is immutable, but we change the cells attributes
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
    cells.filter(c.isNeighbor)
  }
  /**
   * Rules for a living cell next step.
   *
   * @param x the x
   * @param y the y
   * @return true, if living
   */
  def existNext(x: Int, y: Int): Boolean = {
    // O 2,3 => O
    // F 3   => O
    // O < 2 => F
    // O > 3 => F
    //       => F  
    getExistingNeighbors(x, y).size match {
      case 2 => exists(x, y)
      case 3 => true
      case _ => false
    }
  }

  /**
   * The new World after iteration.
   *
   * @return the world
   */
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

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  override def toString = {
    val xList = cells.map(c => c.x)
    val yList = cells.map(c => c.y)

    val sb = new StringBuilder();
    for (y <- yList.max to yList.min by -1) {
      for (x <- xList.min to xList.max) {
        sb + (if (exists(x, y)) 'o' else '.')
      }
      sb + '\n'
    }
    sb.toString
  }
}

/**
 * Companion Object
 */
object World {

  /**
   * Empty Constructor
   */
  def apply() = new World()

  def apply(cells: ListSet[Cell]): World = {
    val w = World()
    w.cells = ListSet() ++ cells
    return w
  }

  def apply(s: String): World = {
    var row = s.split("\n").reverse

    var x: Int = 0
    var y: Int = 0
    var r: String = null
    var cells: ListSet[Cell] = ListSet()
    while (!row.isEmpty) {
      r = row.head
      row = row.tail
      x = 0
      for (c <- r) {
        // if o => addCell
        if ('o' == c) {
          cells = cells + Cell(x, y)
        }
        x += 1
      }
      y += 1
    }
    World(cells)
  }

}
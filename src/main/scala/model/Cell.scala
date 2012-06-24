package model

/**
 * A Cell.
 * Note: the case class generate toString, hashcode, equals
 *       a companion Object with Cell(x,y) constructor
 */
case class Cell(x: Int, y: Int) {

  /**
   * Square Distance with a cell.
   * Note: avoid to work with a Double, to get the real distance use Math.sqrt()
   * @param c the other cell
   * @return the square distance
   */
  def dist2(c: Cell): Int = {
    val xDelta = this.x - c.x
    val yDelta = this.y - c.y

    xDelta * xDelta + yDelta * yDelta
  }

  /**
   * Checks if cell is neighbor.
   *
   * @param c the cell
   * @return true, if is neighbor
   */
  def isNeighbor(c: Cell): Boolean = {
    val dist = dist2(c)
    (dist != 0) && (dist <= 2)
  }

}
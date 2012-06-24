import de.johoop.jacoco4sbt._
import JacocoPlugin._


name := "NewScalaGameOfLife"

version := "1.0"

scalaVersion := "2.9.2"

libraryDependencies += "org.scalatest" % "scalatest_2.9.2" % "1.8"

libraryDependencies += "junit" % "junit" % "4.10"

seq(jacoco.settings : _*)
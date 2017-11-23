name := "split-json"

version := "1.0"
scalaVersion := "2.12.4"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.6"
libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.6" % Test
libraryDependencies += "de.knutwalker" %% "akka-stream-json" % "3.3.0"
libraryDependencies += "org.spire-math" %% "jawn-parser" % "0.10.4"
libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.2.0"
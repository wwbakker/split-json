import java.nio.file.Paths

import scala.concurrent.Await
import scala.concurrent.duration._

object Application extends App {

  override def main(args: Array[String]): Unit =
    args.toList match {
      case path :: Nil =>
        Await.ready(Splitter.split(Paths.get(path)), Duration.Inf)
      case _ =>
        System.err.println(s"usage: split-json-assembly-1.0.jar [filename.json]")
    }

}
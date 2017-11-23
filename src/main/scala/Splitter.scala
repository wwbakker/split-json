import better.files._
import java.nio.file.Path

import akka.actor.{ActorSystem, Terminated}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.FileIO
import de.knutwalker.akka.stream.JsonStreamParser
import jawn.{AsyncParser, Facade, SimpleFacade}
import play.api.libs.json._

import scala.concurrent.{ExecutionContext, Future}

object Splitter {

  implicit val facade: Facade[JsValue] =
    new SimpleFacade[JsValue] {
      def jnull() : JsNull.type = JsNull
      def jfalse() = JsBoolean(false)
      def jtrue() = JsBoolean(true)
      def jnum(s: String) = JsNumber(BigDecimal(s))
      def jint(s: String) = JsNumber(BigDecimal(s))
      def jstring(s: String) = JsString(s)
      def jarray(vs: List[JsValue]) = JsArray(vs)
      def jobject(vs: Map[String, JsValue]) = JsObject(vs)
    }

  def split(filePath: Path): Future[Terminated] = {
    implicit val system: ActorSystem = ActorSystem("reactive-tweets")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val ec : ExecutionContext = system.dispatcher


    FileIO.fromPath(filePath)
      .via(JsonStreamParser[JsValue](AsyncParser.UnwrapArray))
      .map(_.as[JsObject])
      .runFold(0)((filenumber, jsObject) => {
        val file = File(filePath.toFile.getName + "." + filenumber)
        println(s"writing $file")
        file.createIfNotExists().overwrite(Json.prettyPrint(jsObject))
        filenumber + 1
      })
      .flatMap(i =>
      {
        println(s"done: $i files created. shutting down actor system")
        system.terminate()
      })

  }


}

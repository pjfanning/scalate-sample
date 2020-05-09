package com.example.scalate

import java.util.ArrayList

import org.fusesource.scalate.{DefaultRenderContext, TemplateEngine}
import org.fusesource.scalate.support.StringTemplateSource
import org.slf4j.LoggerFactory

case class Strings(strings: ArrayList[String])

object ScalateSample extends App {
  val log = LoggerFactory.getLogger(ScalateSample.getClass)

  val template =
    """#import(scala.collection.JavaConverters._)
      |<%@ var testVal: com.example.scalate.Strings %>
      |#for (str <- testVal.strings.asScala)
      |  ${str}
      |#end
      |""".stripMargin

  val jlist = new ArrayList[String]()
  jlist.add("hello world")
  log.info(transform(template, Map("testVal" -> Strings(jlist))))

  def transform(input: String, tokens: Map[String, Any]): String = {
    val filename = "test.ssp"
    val engine = new TemplateEngine()
    val source = new StringTemplateSource(filename, input)
    val template = engine.compile(source)
    val ctx = new DefaultRenderContext(filename, engine)
    tokens.foreach { case (key, value) =>
      ctx.setAttribute(key, Some(value))
    }
    ctx.capture(template)
  }
}

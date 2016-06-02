import java.net.URI

/**
  * Created by T440P on 2016/6/2.
  */
object URIDemo {
  def main(args: Array[String]) {
    val url = "https://www.google.com/search?newwindow=1&q=set+git+profile+in+intellij&bav=on.2,or.&cad=b&biw=1920&bih=977&dpr=1&ech=1&psi=X5lPV8iJCYjmvgTHwJ1I.1464834402877.3&ei=X5lPV8iJCYjmvgTHwJ1I&emsg=NCSR&noj=1"
    val uri = new URI(url).normalize()
    println("uri.getScheme: " + uri.getScheme)
    println("uri.getHost: " + uri.getHost)
    println("uri.getRawPath: " + uri.getRawPath)
    println("uri.getRawQuery: " + uri.getRawQuery)
    println("uri.getRawFragment: " + uri.getRawFragment)
  }
}

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.time.StopWatch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.jsoup.Jsoup.*;

public class webscrappingtest2 {
  public static void main(String[] args)throws XPathExpressionException, IOException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    /**
    try{
      //load HTML from URL
      Document document = Jsoup.connect("//www.lyricsplanet.com/search.php?field=title&value=A").get();
      //get title of HTML page
      System.out.println(document.title());

      //get meta information
      String description = document.select("p[href^='lyrics.php?']").get(0).attr("content");
      System.out.println("Meta Description : " + description);

      String keywords = document.select("meta[name=keywords]").first().attr("content");
      System.out.println("Meta keyword : " + keywords);
    }catch(IOException e){e.printStackTrace();}
    */


    String searchURL = "https://www.lyricsplanet.com/search.php?field=title&value=A";

    //creat web client to make a request and disable javascript to speed up page loading
    WebClient client = new WebClient();
    client.getOptions().setCssEnabled(false);
    client.getOptions().setJavaScriptEnabled(false);

    HtmlPage page = client.getPage(searchURL);

    //create new connection and fetch(.get()) and parse a HTML file
    //document object represents the HTML DOM
    //Jsoup main class to parse given HTML string
    Document doc1 = Jsoup.connect(searchURL).get();
    Elements ps = doc1.select("p");
    //Element title = doc1.select("dv");
    //System.out.println(title);

    String url = "//*[@id=\"search\"]/div[2]/div/div[2]/table/tbody/tr[1]/td/a";
    //File input = new File("webscrappingtest2");
    //parse HTML into document
    //Document doc = parse(input, "UTF-8", searchURL);
    List hi = page.getByXPath("//*[@id=\"search\"]/div[2]/div/div[2]/table/tbody/tr[1]/td/a");

    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();
    XPathExpression expr = xpath.compile(url);
    String divContent = (String) xpath.evaluate(url,doc1, XPathConstants.STRING);
    System.out.println(divContent);


    System.out.println(hi);


    stopWatch.stop();
    System.out.println(stopWatch.getTime());

  }
}

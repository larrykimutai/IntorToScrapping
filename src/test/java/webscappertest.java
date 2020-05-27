import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.xml.internal.bind.api.impl.NameConverter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Entities;
import org.jsoup.safety.Whitelist;
import org.xml.sax.InputSource;
import org.jsoup.nodes.Document;
import sun.nio.cs.StandardCharsets;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class webscappertest {
  public static void main(String[] args)throws IOException {

    //creat web client to make a request and disable
    //javascript to speed up page loading if applicable
    WebClient client = new WebClient();
    client.getOptions().setCssEnabled(false);
    client.getOptions().setJavaScriptEnabled(false);

    String searchUrl = "https://raleigh.craigslist.org/search/sss?query=iphone+6s&sort=rel";
    HtmlPage page = client.getPage(searchUrl);


  //get html
    String content = null;
    URLConnection connection = null;

    try{
      connection = new URL(searchUrl).openConnection();
      Scanner scanner = new Scanner(connection.getInputStream());
      scanner.useDelimiter("\\Z");
      content = scanner.next();
      scanner.close();
    } catch (Exception e){
      e.printStackTrace();
    }

    String html = content;

    //parse input HTML into a document
    Document doc = Jsoup.parse(html,searchUrl);

    //Document doc = Jsoup.parse(content, "UTF-8", "https://raleigh.craigslist.org/search/sss?query=iphone+6s&sort=rel");
    //Document doc = Jsoup.parse(Jsoup.clean(html, searchUrl, Whitelist.relaxed().preserveRelativeLinks(true)));

    //create new connection and get() and parse HTML file.
    Document doc2 = Jsoup.connect(searchUrl).get();
    String title = doc.title();
    System.out.println(title);

  }
}

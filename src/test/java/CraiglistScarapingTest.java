

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

public class CraiglistScarapingTest {
  public static void main(String[] args) throws IOException {

    String searchQuery = "iphone 6s";

    //creat web client to make a request and disable
    //javascript to speed up page loading if applicable
    WebClient client = new WebClient();
    client.getOptions().setCssEnabled(false);
    client.getOptions().setJavaScriptEnabled(false);

    //HtmlPage Object will contain the HTML code (can be accessed with asXml() method)
    try{
      String searchUrl = "https://raleigh.craigslist.org/search/sss?query=iphone+6s&sort=rel";
      HtmlPage page = client.getPage(searchUrl);
    } catch (Exception e){e.printStackTrace();}

    //make an xpath expression and select all <p> tags that have a class 'result-info'
    HtmlPage page = client.getPage("https://raleigh.craigslist.org/search/sss?query=iphone+6s&sort=rel");
    //List<HtmlElement> item = (List<HtmlElement>) page.getByXPath("");
  }
}

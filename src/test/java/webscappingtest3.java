import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.eclipse.jetty.io.ssl.ALPNProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class webscappingtest3 {
  public static void main(String[] args) throws Exception {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    String searchURL = "https://www.lyricsplanet.com/search.php?field=title&value=A";

    //creat web client to request URL
    WebClient client = new WebClient();
    client.getOptions().setCssEnabled(false);
    client.getOptions().setJavaScriptEnabled(false);

    HtmlPage page = client.getPage(searchURL);
    Document doc = Jsoup.connect(searchURL).get();

    //get HTML as string
    //get html
    String content = null;
    URLConnection connection = null;


    //convert web page HTML into String
    try{
      connection = new URL(searchURL).openConnection();
      Scanner scanner = new Scanner(connection.getInputStream());
      scanner.useDelimiter("\\Z");
      content = scanner.next();
      scanner.close();
    } catch (Exception e){
      e.printStackTrace();
    }

    String html = content;

    //find pattern
    Pattern p = Pattern.compile("A (.*?)</a>");
    Matcher m = p.matcher(html);

    while(m.find()){
      String codeGroup = m.group(1);

      //print group
      System.out.println("A " + codeGroup);
    }


    String title = StringUtils.substringBetween(html,"<td>","</td>");

    stopWatch.stop();
    long time = stopWatch.getTime();
    System.out.println(time);
  }
}

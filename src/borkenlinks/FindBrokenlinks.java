package borkenlinks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FindBrokenlinks {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
WebDriver driver=new FirefoxDriver();

driver.get("https://www.google.com/");
List<WebElement> links=driver.findElements(By.tagName("a"));
for(int i=0;i<links.size();i++)
{
	WebElement ele=links.get(i);
	String url=ele.getAttribute("href");
	verify(url);
}


	}
	public static void verify(String url) throws IOException
	
{
		try{
		URL link=new URL(url);
		HttpURLConnection httpConn=(HttpURLConnection)link.openConnection();
		httpConn.setConnectTimeout(2000);
		httpConn.connect();
		//user get response 
		if(httpConn.getResponseCode()==200)
		{
			System.out.println(url+"-"+httpConn.getResponseMessage());
		}
		
		if(httpConn.getResponseCode()==500)
		{
			System.out.println(url+"-"+httpConn.getResponseMessage());
		}
		}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
		
	}

}

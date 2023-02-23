package ru.dna.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

  public static Date StrToDate(String date_in, String format_in) throws ParseException
  {
      SimpleDateFormat format = new SimpleDateFormat(format_in, Locale.US);
      Date date_out = format.parse(date_in);
      return date_out;
  }
  
  public static String DateToStr(Date date_in, String format_in)
  {
      SimpleDateFormat formatter = new SimpleDateFormat(format_in, Locale.US);
      return formatter.format(date_in);
  }
  
  public static String readFileAsString(String filePath) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
    String line = "";
    StringBuilder result = new StringBuilder(line + "\n");
    while (line != null) {
        line = br.readLine();
        if (line != null) {
            result.append(line + "\n");
        }
    }
    br.close();
    return result.toString();
  }
  
  public static ArrayList<String> readFileAsList(String filePath) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
    String line = "";
    ArrayList<String> res = new ArrayList<String>();
    while (line != null) {
        line = br.readLine();
        if (line != null) {
          res.add(line.trim());
        }
    }
    br.close();   
    return res;
  }
  
  public static String regexpLike(String text, String pattern) {
    Pattern rx = Pattern.compile(pattern, Pattern.DOTALL);
    Matcher matcher = rx.matcher(text);
    if (matcher.find())
    {
      String result = matcher.group(1);
      if (result != null && !result.isEmpty()) {
        return result;
      }
      else return "";
    }
    else {
      return "";
    }
  }
  
  public static String trim(String str, int len) {
    if (str == null) return null;
    return str.substring(0, Math.min(str.length(), len));
  }
  
  public static String httpGet(String url, String content_type, boolean debug) throws Exception
  {
    if (debug) System.out.println("Requesting url: " + url);
    URL client = new URL(url);
    //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy_name, proxy_port));
    HttpURLConnection con = (HttpURLConnection) client.openConnection(); //openConnection(proxy);
    con.setRequestMethod("GET");
    con.setRequestProperty("Accept", content_type);
    con.setRequestProperty("Content-type", content_type + "; charset=UTF-8");
    //con.setConnectTimeout(10000);
    con.connect();
 
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
    }
    in.close();

    return content.toString();
  }

}

package scraping.abstractscollector;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Himanshu
 */
public class ProceedingsURLGenerator {
    
public static List<Integer> ids = new ArrayList<>();
public static List<Integer> urls = new ArrayList<>();
public static String browser = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
public static URL url;
public static Runtime runtime = Runtime.getRuntime();
public static PrintStream out;
 public ProceedingsURLGenerator() throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException
 {
 System.setProperty("java.net.useSystemProxies", "true");
 for (String line : Files.readAllLines(Paths.get("DL_xrds_Magazine_URL_IDs.txt")))
    {
    for (String part : line.split("\\s+")) {
        Integer i = Integer.valueOf(part);
        ids.add(i);
    }
    }
 out=new PrintStream(new FileOutputStream("DL_xrds_Magazine_URLs.txt"));
    try
    {
    for(int i=0;i<ids.size();i++)
    {
    url = new URL("http://dl.acm.org/tab_about.cfm?id="+ids.get(i)+"&type=proceeding&parent_id="+ids.get(i));
    //System.out.println(url);
    out.append(url.toString());
    out.append("\n");
    //System.setOut(out);
    }}
    catch(Exception e)
    {
    System.out.println(e);
    }
 } 
}

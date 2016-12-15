/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scraping.abstractscollector;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Himanshu
 */
public class ACMCitationCountFinder {
   Document doc;
   Citations citations;
   
   
    public ACMCitationCountFinder () throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException
        {
            java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Citation counts");
            ArrayList<String> list_of_titles = new ArrayList<>();
            ArrayList<Citations> listOfCitations = new ArrayList<>();
            Scanner in = new Scanner(new FileReader("sample_titles.txt"));
            while (in.hasNextLine()) {
            list_of_titles.add(in.nextLine());
        }
       
       for(int i=0 ; i<list_of_titles.size();i++)
       {
           //Thread.sleep((long)(Math.random() * 4000));
           
           citations = new Citations();
      final WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);
      webClient.getOptions().setThrowExceptionOnScriptError(false);
      HtmlPage page = webClient.getPage("http://dl.acm.org/");
      HtmlInput searchBox = page.getElementByName("query");
      searchBox.setValueAttribute(list_of_titles.get(i));
     citations.setTitle_name(list_of_titles.get(i));
      
      HtmlImageInput acmSearchSubmitButton = page.getElementByName("Go");
      page =  (HtmlPage) acmSearchSubmitButton.click();
      System.out.println(i+1);
      System.out.println(list_of_titles.get(i));//
      doc = Jsoup.connect(page.toString().replace("HtmlPage(","")).header("Accept-Encoding", "gzip, deflate")
            .userAgent("Chrome")
            .maxBodySize(0)
            .timeout(60000)
            .get();
      System.out.println(page.toString().replace("HtmlPage(",""));
      citations.setGoogle_scholar_title_url(page.toString().replace("HtmlPage(",""));
      
     Elements papers = doc.select("span[class = citedCount]"); 
     for(Element src:papers)
     {
         
     if(!src.text().isEmpty())
     {
     System.out.println(src.text());
     System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------"+"\n");
     citations.setCitation_count(src.text());
     break;
     }
     
        }
     
     listOfCitations.add(citations);
     
    }     
 System.out.println("Citation list size--->"+listOfCitations.size()); 
 for(int r=0;r<listOfCitations.size();r++)
           {
               
           HSSFRow row = sheet.createRow(r);
           row.createCell(0).setCellValue(listOfCitations.get(r).getTitle_name());
           row.createCell(1).setCellValue(listOfCitations.get(r).getGoogle_scholar_title_url());
           row.createCell(2).setCellValue(listOfCitations.get(r).getCitation_count());
           
           }
           FileOutputStream fos = new FileOutputStream("sample_titles.xls");
            workbook.write(fos);
             fos.close(); 
}       
}

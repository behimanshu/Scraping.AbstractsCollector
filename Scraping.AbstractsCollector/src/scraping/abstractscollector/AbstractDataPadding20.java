/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scraping.abstractscollector;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Himanshu
 */
public class AbstractDataPadding20 {
     public static final String GOOGLE_SEARCH_URL = "http://dl.acm.org/tab_about.cfm?id=2858036&type=proceeding&sellOnline=1&parent_id=2858036&parent_type=proceeding&title=Proceedings%20of%20the%202016%20CHI%20Conference%20on%20Human%20Factors%20in%20Computing%20Systems&toctitle=CHI%20Conference%20on%20Human%20Factors%20in%20Computing%20Systems&tocissue_date=&notoc=0&usebody=tabbody&tocnext_id=&tocnext_str=&tocprev_id=&tocprev_str=&toctype=conference&cfid=667626098&cftoken=42295552&_cf_containerId=prox&_cf_nodebug=true&_cf_nocache=true&_cf_clientid=2DA92176CE2BF469F17D59C1481A9337&_cf_rc=1";
     Document doc;

        public AbstractDataPadding20() throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException
        {
            
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ResearchTopics.csv"), "UTF-8"));
        HSSFWorkbook workbook = new HSSFWorkbook();

HSSFSheet sheet = workbook.createSheet("ResearchTopics");
        
        ResearchPapers research;
        ArrayList<String> title_list = new ArrayList<>();
        ArrayList<String> urls_list = new ArrayList<>();
        ArrayList<String> abstracts_list = new ArrayList<>();
        ArrayList<String> doi_list = new ArrayList<>();
        ArrayList<String> authors_list = new ArrayList<>();
        ArrayList<String> abstracts_toShow_list = new ArrayList<>();
        ArrayList<String> individual_data = new ArrayList<>();
        ArrayList<ResearchPapers> research_list = new ArrayList<>();
        ArrayList<String> list_of_URLs = new ArrayList<>();

        String searchURL = GOOGLE_SEARCH_URL;
        //without proper User-Agent, we will get 403 error

        Scanner in = new Scanner(new FileReader("Padding20_URLs.txt"));
        while (in.hasNext()) {
            list_of_URLs.add(in.next());
        }



        for (int k = 0; k < list_of_URLs.size(); k++)
        {
            doc = Jsoup.connect(list_of_URLs.get(k)).header("Accept-Encoding", "gzip, deflate")
            .userAgent("Chrome")
            .maxBodySize(0)
            .timeout(600000)
            .get();

            Elements papers = doc.select("td span"); //[style=padding-left:20]
            //Elements abstracts = doc.select("div span[id*=toHide] div");/* Padding left:0 for urls with padding 0 */
                int author_count = 0;
                int url_count=0;
                int l = 1;
                System.out.println(k);
                for (Element src : papers)
                {
                    
                        //src.getElementsByAttributeValueContaining("style", "padding-left:20").hasAttr(searchURL)
                        //System.out.println(src.getElementsByAttributeValueContaining("style", "padding-left:20").text());//src.text());
                    if(src.getElementsByTag("span").attr("style").contains("padding-left:20"))
                    {
                        if(src.getElementsByTag("a").attr("href").contains("citation.cfm") && !src.text().isEmpty())
                        {
                            title_list.add(src.text());
                           /* if(author_count == 0 && l > 1)// /* Remove "&& l > 1" condition for handling different urls
                            {
                                //System.out.print("T");
                                author_count = 0;
                                authors_list.add("");
                                System.out.println("In author list");

                            }
                            if(url_count==0 && l>1)
                            {
                            url_count=0;
                            urls_list.add("");
                            System.out.println("In URL shit list");
                            }*/
                        }
                        else if(src.getElementsByTag("a").attr("href").contains("author_page.cfm") && !src.text().isEmpty())
                        {
                            author_count = 1;
                            
                            authors_list.add(src.text());
                        }
                        else if(src.getElementsByTag("a").attr("href").contains("ft_gateway.cfm") && !src.text().isEmpty() && src.text().contains("PDF"))
                         {
                            url_count=1;
                            //System.out.println(src.getElementsByTag("a").attr("href"));
                            urls_list.add(src.getElementsByTag("a").attr("href"));
                         }
                        else if(src.getElementsByTag("a").attr("title").contains("DOI") && !src.text().isEmpty())
                        {
                        doi_list.add(src.text());
                        }
                    }
                    
                    //Elements abstracts = doc.select("div span[id*=toHide] div");
                    else if(src.getElementsByTag("span").attr("id").contains("toHide"))
                    {
                       abstracts_list.add(src.text());
                    }

                }
                
                System.out.println("Title size-->" + title_list.size());
                System.out.println("Authors list--->" + authors_list.size());
                System.out.println("URL List size is--->"+urls_list.size());
                System.out.println("DOI List size is--->"+doi_list.size());
                System.out.println("Abstracts list is--->"+abstracts_list.size());
                //title_list.clear();
                //authors_list.clear();
                
                
                
            }

   try
           {
               for (int j = 0; j < abstracts_list.size() && j<title_list.size() && j<authors_list.size() && j<urls_list.size(); j++) {
                    research = new ResearchPapers();
                    research.setTitles(title_list.get(j));
                    research.setLinks(urls_list.get(j));
                    research.setAbstracts(abstracts_list.get(j));
                    research.setAuthors(authors_list.get(j));
                    research.setDoi(doi_list.get(j));
                    research_list.add(research);

                }
                System.out.println("Research List isize------->"+research_list.size());
           for(int r=0;r<research_list.size();r++)
           {
           HSSFRow row = sheet.createRow(r);
           row.createCell(0).setCellValue(research_list.get(r).getTitles());
           row.createCell(1).setCellValue(research_list.get(r).getAuthors());
           row.createCell(2).setCellValue(research_list.get(r).getLinks());
           row.createCell(3).setCellValue(research_list.get(r).getDoi());
           row.createCell(4).setCellValue(research_list.get(r).getAbstracts());
           }
           FileOutputStream fos = new FileOutputStream("ResearchPapers_Padding20.xls");
            workbook.write(fos);
             fos.close();
            
           }
           catch(Exception e)
           {
           System.out.println(e);
                   }
           

Thread.sleep(5000);
        }
      
    }
            
        
           /* try {

                for (ResearchPapers research1 : research_list) {

                    StringBuffer buffer = new StringBuffer();

                    buffer.append("\"" + research1.getTitles() + "\"");
                   //buffer.append(research1.getTitles());
                    buffer.append(",");
                    buffer.append("\"" + research1.getAuthors() + "\"");
                    buffer.append(",");
                    buffer.append("\"" + research1.getLinks() + "\"");
                    buffer.append(",");
                    buffer.append("\"" + research1.getDoi() + "\"");
                    buffer.append(",");
                    buffer.append("\"" + research1.getAbstracts() + "\"");
                    buffer.append(",");
                    bw.write(buffer.toString());
                    bw.newLine();
                }
                bw.newLine();
                bw.flush();
                bw.close();


            }
catch (UnsupportedEncodingException e) {
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
*/
        





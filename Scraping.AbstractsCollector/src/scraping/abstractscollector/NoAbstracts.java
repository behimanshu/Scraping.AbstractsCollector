/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scraping.abstractscollector;

/**
 *
 * @author Himanshu
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static scraping.abstractscollector.AbstractDataPadding20.GOOGLE_SEARCH_URL;

public class NoAbstracts {

    Document doc;

    public NoAbstracts() throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException {

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

        Scanner in = new Scanner(new FileReader("Padding0_NoAbstracts_URLs.txt"));
        while (in.hasNext()) {
            list_of_URLs.add(in.next());
        }

       for (int k = 0; k < list_of_URLs.size(); k++) {
            doc = Jsoup.connect(list_of_URLs.get(k)).header("Accept-Encoding", "gzip, deflate")
                    .userAgent("Chrome")
                    .maxBodySize(0)
                    .timeout(600000)
                    .get();

            Elements papers = doc.select("td span"); //[style=padding-left:20]
            //Elements abstracts = doc.select("div span[id*=toHide] div");/* Padding left:0 for urls with padding 0 */
            int title_count = 1;
            int abstract_count = 1;
            int url_count = 0;
            int l = 1;
            boolean url_check = false;
            // System.out.println(k);
            for (Element src : papers) {
                url_check = false;
                //src.getElementsByAttributeValueContaining("style", "padding-left:20").hasAttr(searchURL)
                //System.out.println(src.getElementsByAttributeValueContaining("style", "padding-left:20").text());//src.text());
                if (src.getElementsByTag("span").attr("style").contains("padding-left:0")) {
                    if(!src.getElementsByTag("a").attr("href").isEmpty())
                    {
                    if (src.getElementsByTag("a").attr("href").contains("citation.cfm") && !src.text().isEmpty()) {
                        title_list.add(src.text());
                        abstracts_list.add("");
                        url_check=true;
                        /*  if(url_count == 0 && l>1)// /* Remove "&& l > 1" condition for handling different urls
                            {
                                //System.out.print("T");
                                url_count = 0;
                                urls_list.add("");
                                System.out.println("In URL list");

                            }*/
                    }
                    else if (src.getElementsByTag("a").attr("href").contains("author_page.cfm") && !src.text().isEmpty()) {
                        //author_count = 1;

                        authors_list.add(src.text());
                        url_check=true;
                    }
                    else if (src.getElementsByTag("a").attr("title").contains("DOI") && !src.text().isEmpty()) {
                        doi_list.add(src.text());
                        url_check=true;
                    }

                    //startsWith("ft_gate")
                    
                    else if (src.getElementsByTag("a").attr("href").contains("ft_gateway.cfm")) {
                            if (!src.text().isEmpty() && src.text().contains("PDF")) {
                                //url_count=1;
                                url_check = false;
                                //System.out.println(src.getElementsByTag("a").attr("href"));
                                urls_list.add(src.getElementsByTag("a").attr("href"));
                            }   
                        }
                       
                      

                    

                    /* else if(src.getElementsByTag("a").attr("href").contains("ft_gateway.cfm") && !src.text().isEmpty() && !src.text().contains("PDF"))
                        {
                        urls_list.add("");
                        System.out.println("null added");
                        }*/
                   
                    /*else if(src.getElementsByTag("a").attr("href").contains("ft_gateway.cfm") && src.text().contains("Mp3 Audio only") && src.text().contains("Mp4 Video") && !src.text().contains("PDF"))
                        {
                         System.out.println(src.text());
                        urls_list.add("");
                        }*/

                }}
                else
                            {
                            urls_list.add("");
                            }
                //Elements abstracts = doc.select("div span[id*=toHide] div");
                //l=2;
            }

            System.out.println("Title size-->" + title_list.size());
            System.out.println("Authors list--->" + authors_list.size());
            System.out.println("URL List size is--->" + urls_list.size());
            System.out.println("DOI List size is--->" + doi_list.size());
            System.out.println("Abstracts list is--->" + abstracts_list.size());
            //title_list.clear();
            //authors_list.clear();

            for (int j = 0; j < abstracts_list.size() && j < title_list.size() && j < authors_list.size() && j < urls_list.size(); j++) {
                research = new ResearchPapers();
                research.setTitles(title_list.get(j));
                research.setLinks(urls_list.get(j));
                research.setAbstracts(abstracts_list.get(j));
                research.setAuthors(authors_list.get(j));
                research.setDoi(doi_list.get(j));
                research_list.add(research);

            }
            System.out.println("Research List isize------->" + research_list.size());
        }

        try {
            for (int r = 0; r < research_list.size(); r++) {
                HSSFRow row = sheet.createRow(r);
                row.createCell(0).setCellValue(research_list.get(r).getTitles());
                row.createCell(1).setCellValue(research_list.get(r).getAuthors());
                row.createCell(2).setCellValue(research_list.get(r).getLinks());
                row.createCell(3).setCellValue(research_list.get(r).getDoi());
                row.createCell(4).setCellValue(research_list.get(r).getAbstracts());
            }
            FileOutputStream fos = new FileOutputStream("ResearchPapers_NoAbstracts.xls");
            workbook.write(fos);
            fos.close();

        } catch (Exception e) {
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

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
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Himanshu
 */
public class URLIDCollector {

    Document doc;
    public static PrintStream out;
    public static List<String> URLIDs = new ArrayList<>();

    public URLIDCollector() throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException {
        doc = Jsoup.connect("http://dl.acm.org/tab_about.cfm?id=J1271&type=proceeding&parent_id=J1271").header("Accept-Encoding", "gzip, deflate")
                .userAgent("Chrome")
                .maxBodySize(0)
                .timeout(600000)
                .get();

        Elements ids = doc.select("a");
        for (Element src : ids) {
            if (src.attr("href").contains("citation.cfm") && !src.text().isEmpty()) {
                URLIDs.add(src.attr("href"));
            }
        }
        out = new PrintStream(new FileOutputStream("DL_xrds_Magazine_URL_IDs.txt"));
        try {
            for (int i = 0; i < URLIDs.size(); i++) {
               
                out.append(URLIDs.get(i).substring(16, 23).replaceAll("&", "").replaceAll("C", "").replaceAll("F", "").replaceAll("I","").replaceAll("D",""));
                out.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
      new ProceedingsURLGenerator();
    }
}

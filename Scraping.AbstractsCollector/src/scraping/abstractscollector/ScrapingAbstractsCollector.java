/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scraping.abstractscollector;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author Himanshu
 */
public class ScrapingAbstractsCollector {


    public static void main(String[] args) throws IOException, InterruptedException {
      //AbstractDataPadding20 pattern1 = new AbstractDataPadding20(); 
      //AbstractsWithPadding0 pattern2 = new AbstractsWithPadding0();
      //NoAbstracts pattern3 = new NoAbstracts();
       DifferentContent pattern4= new DifferentContent();
    
}}
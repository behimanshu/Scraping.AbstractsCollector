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
public class ResearchPapers {
    
    String titles, links, abstracts, authors,doi;
    
    
    public void ResearchPapers()
    {
    this.titles = titles;
    this.links=links;
    this.abstracts=abstracts;
    this.authors = authors;
    this.doi=doi;
    }
    
     public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }  
    
     public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }  
    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }  
    
     public String toString() {
        return "ResearchPapers{" +
                "titles='" + titles + '\'' +
                ", links='" + links + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", doi='" + doi + '\'' +
                '}';
    }
}

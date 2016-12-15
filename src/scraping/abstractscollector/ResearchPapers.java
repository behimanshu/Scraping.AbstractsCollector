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
    
    private String titles, links, abstracts, authors,doi, id;
  
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                "titles='" + titles + '\'' +
                ", links='" + links + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", doi='" + doi + '\'' +
                '}';
    }
}

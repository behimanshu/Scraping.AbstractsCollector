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
public class Citations {
    String title_name, google_scholar_title_url, citation_count;

    public Citations(String title_name, String google_scholar_title_url, String citation_count) {
        this.title_name = title_name;
        this.google_scholar_title_url = google_scholar_title_url;
        this.citation_count = citation_count;
    }

    public Citations() {
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getGoogle_scholar_title_url() {
        return google_scholar_title_url;
    }

    public void setGoogle_scholar_title_url(String google_scholar_title_url) {
        this.google_scholar_title_url = google_scholar_title_url;
    }

    public String getCitation_count() {
        return citation_count;
    }

    public void setCitation_count(String citation_count) {
        this.citation_count = citation_count;
    }

    @Override
    public String toString() {
        return "Citations{" + "title_name=" + title_name + ", google_scholar_title_url=" + google_scholar_title_url + ", citation_count=" + citation_count + '}';
    }
    
    
}

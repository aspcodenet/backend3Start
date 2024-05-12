package se.systementor.backend3start.demos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;


public class book {
    @JacksonXmlProperty(isAttribute = true)
    public String id;

    public String author;
    public String title;

    @JacksonXmlProperty(localName = "genre")
    public String category;

    public float price;
    //public price price;
    @JacksonXmlProperty(localName = "publish_date")
    public Date publishDate;

    public String description;
}


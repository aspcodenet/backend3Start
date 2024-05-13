package se.systementor.backend3start.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id")
    public int id;


    @Column(name="ExternalSystemID")
    private String externalSystemId;

    @Column(name="Author")
    private String author;

    @Column(name="Title")
    private String title;

    @Column(name="Category")
    private String category;

    @Column(name="Price")
    private float price;

    @Column(name="PublishDate")
    private Date publishDate;

    @Column(name="Description")
    private String description;



}

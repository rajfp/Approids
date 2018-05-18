package com.example.rajat.approids;

/**
 * Created by Rajat on 28-Feb-18.
 */
public class Product {
    private int id;
    private String title;
    private String desc;


    public Product( String title, String desc) {
        this.id=id;
        this.title = title;
        this.desc = desc;

    }



    public String getTitle() {
        return title;
    }

    public String getdesc() {
        return desc;
    }


}
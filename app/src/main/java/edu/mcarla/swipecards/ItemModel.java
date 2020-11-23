package edu.mcarla.swipecards;

public class ItemModel {

    private int image;
    private String title, genre, year;



    public ItemModel() {

    }

    public ItemModel(int image, String nama, String usia, String kota) {
        this.image = image;
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }
}

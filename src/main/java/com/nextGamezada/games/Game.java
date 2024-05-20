package com.nextGamezada.games;


public class Game {

    private String name;

    boolean isFinished;

    private long id;

    private int maxPlayers;

    private boolean isCoop;

    private String genre;

    private String price;

    private boolean onSale;

    public Game() {
        id = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean isCoop() {
        return isCoop;
    }

    public void setCoop(boolean coop) {
        isCoop = coop;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", isFinished=" + isFinished +
                ", id=" + id +
                ", maxPlayers=" + maxPlayers +
                ", isCoop=" + isCoop +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", onSale=" + onSale +
                '}';
    }
}
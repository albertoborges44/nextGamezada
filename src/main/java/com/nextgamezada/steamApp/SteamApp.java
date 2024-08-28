package com.nextgamezada.steamApp;

public class SteamApp {

    private int appid;

    private String name;

    public SteamApp(int appid, String name) {
        this.appid = appid;
        this.name = name;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SteamApp{" +
                "appid=" + appid +
                ", name='" + name + '\'' +
                '}';
    }
}

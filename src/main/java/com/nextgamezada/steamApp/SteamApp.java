package com.nextgamezada.steamApp;

public class SteamApp {

    private long appid;

    private String name;

    public SteamApp(long appid, String name) {
        this.appid = appid;
        this.name = name;
    }

    public long getAppid() {
        return appid;
    }

    public void setAppid(long appid) {
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

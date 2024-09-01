package com.nextgamezada.steamApp;

public class Data {
    private String type;

    private String name;

    private long steam_appid;

    private int required_age;

    private boolean is_free;

    private String short_description;

    private PriceOverview priceOverview;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSteam_appid() {
        return steam_appid;
    }

    public void setSteam_appid(long steam_appid) {
        this.steam_appid = steam_appid;
    }

    public int getRequired_age() {
        return required_age;
    }

    public void setRequired_age(int required_age) {
        this.required_age = required_age;
    }

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public PriceOverview getPriceOverview() {
        return priceOverview;
    }

    public void setPriceOverview(PriceOverview priceOverview) {
        this.priceOverview = priceOverview;
    }
}

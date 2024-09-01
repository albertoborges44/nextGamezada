package com.nextgamezada.steamApp;

public class PriceOverview {

    private String currency;

    private int discount_percent;

    private String final_formatted;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(int discount_percent) {
        this.discount_percent = discount_percent;
    }

    public String getFinal_formatted() {
        return final_formatted;
    }

    public void setFinal_formatted(String final_formatted) {
        this.final_formatted = final_formatted;
    }
}

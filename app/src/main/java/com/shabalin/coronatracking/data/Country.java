package com.shabalin.coronatracking.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("Slug")
    @Expose
    private String slug;
    @SerializedName("NewConfirmed")
    @Expose
    private int newConfirmed;
    @SerializedName("TotalConfirmed")
    @Expose
    private int totalConfirmed;
    @SerializedName("NewDeaths")
    @Expose
    private int newDeaths;
    @SerializedName("TotalDeaths")
    @Expose
    private int totalDeaths;
    @SerializedName("NewRecovered")
    @Expose
    private int newRecovered;
    @SerializedName("TotalRecovered")
    @Expose
    private int totalRecovered;
    @SerializedName("Date")
    @Expose
    private String date;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country1 = (Country) o;

        if (newConfirmed != country1.newConfirmed) return false;
        if (totalConfirmed != country1.totalConfirmed) return false;
        if (newDeaths != country1.newDeaths) return false;
        if (totalDeaths != country1.totalDeaths) return false;
        if (newRecovered != country1.newRecovered) return false;
        if (totalRecovered != country1.totalRecovered) return false;
        if (!country.equals(country1.country)) return false;
        if (!countryCode.equals(country1.countryCode)) return false;
        if (!slug.equals(country1.slug)) return false;
        return date.equals(country1.date);
    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + countryCode.hashCode();
        result = 31 * result + slug.hashCode();
        result = 31 * result + newConfirmed;
        result = 31 * result + totalConfirmed;
        result = 31 * result + newDeaths;
        result = 31 * result + totalDeaths;
        result = 31 * result + newRecovered;
        result = 31 * result + totalRecovered;
        result = 31 * result + date.hashCode();
        return result;
    }


}


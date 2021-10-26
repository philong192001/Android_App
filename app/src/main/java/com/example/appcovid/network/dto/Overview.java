package com.example.appcovid.network.dto;

public class Overview {
    public String date;
    public int death;
    public int treating;
    public int cases;
    public int recovered;
    public int avgCases7day;
    public int avgRecovered7day;
    public int avgDeath7day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getTreating() {
        return treating;
    }

    public void setTreating(int treating) {
        this.treating = treating;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getAvgCases7day() {
        return avgCases7day;
    }

    public void setAvgCases7day(int avgCases7day) {
        this.avgCases7day = avgCases7day;
    }

    public int getAvgRecovered7day() {
        return avgRecovered7day;
    }

    public void setAvgRecovered7day(int avgRecovered7day) {
        this.avgRecovered7day = avgRecovered7day;
    }

    public int getAvgDeath7day() {
        return avgDeath7day;
    }

    public void setAvgDeath7day(int avgDeath7day) {
        this.avgDeath7day = avgDeath7day;
    }
}

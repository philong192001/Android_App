package com.example.appcovid.network.dto;

public class Locations {
    public String name;
    public int death;
    public int treating;
    public int cases;
    public int recovered;
    public int casesToday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getCasesToday() {
        return casesToday;
    }

    public void setCasesToday(int casesToday) {
        this.casesToday = casesToday;
    }
}

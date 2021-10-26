package com.example.appcovid.network.dto;

import java.util.List;

public class DiseaseInfo {

    public Total total ;
    public Total today;
    public List<Overview> overview;
    public List<Locations> locations;

    @Override
    public String toString() {
        return "DiseaseInfo{" +
                "total=" + total +
                ", today=" + today +
                ", overview=" + overview +
                ", locations=" + locations +
                '}';
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public Total getToday() {
        return today;
    }

    public void setToday(Total today) {
        this.today = today;
    }

    public List<Overview> getOverview() {
        return overview;
    }

    public void setOverview(List<Overview> overview) {
        this.overview = overview;
    }

    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }
}

package com.example.appcovid.network.dto;

import java.io.Serializable;
import java.util.Date;

public class PostDecDto implements Serializable {
    public String name;
    public Date birthDay;
    public String cmt;
    public String BHXH;
    public boolean gender;
    public String phone;
    public long idCommune;
    public String address;

    @Override
    public String toString() {
        return "PostDecDto{" +
                "name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", cmt='" + cmt + '\'' +
                ", BHXH='" + BHXH + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", idCommune=" + idCommune +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", xposureToF0=" + xposureToF0 +
                ", comeBackFromEpidemicArea=" + comeBackFromEpidemicArea +
                ", contactWithPeopleReturningFromEpidemicAreas=" + contactWithPeopleReturningFromEpidemicAreas +
                ", fever=" + fever +
                ", cough=" + cough +
                ", shortnessOfBreath=" + shortnessOfBreath +
                ", pneumonia=" + pneumonia +
                ", soreThroat=" + soreThroat +
                ", tired=" + tired +
                ", chronicLiverDisease=" + chronicLiverDisease +
                ", chronicBloodDisease=" + chronicBloodDisease +
                ", chronicLungDisease=" + chronicLungDisease +
                ", chronicKideyDisease=" + chronicKideyDisease +
                ", heartRelatedDiseaes=" + heartRelatedDiseaes +
                ", highBloodPressure=" + highBloodPressure +
                ", hivOrImmunocompromised=" + hivOrImmunocompromised +
                ", organTransplantRecipient=" + organTransplantRecipient +
                ", diabetes=" + diabetes +
                ", cancer=" + cancer +
                ", pregnant=" + pregnant +
                ", travelSchedule='" + travelSchedule + '\'' +
                '}';
    }

    public String email;

    public boolean xposureToF0;
    public boolean comeBackFromEpidemicArea;
    public boolean contactWithPeopleReturningFromEpidemicAreas;
    public boolean fever;
    public boolean cough;
    public boolean shortnessOfBreath;
    public boolean pneumonia;
    public boolean soreThroat;
    public boolean tired;
    public boolean chronicLiverDisease;
    public boolean chronicBloodDisease;
    public boolean chronicLungDisease;
    public boolean chronicKideyDisease;
    public boolean heartRelatedDiseaes;
    public boolean highBloodPressure;
    public boolean hivOrImmunocompromised;
    public boolean organTransplantRecipient;
    public boolean diabetes;
    public boolean cancer;
    public boolean pregnant;
    public String travelSchedule;


}

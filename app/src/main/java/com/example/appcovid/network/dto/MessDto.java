package com.example.appcovid.network.dto;

public class MessDto {
    public String message;

    private String name;


    private String birthDay;

    private String cmt;

    private boolean gender;

    private String phone;


    private String provinceName;
    private String communeName;
    private String districtName;

    private String address;
    private String updateAt;

    private boolean exposureToF0;
    private boolean comeBackFromEpidemicArea;
    private boolean contactWithPeopleReturningFromEpidemicAreas;
    private boolean fever;
    private boolean cough;
    private boolean shortnessOfBreath;
    private boolean pneumonia;
    private boolean soreThroat;
    private boolean tired;
    private boolean chronicLiverDisease;
    private boolean chronicBloodDisease;
    private boolean chronicLungDisease;
    private boolean chronicKideyDisease;
    private boolean heartRelatedDiseaes;
    private boolean highBloodPressure;
    private boolean hivOrImmunocompromised;
    private boolean organTransplantRecipient;
    private boolean diabetes;
    private boolean cancer;
    private boolean pregnant;
    private String travelSchedule;

    @Override
    public String toString() {
        return "MessDto{" +
                "message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", cmt='" + cmt + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", communeName='" + communeName + '\'' +
                ", districtName='" + districtName + '\'' +
                ", address='" + address + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", exposureToF0=" + exposureToF0 +
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
}

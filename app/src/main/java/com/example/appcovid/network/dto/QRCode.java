package com.example.appcovid.network.dto;

import java.io.Serializable;

public class QRCode implements Serializable {
    public String name;
    public String birthDay;
    public String cmt;
    public boolean gender;
    public String phone;

    public String provinceName;
    public String communeName;
    public String districtName;

    public String address;
    public String updateAt;

    public boolean exposureToF0;
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

    private String checkGender(){
        if(gender == true){
            return "Nam";
        }else{
            return  "Nữ";
        }
    }
    private String checkExposureToF0(){
        if(exposureToF0 == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkComeBackFromEpidemicArea(){
        if(comeBackFromEpidemicArea == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkContactWithPeopleReturningFromEpidemicAreas(){
        if(contactWithPeopleReturningFromEpidemicAreas == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkFever(){
        if(fever == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkCough(){
        if(cough == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkShortnessOfBreath(){
        if(shortnessOfBreath == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkPneumonia(){
        if(pneumonia == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkSoreThroat(){
        if(soreThroat == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkTired(){
        if(tired == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkChronicLiverDisease(){
        if(chronicLiverDisease == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkChronicBloodDisease(){
        if(chronicBloodDisease == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkChronicLungDisease(){
        if(chronicLungDisease == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkChronicKideyDisease(){
        if(chronicKideyDisease == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkHeartRelatedDiseaes(){
        if(heartRelatedDiseaes == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkHighBloodPressure(){
        if(highBloodPressure == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkHivOrImmunocompromised(){
        if(hivOrImmunocompromised == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkOrganTransplantRecipient(){
        if(organTransplantRecipient == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkDiabetes(){
        if(diabetes == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkCancer(){
        if(cancer == true){
            return "Có";
        }else{
            return  "Không";
        }
    }
    private String checkPregnant(){
        if(pregnant == true){
            return "Có";
        }else{
            return  "Không";
        }
    }

    @Override
    public String toString() {
        return "Thông tin khai báo : \n" +
                "- Ho va Tên :" + name + '\n' +
                "- Ngay Sinh :" + birthDay + '\n' +
                "- CMTND/CCCD :" + cmt + '\n' +
                "- Gioi Tính :" + checkGender() + '\n' +
                "- SDT :" + phone + '\n' +
                "- Ten Tinh/Thanh Pho :" + provinceName + '\n' +
                "- Ten Quan/Huyen :" + communeName + '\n' +
                "- Ten Xa/Phuong :" + districtName + '\n' +
                "- Dia chi chi tiet :" + address + '\n' +
                "- Thoi gian khai bao :" + updateAt + '\n' +
                "- Tiep xuc voi truong hop ben hoac nghi ngo mac benh: " + checkExposureToF0() + '\n' +
                "- Di tu vùng có dich ve :" + checkComeBackFromEpidemicArea() + '\n' +
                "- Tiep xuc voi truong hop di tu vung dich :" + checkContactWithPeopleReturningFromEpidemicAreas() + '\n' +
                "- Sot :" + checkFever() + '\n' +
                "- Ho :" + checkCough() + '\n' +
                "- Kho tho :" + checkShortnessOfBreath() + '\n'  +
                "- Viem phoi :" + checkPneumonia() + '\n' +
                "- Dau hong :" + checkSoreThroat() + '\n' +
                "- Met moi :" + checkTired() + '\n' +
                "- Benh gan man tinh :" + checkChronicLiverDisease() + '\n' +
                "- Benh mau man tinh :" + checkChronicBloodDisease() + '\n' +
                "- Benh phoi man tinh :" + checkChronicLungDisease() + '\n' +
                "- Benh than man tinh :" + checkChronicKideyDisease() + '\n' +
                "- Benh tim mach :" + checkHeartRelatedDiseaes() + '\n' +
                "- Huyet ap cao :" + checkHighBloodPressure() +
                "- HIV hoac suy giam mien dich :" + checkHivOrImmunocompromised() + '\n' +
                "- Ghep tang, tuy, xuong :" + checkOrganTransplantRecipient() + '\n' +
                "- Tieu duong :" + checkDiabetes() + '\n' +
                "- Ung thu :" + checkCancer() + '\n' +
                "- Co thai :" + checkPregnant() + '\n' +
                "- Trong 14 ngay co di qua quốc gia nao co dich  :" + travelSchedule + '\n';
    }
}

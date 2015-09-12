package bison.solutions.domain;

import java.io.Serializable;

public class Court implements Serializable{
    private String municipality;
    private String municipalCourt;
    private String municipalWebsite;
    private String municipalCourtWebsite;
    private String clerkPhoneNumber;
    private boolean onlinePayment;

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getMunicipalCourt() {
        return municipalCourt;
    }

    public void setMunicipalCourt(String municipalCourt) {
        this.municipalCourt = municipalCourt;
    }

    public String getMunicipalWebsite() {
        return municipalWebsite;
    }

    public void setMunicipalWebsite(String municipalWebsite) {
        this.municipalWebsite = municipalWebsite;
    }

    public String getMunicipalCourtWebsite() {
        return municipalCourtWebsite;
    }

    public void setMunicipalCourtWebsite(String municipalCourtWebsite) {
        this.municipalCourtWebsite = municipalCourtWebsite;
    }

    public String getClerkPhoneNumber() {
        return clerkPhoneNumber;
    }

    public void setClerkPhoneNumber(String clerkPhoneNumber) {
        this.clerkPhoneNumber = clerkPhoneNumber;
    }

    public boolean isOnlinePayment() {
        return onlinePayment;
    }

    public void setOnlinePayment(boolean onlinePayment) {
        this.onlinePayment = onlinePayment;
    }
}
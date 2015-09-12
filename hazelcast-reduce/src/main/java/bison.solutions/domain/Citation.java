package bison.solutions.domain;

import java.io.Serializable;
import java.util.Date;

public class Citation implements Serializable {
    private long id;
    private long citationNumber;
    private Date citationDate;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String defendentAddress;
    private String defendentCity;
    private String defendentState;
    private String driversLicenseNumber;
    private Date courtDate;
    private String courtLocation;
    private String courtAddress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCitationNumber() {
        return citationNumber;
    }

    public void setCitationNumber(long citationNumber) {
        this.citationNumber = citationNumber;
    }

    public Date getCitationDate() {
        return citationDate;
    }

    public void setCitationDate(Date citationDate) {
        this.citationDate = citationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDefendentAddress() {
        return defendentAddress;
    }

    public void setDefendentAddress(String defendentAddress) {
        this.defendentAddress = defendentAddress;
    }

    public String getDefendentCity() {
        return defendentCity;
    }

    public void setDefendentCity(String defendentCity) {
        this.defendentCity = defendentCity;
    }

    public String getDefendentState() {
        return defendentState;
    }

    public void setDefendentState(String defendentState) {
        this.defendentState = defendentState;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public void setDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public Date getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(Date courtDate) {
        this.courtDate = courtDate;
    }

    public String getCourtLocation() {
        return courtLocation;
    }

    public void setCourtLocation(String courtLocation) {
        this.courtLocation = courtLocation;
    }

    public String getCourtAddress() {
        return courtAddress;
    }

    public void setCourtAddress(String courtAddress) {
        this.courtAddress = courtAddress;
    }
}

package bison.solutions.domain;

import java.io.Serializable;
import java.util.Date;

public class Violation implements Serializable {
    private enum Status {
        CONT_FOR_PAYMENT("CONT FOR PAYMENT"),
        FTA_WARRENT_ISSUED("FTA WARRANT ISSUED"),
        DISMISS_WITHOUT_COSTS("DISMISS WITHOUT COSTS"),
        CLOSED("CLOSED");
        private String status;
        Status(String s) {
            this.status = s;
        }
        public String getStatus() {
            return status;
        }
    }

    private long id;
    private long citationNumber;
    private String violationNumber;
    private String violationDescription;
    private boolean warrantStatus;
    private String warrantNumber;
    private Status status;
    private Date statusDate;
    private String fineAmount;
    private String courtCost;

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

    public String getViolationNumber() {
        return violationNumber;
    }

    public void setViolationNumber(String violationNumber) {
        this.violationNumber = violationNumber;
    }

    public String getViolationDescription() {
        return violationDescription;
    }

    public void setViolationDescription(String violationDescription) {
        this.violationDescription = violationDescription;
    }

    public boolean isWarrantStatus() {
        return warrantStatus;
    }

    public void setWarrantStatus(boolean warrantStatus) {
        this.warrantStatus = warrantStatus;
    }

    public String getWarrantNumber() {
        return warrantNumber;
    }

    public void setWarrantNumber(String warrantNumber) {
        this.warrantNumber = warrantNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(String fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getCourtCost() {
        return courtCost;
    }

    public void setCourtCost(String courtCost) {
        this.courtCost = courtCost;
    }
}

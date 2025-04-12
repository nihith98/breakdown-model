package com.nihith.breakdown.model.response;

import java.util.List;

public class ResponseMessages {

    List<String> informationMessages;
    List<String> warningMessages;
    List<String> errorMessages;

    public List<String> getInformationMessages() {
        return informationMessages;
    }

    public void setInformationMessages(List<String> informationMessages) {
        this.informationMessages = informationMessages;
    }

    public List<String> getWarningMessages() {
        return warningMessages;
    }

    public void setWarningMessages(List<String> warningMessages) {
        this.warningMessages = warningMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    public String toString() {
        return "ResponseMessages{" +
                "informationMessages=" + informationMessages +
                ", warningMessages=" + warningMessages +
                ", errorMessages=" + errorMessages +
                '}';
    }
}

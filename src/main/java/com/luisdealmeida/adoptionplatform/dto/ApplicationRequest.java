package com.luisdealmeida.adoptionplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ApplicationRequest {

    @NotBlank
    private String applicantName;

    @NotBlank
    @Email
    private String applicantEmail;

    @NotBlank
    private String applicantPhone;

    @NotBlank
    private String applicantAddress;

    private String message;

    public ApplicationRequest() {
    }

    public ApplicationRequest(String applicantName, String applicantEmail, String applicantPhone,
                               String applicantAddress, String message) {
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.applicantAddress = applicantAddress;
        this.message = message;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

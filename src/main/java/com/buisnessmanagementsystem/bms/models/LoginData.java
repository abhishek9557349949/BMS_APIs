package com.buisnessmanagementsystem.bms.models;

public class LoginData {
    private String clientName;
    private String mailId;
    private int failedLoginAttempts;
    private String accountStatus;
    private String businessName;
    private String role;
    private String approvedByOwner;
    private String userId;
    private String password;
    private String response;

    public LoginData(String clientName, String mailId, int failedLoginAttempts, String accountStatus,
                     String businessName, String role, String approvedByOwner, String userId, String password, String response) {
        super();
        this.clientName = clientName;
        this.mailId = mailId;
        this.failedLoginAttempts = failedLoginAttempts;
        this.accountStatus = accountStatus;
        this.businessName = businessName;
        this.role = role;
        this.approvedByOwner = approvedByOwner;
        this.userId = userId;
        this.password = password;
        this.response = response;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getApprovedByOwner() {
        return approvedByOwner;
    }

    public void setApprovedByOwner(String approvedByOwner) {
        this.approvedByOwner = approvedByOwner;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

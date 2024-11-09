package org.example.dto;

public class AdminDto {
    private  String userId;
    private String userName;
    private  String password;

    public AdminDto() {
    }

    public AdminDto(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public AdminDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

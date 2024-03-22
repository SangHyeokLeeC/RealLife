package DTO;

public class Users {
    private int uCode;
    private String userId;
    private String uName;
    private String uPw;
    private String uEmail;


    public Users() {}

    public Users(String uName, String userId, String uPw, String uEmail) {
        this.uName = uName;
        this.userId = userId;
        this.uPw = uPw;
        this.uEmail = uEmail;
    }

    public int getuCodeId() {
        return uCode;
    }

    public void setuCodeId(int uCodeId) {
        this.uCode = uCodeId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getuPw() {
        return uPw;
    }

    public void setuPw(String uPw) {
        this.uPw = uPw;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    @Override
    public String toString() {
        return "Client{" +
                "uCodeId=" + uCode +
                ", uName='" + uName + '\'' +
                ", userId='" + userId + '\'' +
                ", uPw='" + uPw + '\'' +
                ", uEmail='" + uEmail + '\'' +
                '}';
    }

}
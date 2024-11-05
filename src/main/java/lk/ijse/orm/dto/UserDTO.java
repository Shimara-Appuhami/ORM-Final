package lk.ijse.orm.dto;

public class UserDTO {
    private int userId;
    private String username;
    private String password;
    private String possession;

    public UserDTO() {
    }

    public UserDTO(int userId, String username, String password,String possession) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.possession = possession;
    }

    public UserDTO(String id, String username, String password, String possession) {
        this.userId = Integer.parseInt(id);
        this.username = username;
        this.password = password;
        this.possession = possession;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPossession() {
        return possession;
    }

    public void setPossession(String possession) {
        this.possession = possession;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", possession='" + possession + '\'' +
                '}';
    }
}

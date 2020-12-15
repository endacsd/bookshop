package cn.endacsd.yingbookshop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class YUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "password")
    private String password;
    @JsonProperty(value = "avatar")
    private String avatar;
    @JsonProperty(value = "mail")
    private String mail;
    @JsonProperty(value = "hide")
    private boolean hide;
    @JsonProperty(value = "surplus")
    private long surplus;
    @JsonProperty(value = "authority")
    private String authority;

    private String salt;

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }





    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }



    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getSurplus() {
        return surplus;
    }

    public void setSurplus(long surplus) {
        this.surplus = surplus;
    }



    @Override
    public String toString() {
        return "YUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mail='" + mail + '\'' +
                ", surplus=" + surplus +
                '}';
    }
}

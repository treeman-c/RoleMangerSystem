package com.treeman.pojo;


import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;

//@Table("user_role")
public class UserRole {

  private long id;
  private String username;
  private String password;
  private Date createDate;
  private Date updateDate;
  private String role;

  public UserRole(String username, String encode, List<GrantedAuthority> authorities) {
  }

  public UserRole() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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


  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }


  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(java.sql.Timestamp updateDate) {
    this.updateDate = updateDate;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}

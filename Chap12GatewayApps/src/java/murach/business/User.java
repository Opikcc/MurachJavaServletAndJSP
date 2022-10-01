package murach.business;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String firstName;
  private String lastName;
  private String email;
  private String wantUpdates;

  public User() {
    this.firstName  = "";
    this.lastName   = "";
    this.email      = "";
  }

  public User(String firstName,
              String lastName,
              String email) {
    this.firstName  = firstName;
    this.lastName   = lastName;
    this.email      = email;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWantUpdates() {
    return wantUpdates;
  }

  public void setWantUpdates(String wantUpdates) {
    this.wantUpdates = wantUpdates;
  }
  
}

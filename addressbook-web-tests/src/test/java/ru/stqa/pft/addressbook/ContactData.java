package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String phonehome;
  private final String phonemobile;
  private final String email;

  public ContactData(String firstname, String middlename, String lastname, String address, String phonehome, String phonemobile, String email) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.phonehome = phonehome;
    this.phonemobile = phonemobile;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getPhonehome() {
    return phonehome;
  }

  public String getPhonemobile() {
    return phonemobile;
  }

  public String getEmail() {
    return email;
  }
}

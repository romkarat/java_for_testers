package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getPhonehome());
    type(By.name("mobile"),contactData.getPhonemobile());
    type(By.name("email"),contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void selectContactById(int id) {
    click(By.cssSelector("input[value='" + id + "']"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    accept();
  }

  public void initContactModificationById(int id) {
    click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements) {
      List<WebElement> tagtd = wd.findElements(By.tagName("td"));
      String lastname = tagtd.get(1).getText();
      String firstname = tagtd.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return new Contacts(contactCache);
  }

}

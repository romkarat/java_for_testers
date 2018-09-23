package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Петр", "Иванович", "Сидоров", "г.Москва, Ленинградский проспект 12-27", "+7(495)123-45-67", "+7(925)123-45-67", "email@mail.com"));
    app.getContactHelper().submitContactModification();
  }
}

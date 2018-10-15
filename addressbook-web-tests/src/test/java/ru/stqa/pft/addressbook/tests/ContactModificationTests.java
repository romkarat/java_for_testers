package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0){
      app.goTo().addContactPage();
      app.contact().create(new ContactData()
              .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров")
              .withAddress("г.Москва, Ленинградский проспект 12-27").withPhonehome("+7(495)123-45-67")
              .withPhonemobile("+7(925)123-45-67").withEmail("email@mail.ru").withGroup("[none]"));
    }
  }

  @Test (enabled = true)
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров")
            .withAddress("г.Москва, Ленинградский проспект 12-27").withPhonehome("+7(495)123-45-67")
            .withPhonemobile("+7(925)123-45-67").withEmail("email@mail.com");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }

}

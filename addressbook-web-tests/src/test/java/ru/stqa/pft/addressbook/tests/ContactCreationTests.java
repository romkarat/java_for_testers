package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.contact().list();
    app.goTo().addContactPage();
    ContactData contact = new ContactData()
            .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров")
            .withAddress("г.Москва, Ленинский проспект 12-27").withPhonehome("+7(495)123-45-67")
            .withPhonemobile("+7(925)123-45-67").withEmail("testemail@mail.ru").withGroup("[none]");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }

}

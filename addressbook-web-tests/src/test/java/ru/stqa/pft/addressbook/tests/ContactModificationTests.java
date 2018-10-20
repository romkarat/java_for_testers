package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0){
      app.goTo().addContactPage();
      app.contact().create(new ContactData()
              .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинградский проспект 12-27")
              .withPhonehome("+7(495)123-45-67").withPhonemobile("+7(925)123-45-67").withEmail("email@mail.ru").withGroup("[none]"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинградский проспект 12-27")
            .withPhonehome("+7(495)123-45-67").withPhonemobile("+7(925)123-45-67").withEmail("email@mail.com");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}

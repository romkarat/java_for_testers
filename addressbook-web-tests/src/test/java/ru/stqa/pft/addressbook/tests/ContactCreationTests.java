package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().addContactPage();
    ContactData contact = new ContactData()
            .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинский проспект 12-27")
            .withPhonehome("+7(495)123-45-67").withPhonemobile("+7(925)123-45-67").withEmail("testemail@mail.ru").withGroup("[none]");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}

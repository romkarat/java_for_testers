package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0){
      Groups groups = app.db().groups();
      app.goTo().addContactPage();
      app.contact().create(new ContactData()
              .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинградский проспект 12-27")
              .withPhoneHome("+7(495)123-45-67").withPhoneMobile("+7(925)123-45-67").withPhoneWork("+7(495)765-43-21")
              .withEmail("email@mail.ru").withEmail2("email2@mail.ru").withEmail3("email3@mail.ru").inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинградский проспект 12-27")
            .withPhoneHome("+7(495)123-45-67").withPhoneMobile("+7(925)123-45-67").withPhoneWork("+7(495)765-43-21")
            .withEmail("email@mail.com").withEmail2("email2@mail.com").withEmail3("email3@mail.com");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}

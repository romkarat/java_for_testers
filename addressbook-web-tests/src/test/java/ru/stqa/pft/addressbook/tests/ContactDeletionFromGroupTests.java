package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletionFromGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.goTo().addContactPage();
      app.contact().create(new ContactData()
              .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинградский проспект 12-27")
              .withPhoneHome("+7(495)123-45-67").withPhoneMobile("+7(925)123-45-67").withPhoneWork("+7(495)765-43-21")
              .withEmail("email@mail.ru").withEmail2("email2@mail.ru").withEmail3("email3@mail.ru"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testDeletionContactFromGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    if (!group.equals(contact.getGroups())) {
      app.goTo().homePage();
      app.contact().contactAddToGroup(contact, group);
    }
    groups.removeAll(contact.getGroups());
    app.goTo().homePage();
    app.contact().contactDeletionFromGroup(contact, group);
    app.db().refresh(contact);
    assertThat(contact.getGroups(), not(hasItem(group)));
  }
}

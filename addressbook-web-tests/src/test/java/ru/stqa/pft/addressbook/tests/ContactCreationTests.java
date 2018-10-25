package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() {
    File photo = new File("src/test/resources/stru.png");
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData()
            .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинский проспект 11-27")
            .withPhoneHome("+7(495)123-45-61").withPhoneWork("+7(495)654-32-11").withPhoneMobile("+7(925)123-45-61")
            .withEmail("testemail1@mail.ru").withGroup("[none]").withPhoto(photo)});
    list.add(new Object[] {new ContactData()
            .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинский проспект 12-27")
            .withPhoneHome("+7(495)123-45-62").withPhoneWork("+7(495)654-32-12").withPhoneMobile("+7(925)123-45-62")
            .withEmail("testemail2@mail.ru").withGroup("[none]").withPhoto(photo)});
    list.add(new Object[] {new ContactData()
            .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинский проспект 13-27")
            .withPhoneHome("+7(495)123-45-63").withPhoneWork("+7(495)654-32-13").withPhoneMobile("+7(925)123-45-63")
            .withEmail("testemail3@mail.ru").withGroup("[none]").withPhoto(photo)});
    return list.iterator();
  }

  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.goTo().addContactPage();
    //File photo = new File("src/test/resources/stru.png");
    /*ContactData contact = new ContactData()
            .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров").withAddress("г.Москва, Ленинский проспект 12-27")
            .withPhoneHome("+7(495)123-45-67").withPhoneMobile("+7(925)123-45-67").withEmail("testemail@mail.ru").withGroup("[none]")
            .withPhoto(photo);*/
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}

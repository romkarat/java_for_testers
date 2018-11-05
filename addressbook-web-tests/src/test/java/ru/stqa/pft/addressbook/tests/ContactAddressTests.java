package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

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
              .withFirstname("Иван").withMiddlename("Петрович").withLastname("Сидоров")
              .withAddress("г.Москва, Ленинградский проспект 12-27")
              .withPhoneHome("+7(495)123-45-67").withPhoneMobile("+7 (925) 123 45 67").withPhoneWork("9250001234")
              .withEmail("email@mail.ru").withEmail2("email2@mail.ru").withEmail3("email3@mail.ru")
              .inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactAddresses() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(mergeAddresses(contactInfoFromEditForm)));
  }

  private String mergeAddresses(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactAddressTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned (String address) {
    return address.replaceAll("\\s{2}", "");
  }

}

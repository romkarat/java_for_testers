package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter (names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-f", description = "Target file ")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContact(count);
    save(contacts, new File(file));
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);

    for (ContactData contact: contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n",
              contact.getFirstname(), contact.getMiddlename(), contact.getLastname(), contact.getAddress(),
              contact.getPhoneHome(), contact.getPhoneWork(), contact.getPhoneMobile(), contact.getEmail()));
    }
    writer.close();

  }

  private static List<ContactData> generateContact(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirstname(String.format("Firstname %s", i)).withMiddlename(String.format("Middlename %s", i))
              .withLastname(String.format("Lastname %s", i)).withAddress(String.format("Cityname str.Lenina house %s", i))
              .withPhoneHome(String.format("+7(499)654321%s", i)).withPhoneWork(String.format("+7(495)123456%s", i))
              .withPhoneMobile(String.format("+7(925)123456%s", i)).withEmail(String.format("testmail%s@yandex.ru", i)));
    }
    return contacts;
  }
}

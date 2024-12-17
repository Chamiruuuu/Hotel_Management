
public class Customer {

    private String name;
    private int contact;

    public Customer(String name, int contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return this.name;
    }

    public int getContact() {
        return this.contact;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public int setContact(int Contact) {
        return this.contact = Contact;
    }
}

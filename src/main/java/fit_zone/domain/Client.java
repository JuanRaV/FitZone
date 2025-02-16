package fit_zone.domain;

import java.util.Objects;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private int membership;

    public Client(){}

    //For delete opn
    public Client(int id){
        this.id = id;
    }

    //For insert opn
    public Client(String firstName, String lastName, int membership){
        this.firstName = firstName;
        this.lastName = lastName;
        this.membership = membership;
    }

    public Client(int id, String firstName, String lastName, int membership){
        this(firstName, lastName, membership);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", membership=" + membership +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && membership == client.membership && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, membership);
    }
}

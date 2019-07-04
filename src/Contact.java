import java.io.Serializable;

public class Contact implements Serializable {

    // Encapsulated variables which we will need
    private int id = 0;
    private String name = "";
    private String number = "";

    // Override constructor for initialize some variables in the moment of create
    public Contact(String name, String number){
        this.name = name;
        this.number = number;

    }

    // Access to encapsulated variables
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // To have result we need in console
    @Override
    public String toString() {
        return id + " " + name + " " + number;
    }
}



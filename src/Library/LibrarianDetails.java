package Library;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class LibrarianDetails {
    public String id;
    public String firstname;
    public String lastname;
    // public CheckBox checkBox = new CheckBox();


    public LibrarianDetails(String id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setId(String id) {
        this.id = id;
    }
}
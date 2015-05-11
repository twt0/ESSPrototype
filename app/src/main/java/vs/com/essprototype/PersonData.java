package vs.com.essprototype;

/**
 * Created by USER on 4/27/2015.
 */
public class PersonData {


    String name;
    String email;

    int id_;

    public PersonData(String name, String email, int id_) {
        this.name = name;
        this.email = email;

        this.id_ = id_;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }




    public int getId() {
        return id_;
    }
}
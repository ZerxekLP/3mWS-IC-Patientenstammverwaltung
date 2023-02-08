package Data;

public class Patient {
    int id;
    String firstname;
    String lastname;
    int religiousCode;
    String nationalityCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getReligiousCode() {
        return religiousCode;
    }

    public void setReligiousCode(int religiousCode) {
        this.religiousCode = religiousCode;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public Patient(int id, String firstname, String lastname, int religiousCode, String nationalityCode) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.religiousCode = religiousCode;
        this.nationalityCode = nationalityCode;
    }

    @Override
    public String toString() {
        return id + " " + firstname + " " + lastname;
    }
}


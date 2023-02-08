package Data;

public class Denomination {
    int code;
    String konfession;

    public Denomination(int code, String konfession) {
        this.code = code;
        this.konfession = konfession;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getKonfession() {
        return konfession;
    }

    public void setKonfession(String konfession) {
        this.konfession = konfession;
    }

    @Override
    public String toString() {
        return  "" + konfession;
    }
}

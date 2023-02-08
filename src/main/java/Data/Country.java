package Data;

public class Country {
    String code;
    String country;
    String prefix;

    public Country(String code, String country, String prefix) {
        this.code = code;
        this.country = country;
        this.prefix = prefix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return"" + country;
    }
}

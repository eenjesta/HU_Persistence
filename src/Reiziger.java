import java.time.LocalDate;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;

    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum) {
        this.reiziger_id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Integer getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(Integer reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Override
    public String toString() {
        if (this.tussenvoegsel != null) {
            return String.format("#%d: %s %s %s (%s)", this.reiziger_id, this.voorletters, this.tussenvoegsel, this.achternaam, this.geboortedatum);
        } else {
            return String.format("#%d: %s %s (%s)", this.reiziger_id, this.voorletters, this.achternaam, this.geboortedatum);
        }
    }
}

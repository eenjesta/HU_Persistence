import java.time.LocalDate;
import java.util.List;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;
    private Adres adres;
    private List<OVChipkaart> ov_chipkaart;

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

    public void setReiziger_id(int reiziger_id) {
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

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OVChipkaart> getOv_chipkaart() {
        return ov_chipkaart;
    }

    public void setOv_chipkaart(List<OVChipkaart> ov_chipkaart) {
        this.ov_chipkaart = ov_chipkaart;
    }

    @Override
    public String toString() {
        if (this.tussenvoegsel != null) {
            return String.format("Reiziger: {#%d %s %s %s, geb. %s, Adres {%s}}", this.reiziger_id, this.voorletters, this.tussenvoegsel, this.achternaam, this.geboortedatum, this.adres);
        } else {
            return String.format("Reiziger: {#%d %s %s, geb. %s, Adres {%s}}", this.reiziger_id, this.voorletters, this.achternaam, this.geboortedatum, this.adres);
        }
    }
}

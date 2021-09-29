import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;
    private Adres adres;
    private List<OVChipkaart> ov_chipkaarten = new ArrayList<>();

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

    public List<OVChipkaart> getOv_chipkaarten() {
        return ov_chipkaarten;
    }

    public void setOv_chipkaarten(List<OVChipkaart> ov_chipkaarten) {
        this.ov_chipkaarten = ov_chipkaarten;
    }

    public void addOv_chipkaart(OVChipkaart ov_chipkaart) {
        List<OVChipkaart> toAdd = new ArrayList<>();
        if (this.ov_chipkaarten.isEmpty()) {
            this.ov_chipkaarten.add(ov_chipkaart);
        } else {
            for (OVChipkaart ov_chipkaart_list : this.ov_chipkaarten) {
                if (ov_chipkaart.equals(ov_chipkaart_list)) {
                    System.out.print("Oops deze OV-kaart bestaat al!");
                } else {
                    toAdd.add(ov_chipkaart);
                }
            }
            this.ov_chipkaarten.addAll(toAdd);
        }
    }

    public void updateOv_chipkaart(OVChipkaart ov_chipkaart) {
        if (this.ov_chipkaarten.isEmpty()) {
            System.out.print("Oops deze OV-kaart bestaat niet!");
        } else {
            for (OVChipkaart ov_chipkaart_list : this.ov_chipkaarten) {
                if (ov_chipkaart.equals(ov_chipkaart_list)) {
                    int index = this.ov_chipkaarten.indexOf(ov_chipkaart_list);
                    this.ov_chipkaarten.set(index, ov_chipkaart);
                }
            }
        }
    }

    public void deleteOv_chipkaart(OVChipkaart ov_chipkaart) {
        List<OVChipkaart> toRemove = new ArrayList<>();
        if (this.ov_chipkaarten.isEmpty()) {
            System.out.print("Oops deze OV-kaart bestaat niet!");
        } else {
            for (OVChipkaart ov_chipkaart_list : this.ov_chipkaarten) {
                if (ov_chipkaart.equals(ov_chipkaart_list)) {
                    toRemove.add(ov_chipkaart);
                }
            }
            this.ov_chipkaarten.removeAll(toRemove);
        }
    }

    @Override
    public String toString() {
        if (this.tussenvoegsel != null) {
            return String.format("Reiziger: {#%d %s %s %s, geb. %s, Adres {%s}, OV-chipkaarten {%s}}", this.reiziger_id, this.voorletters, this.tussenvoegsel, this.achternaam, this.geboortedatum, this.adres, this.ov_chipkaarten);
        } else {
            return String.format("Reiziger: {#%d %s %s, geb. %s, Adres {%s}, OV-chipkaarten {%s}}", this.reiziger_id, this.voorletters, this.achternaam, this.geboortedatum, this.adres, this.ov_chipkaarten);
        }
    }
}

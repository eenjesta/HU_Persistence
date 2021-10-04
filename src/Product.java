import java.util.ArrayList;
import java.util.List;

public class Product {
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    private List<OVChipkaart> ov_chipkaarten = new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, double prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
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

    public List<OVChipkaart> getOv_chipkaarten() {
        return ov_chipkaarten;
    }

    public void setOv_chipkaarten(List<OVChipkaart> ov_chipkaarten) {
        this.ov_chipkaarten = ov_chipkaarten;
    }

    @Override
    public String toString() {
        return String.format("product_nummer: %d, naam: %s, beschrijving: %s, prijs: %,.2f, OV-chipkaarten: {%s}", this.product_nummer, this.naam, this.beschrijving, this.prijs, this.ov_chipkaarten);
    }
}

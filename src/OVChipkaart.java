import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OVChipkaart {
     private int kaart_nummer;
     private LocalDate geldig_tot;
     private int klasse;
     private double saldo;
     private Reiziger reiziger;
     private List<Product> producten;

     public OVChipkaart(int kaart_nummer, LocalDate geldig_tot, int klasse, double saldo, Reiziger reiziger) {
          this.kaart_nummer = kaart_nummer;
          this.geldig_tot = geldig_tot;
          this.klasse = klasse;
          this.saldo = saldo;
          this.reiziger = reiziger;
     }

     public int getKaart_nummer() {
          return kaart_nummer;
     }

     public void setKaart_nummer(int kaart_nummer) {
          this.kaart_nummer = kaart_nummer;
     }

     public LocalDate getGeldig_tot() {
          return geldig_tot;
     }

     public void setGeldig_tot(LocalDate geldig_tot) {
          this.geldig_tot = geldig_tot;
     }

     public int getKlasse() {
          return klasse;
     }

     public void setKlasse(int klasse) {
          this.klasse = klasse;
     }

     public double getSaldo() {
          return saldo;
     }

     public void setSaldo(double saldo) {
          this.saldo = saldo;
     }

     public Reiziger getReiziger() {
          return reiziger;
     }

     public void setReiziger(Reiziger reiziger) {
          this.reiziger = reiziger;
     }

     public List<Product> getProducten() {
          return producten;
     }

     public void setProducten(List<Product> producten) {
          this.producten = producten;
     }

     @Override
     public boolean equals(Object obj) {
          boolean sameObjects = false;
          if (obj instanceof OVChipkaart) {
               OVChipkaart otherOV_chipkaart = (OVChipkaart) obj;

               if (this.kaart_nummer == otherOV_chipkaart.kaart_nummer && this.reiziger.getReiziger_id().equals(reiziger.getReiziger_id())) {
                    sameObjects = true;
               }
          }
          return sameObjects;
     }

     @Override
     public int hashCode() {
          return Objects.hash(kaart_nummer, geldig_tot, klasse, saldo, reiziger);
     }

     @Override
     public String toString() {
          return String.format("#%d: kaartnummer: %d, geldig tot: %s, klasse: %d, saldo: %,.2f", this.reiziger.getReiziger_id(), this.kaart_nummer, this.geldig_tot, this.klasse, this.saldo);
     }
}

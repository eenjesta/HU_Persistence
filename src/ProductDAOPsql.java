import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    private Connection conn;
    private OVChipkaartDAO oDAO;

    public ProductDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Product product) throws SQLException {
        try {
            String q = "insert into product (product_nummer, naam, beschrijving, prijs) values (?,?,?,?)";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, product.getProduct_nummer());
            pst.setString(2, product.getNaam());
            pst.setString(3, product.getBeschrijving());
            pst.setDouble(4, product.getPrijs());
            pst.executeUpdate();
            for (OVChipkaart ov_chipkaart : product.getOv_chipkaarten()) {
                this.saveLinkTable(product, ov_chipkaart);
            }
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    public void saveLinkTable(Product product, OVChipkaart ov_chipkaart) throws SQLException {
        try {
            String q = "insert into ov_chipkaart_product (kaart_nummer, product_nummer) values (?,?)";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, ov_chipkaart.getKaart_nummer());
            pst.setInt(2, product.getProduct_nummer());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
        }
    }

        @Override
    public boolean update(Product product) throws SQLException {
        try {
            this.updateLinkTable(product);

            String q = "update product set product_nummer=?, naam=?, beschrijving=?, prijs=? where product_nummer=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, product.getProduct_nummer());
            pst.setString(2, product.getNaam());
            pst.setString(3, product.getBeschrijving());
            pst.setDouble(4, product.getPrijs());
            pst.setInt(5, product.getProduct_nummer());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    public void updateLinkTable(Product product) throws SQLException {
        for (OVChipkaart ov_chipkaart : product.getOv_chipkaarten()) {
            this.deleteLinkTable(product, ov_chipkaart);
        }

        for (OVChipkaart ov_chipkaart : product.getOv_chipkaarten()) {
            this.saveLinkTable(product, ov_chipkaart);
        }
    }

        @Override
    public boolean delete(Product product) throws SQLException {
        try {
            for (OVChipkaart ov_chipkaart : product.getOv_chipkaarten()) {
                this.deleteLinkTable(product, ov_chipkaart);
            }
            String q = "delete from product where product_nummer=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, product.getProduct_nummer());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    public void deleteLinkTable(Product product, OVChipkaart ov_chipkaart) throws SQLException {
        try {
            String q = "delete from ov_chipkaart_product where product_nummer=? AND kaart_nummer=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, product.getProduct_nummer());
            pst.setInt(2, ov_chipkaart.getKaart_nummer());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
        }
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String q = "select * from product";
        PreparedStatement pst = this.conn.prepareStatement(q);
        List<Product> producten = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int product_nummer = rs.getInt("product_nummer");
            String naam = rs.getString("naam");
            String beschrijving = rs.getString("beschrijving");
            double prijs = rs.getDouble("prijs");
            Product product = new Product(product_nummer, naam, beschrijving, prijs);
            product.setOv_chipkaarten(oDAO.findByProduct(product));

            producten.add(product);
        }
        return producten;
    }

    public List<Product> findByOVChipkaart(OVChipkaart ov_chipkaart) throws SQLException {
        String q = "select * from product\n" +
                "inner join ov_chipkaart_product ocp on product.product_nummer = ocp.product_nummer\n" +
                "where ocp.kaart_nummer=?";
        PreparedStatement pst = this.conn.prepareStatement(q);
        pst.setInt(1, ov_chipkaart.getKaart_nummer());
        List<Product> producten = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int product_nummer = rs.getInt("product_nummer");
            String naam = rs.getString("naam");
            String beschrijving = rs.getString("beschrijving");
            double prijs = rs.getDouble("prijs");
            Product product = new Product(product_nummer, naam, beschrijving, prijs);
            product.setOv_chipkaarten(oDAO.findByProduct(product));
            producten.add(product);
        }
        return producten;
    }

    @Override
    public void setoDAO(OVChipkaartDAO oDAO) {
        this.oDAO = oDAO;
    }
}

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private Connection conn;
    private ReizigerDAO rDAO;

    public AdresDAOPsql(Connection conn) {
        this.conn = conn;
        this.rDAO = rDAO;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            String q = "insert into adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) values (?,?,?,?,?,?)";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, adres.getAdres_id());
            pst.setString(2, adres.getPostcode());
            pst.setString(3, adres.getHuisnummer());
            pst.setString(4, adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger_id());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            String q = "update adres set adres_id=?, postcode=?, huisnummer=?, straat=?, woonplaats=?, reiziger_id=? where adres_id=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, adres.getAdres_id());
            pst.setString(2, adres.getPostcode());
            pst.setString(3, adres.getHuisnummer());
            pst.setString(4, adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger_id());
            pst.setInt(7, adres.getAdres_id());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            String q = "delete from adres where adres_id=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, adres.getAdres_id());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    public Adres findById(int id) throws SQLException {
        String q = "select * from adres where adres_id=?";
        PreparedStatement pst = this.conn.prepareStatement(q);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return new Adres(
                rs.getInt("adres_id"),
                rs.getString("postcode"),
                rs.getString("huisnummer"),
                rs.getString("straat"),
                rs.getString("woonplaats"),
                rs.getInt("reiziger_id")
        );
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        String q = "select * from adres where reiziger_id=?";
        PreparedStatement pst = this.conn.prepareStatement(q);
        pst.setInt(1, reiziger.getReiziger_id());
        ResultSet rs = pst.executeQuery();
        rs.next();
        return new Adres(
                rs.getInt("adres_id"),
                rs.getString("postcode"),
                rs.getString("huisnummer"),
                rs.getString("straat"),
                rs.getString("woonplaats"),
                rs.getInt("reiziger_id")
        );
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        String q = "select * from adres";
        PreparedStatement pst = this.conn.prepareStatement(q);
        List<Adres> adressen = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int adres_id = rs.getInt("adres_id");
            String postcode = rs.getString("postcode");
            String huisnummer = rs.getString("huisnummer");
            String straat = rs.getString("straat");
            String woonplaats = rs.getString("woonplaats");
            int reiziger_id = rs.getInt("reiziger_id");
            Adres adres = new Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id);

            adressen.add(adres);
        }
        return adressen;
    }
}

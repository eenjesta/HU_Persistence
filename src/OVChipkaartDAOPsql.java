import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;

    public OVChipkaartDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(OVChipkaart ov_chipkaart) throws SQLException {
        try {
            String q = "insert into ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) values (?,?,?,?,?)";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, ov_chipkaart.getKaart_nummer());
            pst.setDate(2, ov_chipkaart.getGeldig_tot());
            pst.setInt(3, ov_chipkaart.getKlasse());
            pst.setDouble(4, ov_chipkaart.getSaldo());
            pst.setInt(5, ov_chipkaart.getReiziger_id());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }    }

    @Override
    public boolean update(OVChipkaart ov_chipkaart) throws SQLException {
        try {
            String q = "update ov_chipkaart set kaart_nummer=?, geldig_tot=?, klasse=?, saldo=?, reiziger_id=? where kaart_nummer=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, ov_chipkaart.getKaart_nummer());
            pst.setDate(2, ov_chipkaart.getGeldig_tot());
            pst.setInt(3, ov_chipkaart.getKlasse());
            pst.setDouble(4, ov_chipkaart.getSaldo());
            pst.setInt(5, ov_chipkaart.getReiziger_id());
            pst.setInt(6, ov_chipkaart.getKaart_nummer());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ov_chipkaart) throws SQLException {
        try {
            String q = "delete from ov_chipkaart where kaart_nummer=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, ov_chipkaart.getKaart_nummer());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public OVChipkaart findById(int id) throws SQLException {
        String q = "select * from ov_chipkaart where kaart_nummer=?";
        PreparedStatement pst = this.conn.prepareStatement(q);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return new OVChipkaart(
                rs.getInt("kaart_nummer"),
                rs.getDate("geldig_tot"),
                rs.getInt("klasse"),
                rs.getDouble("saldo"),
                rs.getInt("reiziger_id")
        );
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        String q = "select * from ov_chipkaart";
        PreparedStatement pst = this.conn.prepareStatement(q);
        List<OVChipkaart> ov_chipkaarten = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int kaartnummer = rs.getInt("kaart_nummer");
            Date geldig_tot = rs.getDate("geldig_tot");
            int klasse = rs.getInt("klasse");
            double saldo = rs.getDouble("saldo");
            int reiziger_id = rs.getInt("reiziger_id");
            OVChipkaart ov_chipkaart = new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, reiziger_id);

            ov_chipkaarten.add(ov_chipkaart);
        }
        return ov_chipkaarten;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        String q = "select * from ov_chipkaart where reiziger_id=?";
        PreparedStatement pst = this.conn.prepareStatement(q);
        pst.setInt(1, reiziger.getReiziger_id());
        List<OVChipkaart> ov_chipkaarten = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int kaartnummer = rs.getInt("kaart_nummer");
            Date geldig_tot = rs.getDate("geldig_tot");
            int klasse = rs.getInt("klasse");
            double saldo = rs.getDouble("saldo");
            int reiziger_id = rs.getInt("reiziger_id");
            OVChipkaart ov_chipkaart = new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, reiziger_id);

            ov_chipkaarten.add(ov_chipkaart);
        }
        return ov_chipkaarten;
    }
}

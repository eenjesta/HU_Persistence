import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ReizigerDAO rDAO;

    public OVChipkaartDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(OVChipkaart ov_chipkaart) throws SQLException {
        try {
            String q = "insert into ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) values (?,?,?,?,?)";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, ov_chipkaart.getKaart_nummer());
            pst.setDate(2, Date.valueOf(ov_chipkaart.getGeldig_tot()));
            pst.setInt(3, ov_chipkaart.getKlasse());
            pst.setDouble(4, ov_chipkaart.getSaldo());
            pst.setInt(5, ov_chipkaart.getReiziger().getReiziger_id());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ov_chipkaart) throws SQLException {
        try {
            List<OVChipkaart> ov_chipkaarten = this.findAll();
            for (OVChipkaart ov_chipkaart_list : ov_chipkaarten) {
                if (ov_chipkaart.equals(ov_chipkaart_list)) {
                    int index = ov_chipkaarten.indexOf(ov_chipkaart_list);
                    ov_chipkaarten.set(index, ov_chipkaart);
                }
            }
            String q = "update ov_chipkaart set kaart_nummer=?, geldig_tot=?, klasse=?, saldo=?, reiziger_id=? where kaart_nummer=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, ov_chipkaart.getKaart_nummer());
            pst.setDate(2, Date.valueOf(ov_chipkaart.getGeldig_tot()));
            pst.setInt(3, ov_chipkaart.getKlasse());
            pst.setDouble(4, ov_chipkaart.getSaldo());
            pst.setInt(5, ov_chipkaart.getReiziger().getReiziger_id());
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
        Reiziger reiziger = rDAO.findById(rs.getInt("reiziger_id"));
        return new OVChipkaart(
                rs.getInt("kaart_nummer"),
                new Date(rs.getDate("geldig_tot").getTime()).toLocalDate(),
                rs.getInt("klasse"),
                rs.getDouble("saldo"),
                reiziger
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
            LocalDate geldig_tot = new Date(rs.getDate("geldig_tot").getTime()).toLocalDate();
            int klasse = rs.getInt("klasse");
            double saldo = rs.getDouble("saldo");
            Reiziger reiziger = rDAO.findById(rs.getInt("reiziger_id"));
            OVChipkaart ov_chipkaart = new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, reiziger);
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
            LocalDate geldig_tot = new Date(rs.getDate("geldig_tot").getTime()).toLocalDate();
            int klasse = rs.getInt("klasse");
            double saldo = rs.getDouble("saldo");
            OVChipkaart ov_chipkaart = new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, reiziger);

            ov_chipkaarten.add(ov_chipkaart);
        }
        return ov_chipkaarten;
    }

    @Override
    public List<OVChipkaart> findByProduct(Product product) throws SQLException {
        String q = "select * from ov_chipkaart\n" +
                "inner join ov_chipkaart_product ocp on ov_chipkaart.kaart_nummer = ocp.kaart_nummer\n" +
                "where ocp.product_nummer=?";
        PreparedStatement pst = this.conn.prepareStatement(q);
        pst.setInt(1, product.getProduct_nummer());
        List<OVChipkaart> ov_chipkaarten = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int kaartnummer = rs.getInt("kaart_nummer");
            LocalDate geldig_tot = new Date(rs.getDate("geldig_tot").getTime()).toLocalDate();
            int klasse = rs.getInt("klasse");
            double saldo = rs.getDouble("saldo");
            Reiziger reiziger = rDAO.findById(rs.getInt("reiziger_id"));
            OVChipkaart ov_chipkaart = new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, reiziger);

            ov_chipkaarten.add(ov_chipkaart);
        }
        return ov_chipkaarten;
    }

    public void setrDAO(ReizigerDAO rDAO) {
        this.rDAO = rDAO;
    }
}

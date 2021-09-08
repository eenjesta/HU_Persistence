import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        try {
            String q = "insert into reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) values (?,?,?,?,?)";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, reiziger.getReiziger_id());
            pst.setString(2, reiziger.getVoorletters());
            pst.setString(3, reiziger.getTussenvoegsel());
            pst.setString(4, reiziger.getAchternaam());
            pst.setDate(5, Date.valueOf(reiziger.getGeboortedatum()));
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        try {
            String q = "update reiziger set reiziger_id=?, voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? where reiziger_id=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, reiziger.getReiziger_id());
            pst.setString(2, reiziger.getVoorletters());
            pst.setString(3, reiziger.getTussenvoegsel());
            pst.setString(4, reiziger.getAchternaam());
            pst.setDate(5, Date.valueOf(reiziger.getGeboortedatum()));
            pst.setInt(6, reiziger.getReiziger_id());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        try {
            String q = "delete from reiziger where reiziger_id=?";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setInt(1, reiziger.getReiziger_id());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        String q = "select * from reiziger where reiziger_id=?";
        PreparedStatement pst = this.conn.prepareStatement(q);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return new Reiziger(
            rs.getInt("reiziger_id"),
            rs.getString("voorletters"),
            rs.getString("tussenvoegsel"),
            rs.getString("achternaam"),
            new Date(rs.getDate("geboortedatum").getTime()).toLocalDate());
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) throws SQLException {
        String q = "select * from reiziger where geboortedatum=?";
        PreparedStatement pst = this.conn.prepareStatement(q);
        pst.setDate(1, Date.valueOf(datum));
        List<Reiziger> reizigers = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int reiziger_id = rs.getInt("reiziger_id");
            String voorletters = rs.getString("voorletters");
            String tussenvoegsel = rs.getString("tussenvoegsel");
            String achternaam = rs.getString("achternaam");
            LocalDate geboortedatum = new Date(rs.getDate("geboortedatum").getTime()).toLocalDate();
            Reiziger reiziger = new Reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum);

            reizigers.add(reiziger);
        }
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        String q = "select * from reiziger";
        PreparedStatement pst = this.conn.prepareStatement(q);
        List<Reiziger> reizigers = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int reiziger_id = rs.getInt("reiziger_id");
            String voorletters = rs.getString("voorletters");
            String tussenvoegsel = rs.getString("tussenvoegsel");
            String achternaam = rs.getString("achternaam");
            LocalDate geboortedatum = new Date(rs.getDate("geboortedatum").getTime()).toLocalDate();
            Reiziger reiziger = new Reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum);

            reizigers.add(reiziger);
        }
        return reizigers;
    }
}

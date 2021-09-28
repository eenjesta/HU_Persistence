import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    public boolean save(OVChipkaart ov_chipkaart) throws SQLException;
    public boolean update(OVChipkaart ov_chipkaart) throws SQLException;
    public boolean delete(OVChipkaart ov_chipkaart) throws SQLException;
    public OVChipkaart findById(int id) throws SQLException;
    public List<OVChipkaart> findAll() throws SQLException;
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
    public void setrDAO(ReizigerDAO rDAO);
}

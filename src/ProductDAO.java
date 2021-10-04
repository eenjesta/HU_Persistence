import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    public boolean save(Product product) throws SQLException;
    public void saveLinkTable(Product product, OVChipkaart ov_chipkaart) throws SQLException;
    public void deleteLinkTable(Product product, OVChipkaart ov_chipkaart) throws SQLException;
    public List<Product> findAll() throws SQLException;
    public List<Product> findByOVChipkaart(OVChipkaart ov_chipkaart) throws SQLException;
    public boolean update(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;
    public void setoDAO(OVChipkaartDAO oDAO);
}

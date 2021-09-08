import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Alle reizigers:");
        try {
            String url = "jdbc:postgresql://localhost/ov?user=postgres&password=db123";
            Connection conn = DriverManager.getConnection(url);
            ReizigerDAO rDAO = new ReizigerDAOPsql(conn);
            testReizigerDAO(rDAO);

            AdresDAO aDAO = new AdresDAOPsql(conn);
            testAdresDAO(aDAO, rDAO);
        } catch (Exception e) {
            System.out.println("Oops something went wrong: " + e);
        }
    }

    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.parse(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Update een reiziger in de database
        Reiziger sietske_update = new Reiziger(77, "S", "", "Boeren", LocalDate.parse(gbdatum));
        System.out.print("[Test] Eerst reizigers achternaam, " + sietske.getAchternaam() + " voor ReizigerDAO.update() ");
        rdao.update(sietske_update);
        Reiziger reiziger = rdao.findById(77);
        System.out.print("[Test] Eerst reizigers achternaam, " + reiziger.getAchternaam() + " na ReizigerDAO.update() \n");

        // Verwijder een reiziger uit de database
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete() ");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Haal een reiziger op aan de hand van zijn ID (4) en dit moet F Memari zijn
        System.out.println("[Test] ReizigerDAO.findById(4) geeft de volgende reiziger:");
        System.out.println(rdao.findById(4));

        // Haal reizigers op aan de hand van een geboortedatum (2002-12-03) en dit moet F Memari en G Piccardo zijn
        List<Reiziger> reizigersByGb = rdao.findByGbdatum("2002-12-03");
        System.out.println("[Test] ReizigerDAO.findByGbdatum() geeft de volgende reizigers:");
        for (Reiziger r : reizigersByGb) {
            System.out.println(r);
        }
        System.out.println();

    }

    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test AdresADAO -------------");

        // Haal alle adressen op uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();

        // Maak een nieuw adres aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger siebe = new Reiziger(121, "S", "", "Jenk", LocalDate.parse(gbdatum));
        rdao.save(siebe);
        Adres appelhof = new Adres(7, "6678KL", "25", "Appelhof", "Utrecht", 121);
        System.out.print("[Test] Eerst " + adressen.size() + " reizigers, na ReizigerDAO.save() ");
        adao.save(appelhof);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " reizigers\n");

        // Update een adres in de database
        Adres appelhof_update = new Adres(7, "6678KL", "31", "Appelhof", "Utrecht", 121);
        System.out.print("[Test] Eerst adres huisnummer, " + appelhof.getHuisnummer() + " voor AdresDAO.update() ");
        adao.update(appelhof_update);
        Adres adres = adao.findById(7);
        System.out.print("[Test] Eerst adres huisnummer, " + adres.getHuisnummer() + " na AdresDAO.update() \n");

        // Haal een adres op aan de hand van zijn ID (4) en dit moet 3817CH 4 zijn
        System.out.println("[Test] AdresDAO.findById(4) geeft het volgende adres:");
        System.out.println(adao.findById(4));

        // Haal een adres op aan de hand van een reiziger
        System.out.println("[Test] AdresDAO.findByReiziger() geeft de volgende reizigers:");
        Adres adresr = adao.findByReiziger(siebe);
        System.out.println(adresr);

        // Verwijder een adres uit de database
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.delete() ");
        adao.delete(appelhof);
        rdao.delete(siebe);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " reizigers\n");
    }
}

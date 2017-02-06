import java.sql.*;
import java.util.HashMap;

// connecting with SQLite database

public class Puunaidud {
    private Connection conn;

    Puunaidud(){
        try {
            looYhendus();
            looTabelid();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

//  Et andmebaasi kasutada, peame sellega esiteks ühenduse looma--> KRISTERI KOODIST
//  (https://github.com/KristerV/javaSQLNaide/blob/master/src/Andmebaas.java)

    private void looYhendus() {
        try {
            Class.forName("org.sqlite.JDBC");                               // Lae draiver sqlite.jar failist
            conn = DriverManager.getConnection("jdbc:sqlite:puunaidud.db"); // loo ühendus andmebaasi failiga
        } catch ( Exception e ) {                                           // püüa kinni võimalikud errorid
            System.err.println(e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Ühendus loodud");                               // lihtsalt meie enda jaoks teade
    }

    private void looTabelid() throws Exception{
//  Loome tabeli kasutaja kus on kasutajaga seotud andmed
//  Teeme username'i unikaalsekse -> ei tohi olla kaht sama nimega
        String looKasutajad = "CREATE TABLE IF NOT EXISTS KASUTAJA (USER_ID INTEGER PRIMARY KEY, USERNAME TEXT, " +
                "PASSWORD TEXT, NAME TEXT, ADDRESS TEXT, SAAJAEMAIL TEXT, " +
                "CONSTRAINT unique_user UNIQUE(USERNAME));";
        teostaParing(looKasutajad);

//  Loome tabeli naidud, kus on näitudega seotud andmed
//  Foreign key -> kasutaja id peab eksisteerima ka kasutaja tabelis, ei saa sisestada näite,
//  mis ei kuulu ühelegi kasutajale
        String looNaidud = "CREATE TABLE IF NOT EXISTS NAIDUD (USER_ID INTEGER, KUUPAEV TEXT, SOE INTEGER, KYLM INTEGER," +
                "FOREIGN KEY(USER_ID) REFERENCES KASUTAJA(USER_ID));";
        teostaParing(looNaidud);
    }

//  Päringute teostamiseks
    private void teostaParing(String sql) throws Exception{
        // Statement objekt on vajalik, et SQL päringut käivitada
        Statement stat = conn.createStatement();
        stat.executeUpdate(sql);
        stat.close();
    }

//  Andmebaasi ühenduse sulgemine --> KRISTERI KOODIST
//  (https://github.com/KristerV/javaSQLNaide/blob/master/src/Andmebaas.java)
    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ühendus suletud");
    }

//  Login funktsioon --> osa KRISTERI KOODIST (https://github.com/KristerV/javaSQLNaide/blob/master/src/Andmebaas.java),
//  kui sisselogimine õnnestub,tagastame kasutaja_id, kui mitte, siis nulli (sest sellist id' ei kasutata)
    public Integer login(String username, String password) throws Exception{
        try {
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM KASUTAJA WHERE USERNAME = '" + username + "';";

//  Kuna tegu on päringuga siis käsuks on executeQuery ja ta tagastab andme objekti ResultSet.
//  ResultSet'i saab kasutada ainult siis, kui päring tagastab andmeid. Kui päring andmeid ei tagasta,
//  viskab ResultSet errori.
            ResultSet rs = stat.executeQuery(sql);
            String dbPassword = rs.getString("password"); //saame andmebaasist passwordi
            Integer userID = rs.getInt("user_id"); //saame andmebaasist kasutaja id

            rs.close();
            stat.close();
            sulgeYhendus();

            if (password.equals(dbPassword)){
                return userID;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.getMessage();
            throw e;
        }
    }

//  Kasutaja andmete saamine kasutaja andmete ekraanil
    public HashMap<String, String> saaKasutajaAndmed(Integer user_id) {
        HashMap<String, String> kasutajaAndmed = new HashMap<>();

        try {
            Statement stat = conn.createStatement();
            String kasutajaParing = "SELECT * FROM KASUTAJA WHERE USER_ID = " + user_id;

            ResultSet andmed = stat.executeQuery(kasutajaParing);

            kasutajaAndmed.put("user_id",andmed.getString("user_id"));
            kasutajaAndmed.put("username",andmed.getString("username"));
            kasutajaAndmed.put("password",andmed.getString("password"));
            kasutajaAndmed.put("name",andmed.getString("name"));
            kasutajaAndmed.put("address",andmed.getString("address"));
            kasutajaAndmed.put("saajaemail",andmed.getString("saajaemail"));

            andmed.close();
            stat.close();
            return kasutajaAndmed;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kasutajaAndmed;
    }

//  eelmise kuu näitude saamine
    public HashMap<String, String> saaEelmisedNaidud(Integer user_id){
        HashMap<String, String> eelmisedNaidud = new HashMap<>();

        try {
            Statement stat = conn.createStatement();
            String kasutajaParing = "SELECT * FROM NAIDUD WHERE USER_ID = " + user_id + " ORDER BY KUUPAEV DESC LIMIT 1";

            ResultSet andmed = stat.executeQuery(kasutajaParing);

            eelmisedNaidud.put("eelmine_NÄIT", andmed.getString("soe"));
            eelmisedNaidud.put("eelmine_NÄIT", andmed.getString("kylm"));

            andmed.close();
            stat.close();
            return eelmisedNaidud;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eelmisedNaidud;
    }

//  Kasutaja andmete uuendamine kasutaja andmete ekraanil
    public void salvestaKasutajaAndmed(HashMap<String, String> andmed) throws Exception{
        String user_id = andmed.get("user_id");
        String username = andmed.get("username");
        String password = andmed.get("password");
        String name = andmed.get("name");
        String address = andmed.get("address");
        String saajaemail = andmed.get("saajaemail");
        String salvestaAndmed;

        if (!user_id.equals("0")) { //juhul kui tegu on olemasoleva kasutajaga, siis Updateidme
            salvestaAndmed = String.format("UPDATE KASUTAJA SET USERNAME = '%s', PASSWORD = '%s', NAME = '%s', ADDRESS = '%s', SAAJAEMAIL = '%s' WHERE USER_ID = '%s';",
                    username, password, name, address, saajaemail, user_id);
        } else { //Muul juhul insertime uued andmed
            salvestaAndmed = String.format("INSERT INTO KASUTAJA (USERNAME, PASSWORD, NAME, ADDRESS, SAAJAEMAIL) VALUES('%s','%s','%s','%s','%s');",
                    username, password, name, address, saajaemail);
        }

        try {
            System.out.println(salvestaAndmed);
            teostaParing(salvestaAndmed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void salvestaNaidud(Integer user_id, String kuupaev, String soe, String kylm) throws Exception{
        // näitude salvestamine
        String salvestaNaidudParing = String.format("INSERT INTO NAIDUD (USER_ID, KUUPAEV, SOE, KYLM) VALUES ('%s', '%s', '%s', '%s')", user_id, kuupaev, soe, kylm);

        try {
            teostaParing(salvestaNaidudParing);
            saadaNaidud(user_id, kuupaev, soe, kylm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    private void saadaNaidud(Integer user_id, String kuupaev, String soe, String kylm) throws Exception{
        //näitude e-maili saatmine
        try {
            EmailiSaatja mail = new EmailiSaatja();
            mail.saada(user_id, kuupaev, soe, kylm);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
}
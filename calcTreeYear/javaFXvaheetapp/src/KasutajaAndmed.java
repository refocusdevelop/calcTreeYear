import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.HashMap;

public class KasutajaAndmed {
    private TextField nimi;
    private TextField kasutajanimi;
    private TextField parool;
    private TextField aadress;
    private TextField saajaemail;

    private Button tagasi;
    private Button salvesta;                    //important

    private VBox kasutajaAndmed;
    private Label teavitus;

    private Stage stage = new Stage();
    private Integer kasutaja_id;

    private HashMap<String, String> andmed;
    private Puunaidud naidud;

//  Constructor for starting functions
    KasutajaAndmed(Integer user_id) {
        naidud = new Puunaidud();
        kasutaja_id = user_id;
        teavitus = new Label();
        kasutajaAndmeteEkraan();
        setupTagasi();
        setupSalvesta();
    }

//Drawing the stage with elements:
    private void kasutajaAndmeteEkraan() {
        kasutajaAndmed = new VBox();
        kasutajaAndmed.setAlignment(Pos.CENTER);
        Scene kasutajaScene = new Scene(kasutajaAndmed, 300, 500);
        stage.setScene(kasutajaScene);

        stage.setTitle("EI OLE KASUTUSES");

        andmed = naidud.saaKasutajaAndmed(kasutaja_id);

        Label nimiLabel = new Label("Täisnimi");
        nimi = new TextField(andmed.get("name"));
        nimi.setMaxWidth(200);

        Label kasutajanimiLabel = new Label("Kasutajanimi (e-mail)");
        kasutajanimi = new TextField(andmed.get("username"));
        kasutajanimi.setMaxWidth(200);

        Label paroolLabel = new Label("Parool");
        parool = new TextField(andmed.get("password"));
        parool.setMaxWidth(200);

        Label saajameilLabel = new Label("Saaja e-mail");
        saajaemail = new TextField(andmed.get("saajaemail"));
        saajaemail.setMaxWidth(200);

        Label aadressLabel = new Label("Aadress");
        aadress = new TextField(andmed.get("address"));
        aadress.setMaxWidth(200);

        tagasi = new Button("Tagasi");
        salvesta = new Button("Salvesta");

        kasutajaAndmed.getChildren().addAll(nimiLabel, nimi, kasutajanimiLabel, kasutajanimi, paroolLabel, parool,
                saajameilLabel, saajaemail,aadressLabel, aadress, tagasi, salvesta, teavitus);
        stage.show();
    }

    private void setupTagasi(){                 //Back-button logic
        tagasi.setOnAction(event -> {
            if (kasutaja_id != 0) {             //if if it finds the user, to the next STAGE
                new SisestaNaidud(kasutaja_id);
                stage.close();
            } else {
                new LoginAken();               //if didnt find, to the Login-STAGE
                stage.close();
            }
        });
    }


    private void setupSalvesta(){               //Save data logic
        salvesta.setOnAction(event -> {


            andmed.put("user_id", String.valueOf(kasutaja_id));
            andmed.put("username", kasutajanimi.getText());
            andmed.put("password", parool.getText());
            andmed.put("name", nimi.getText());
            andmed.put("address", aadress.getText());
            andmed.put("saajaemail", saajaemail.getText());

            try {
                naidud.salvestaKasutajaAndmed(andmed);
                teavitus.setText("Andmed edukalt salvestatud");
                teavitus.setTextFill(Color.BURLYWOOD);

            } catch (Exception e) {
                teavitus.setText("Andmete salvestamine ebaõnnestus");
                teavitus.setTextFill(Color.RED);
            }
        });
    }
}

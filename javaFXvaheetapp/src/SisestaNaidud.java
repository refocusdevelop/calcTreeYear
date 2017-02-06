import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SisestaNaidud {
    private Stage stage = new Stage();
    private VBox veenaidud;
    private TextField raadiusnait;
    private TextField kulmnait;

    private Button saadaNait;
    private Button logOut;

    private Hyperlink puuAndmed;
    private Integer kasutaja_id;

    private HashMap<String, String> andmed;
    private Label teavitus;
    private Puunaidud naidud;

    SisestaNaidud(Integer user_id) {     //  Constructor for starting functions
        naidud = new Puunaidud();
        kasutaja_id = user_id;
        teavitus = new Label();
        naitudeAken();
        naitudeSaatmine();
        setupMinuAndmed();
        setupLogout();
    }

    private void naitudeAken() {
        stage.setTitle("kirjuat 2 mõõtu: salvestame, siis arvutame");

        andmed = naidud.saaEelmisedNaidud(kasutaja_id);

        veenaidud = new VBox();
        veenaidud.setAlignment(Pos.CENTER);
        Scene veenait = new Scene(veenaidud, 300, 500);

        puuAndmed = new Hyperlink("Mõõdulindiga mõõdetud andmed on:");

        Label soevesi = new Label("Puu ümbermõõdu näit");
        raadiusnait = new TextField();
        //raadiusnait.setPromptText(andmed.get("eelmine"));
        raadiusnait.setMaxWidth(100);

        Label kulmvesi = new Label("Puu pikkuse näit");
        kulmnait = new TextField();
        //kulmnait.setPromptText(andmed.get("eelmine"));
        kulmnait.setMaxWidth(100);

        saadaNait = new Button("Saada näidud GOES NEXT");  //MUUTUS
        logOut = new Button("Logi välja");

        veenaidud.getChildren().addAll(puuAndmed, soevesi, raadiusnait, kulmvesi, kulmnait, saadaNait, logOut);  //teavitus- out

        stage.setScene(veenait);
        stage.show();
    }

    //TwoScene twoscene = new TwoScene();     KAS SELLINE VAJA?
    private void naitudeSaatmine() {
        saadaNait.setOnAction(event -> {
            //this.twoscene= new TwoScene();
            new TwoScene();
            stage.close();
        });
    }

    private void setupMinuAndmed() {
        puuAndmed.setOnAction(event -> {
            new KasutajaAndmed(kasutaja_id);
            stage.close();
        });
    }

    private void setupLogout() {
        logOut.setOnAction(event -> {
            new LoginAken();
            stage.close();
        });
    }
}

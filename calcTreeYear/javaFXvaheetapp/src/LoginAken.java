import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LoginAken {
    private TextField kasutajanimi;
    private PasswordField password;
    private VBox login;
    private Button loginButton;
    private Hyperlink registreeri;
    private Stage stage = new Stage();
    private Label teavitus;

    //  Constructor for starting functions
    LoginAken() {
        teavitus = new Label();
        loginEkraan();
        setupLoginButton();
        setupRegistreeri();
    }

    private void annaViga(String teade){                //teavitus as a method. Using .setText
        teavitus.setText(teade);
        teavitus.setTextFill(Color.RED);
    }

    private void loginEkraan(){                       //LOGIN-aken
        stage.setTitle("PUUMÕÕDIK");

        login = new VBox();
        login.setAlignment(Pos.CENTER);
        Scene loginScene = new Scene(login, 400, 500);

        Label kasutajanimiLabel = new Label("Hüüdnimi (vihje: sss@sss.ee)");
        kasutajanimi = new TextField();
        kasutajanimi.setMaxWidth(280);

        Label passwordLabel = new Label("Pässakas (vihje: 999)");
        password = new PasswordField();
        password.setMaxWidth(280);

        loginButton = new Button("ENTER");
        registreeri = new Hyperlink("Registreeri");

        login.getChildren().addAll(kasutajanimiLabel, kasutajanimi, passwordLabel, password, loginButton, registreeri, teavitus);
        //(MINA: teavitus ei ole visuaalselt nähtav)

        stage.setScene(loginScene);
        stage.show();
    }


    private void setupLoginButton() {             //  Login-nupu loogika ja liikumine numbrite sisestamise aknasse
        loginButton.setOnAction(event -> {
            String username = kasutajanimi.getText();
            String parool = password.getText();

            if (!username.isEmpty() && !parool.isEmpty()){ //standard kontroll, ega väljad pole tühjad
                try {
                    Puunaidud naidud = new Puunaidud();
                    Integer kasutaja_id = naidud.login(username, parool);
                    naidud.sulgeYhendus();

                    if (kasutaja_id != 0) {         //kui leiab kasutaja?, logi sisse!!!!!!!!!

                        new SisestaNaidud(kasutaja_id);    //OLULINE, see ongi JÄRGMINE STEPP

                        stage.close();
                    } else {
                        annaViga("Vale parool!");
                    }
                } catch (Exception e) {
                    annaViga("Selliste andmetega kasutajat ei ole");
                }
            } else { //kui kumbki väli oli tühi, siis anna sellest teada
                annaViga("Mõlemad väljad peavad olema täidetud!");
            }
        });
    }


    private void setupRegistreeri() {       // Rega-nupu loogika ja liikumine numbrite sisestamise aken
        registreeri.setOnAction(event -> {
            new KasutajaAndmed(0);
            stage.close();
        });
    }
}
package TestDevelopment;
/**
 * Created by kullirist on 01/11/2016. http://docs.oracle.com/javafx/2/get_started/form.htm
 */
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Parool extends Application {

    VBox vboxParool = new VBox();

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vboxParool = new VBox();
        /*Scene sisselogimiseks = new Scene(vboxParool, 500, 300);
        primaryStage.setScene(sisselogimiseks);  //milline stseen on aktiivne
        primaryStage.setTitle("Puuvanuse Äpp! Welcome!");
        primaryStage.show();
        Label pealkiri = new Label("Ütle parool, kui tahad seda rakendust kasutada, muidu ei saa!");
        PasswordField a = new PasswordField();  //uus
        a.setPromptText("siis sisesta oma parool, vihje: jõulud");          //uus
        String b = a.getText();
        //a.clear();
        Button btn = new Button("Sign in!");
        //btn.setText("Vajuta siia!");  //topelt muidu, üleval juba on nupu pealkiri
        vboxParool.getChildren().addAll(pealkiri, a, btn);

        System.out.println("kontroll: tegin parooli koha");
        */
    }
}
package TestDevelopment;
/**
 * http://www.javafxtutorials.com/tutorials/switching-to-different-screens-in-javafx-and-fxml/
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TwoScene {
    public Stage thestage = new Stage();
    Button btnscene1, btnscene2;
    Label lblscene1, lblscene2;
    FlowPane pane1, pane2;
    Scene scene1, scene2;

    public TwoScene(){              //mis miks
        makeMenu();
    }

    /*Connection conn = null; //see tuleb sql-baasi lisamisest siin all, logimise käsu ühendus

    public void yhendus() {   //added this sector 24.01.

        try {               //andmebaasi SQLite lisamine from Krister Viirsaar
            Class.forName("org.sqlite.JDBC");                          // Lae draiver sqlite.jar failist, TEHTUD!
            conn = DriverManager.getConnection("jdbc:sqlite:test.db"); // loo ühendus andmebaasi failiga TEKKIS!

            String sql = "CREATE TABLE IF NOT EXISTS USERS (ID INT AUTO_INCREMENT, USERNAME TEXT, " + // jätkub järgmisel real
                    "PASSWORD TEXT, FULLNAME TEXT, NUMBER INT, ADDRESS TEXT);";
            // Statement objekt on vajalik, et SQL_Login käsku käivitada
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close();
        } catch (Exception e) {                                      // püüa kinni võimalikud errorid
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    */

    public void makeMenu(){

        btnscene1 = new Button("Nupp: mine puu suurus - 2"); //make things to put on panes
        btnscene2 = new Button("Nupp: algusesse - 1");
        btnscene1.setOnAction(e -> ButtonClicked(e));
        btnscene2.setOnAction(e -> ButtonClicked(e));
        lblscene1 = new Label("1. Siin on puu liik valik");
        lblscene2 = new Label("2. Siin on puu suurus valik");
        btnscene1.setPrefSize(300, 20);
        btnscene2.setPrefSize(170, 100);  //nupp slider kastis


        pane1=new FlowPane();   //make 2 Panes: KASTID VASAKULT PAREMALE
        pane2=new FlowPane();
        pane1.setVgap(50);
        pane2.setVgap(50);

        pane1.setStyle("-fx-background-color: tan;-fx-padding: 10px;"); //set background color of each Pane
        pane2.setStyle("-fx-background-color: green;-fx-padding: 10px;");

        ToggleGroup g = new ToggleGroup();          //ise lisasin selle osa
        RadioButton p1 = new RadioButton("Valik 1: VAHER");
        RadioButton p2 = new RadioButton("Valik 2: MÄND");
        RadioButton p3 = new RadioButton("Valik 3: TAMM");
        p1.setToggleGroup(g);
        p2.setToggleGroup(g);
        p3.setToggleGroup(g);
        p3.setSelected(true);


        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(40);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);


        pane1.getChildren().addAll(lblscene1, btnscene1, p1, p2, p3); //add everything to panes
        pane2.getChildren().addAll(lblscene2, btnscene2, slider);


        //g.getSelectedToggle(); siin on aretuskoht

        //make 2 scenes from 2 panes
        scene1 = new Scene(pane1, 400, 200);  //liikide kast, nende suurus määras kuhu default satub nupp
        scene2 = new Scene(pane2, 200, 400);  //slider kast

        thestage.setTitle("Minu puu äpp!");
        thestage.setScene(scene1);
        //thestage.setScene(menuContentFrame); //HP
        thestage.show();

    }
    public void ButtonClicked(ActionEvent e)
    {
        if (e.getSource()==btnscene1)
            thestage.setScene(scene2);
        else
            thestage.setScene(scene1);
    }
    
     /*private void lahkumine() {          //ARENDADA
        puuproge.close();
        StackPane stack = new StackPane();
        Label go = new Label("Good Bye!!!");
        stack.getChildren().add(go);
        Scene scene = new Scene(stack, 300, 300);
        Stage goStage = new Stage();
        goStage.setScene(scene);
        goStage.show();
    }
    */


}



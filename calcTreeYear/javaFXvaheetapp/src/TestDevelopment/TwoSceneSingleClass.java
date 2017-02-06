package TestDevelopment;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

//jajah, moment olen uhkt et tehen ühest 2ks ja 2st üks

public class TwoSceneSingleClass extends Application {

    public Stage thestage = new Stage();
    Button btnscene1, btnscene2;
    Label lblscene1, lblscene2;
    FlowPane pane1, pane2;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {

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


}


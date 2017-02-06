import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

//SOURCE: http://docs.oracle.com/javafx/2/events/convenience_methods.htm

public class TwoScene {

    public Stage thestage = new Stage();
    Button btnscene1, btnscene2;
    Label lblscene1, lblscene2;
    FlowPane pane1, pane2;
    Scene scene1, scene2;

    public void opens() {

        btnscene1 = new Button("Button: to the page - 2");
        btnscene2 = new Button("Button: to the page - 1");
        btnscene1.setOnAction(e -> ButtonClicked(e));
        btnscene2.setOnAction(e -> ButtonClicked(e));
        lblscene1 = new Label("1. Select the type");
        lblscene2 = new Label("2. Select size");
        btnscene1.setPrefSize(300, 20);
        btnscene2.setPrefSize(170, 100);


        pane1=new FlowPane();
        pane2=new FlowPane();
        pane1.setVgap(50);
        pane2.setVgap(50);
        pane1.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        pane2.setStyle("-fx-background-color: green;-fx-padding: 10px;");

        ToggleGroup g = new ToggleGroup();
        RadioButton p1 = new RadioButton("Choice 1: VAHER");
        RadioButton p2 = new RadioButton("Choice 2: MÄND");
        RadioButton p3 = new RadioButton("Choice 3: TAMM");
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

        pane1.getChildren().addAll(lblscene1, btnscene1, p1, p2, p3);
        pane2.getChildren().addAll(lblscene2, btnscene2, slider);

        //g.getSelectedToggle(); ??? DEVELOP HOW TO SAVE USER DATA FROM THE CHOICE


        scene1 = new Scene(pane1, 400, 200);  //type
        scene2 = new Scene(pane2, 200, 400);  //slider

        thestage.setTitle("Headline: lets review it!");
        thestage.setScene(scene1);
        //thestage.setScene(menuContentFrame); //HP....
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Mateusz on 20.01.2017.
 */
public class Board {

    private Scene boardScene;
    private int sizeX;
    private int sizeY;

    public Board(String input, int x, int y, HashSet hs,Main main){
        this.sizeX=x;
        this.sizeY=y;

        BorderPane pane =new BorderPane();

        Button resetButton = new Button("Reset");
        resetButton.setMinSize(100,40);
        resetButton.setStyle("-fx-background-color: darkgrey;");
        resetButton.setOnAction( e ->{
            pane.setCenter(fillCells(input,hs));
        });

        Button backButton = new Button("Back");
        backButton.setMinSize(100,40);
        backButton.setStyle("-fx-background-color: darkgrey;");
        backButton.setOnAction(e-> main.setMainScene());

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.getChildren().addAll(backButton,resetButton);

        pane.setStyle("-fx-background-image: url('numbers.png'); -fx-background-size: cover;");
        pane.setCenter(fillCells(input,hs));
        pane.setBottom(hbox);
        pane.setPadding(new Insets(5,5,5,5));
        BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);
        BorderPane.setMargin(pane.getBottom(),new Insets(5,5,5,5));
        boardScene =new Scene(pane);
    }

    public Scene getScene() {
        return boardScene;
    }

    private GridPane fillCells(String s,HashSet hs){
        GridPane layout = new GridPane();
        ArrayList<Field> fieldList = new ArrayList<>();
        for(int i=0;i<sizeX*sizeX*sizeY*sizeY;i++) fieldList.add(new Field());
        VetoableListener vetoableListener = new VetoableListener(hs, s, sizeX, sizeY);
        PropertyListener propertyListener = new PropertyListener(fieldList, sizeX, sizeY);
        for(int it=0;it<sizeY;it++) {
            for(int jt=0;jt<sizeX;jt++) {
                GridPane grid = new GridPane();
                grid.setStyle("-fx-border-color: darkblue; -fx-border-width: 1;");
                for (int i = 0; i < sizeX; i++) {
                    for (int j = 0; j < sizeY; j++) {
                        int poz=(it*sizeX+i)*(sizeX*sizeY) + (jt*sizeY)+j;
                        String value=s.substring(poz, poz + 1);
                        if(value.equals(" ")) value="";
                        Field field=new Field();
                        field.addVetoableChangeListener(vetoableListener);
                        field.addPropertyChangeListener(propertyListener);
                        field.initialize(value,it*sizeX+i, jt*sizeY+j,!value.equals(""));
                        field.getTextField().textProperty().addListener((observableValue, oldValue, newValue) -> {
                            try {
                                field.setValue(newValue);
                            } catch (PropertyVetoException e) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Veto");
                                alert.setHeaderText(null);
                                alert.setContentText("You cannot place this value there.");

                                alert.showAndWait();
                                field.getTextField().setText(oldValue);

                            }
                        });
                        grid.add(field.getTextField(), j, i);
                        fieldList.set(poz,field);
                    }
                }
                layout.add(grid,jt,it);
            }
        }
        return layout;
    }

}

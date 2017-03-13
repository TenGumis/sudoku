import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

/**
 * Created by Mateusz on 21.01.2017.
 */
public class Menu {

    private Scene menuScene;
    private Button okButton;
    private ComboBox comboBox;

    public Menu(){
        BorderPane pane =new BorderPane();
        okButton = new Button("OK");
        okButton.setMinSize(100,40);
        okButton.setStyle("-fx-background-color: darkgrey;");
        pane.setMinSize(240,300);
        pane.setStyle("-fx-background-image: url('numbers.png'); -fx-background-size: cover;");
        comboBox=new ComboBox();
        comboBox.setMinSize(100,40);
        comboBox.getItems().addAll("4x4","6x6","9x9","10x10");
        comboBox.setPromptText("Chose size ");
        comboBox.setStyle("-fx-background-color: darkgrey;");
        pane.setCenter(comboBox);
        pane.setBottom(okButton);
        pane.setPadding(new Insets(10,10,10,10));
        BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);
        menuScene=new Scene(pane);
    }

    public Scene getMenuScene(){
        return menuScene;
    }

    public Button getOkButton(){
        return okButton;

    }

    public int getChoice(){
        int res = comboBox.getSelectionModel().getSelectedIndex();
        System.out.println(res);
        return res;
    }

}

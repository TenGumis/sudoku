import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import java.beans.*;
import java.io.Serializable;

/**
 * Created by Mateusz on 20.01.2017.
 */

class Field implements Serializable{

    private boolean green;
    private TextField textField=new TextField();
    private String value;
    private int pozX;
    private int pozY;
    private boolean locked;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
    private String cssStyle="-fx-border-color: gray; -fx-border-width: 1; -fx-font-size: 20; -fx-text-fill: gray;";

    Field() {

    }

    void initialize(String value, int pozX, int pozY, boolean locked){
        this.green=false;
        this.value=value;
        this.pozX=pozX;
        this.pozY=pozY;
        this.locked=locked;
        textField.setText(value);
        textField.setMinSize(60, 60);
        textField.setMaxSize(60, 60);
        textField.setAlignment(Pos.CENTER);
        textField.setStyle(cssStyle);
        if(locked){
            textField.setEditable(false);
            textField.setStyle(
                    "-fx-border-color: gray; -fx-border-width: 1; -fx-font-weight: bold;  -fx-font-size: 20; -fx-text-fill: black;"
            );
        }
    }

    String getValue(){
        return value;
    }

    boolean getGreen(){
        return this.green;
    }

    void setBackgroundRed(boolean x){
        this.green=false;
        if(x){
            textField.setStyle( "-fx-background-color: darkred; "+cssStyle);
        }
        else{
            textField.setStyle(cssStyle);
        }
    }

    void fillGreen(){
        this.green=true;
        if(locked){
            textField.setStyle("-fx-background-color: green; -fx-border-color: gray; -fx-border-width: 1; -fx-font-weight: bold;  -fx-font-size: 20; -fx-text-fill: black;");
        }else {
            textField.setStyle(cssStyle+"-fx-background-color: green;");
        }
    }

    void fillWhite(){
        this.green=false;
        if(locked){
           textField.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-font-weight: bold;  -fx-font-size: 20; -fx-text-fill: black;");
        }else {
            textField.setStyle(cssStyle);
        }
    }

    void setValue(String value) throws PropertyVetoException {
        String oldValue=new String(this.value);
        vetoableChangeSupport.fireVetoableChange("value",oldValue,value);
        this.value=value;
        this.textField.setText(value);
        propertyChangeSupport.firePropertyChange("value",oldValue,value);
    }

    TextField getTextField(){
        return textField;
    }

    int getPozX(){
        return pozX;
    }

    int getPozY(){
        return pozY;
    }

    void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

}

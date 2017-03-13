import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.HashSet;

public class Main extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        primaryStage.setTitle("Sudoku");
        setMainScene();
    }

    public void setMainScene(){
        Menu menu=new Menu();
        Button button=menu.getOkButton();
        Scene scene= menu.getMenuScene();
        button.setOnAction(e -> {
            HashSet<String> hs;
            String text;
            int x,y;
            int choice = menu.getChoice();
            if(choice==-1) return;

            if(choice==0){
                x=2;
                y=2;
                hs=new HashSet<>();
                for(int i=1;i<=4;i++){
                    hs.add(String.valueOf(i));
                }
                hs.add("");
                text=" 13 2      3 21 ";
            }else if(choice==1){
                x=2;
                y=3;
                hs=new HashSet<>();
                for(int i=1;i<=6;i++){
                    hs.add(String.valueOf(i));
                }
                hs.add("");
                text="  3 1 56 32  542 32 645  12 45 4 1  ";
            }else if(choice==2){
                x=3;
                y=3;
                hs=new HashSet<>();
                for(int i=1;i<=9;i++){
                    hs.add(String.valueOf(i));
                }
                hs.add("");
                text="53  7    6  195    98    6 8   6   34  8 3  17   2   6 6    28    419  5    8  79";
            }else{
                x=2;
                y=5;
                hs=new HashSet<>();
                for(int i=0;i<=9;i++){
                    hs.add(String.valueOf(i));
                }
                hs.add("");
                text="   4   5 7 027     91 3 825   2 7  9 1 8   2    9  9    8   0 4 2  8 3   860 2 59     405 5 8   2   ";

            }
            Board board=new Board(text,x,y,hs,this);
            primaryStage.setScene(board.getScene());
            primaryStage.show();
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

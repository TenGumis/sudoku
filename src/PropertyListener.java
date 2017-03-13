import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Mateusz on 20.01.2017.
 */
public class PropertyListener implements PropertyChangeListener {

    private int sizeX;
    private int sizeY;
    private ArrayList<Field> fieldList;
    private HashSet<Field> redFields;

    public PropertyListener(ArrayList<Field> AL, int x, int y){
        this.fieldList=AL;
        this.sizeX=x;
        this.sizeY=y;
        this.redFields=new HashSet<>();
    }
    private boolean rectangleConflict(String value,int x,int y){
        boolean result=false;
        int tmpX,tmpY,poz;
        tmpX=x/sizeX;
        tmpY=y/sizeY;
        for(int i=0;i<sizeX;i++) {
            for(int j=0;j<sizeY;j++){
                poz=(tmpX*sizeX+i)*(sizeX*sizeY)+(tmpY*sizeY+j);
                if(fieldList.get(poz).getValue().equals(value) && poz!=x*(sizeX*sizeY)+y){
                    fieldList.get(poz).setBackgroundRed(true);
                    redFields.add(fieldList.get(poz));
                    result=true;
                }
            }
        }
        return result;
    }

    private boolean verticalConflict(String value,int x,int y){
        boolean result=false;
        int poz;
        for(int i=0;i<sizeX*sizeY;i++){
            poz=i*(sizeX*sizeY)+y;
            if(fieldList.get(poz).getValue().equals(value) && poz!=x*(sizeX*sizeY)+y){
                fieldList.get(poz).setBackgroundRed(true);
                redFields.add(fieldList.get(poz));
                result=true;
            }
        }
        return result;
    }

    private boolean horizontalConflict(String value,int x,int y){
        boolean result=false;
        int poz;
        for(int i=0;i<sizeX*sizeY;i++){
            poz=(x)*(sizeX*sizeY)+i;
            if(fieldList.get(poz).getValue().equals(value) && poz!=x*(sizeX*sizeY)+y){
                fieldList.get(poz).setBackgroundRed(true);
                redFields.add(fieldList.get(poz));
                result=true;
            }
        }
        return result;
    }

    private int checkAll(){
        int res=0;
        for(Field f:  fieldList){
            if(!f.getValue().equals("")) res+=1;
        }
        return  res;
    }

    private void fillGreenAll(){
        for(Field f:  fieldList){
            f.fillGreen();
        }
    }

    private void fillWhiteAll(){
        for(Field f:  fieldList){
            f.fillWhite();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String value = String.valueOf(evt.getNewValue());
        Field field = (Field) evt.getSource();
        if(field.getGreen()){
            fillWhiteAll();
        }
        if (!value.equals("")) {
            if (rectangleConflict(value, field.getPozX(), field.getPozY())) {
                redFields.add(field);
                field.setBackgroundRed(true);
            }
            if (verticalConflict(value, field.getPozX(), field.getPozY())) {
                redFields.add(field);
                field.setBackgroundRed(true);
            }
            if (horizontalConflict(value, field.getPozX(), field.getPozY())) {
                redFields.add(field);
                field.setBackgroundRed(true);
            }
        }

        for(Field f: new HashSet<>(redFields)){
            if(f.getValue().equals("")){
                redFields.remove(f);
                f.setBackgroundRed(false);
            }
            else if(
                    !rectangleConflict(f.getValue(),f.getPozX(),f.getPozY())
                    && !horizontalConflict(f.getValue(),f.getPozX(),f.getPozY())
                    && !verticalConflict(f.getValue(),f.getPozX(),f.getPozY())
               ){
                redFields.remove(f);
                f.setBackgroundRed(false);
            }
        }

        if(redFields.size()==0){
            if(checkAll()==sizeX*sizeY*sizeX*sizeY){
                fillGreenAll();
            }
        }

    }
}

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.HashSet;

/**
 * Created by Mateusz on 20.01.2017.
 */
public class VetoableListener implements VetoableChangeListener {

    private HashSet<String> correctValues;
    private String s;
    private int sizeX;
    private int sizeY;

    public VetoableListener(HashSet<String> HS, String s, int x, int y){
        this.correctValues=HS;
        this.s=s;
        this.sizeX=x;
        this.sizeY=y;
    }

    private boolean checkRectangle(String value,int x,int y){
        int tmpX,tmpY,poz;
        tmpX=x/sizeX;
        tmpY=y/sizeY;
        for(int i=0;i<sizeX;i++) {
            for(int j=0;j<sizeY;j++){
                poz=(tmpX*sizeX+i)*(sizeX*sizeY)+(tmpY*sizeY+j);
                if(s.substring(poz,poz+1).equals(value)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkVertical(String value,int x,int y){
        int poz;
        for(int i=0;i<sizeX*sizeY;i++){
            poz=i*(sizeX*sizeY)+y;
            if(s.substring(poz,poz+1).equals(value)){
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontal(String value,int x,int y){
        int poz;
        for(int i=0;i<sizeX*sizeY;i++){
            poz=(x)*(sizeX*sizeY)+i;
            if(s.substring(poz,poz+1).equals(value)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        String value = String.valueOf(evt.getNewValue());
        Field field = (Field) evt.getSource();
        if(correctValues.contains(value)){
            if (value.equals("")) return;
            if (!checkRectangle(value,field.getPozX(),field.getPozY())){
                throw new PropertyVetoException("Wrong", evt);
            }
            if (!checkVertical(value,field.getPozX(),field.getPozY())){
                throw new PropertyVetoException("Wrong", evt);
            }
            if (!checkHorizontal(value,field.getPozX(),field.getPozY())){
                throw new PropertyVetoException("Wrong", evt);
            }
        }
        else throw new PropertyVetoException("Wrong", evt);
    }
}

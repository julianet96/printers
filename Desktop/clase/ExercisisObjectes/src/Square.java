/**
 * Created by julià on 31/01/2017.
 */
public class Square extends Rectangle {
   public Square(){}
   public Square(double side){super(side,side);}
   public Square(double side, String color, boolean filled){
        super(side,side,color,filled);
    }
    public double getSide(){return getWidth();}
    public void setSide(double Side){
       super.setWidth(Side);
        super.setLength(Side);
    }

    @Override
    public void setWidth(double width) {
        setSide(width);
    }

    @Override
    public void setLength(double length) {
        setSide(length);
    }

    @Override
    public String toString() {
        return"Square de: "+getSide()+" "+ super.toString();
    }
}

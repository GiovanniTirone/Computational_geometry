package Constants.dimensions;

public class Dim2 extends Dim {

    private static Dim2 dim2 = new Dim2(2);

    private Dim2(int value){
        super(value);
    }

    public static Dim2 get() {
        return dim2;
    }

    public static int getVal() {
        return dim2.getValue();
    }

}



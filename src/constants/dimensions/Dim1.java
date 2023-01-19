package constants.dimensions;

public class Dim1 extends Dim {

    private static Dim1 dim1 = new Dim1(1);

    private Dim1 (int value){
        super(value);
    }

    public static Dim1 get() {
        return dim1;
    }

    public static int getVal() {
        return dim1.getValue();
    }

}



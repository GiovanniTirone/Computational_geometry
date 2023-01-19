package constants.dimensions;

public class Dim3 extends Dim {

    private static Dim3 dim3 = new Dim3(3);

    private Dim3(int value){
        super(value);
    }

    public static Dim3 get() {
        return dim3;
    }

    public static int getVal() {
        return dim3.getValue();
    }

}



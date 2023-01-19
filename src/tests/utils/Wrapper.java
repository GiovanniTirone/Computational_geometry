package tests.utils;

public class Wrapper<T> {

    private T obj;

    public Wrapper(T obj){
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void pointToNewObj(T newObj) {
        this.obj = newObj;
    }

    public boolean isNull () {
        return obj == null;
    }

}

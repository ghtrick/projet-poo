package src.main.java.abstractClass;

public abstract class AbstractElement<E> {
    protected E val;

    public AbstractElement(E val) {
        this.val=val;
    }
    public E getVal() {
        return val;
    }
}

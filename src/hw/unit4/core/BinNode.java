package hw.unit4.core;

public class BinNode<T> {
    private T value;
    private BinNode<T> left;
    private BinNode<T> right;

    public BinNode(T value, BinNode<T> left, BinNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    //Getter for Value
    public T getValue() {
        return value;
    }

    //Setter for value
    public void setValue(T value) {
        this.value = value;
    }

    //Getter for Left
    public BinNode<T> getLeft() {
        return left;
    }

    //Setter for left
    public void setLeft(BinNode<T> left) {
        this.left = left;
    }

    //Getter for Right
    public BinNode<T> getRight() {
        return right;
    }

    //Setter for right
    public void setRight(BinNode<T> right) {
        this.right = right;
    }

    public boolean hasRight(){
        return right != null;
    }

    public boolean hasLeft(){
        return left != null;
    }

    @Override
    public String toString(){
        return "(" + left + "," + value + "," + right + ")";
    }
}

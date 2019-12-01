package expiramental.inegral;

public abstract class IntegralUnitNode {

    protected IntegralUnitNode[] children;
    protected String value;

    public IntegralUnitNode(String value){
        this.value = value;
        children = new IntegralUnitNode[2];
    }

    public void setChild(int index, IntegralUnitNode value){
        children[index] = value;
    }
    public abstract String integrate();
}

public class SkipNode {
    private SkipNode left;
    private SkipNode right;
    private SkipNode parent;
    private SkipNode child;
    private Boolean hasChild;
    private Boolean hasParent;
    private double value;

    public SkipNode(double val) {
        left = null;
        right = null;
        child = null;
        parent = null;
        hasChild = false;
        hasParent = false;
        value = val;
    }

    public void setRight(SkipNode right) {
        this.right = right;
    }
    public void setLeft(SkipNode left){
        this.left = left;
    }
    public void setParent(SkipNode parent){
        this.parent = parent;
        this.hasParent = true;
    }
    public void setChild(SkipNode child){
        this.child = child;
        this.hasChild = true;
    }
    public SkipNode getRight(){
        return right;
    }
    public SkipNode getChild() {
        return child;
    }
    public SkipNode getParent() {
        return parent;
    }
    public double getValue(){

        return value;
    }
    public boolean hasChild(){
        return this.hasChild;
    }
    public Boolean hasParent(){
        return this.hasParent;
    }
}
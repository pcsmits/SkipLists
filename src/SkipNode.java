public class SkipNode {
    private SkipNode left;
    private SkipNode right;
    private SkipNode parent;
    private SkipNode child;
    private Boolean hasChild;
    private Boolean hasParent;
    private Boolean hasRight;
    private Boolean locked;
    private double value;
    private int level;

    public SkipNode(double val, int level) {
        left = null;
        right = null;
        child = null;
        parent = null;
        hasChild = false;
        hasParent = false;
        hasRight = false;
        value = val;
        this.level = level;
        locked = false;
    }

    public void setRight(SkipNode right) {
        this.right = right;
        this.hasRight = true;
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
    public void setLock(Boolean lock){
        locked = lock;
    }

    public SkipNode getRight(){
        return right;
    }
    public SkipNode getLeft(){
        return left;
    }
    public SkipNode getChild() {
        return child;
    }
    public SkipNode getParent() {
        return parent;
    }
    public Boolean getLock() {return locked;}
    public double getValue(){
        return value;
    }
    public boolean hasChild(){
        return this.hasChild;
    }
    public boolean hasParent(){
        return this.hasParent;
    }
    public boolean hasRight(){
        return this.hasRight;
    }
}
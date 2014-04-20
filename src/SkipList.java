import  java.util.Random;

public class SkipList {

    private int levels = 0;
    private SkipNode head;
    private SkipNode tail;

	public SkipList () {
        levels = 1;
        head = new SkipNode(Double.NEGATIVE_INFINITY);
        tail = new SkipNode(Double.POSITIVE_INFINITY);
        horizontalLink(head, tail);
	}

	public void insertNode(double num){
        insert(head, num);
	}
	public void removeNode(){

	}
	public SkipNode searchNode(){
        SkipNode node = null;

        return node;
	}

    public int getLevels () {return this.levels;}

    public void horizontalLink(SkipNode left, SkipNode right){
        left.setRight(right);
        right.setLeft(left);
    }
    public void verticalLink(SkipNode parent, SkipNode child){
        parent.setChild(child);
        child.setParent(parent);
    }

    private void insert(SkipNode node, double val){

        if(val > node.getRight().getValue()){
            //move left
            insert(node.getRight(), val);
        } else if (node.hasChild()){
            //move down
            insert(node.getChild(), val);
        }

        /** no where to go insert
         *  make val into a node and create horizontal links
         *  then flip coin and create vertical links
         */
        else {
            SkipNode newNode = new SkipNode(val);
            SkipNode right = node.getRight();

            horizontalLink(node, newNode);
            horizontalLink(newNode, right);

            grow(node, val);
        }
    }
    private void grow(SkipNode left, double val){
        if(flip()){
            SkipNode newNode = new SkipNode(val);

            if(left.hasParent()){
                horizontalLink(left.getParent(), newNode);
                horizontalLink(newNode, left.getParent().getRight());
                verticalLink(newNode, left.getRight());

                //recurse
                grow(newNode, val);
            } else {
                //create a new level change head and tail
                levels = levels++;

                // for the new level you need 3 new nodes head tail and new val
                SkipNode newHead = new SkipNode(Double.NEGATIVE_INFINITY);
                SkipNode newTail = new SkipNode(Double.POSITIVE_INFINITY);

                //start linking on tail end to avoid concurrency issues
                verticalLink(newTail, tail);
                horizontalLink(newNode, newTail);
                verticalLink(newNode, left.getRight());
                horizontalLink(newHead, newNode);
                verticalLink(newHead, head);

                head = newHead;
                tail = newTail;
            }
        }
    }
    private boolean flip(){
        Random rnd = new Random();
        Double val = rnd.nextDouble();

        if(val < .5){
            return false;
        }
        return true;
    }

    public String toString(){
        //pring string
        return "nonsense";
    }
}

import  java.util.Random;

public class SkipList  {

    private int levels;
    private SkipNode head;
    private SkipNode tail;
    public int traversal = 0;

	public SkipList () {
        levels = 1;
        head = new SkipNode(Double.NEGATIVE_INFINITY, levels);
        tail = new SkipNode(Double.POSITIVE_INFINITY, levels);
        horizontalLink(head, tail);
	}

	public void insert(double num){
        insertNode(head, num);
	}

	public double search(double search) throws ValueNotFoundException {
        SkipNode node = head;
        traversal = 0;
        while(search != node.getValue()) {
            System.out.print(node.getValue() + ", ");
            if (search > node.getRight().getValue()) {
                //move right
                node = node.getRight();
            } else if (search < node.getRight().getValue()) {
                //move down
                if (!node.hasChild()) {
                    // no child but not correct value
                    throw new ValueNotFoundException("Value Not Found in Skip List");
                }
                node = node.getChild();
            } else {
                node = node.getRight();
                while(node.hasChild()) {
                    node = node.getChild();
                    traversal++;
                }
            }
            traversal++;
        }
        return node.getValue();
	}


    public void horizontalLink(SkipNode left, SkipNode right){
        // when making new links lock left and left parent
        left.setLock(true);

        right.setLeft(left);
        left.setRight(right);

        left.setLock(false);
    }
    public void verticalLink(SkipNode parent, SkipNode child){
        child.setParent(parent);
        parent.setChild(child);
    }

    public int getLevels () {return this.levels;}


    public void printList(){
        //pring string
        SkipNode node = head;
        printRow(node);
        while(node.hasChild()){
            node = node.getChild();
            printRow(node);
        }
    }

    private void insertNode(SkipNode node, double val){
        // check for locks
        if(node.getLock()){
            //if locked repeat until not locked
            insertNode(node, val);
        }
        if(val > node.getRight().getValue()){
            //move left
            insertNode(node.getRight(), val);
        } else if (node.hasChild()){
            //move down
            insertNode(node.getChild(), val);
        }

        /** no where to go insert
         *  make val into a node and create horizontal links
         *  then flip coin and create vertical links
         */
        else {
            SkipNode newNode = new SkipNode(val, 1);
            SkipNode right = node.getRight();

            horizontalLink(node, newNode);
            horizontalLink(newNode, right);

            grow(node, val, 1);
        }
    }
    private void grow(SkipNode left, double val, int height){
        if(flip()){
            height++;
            SkipNode newNode = new SkipNode(val, height);
            if(height > levels){
                //create a new level change head and tail
                //System.out.println("\tNew level created " + height + " - " + levels);
                levels = levels + 1;

                // for the new level you need 3 new nodes head tail and new val
                SkipNode newHead = new SkipNode(Double.NEGATIVE_INFINITY, height);
                SkipNode newTail = new SkipNode(Double.POSITIVE_INFINITY, height);

                //start linking on tail end to avoid concurrency issues
                verticalLink(newTail, tail);
                horizontalLink(newNode, newTail);
                verticalLink(newNode, left.getRight());
                horizontalLink(newHead, newNode);
                verticalLink(newHead, head);

                head = newHead;
                tail = newTail;
            } else {
                // insert at first node to the left with a parent
                SkipNode parentLeft = getFirstWithParent(left);
                horizontalLink(newNode, parentLeft.getParent().getRight());
                horizontalLink(parentLeft.getParent(), newNode);
                verticalLink(newNode, left.getRight());

                //recurse
                grow(parentLeft.getParent(), val, height);


            }
        }
    }
    private boolean flip(){
        Random rnd = new Random();
        Double val = rnd.nextDouble();

        if(val < .5){
            return false;
        }
        //System.out.println("\tgrowing");
        return true;
    }

    private SkipNode getFirstWithParent(SkipNode node){
        while(!node.hasParent()){
            node = node.getLeft();
        }
        return node;
    }


    private void printRow(SkipNode node) {
        while(node.hasRight()){
            System.out.print(node.getValue() + " -- ");
            node = node.getRight();
        }
        System.out.println(node.getValue());
    }
}

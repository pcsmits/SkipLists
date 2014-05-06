import java.util.Random;
import java.util.Scanner;

public class Builder extends Thread
{
	public static void main(String[] args) {
        SkipList list = new SkipList();
        RedBlackTree<Double> tree = new RedBlackTree<Double>();
        Random rnd = new Random();

        // Loop Through Arguements
        int size = 128;
        int dataSet = 0; //random = 0, linear = 1
        boolean debug = false;
        boolean duplicates = false;
        boolean query = false;
        boolean threads = false;

        int loop = 1;
        for (int i = 0; i < args.length; i++){
            try {
                size = Integer.parseInt(args[i]);
            } catch(NumberFormatException e) {
                //do nothing
            }
           if(args[i].equals("debug")){
               debug = true;
           } else if (args[i].equals("random")) {
                dataSet = 0;
           } else if (args[i].equals("linear")){
               dataSet = 1;
           } else if (args[i].equals("duplicates")){
               duplicates = true;
           } else if (args[i].equals("repeat")){
               query = false;
               loop = 100;
           } else if (args[i].equals("search")){
               query = true;
           } else if (args[i].equals("threaded")){
               threads = true;
           }
        }

        if(threads){
            (new Builder()).start();
            (new Builder()).start();
            (new Builder()).start();
            (new Builder()).start();
        }

        double averageList = 0;
        double averageTree = 0;
        for (int run = 0; run < loop; run++) {
            list = new SkipList();
            tree = new RedBlackTree<Double>();
            for (int i = 0; i < size; i++) {
                Double val = rnd.nextDouble();
                double number = 0;
                if (dataSet == 0) {
                    number = (double) (int) (val * 1000);
                } else if (dataSet == 1) {
                    number = i;
                }

                list.insert(number);
                tree.insert(number);

            }
            if(debug) {list.printList();}

            averageList += list.getLevels();
            averageTree += tree.height();

            System.out.println(list.getLevels() + " list height");
            System.out.println(tree.height() + " tree height");
        }
        System.out.println("");
        System.out.println(averageList/loop + ": Average List Height");
        System.out.println(averageTree/loop + ": Average Tree Height");


        Scanner scan = new Scanner(System.in);
        String input;
        if (query) {
            System.out.println("Search Data Structures: ");
        }

        boolean failed = true;
        double listValue = -1;
        double treeValue = -1;

        while (failed && query) {
            input = scan.nextLine();
            try {
                listValue = list.search(Double.parseDouble(input));
                tree.getComparison(Double.parseDouble(input));
                failed = false;
            } catch (ValueNotFoundException E) {
                System.out.println("");
                System.out.println("Not Found, Try new search: ");
                failed = true;
            }
        }

        if (query) {
            System.out.println("");
            System.out.println("List comparisons: " + list.traversal);
            System.out.println("Tree comparison: "  + tree.comparison);
        }
	}

    public void run() {

        System.exit(0);
    }
}

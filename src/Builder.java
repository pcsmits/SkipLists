import java.util.Random;
import java.util.Scanner;

public class Builder
{
	public static void main(String[] args) {
        SkipList list = new SkipList();
        Random rnd = new Random();

        for (int i = 0; i < 16; i++){
            Double val = rnd.nextDouble();
            double rand = (double) (int) (val * 100);
            //System.out.println(rand);
            list.insert(rand);
        }
        list.printList();
        System.out.println(list.getLevels() + " levels");

        Scanner scan = new Scanner(System.in);
        String input;
        System.out.println("Search Skip list: ");

        boolean failed = true;
        double value = -1;
        while (failed) {
            input = scan.nextLine();
            try {
                value = list.search(Double.parseDouble(input));
                failed = false;
            } catch (ValueNotFoundException E) {
                System.out.println("Not Found, Try new search: ");
                failed = true;
            }
        }
        System.out.println("");
        System.out.println(value);
        System.out.println(list.traversal);
	}
}

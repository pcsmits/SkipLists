import java.util.Random;

public class Builder
{
	public static void main(String[] args) {
        SkipList list = new SkipList();
        Random rnd = new Random();

        for (int i = 0; i < 256; i++){
            Double val = rnd.nextDouble();
            double rand = (double) (int) (val * 10000);
            //System.out.println(rand);
            list.insert(rand);
        }
        list.printList();
        System.out.println(list.getLevels() + " levels");

	}
}

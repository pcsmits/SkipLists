import java.util.Random;
import java.lang.Math;

public class Builder
{
	public static void main(String[] args) {
        SkipList list = new SkipList();
        for (int i = 0; i < 50; i++){
            double rand = (double) (int) Math.random() * 10000;
            list.insert(rand);
        }

	}

	private String runStatistics() {

        return "Fail";
	}
}

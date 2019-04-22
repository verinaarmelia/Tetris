package source;

import java.util.Random;
import java.util.Scanner;

public class Helper {

	public static Scanner scan = new Scanner(System.in);
	public static Random rand = new Random();

	public static int randomNum(int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}

}

import java.util.Scanner;
import java.util.Arrays;

class SortAlphabeticalOrder {
	public String sort(String str) {
		char a[] = str.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}
}

public class String6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String myStr = sc.nextLine();
		System.out.println(new SortAlphabeticalOrder().sort(myStr));
	}
}
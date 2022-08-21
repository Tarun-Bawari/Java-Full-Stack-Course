import java.util.Scanner;

class RepeatedCharacter {
	public String findMaxChar(String str) {
        char[] array = str.toCharArray();
        int maxCount = 1;
        char maxChar = array[0];
        for(int i = 0, j = 0; i < str.length() - 1; i = j){
            int count = 1;
            while (++j < str.length() && array[i] == array[j]) {
                count++;
            }
            if (count > maxCount) {
                maxCount = count;
                maxChar = array[i];
            }
        }
        return (maxChar + " = " + maxCount);
    }
}

public class String5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String myStr = sc.nextLine();
		System.out.println(new RepeatedCharacter().findMaxChar(myStr));
	}
}

import java.util.Scanner;

class ReverseWord {
	public String reverse(String str) {
		String words[] = str.split("\\s");
		String reverseString = "";
		int len = words.length;
		for(int i=0; i<len; i++) {
			String word = words[i];
			String reverseWord = "";
			int wordLen = word.length();
			for(int j=wordLen-1; j>=0; j--) {
				reverseWord = reverseWord + word.charAt(j);
			}
			reverseString = reverseString + reverseWord + " ";
		}
		return reverseString;
	}
}

public class String2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String myStr = sc.nextLine();
		System.out.println(new ReverseWord().reverse(myStr));
	}
}
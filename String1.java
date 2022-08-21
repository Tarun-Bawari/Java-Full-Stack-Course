import java.util.Scanner;

class Reverse {
	public String reverseWord(String str) {
		int end = str.length()-1;
		String ans="";
		while(end>=0) {
			ans+=str.charAt(end);
			end--;
		}
		return ans;
	}
}

public class String1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String myStr = sc.nextLine();
		System.out.println(new Reverse().reverseWord(myStr));
	}
}
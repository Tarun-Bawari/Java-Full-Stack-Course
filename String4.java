import java.util.Scanner;

class Pangram {
	public boolean allLetter(String str) {
		int len = str.length();
		str = str.toLowerCase();
		boolean[] count = new boolean[26];
		for(int i=0; i<len; i++) {
			char ch = str.charAt(i);
			if(ch >= 'a' && ch <= 'z') {
				int value = ch-'a';
				count[value]=true;
			}
		}
		for(int i=0; i<26; i++) {
			if(!count[i])
			   return false;
		}
		return true;
	}
}

public class String4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String myStr = sc.nextLine();
		if(new Pangram().allLetter(myStr)) {
			System.out.println(myStr + " is Pangram");
		}else {
			System.out.println(myStr + " is not Pangram");
		}
	}
}
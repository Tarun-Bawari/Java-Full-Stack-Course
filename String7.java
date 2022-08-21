import java.util.Scanner;

class CountVowelConsonant {
	public void count(String str) {
		int vCount=0, cCount=0;
		str = str.toLowerCase();
		int len = str.length();
		for(int i=0; i<len; i++) {
			char ch = str.charAt(i);
			if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ) {
				vCount++;
			}else if(ch >= 'a' && ch <= 'z') {
				cCount++;
			}
		}
		System.out.println("No. of Vowels: "+ vCount);
		System.out.println("No. of Consonants: "+ cCount);
	}
}

public class String7 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String myStr = sc.nextLine();
		new CountVowelConsonant().count(myStr);
	}
}
import java.util.Scanner;

class CountSpecialCharacter {
	public void count(String str) {
		int splCount=0;
		int len = str.length();
		for(int i=0; i<len; i++) {
			char ch = str.charAt(i);
			if(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9') {
				continue;
			}else {
				splCount++;
			}
		}
		System.out.println("No. of Special Characters in String is: "+ splCount);
	}
}

public class String8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String myStr = sc.nextLine();
		new CountSpecialCharacter().count(myStr);
	}
}
import java.util.Scanner;
import java.util.Arrays;

class Anagram {
	public boolean areAnagram(String str1, String str2) {
		String s1 = str1.replaceAll("\\s","");
		String s2 = str2.replaceAll("\\s","");
		int len1 = s1.length();
		int len2 = s2.length();
		if(len1 != len2) return false;
		char[] a1 = s1.toLowerCase().toCharArray();
		char[] a2 = s1.toLowerCase().toCharArray();
		Arrays.sort(a1);
		Arrays.sort(a2);
		return Arrays.equals(a1,a2);
	}
}

public class String3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		if(new Anagram().areAnagram(str1,str2)) {
			System.out.println(str1 + " and " + str2 + " are Anagrams");
		}else {
			System.out.println(str1 + " and " + str2 + " are not Anagrams");
		}
	}
}
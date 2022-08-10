public class Pattern3 {
	public static void main(String[] args) {
		int n=14;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==n-1 || j==0 || j==n-1 || j<n/2-i || j>=n/2+i-1) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
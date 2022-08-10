public class Pattern4 {
	public static void main(String[] args) {
		int n=8;
		for(int i=0; i<n; i++) {
			int m=(n*2)-2;
			for(int j=0; j<m; j++) {
				if(i==n-1 || i==n-2 || j<=i || j>=m-1-i) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
package test;

public class time {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a =0;
		while(true) {
			
			try {
				a++;
				System.out.println(a);
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}

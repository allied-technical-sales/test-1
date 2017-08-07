package tail;

import java.lang.String;
import java.util.List;



public class Main {
	
	private static void print(List<String> l) {
		for (String s : l) {
			System.out.println(s);
		}
	}
	

	public static void main(String[] args) {
		
		TailClass t = new TailClass(args);
		print(t.getData());

		
	}

}



package tail;

import java.lang.String;
import java.util.List;



public class Main {
	

	public static void main(String[] args) {
		
		TailClass t = new TailClass(args);
		List<String> l = t.getData();
		for (String s : l) {
			System.out.println(s);
		}
		
	}

}



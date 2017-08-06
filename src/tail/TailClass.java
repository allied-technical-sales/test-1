package tail;
import java.nio.file.Path;

public class TailClass {
	private Parser p;
	private PathHandler ph;
	private ReadData rd;
	
	public TailClass(String [] args) {
		p = new Parser(args);
		ph = new PathHandler(p.getFileName());
		Path file = ph.getPath();
		if(file == null) {
			System.out.println("File Name is not Valid, Please pass a valid file");
			return;
		}
		
		rd = new ReadData(ph.getPath(), 10, 0);
		
	}
	
	
	
	public void print() {
		ph.print();
	}
}

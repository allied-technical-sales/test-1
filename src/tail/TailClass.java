package tail;
import java.nio.file.Path;
import java.util.List;

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
			throw new IllegalArgumentException();
		}
		int count = p.getCount();
		rd = new ReadData(ph.getPath(), count);
		if(rd == null) {
			System.out.println("ReadData failed to grab handle, please check input");
			throw new NullPointerException();
		}
	}
	
	public List<String> getData(){
		return rd.getData();
	}
}

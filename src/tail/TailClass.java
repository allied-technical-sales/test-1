package tail;
import java.nio.file.Path;
import java.util.List;
/*  
 *  This class is the main class for tail utility
 *  this one has Parser class to parse command line arguments, PathHandler to check if
 *  file path is valid and file can be read, and actual ReadData class that reads the 
 *  data from the file.  
 */
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

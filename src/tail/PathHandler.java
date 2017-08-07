package tail;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PathHandler {
	private String fileName;
	
	public PathHandler(String fileName) {
		if (fileName == null) {
			return;
		}
		this.fileName = new String(fileName);
	}
	
	public void print() {
		System.out.println(this.fileName);
	}
	
	public Path getPath() {

		try {
			Path p = Paths.get(this.fileName);
		
			boolean fileExists = Files.exists(p);
			if (fileExists == false){
				System.out.println("The provided file doesn't exist or its existance can't be confirmed");
				return null;
			}
		
			boolean isRegularReadbleFile = Files.isRegularFile(p) & Files.isReadable(p);
			if (isRegularReadbleFile == false) {
				System.out.println("The provided file is can't be read");
				return null;
			}	
			
			return p;
			
		} catch(Exception e) {
			System.out.println("Error in getPath function of PathHandler Class");
			System.out.println(e);
			return null;
		}
	}
}

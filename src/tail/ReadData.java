package tail;


/*  
 *  This class handles the actual file operation of reading data from the file. 
 *  we use circular buffer here to save only the relevant data
 *  and all the data collected is returned as List<String> currently
 *  when we add other functionality we will modify this class
 */

/* use when size has to be considered */
//import java.nio.channels.SeekableByteChannel;
//import java.util.EnumSet;
//import static java.nio.file.StandardOpenOption.READ;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.io.IOException;

public class ReadData {
	
	/* use this when size is provided rather than number of lines 
	 * private SeekableByteChannel sbc;
	 * private long s;
	 */
	
	private int c;
	private CircularBuffer cb;
	
	public ReadData(Path p, int count) {
		
		/* use this when size is provided rather than number of lines
		 * to randomly access file and read only the portion
		 * that is relevant rather than loading the entire file	
		 *	sbc = Files.newByteChannel(p,EnumSet.of(READ));
		 *	this.s = size;
		 *	long total_length;
		 *	total_length = sbc.size();
		 */
		if (count <= 0) {
			System.out.println("Do you really want to read zero or negative number of lines?? ");
			System.out.println("Sorry but we don't support it");
			throw new IllegalArgumentException();
		}
		
		this.c = count;
		
		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(p)) {
			
			this.cb = new CircularBuffer(this.c);
			stream.forEach(line ->cb.add(line));
			
		} catch (IOException e) {	
			e.printStackTrace();
			
		}		
	}
	
	public List<String> getData() {
		return this.cb.data();
	}

}

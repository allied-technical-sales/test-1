package tail;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;


import static java.nio.file.StandardOpenOption.READ;

import java.io.IOException;

public class ReadData {
	private SeekableByteChannel sbc;
	private long s;
	private int c;
	
	public ReadData(Path p, int count, long size) {
		long total_length;
		
		try {
			sbc = Files.newByteChannel(p,EnumSet.of(READ));
			total_length = sbc.size();
			System.out.println(total_length);
			this.s = size;
			this.c = count;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
			
	}

}

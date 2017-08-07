package tail;

/*  
 *  This class creates a circular buffer and keeps only the latest data. 
 */

import java.util.ArrayList;
import java.util.List;

public class CircularBuffer {
	
        private final int size;
        private final String[] data;
        private int counter;

        public CircularBuffer(int size) {
        	if (size <=0 ) {
    			throw new IllegalArgumentException();
        	}
            this.size = size;
            this.data = new String[size];
            this.counter = 0;
        }

        public void add(String line) {
            data[this.counter % size] = line;
            this.counter = (++this.counter) % size;
        }

        public List<String> data() {

        	List<String> l = new ArrayList<String>();
        	for(int i = this.counter; i < this.size ; i++ ) {
        		if(data[i] !=null) {
        			l.add(data[i]);
        		}
        	}
        	if(this.counter > 0) {
            	for(int i = 0; i < this.counter ; i++ ) {
            		l.add(data[i]);
            	}
        	}      	
        	return l;
    

    }

}

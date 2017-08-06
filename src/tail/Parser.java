package tail;

import java.util.Arrays;
import java.util.List;

import joptsimple.OptionParser;

import joptsimple.OptionSet;
import java.lang.String;

public class Parser {
	
	/* option to provide to the customer */
	private final String[] lineOptions = {"n", "lines"};
	private final String[] helpOptions = {"h", "help"};
	private final String[] verboseOptions = {"v", "verbose"};
	
	private final OptionParser p;
	private final OptionSet o;
	
	public Parser(String [] args) {
		
		this.p = new OptionParser();
		
		
		/* lines */
		p.acceptsAll(Arrays.asList(lineOptions), "Number of Lines to print").withRequiredArg();
		
		/* help */
		p.acceptsAll(Arrays.asList(helpOptions), "Help for the command").forHelp();
		
		/* verbose */
		p.acceptsAll(Arrays.asList(verboseOptions), "Verbose Logging for the Command");
		
		/* file */
		p.nonOptions( "File Name to be processed" ).ofType(String.class);
		
		this.o = p.parse(args);
		
	}
	
	public String getFileName() {
		if ( this.o.nonOptionArguments().size() == 1  ) {
			@SuppressWarnings("unchecked")
			List<String> l = (List<String>)this.o.nonOptionArguments();
			return l.get(0);
		}
		return null;
	}
	
	public int getCount() {
		if ( this.o.nonOptionArguments().size() == 1  ) {
			return 10;
		} else {
			return 0;
		}
	}
			

}

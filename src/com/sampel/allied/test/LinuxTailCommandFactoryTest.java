package com.sampel.allied.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sample.allied.LinuxTailCommandFactory;

public class LinuxTailCommandFactoryTest {
	
	@Test
	public void testfunctionnwithflagflase() {
		String fileName ="//Sample.txt";
		int numberOfLines = 4;
		boolean flag = false;
		new LinuxTailCommandFactory();
		StringBuilder output= LinuxTailCommandFactory.functionn(fileName, numberOfLines, flag); 
		String s=output.toString();
		String s1="11. Kevin12. Shivam13. Isha14. Bansari";
		assertEquals(s1, s);
	}
	@Test
	public void testfunctionnwithflagtrue() {
		String fileName ="//Sample.txt";
		int numberOfLines = 4;
		boolean flag = true;
		new LinuxTailCommandFactory();
		StringBuilder output=LinuxTailCommandFactory.functionn(fileName, numberOfLines, flag); 
		String s=output.toString();
		String s1="4. Dipak5. Rita6. Kavita7. Milan8. Megha9. Pravinbhai10. Bhavnaben11. Kevin12. Shivam13. Isha14. Bansari";
		assertEquals(s1, s);
	}
}

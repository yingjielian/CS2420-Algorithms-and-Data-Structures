package assignment05;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class TagTreeTest {

	@Test
	public void test() {
		
		File f =new File("test3.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		TagTree text = new TagTree(sc);
		
		System.out.println("get height: "+text.getHeight());
		System.out.println("get MaxDegree: "+text.getMaxDegree());
		System.out.println("outputPrefix:"+text.outputPrefix());
		System.out.println("outputPostfix:"+text.outputPostfix());
		System.out.println("isBinaryTree: "+text.isBinaryTree());
		System.out.println("isTwoTree: "+text.isTwoTree());
		System.out.println("isFullBinaryTree: "+text.isFullBinaryTree());
		System.out.println("findDepth: "+text.findDepth("M"));



		
		
	}

}

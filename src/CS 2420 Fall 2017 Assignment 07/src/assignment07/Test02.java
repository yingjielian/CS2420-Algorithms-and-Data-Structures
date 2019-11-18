package assignment07;

public class Test02 {
	
	public static void main(String[] args)
	{
		SearchTree<String> tree = new SearchTree<String>((String.CASE_INSENSITIVE_ORDER), true);
//		tree.add("b");
//		tree.add("a");
//		tree.add("c");
//		tree.add("d");
//		tree.add("e");
//		tree.add("f");
//		tree.add("g");
		
		//SearchTree a = new SearchTree(null, false);
		
//		single left 
		
		String b = "b";
		String b2 = "b";
//	tree.add(b);
//	tree.add("z");
//	tree.add("c");
//	tree.add("d");
//	tree.add("e");
//	tree.add("h");
//	tree.add("u");
//	tree.add(b);
//	tree.add("s");
//	tree.add("w");
//	tree.add("b");
		tree.add(b);
		tree.add("w");
		tree.add(b2);
		tree.add(b);
	tree.removeReference(b);
	tree.removeReference(b);
	if( tree.removeReference(b) != null)
		System.out.println("look what you've done!");


		
		//single right
//		tree.add("c");
//		tree.add("b");
//		tree.add("a");
		
		//double left

//		tree.add("a");
//		tree.add("c");
//		tree.add("b");
		
		//double right
		
//		tree.add("c");
//		tree.add("a");
//		tree.add("b");
		
		
		
		
		
//		tree.add("a");
//		tree.add("b");
//		tree.add("c");	
//		tree.add("d");
//		tree.add("e");
		
		
		//tree.rotateLeftThenRight();
		//tree.rotateRightThenLeft();
		
		
		
		//tree.add("c");

		//tree.removeValue("d");
		//tree.rotationLeft();
		//tree.rotationLeft();

		//tree.updateBalances();
		DotFileGenerator.writeDot("tree4", tree);


		//System.out.println(tree.removeReference(targetReference));
	}

}

package patterns.prototype;

public class MainPrototype {
	public static void main(String[] args) throws CloneNotSupportedException {
		SomeClass someClass1 = new SomeClass(1,2,"asd");
		SomeClass someClass2 = someClass1;
		SomeClass someClass3 = someClass1.clone();
		
		System.out.println(someClass1);
		System.out.println(someClass2);
		System.out.println(someClass3);
		
		someClass1.setWord("qwe");
		
		System.out.println(someClass1);
		System.out.println(someClass2);
		System.out.println(someClass3);
		
		System.out.println("someClass1 = someClass2 -> "+someClass1.equals(someClass2));
		System.out.println("someClass2 = someClass3 -> "+someClass2.equals(someClass3));
		System.out.println("someClass3 = someClass1 -> "+someClass3.equals(someClass1));
		
	}
}

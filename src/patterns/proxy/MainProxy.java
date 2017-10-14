package patterns.proxy;

public class MainProxy {

	public static void main(String[] args) {
		Image image = new ProxyImage("test_10mb.jpg");
		System.out.println("");
		// image will be loaded from disk
		image.display();
		System.out.println("");

		// image will not be loaded from disk
		image.display();
	}

}

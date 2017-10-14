package patterns.prototype;

public class SomeClass implements Cloneable{
	
	private int x;
	private int y;
	private String word;
	
	@Override
	protected SomeClass clone() throws CloneNotSupportedException {
		
		return new SomeClass(this);
	}
	
	public SomeClass(SomeClass target) {
		this.x = target.getX();
		this.y = target.getY();
		this.word = target.getWord();
	}
	
	public SomeClass(int x, int y, String word) {
		super();
		this.x = x;
		this.y = y;
		this.word = word;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	
	@Override
	public String toString() {
		return "SomeClass [x=" + x + ", y=" + y + ", word=" + word + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SomeClass other = (SomeClass) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}

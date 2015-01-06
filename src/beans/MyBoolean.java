package beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyBoolean {

	private boolean b;

	public MyBoolean() {

	}

	public MyBoolean(boolean b) {
		this.setB(b);

	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

}

import processing.core.PApplet;
import processing.core.PImage;

public class Button {
	private int length;
	private int height;
	private int posX;
	private int posY;
	private String imagePath;
	private PApplet app;
	private PImage buttonImage;
	
	
	public Button(int x, int y, int l, int h, String imagePath, PApplet a) {
		posX = x;
		posY = y;
		length = l;
		height = h;
		app = a;
		this.imagePath = imagePath;
		buttonImage = app.loadImage(this.imagePath);
	}
	
	public void draw() {
		app.image(buttonImage, posX, posY, length, height);
	}
	
	public boolean isPressed(int x, int y, int width, int height) {
		return ( x >= posX - width / 2 && x <= posX + width / 2 && y >= posY - height / 2 && y <= posY + height / 2);
	}
}

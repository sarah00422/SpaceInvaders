
import processing.core.PApplet;
import processing.core.PImage;

public class Ship {
	protected int size;
	protected int posX;
	protected int posY;
	protected int lives;
	private PApplet app;
	private String imagePath;
	private PImage ship;
	
	public Ship(int x, int y, int liv, int sze, String imagePath, PApplet app) { 
		this.app=app;
		this.imagePath = imagePath;
		ship = app.loadImage(imagePath);
		posX = x;
		posY = y;
		size = sze;
		lives = liv;
	}
	
	private void loseLive() {
		this.lives--;
	}
	
	public boolean getHit(Bullet bullet) { 
		int bX = bullet.getPosX();
		int bY = bullet.getPosY();
		boolean gotHit = false;
		boolean isInRangeX= bX >= (posX-(size/2)) && bX <= (posX + (size/2));
		boolean isInRangeY= bY >= (posY -(size/2)) && bY <= (posY + (size/2));
		if( isInRangeX && isInRangeY ) {
			this.loseLive();
			gotHit = true;
		}
		return gotHit;
	}
	
	public void drawShip() {
		app.image(ship, posX, posY, 50, 50);
	}
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}	
	
}

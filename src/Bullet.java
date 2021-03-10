import processing.core.PApplet;

public class Bullet {
	int posX;
	int posY;
	int speedY;
	int damage;
	int size;
	Color color; 
	public Bullet(int x, int y, int speed, int dmge, Color color, int sz) {
		posX = x;
		posY = y;
		speedY = speed;
		damage = dmge;
		size = sz;
		this.color= color; 
	}
	
	public void draw(PApplet app) {
		app.fill(color.getR(), color.getG(), color.getB());
		app.circle(posX, posY, size);
	}
	
	public void move(String direction) {
		if(direction == "up") {
			posY -= speedY;
		}
		if(direction == "down") {
			posY += speedY;
		}
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
}

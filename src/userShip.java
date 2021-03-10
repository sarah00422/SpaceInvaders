import processing.core.PApplet;
import processing.core.PImage;

public class userShip extends Ship {
	int speedX = 10;
	private int coldDown;
	private int attackSpeed;
	private int damage;
	private int bulletSize;
	private Color color;

	
	public userShip(int x, int y, int liv, int sz, int attckspeed, int dmg, int bulletsz, String imagePath,  PApplet app) {
		super(x, y, liv, sz, imagePath, app);
		attackSpeed = attckspeed;
		damage = dmg;
		bulletSize = bulletsz;
		color = new Color(0,250,0,0);
	}
	
	public void move(String direction) { 
		if(posX < 600 && direction == "right") {
			posX += speedX;
		}
		if(posX > 0 && direction == "left") {
			posX -= speedX;
		}
	}
	
	public Bullet attack() {
		return new Bullet(posX, posY, attackSpeed, damage, color, bulletSize );
	}
}

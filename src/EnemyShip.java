import processing.core.PApplet;

public class EnemyShip extends Ship {
	int speedX = 6;
	int damage = 1; 
    int attackSpeed = 8;
    int bulletSize= 20; 
    Color color = new Color(250,0,0,0);
	public EnemyShip(int x, int y, int liv, int sz, String imagePath, PApplet app) {
		super(x, y, liv, sz, imagePath, app);
	}
	
	public void move() {
		posX+= speedX;
		if(posX >= 550 || posX <= 50){
			posY += 80;
			speedX *= -1;		
		}
	}
	
	public Bullet attack() {
			return new Bullet(posX, posY, attackSpeed, damage, color, bulletSize);	
	}
}

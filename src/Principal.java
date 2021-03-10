import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;


public class Principal extends PApplet{

	public static void main(String[] args) {
		 PApplet.main("Principal");
		}
	PImage pantalla1, pantalla2, pantalla3, pantalla4;
	Random rand = new Random(); 
	int timer;
	int screen;
	int points;
	int width;
	int height;
	int enemyNum;
	int enemyLives;
	int shipSizes;
	int userLives;
	int userDamage;
	int userBulletSize;
	int userAttackSpeed;
	int userCoolDown;
	int enemyCoolDown;
	int userFrameCount;
	int enemyFrameCount;
	boolean enemyAllowToShot;
	boolean userAllowToShot;
	Button startButton, instructionsButton;
	private ArrayList<EnemyShip>enemies;
	private userShip user; 
	private ArrayList<Bullet> userBullets;
	private ArrayList<Bullet> enemiesBullets;
	int enemyDamage;
	int enemyAttackSpeed;
	int enemyBulletSz; 
	
	@Override
	public void settings() {
	   size(600,750);
	}
	
	@Override
	public void setup() {
		
		
		pantalla1 = loadImage("../Images/1Screen.png");
		pantalla2 = loadImage("../Images/background.png");
		pantalla3 = loadImage("../Images/Instructions.png");
		pantalla4 = loadImage("../Images/Score.png");
		
		enemyDamage= 1;
		enemyAttackSpeed= 2;
		enemyBulletSz= 20;
		screen = 1;
		width = 80;
		height = 50;
		enemyNum = 5;
		enemyLives = 2;
		userLives = 3;
		userAttackSpeed = 8;
		userDamage = 1;
		userBulletSize = 20;
		userCoolDown = 10;
		enemyCoolDown = 100;
		shipSizes = 50;
		userFrameCount = 0;
		enemyFrameCount = 0;
		userAllowToShot = true;
		enemyAllowToShot = true;
		points = 0;
		enemies = new ArrayList<>(); 
		userBullets = new ArrayList<>();
		enemiesBullets = new ArrayList<>();
		startButton = new Button(300, 600, 200, 120, "../Images/getStarted.png", this);
		instructionsButton = new Button(300, 500, 200, 160, "../Images/Button.png", this);
		user = new userShip(300, 600, userLives, shipSizes, userAttackSpeed, userDamage, userBulletSize, "../Images/userShip.png", this);
		for(int i = 0 ; i <= enemyNum ; i++) {
			enemies.add(new EnemyShip((i * 60) + 50, 100, enemyLives, shipSizes, "../Images/Enemy.png", this));
		}
	}
	
	@Override
	public void draw() {
		
		imageMode(CENTER);
		background(250);
		rectMode(CENTER);
		if (screen == 1) {
			image(pantalla1, 300, 375,600, 750);
			startButton.draw();
			instructionsButton.draw();
		}
		if (screen == 2) {
			image(pantalla2, 300, 375, 600, 750);
			textSize(20);
			fill(255);
			text("Puntaje: "+ points, 310, 30);
			text("Vidas: "+ user.getLives(),40,30);
			
			user.drawShip();
			
			//handle user bullets
			for(int i = 0 ; i < userBullets.size() ; i++) {
				userBullets.get(i).draw(this);
				userBullets.get(i).move("up");
				if(userBullets.get(i).getPosY() < 0) {
					userBullets.remove(i);
				}
			}
			
			
			//handle enemy bullets
			for(int i = 0 ; i < enemiesBullets.size() ; i++) {
				enemiesBullets.get(i).draw(this);
				enemiesBullets.get(i).move("down");
				if(enemiesBullets.get(i).getPosY() > 750 || user.getHit(enemiesBullets.get(i))) {
					enemiesBullets.remove(i);
				}
			}
			
			
			//handle enemies behavior
			for(int i = 0 ; i < enemies.size() ; i++) {
				enemies.get(i).drawShip();
				enemies.get(i).move();
				for (int j = 0; j < userBullets.size(); j++) {
					if(enemies.get(i).getHit(userBullets.get(j))) {
						userBullets.remove(j);
						points +=10;
					}
				}
				if(enemies.get(i).getLives() == 0) {
					enemies.remove(i);
					points += 50;
				}
			}
			
			
			if(enemies.size() < enemyNum) {
				enemies.add(new EnemyShip(50, 100, enemyLives, shipSizes, "../Images/Enemy.png", this));
			}
			
			//handle user Behavior
			if(user.getLives() == 0) {
				screen = 4;
			}
			
			
			if(enemyAllowToShot) {
				for(int i = 0 ; i < enemies.size() ; i++) {
					if(rand.nextInt(100) > 50) { 
						enemiesBullets.add(enemies.get(i).attack());						
					}
				}
				enemyAllowToShot = false;
			}
	
			
			//handle bullets frames
			if(!userAllowToShot && userFrameCount > userCoolDown) {
				userFrameCount = 0;
				userAllowToShot = true;
			}
			if(!enemyAllowToShot && enemyFrameCount > enemyCoolDown) {
				enemyFrameCount = 0;
				enemyAllowToShot = true;
			}
			userFrameCount++;
			enemyFrameCount++;
			timer++;
			
			//handle increase difficulty
			if(timer > 100) {
				enemyNum++;
				enemyLives++;
				enemyCoolDown -=10;
				timer = 0;	
			}
		}
			
		if(screen == 3) { 
			image(pantalla3, 300, 375);	
			startButton.draw();
		}
		if(screen == 4) {
			image(pantalla4, 300, 375, 600, 750);
			textSize(20);
			fill(255);
			text("Puntaje: "+ points, 350,400 );
			text("Vidas: "+ user.getLives(),200,400);
			startButton.draw();	
		}
	}
	
	public void mousePressed() {
		if(startButton.isPressed(mouseX, mouseY, width, height)) {
			setValues();
			screen = 2;
		}
		if (instructionsButton.isPressed(mouseX, mouseY, width, height)) {
			screen=3;
		}
	}
	
	private void setValues() {
		user.setLives(3);
		enemyNum = 5;
		enemyLives = 2;
		userLives = 3;
		enemyCoolDown= 100;
		points = 0;
		enemies.clear();
		enemiesBullets.clear();
		userBullets.clear();
		for(int i = 0 ; i <= enemyNum ; i++) {
			enemies.add(new EnemyShip((i * 60) + 50, 100, enemyLives, shipSizes, "../Images/Enemy.png", this));
		}
	}
	
	public void keyPressed() {
		switch (key) {
		case 'a':
			user.move("left");
			break;
		case 'd':
			user.move("right");
			break;
		case ' ':
			if(userAllowToShot)
			userBullets.add(user.attack());
			userAllowToShot = false;
			break;
		}
	}
}

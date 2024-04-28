package Breakout;
import java.util.Date;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import utilities.GDV5;

public class BreakoutBall extends Rectangle{
	
	public static int lives = 3;
	
	public int ballVelX = 2;
	public int ballVelY = 7;
	
	public int winX = GDV5.getMaxWindowX();
	public int winY =GDV5.getMaxWindowY();
	
	public int cutOff = 5;
	public int radius = (int)(getWidth()/2);
	
	public double ballMaxX = getCenterX()+radius;
	public double ballMinX = getCenterX() -radius;
	
	public double ballMaxY = getCenterY()+radius;
	public double ballMinY = getCenterY()-radius;
	
	private static int startX = GDV5.getMaxWindowX()/2;
	private static int startY = 2*GDV5.getMaxWindowY()/3;
	
	public int count = 0;
	
	public int scoreA = 0;
	public int scoreB = 0;
	
	public int scoreX = GDV5.getMaxWindowX()/2-20,scoreY =2*(GDV5.getMaxWindowY()/3)-40;
	
	private int brickResult;
	
	//public String score  = String.valueOf(scoreA)+"-" +String.valueOf(scoreB);
	
	
	public BreakoutBall(int startX, int startY, int size) {
		super(startX,startY,size,size);
	}
	
	public void moveBall() {
		if(count==120) {
			this.translate(ballVelX, ballVelY);
		}
		else {
			this.translate(0,0);
			count++;
		}
		ballWallBounce();
	
	}
	public void ballWallBounce() {
		if(this.getX()<=0) this.ballVelX = - ballVelX;
		if(this.getX()+this.getWidth() >=GDV5.getMaxWindowX()) this.ballVelX = -ballVelX;
		if(this.getY()<=0) this.ballVelY = -ballVelY;
		if(this.getY() +this.getHeight() >=GDV5.getMaxWindowY()) {
			count = 0;
			this.setLocation(startX, startY);
			lives-=1;
		}
	}
	public void ballBounce() {
		ballVelX = -ballVelX;
	}
	public void ballReset() {
		count = 0;
		this.setLocation(startX, startY);
	}
	public void ballBounceY() {
		ballVelY = - ballVelY;
	}
	public static int getStartX() {
		return startX;
	}
	public static int getStartY() {
		return startY;
	}
	public void brickHit() {
		for(int i =0; i<BreakoutRunner.bricks.length; i++) {
			if(this.intersects(BreakoutRunner.bricks[i]) && BreakoutRunner.bricks[i].visible == 0) {
				BreakoutRunner.bricks[i].visible = 1;
				brickResult = GDV5.collisionDirection(BreakoutRunner.bricks[i], this, this.ballVelX, this.ballVelY);
				brickBounce(brickResult);
				BreakoutRunner.bricks[i].drawPart = true;
				scoreA+=100;
			}
		}
	}
	
	public void paddleHit(BreakoutPaddle paddle) {
		if(this.intersects(paddle)){
			ballBounceY();
		}
	}
	
	public static void checkLives() {
		if(BreakoutRunner.display==3 && lives ==0) {
			BreakoutRunner.display = 7;
		}
	}
	
	public void brickBounce(int brickResult) {
		if(brickResult%2 ==1) this.ballVelY= -ballVelY;
		else this.ballVelX = -ballVelX;
	}
	public void drawBall(Graphics2D win) {
		win.drawRect((int)(getX()), (int)(getY()), (int)(getWidth()), (int)(getHeight()));
		win.setColor(Color.MAGENTA);
		win.fillRect((int)(getX()), (int)(getY()), (int)(getWidth()), (int)(getHeight()));
		win.setColor(Color.white);
	}

	
}
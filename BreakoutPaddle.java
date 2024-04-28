package Breakout;
import java.util.Date;

import Pong.PongRunner;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import utilities.GDV5;

import java.awt.Color;
import java.awt.Graphics2D;

public class BreakoutPaddle extends Rectangle {
	int xCoordA = 0;
	int xCoordB = 0;
	int cutOff = 5;
	//double yMax = this.getY() + this.getHeight();
	//double yMin = this.getY();
	int moveAmount = 10;
	boolean smashBallup = false; 
	public BreakoutPaddle(int xCoord) {
		super(xCoord,(int)(GDV5.getMaxWindowY()-75), 200,15);
		
	}	
	
	public double yMax() {
		return this.getY() + this.getHeight();
	}
	public double yMin() {
		return this.getY();
	}
	public double xMaxA() {
		return this.getCenterX()+(this.getWidth()/2);
	}
	public double xMinA() {
		return this.getCenterX()-this.getWidth()/2;
	}
	public double xMaxB() {
		return this.getCenterX()-(this.getWidth()/2);
	}
	
	public void keyCheckA() {
		if ((GDV5.KeysPressed[KeyEvent.VK_LEFT]) && xMinA()>=10) {
			this.translate(-moveAmount,0);}
		if ((GDV5.KeysPressed[KeyEvent.VK_RIGHT]) && xMaxA()<=GDV5.getMaxWindowX()-10)  {
			this.translate(moveAmount,0);}
	}
	
	public void zeroPlayer(BreakoutBall ball) {
		this.setLocation((int)ball.getCenterX()-100, (int)GDV5.getMaxWindowY()-75);	
		
	}
		
	
	public void drawPaddle(Graphics2D win) {
		win.setColor(Color.white);
		win.drawRect((int)(getX()),(int)(getY()), (int)(getWidth()),(int)(getHeight()));
		win.fillRect((int)(getX()),(int)(getY()), (int)(getWidth()),(int)(getHeight()));
	}
	
	
	/*public void paddleBounce(BreakoutBall ball) {
		if(((int)ball.getCenterX() <=(int)this.xMaxA() &&(int)ball.getCenterX()>=(int)this.xMinA()) && ((int)(ball.ballMaxY)-this.yMax()<=this.cutOff)){
			
			ball.ballBounceY();
		}
	}*/
}

package Breakout;
import java.util.Date;

import Pong.Ball;
import Pong.Paddle;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BreakoutRunner extends GDV5{
	
	public static Brick[] bricks;
	int count = 0;
	static int display = 1;// 1 is front screen, 2 is instructions, 3 is singleplayergame, 4 is win, 5 is single pause, 6 is zeroplayer, 7 is single win/lose,8 is zero pause
	
	Font scoreFont = new Font("GEORGIA", Font.BOLD,50);
	Font scoreFontSmall = new Font("GEORGIA", Font.BOLD,20);
	Font scoreFontFront = new Font("GEORGIA", Font.BOLD,80);
	Font scoreFontInfo = new Font("GEORGIA", Font.BOLD,35);
	
	public BreakoutRunner() {
		super();
		bricks = Brick.makeBricks(Brick.getRows(), Brick.getNumberPerRow());
	}
	BreakoutPaddle paddleA = new BreakoutPaddle(GDV5.getMaxWindowX()/2 - 100);
	BreakoutBall ball = new BreakoutBall(BreakoutBall.getStartX(),BreakoutBall.getStartY(),25);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BreakoutRunner b = new BreakoutRunner();
		b.start();

	}
	public void update() {
		gameState();
		if(display == 3) {
			paddleA.keyCheckA();
			ball.moveBall();
			ball.brickHit();
			ball.paddleHit(paddleA);
			for(Brick b: bricks) {
				b.brickUpdate();}
			Brick.brickWinCheck();
			BreakoutBall.checkLives();}
		if(display == 6) {
			ball.moveBall();
			ball.brickHit();
			ball.paddleHit(paddleA);
			for(Brick b: bricks) {
				b.brickUpdate();}
			paddleA.zeroPlayer(ball);
		}
	}
	public static Brick[] getBricks() {
		return bricks;
	}
	
	public void gameState(){
		if ((GDV5.KeysPressed[KeyEvent.VK_I]) && display == 1){
			display = 2;}
		if((GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) && display == 2) {
			display = 1;
		}
		if((GDV5.KeysPressed[KeyEvent.VK_M]) && display == 1) {
			display = 3;
		}
		if((GDV5.KeysPressed[KeyEvent.VK_P]) && display == 3) {
			display = 5;
		}
		if((GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) && display == 5) {
			display = 3;

		}
		if((ball.scoreA == 5|| ball.scoreB ==5) && display == 3) {
			display = 4;
		}
		if((GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) && display == 4) {
			ball.scoreA = 0;
			ball.scoreB=0;
			display = 1;
			ball.ballReset();
		}
		
		if((GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) && display == 7) {
			display = 1;
			ball.scoreA = 0;
			ball.lives = 3;
			for (Brick b: BreakoutRunner.bricks) {
				b.visible =0;
			}
		}
		if((GDV5.KeysPressed[KeyEvent.VK_S]) && display == 1) {
			display = 6;
		}
		if((GDV5.KeysPressed[KeyEvent.VK_B]) && display == 5) {
			display = 1;
			ball.scoreA= 0;
			ball.scoreB = 0;
			ball.ballReset();
			//ball.setLocation(562,375);
			for (Brick b: BreakoutRunner.bricks) {
				b.visible =0;
			}
			
		}
		if((GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) && display == 6) {
			display = 1;
			ball.scoreA= 0;
			ball.scoreB = 0;
			ball.ballReset();
			//ball.setLocation(562,375);
			for (Brick b: BreakoutRunner.bricks) {
				b.visible =0;
			}
		}
		if((GDV5.KeysPressed[KeyEvent.VK_B]) && display == 8) {
			display = 1;
			ball.scoreA= 0;
			ball.scoreB = 0;
			ball.ballReset();
		}
			
		
	}
	public void draw(Graphics2D win) {
		if(display == 1) {
			win.setColor(Color.white);
			win.setFont(scoreFontFront);
			win.drawString("BREAKOUT",GDV5.getMaxWindowX()/2/2 -80,150);
			win.setFont(scoreFont);
			win.setColor(Color.red);
			win.drawString("By Erel Ozen P4",GDV5.getMaxWindowX()/2 -205,220);
			win.setColor(Color.PINK);
			win.setFont(scoreFontSmall);
			win.drawString("Press 'I' for Instructions",GDV5.getMaxWindowX()/2 -120,300);
			win.setColor(Color.GREEN);
			win.drawString("Press 'S' for Zero-Player",GDV5.getMaxWindowX()/2 -120,350);
			win.setColor(Color.WHITE);
			win.drawString("Press 'M' to play Singleplayer",GDV5.getMaxWindowX()/2 -145,400);
		}
		if(display == 2) {
			win.setColor(Color.white);
			win.setFont(scoreFont);
			win.drawString("Zero-Player",GDV5.getMaxWindowX()/2-160,100);
			win.drawString("Single-Player",GDV5.getMaxWindowX()/2-165,320);
			win.setColor(Color.CYAN);
			win.setFont(scoreFontSmall);
			win.drawString("Observe the bot play!",GDV5.getMaxWindowX()/2-100,175);
			win.drawString("Achieve the highest score",GDV5.getMaxWindowX()/2-125,440);
			win.drawString("Use the 'Left' and 'Right' keys to control!",GDV5.getMaxWindowX()/2-205,400);
			win.setFont(scoreFontInfo);
			win.setColor(Color.red);
			win.drawString("Press 'ESC' to return to menu",GDV5.getMaxWindowX()/2-260,600);
		}
		
		if(display == 3) {
			
			for (Brick b:bricks) {
				b.draw(win);
			}
			paddleA.drawPaddle(win);
			ball.drawBall(win);
			win.setColor(Color.white);
			win.setFont(scoreFont);
			win.drawString(String.valueOf(ball.scoreA), ball.scoreX, ball.scoreY);
			win.drawString(String.valueOf(ball.lives), ball.scoreX, ball.scoreY+100);
		}
		if(display == 5) {
		win.setColor(Color.white);
		win.setFont(scoreFont);
		win.drawString("PAUSE",GDV5.getMaxWindowX()/2 -80, GDV5.getMaxWindowY()/2/2 - 200);
		win.setFont(scoreFontInfo);
		win.setColor(Color.red);
		win.drawString("Press 'ESC' to return to play",GDV5.getMaxWindowX()/2-245,300);
		win.drawString("Press 'B' to return to menu",GDV5.getMaxWindowX()/2-245,420);
		}
		if(display == 6) {
			
			for (Brick b:bricks) {
				b.draw(win);
			}
			paddleA.drawPaddle(win);
			ball.drawBall(win);
			win.setColor(Color.white);
			win.setFont(scoreFont);
			win.drawString(String.valueOf(ball.scoreA), ball.scoreX, ball.scoreY);
		}
		if(display ==7) {
			win.setColor(Color.pink);
			win.setFont(scoreFont);
		//	win.drawString("You Win!",GDV5.getMaxWindowX()/2 -115, GDV5.getMaxWindowY()/2 -200);
			win.drawString("Your Score:"+ball.scoreA,GDV5.getMaxWindowX()/2 -165, GDV5.getMaxWindowY()/2 - 100);
			win.setFont(scoreFontInfo);
			win.setColor(Color.red);
			win.drawString("Press 'ESC' to return to menu",GDV5.getMaxWindowX()/2-245,400);
		}
	}	
}

package Breakout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import utilities.GDV5;
public class BreakoutParticles extends BreakoutBall{
	
	//static BreakoutParticles[][] masterParticles = new BreakoutParticles[45][];
	static BreakoutParticles[] particles;
	
	private static final Random random = new Random();

	private boolean isActive = false;
	
	private int partVelX,partVelY;
	private Color col;
	private static int partsPerRow = 12;
	private static int rowsPerArray = 8;
	private static int width = (GDV5.getMaxWindowX()/6-3)/partsPerRow;
	private static int height = 25/rowsPerArray;
	
	public static final Color VERY_LIGHT_BLUE = new Color(51,204,255);
	public static final Color LIGHT_BLUE = new Color(51,153,255);
	public static final Color BLUE = new Color(0,0,255);
	public static final Color DARK_BLUE = new Color(0,0,204);
	public static final Color VERY_DARK_BLUE = new Color(0,0,153);
	public static final Color VERY_LIGHT_GREEN = new Color(103,255,102);
	private static Color[] colors = new Color[5];
	
	public BreakoutParticles(int x, int y, int width, int height, Color c) {
		super(x, y, width);
		col = c;
		partVelX = getRandomNumber();
		partVelY =  getRandomNumber();
	}
	
	public static BreakoutParticles[] makeParticle(Brick b) {		
		particles = new BreakoutParticles[30];
		int x = (int)(b.getX()); 
		int y = (int)(b.getY());
		int currentRowNum=0;
		//int partsPerRow = 8;
		//int rowsPerArray = 5;
		//int width = (int) b.getWidth()/partsPerRow;
		for(int i = 0; i<particles.length; i++) {
			particles[i] = new BreakoutParticles(x, y, width, height, b.getColor());
			x+=(width); 
			if(currentRowNum==partsPerRow) {
				y+=height;
				currentRowNum = 0;
				x=(int)b.getX();
			}
		}
		return particles;
	}
	
	public void moveParticle() {
		this.translate(partVelX, partVelY);
	}
	
	/*public void moveParticles() {
		for(int i = 0; i<particles.length; i++) {
			if(count<240) {
				particles[i].translate(particles[i].partVelX, particles[i].partVelY);
				count++;
			}
			else {
				particles[i].translate(0,0);
				
			}
		}
	}*/
	
	public boolean getActive() {
		return isActive;
	}
	public void setActive(boolean bool) {
		isActive = bool;
	}
		
	public static int getRandomNumber() {
		// Generate a random integer from 1 to 8
		int n = random.nextInt(8)+1;
		if(Math.random()<0.5) {
			n=-n;
		}
		return n;
	}

	
	
	public void drawParticle(Graphics2D win) {
			
			win.setColor(col);
			win.drawRect((int)(getX()), (int)(getY()), (int)(getWidth()), (int)(getHeight()));
			win.fill(this);
		
	}

	
}

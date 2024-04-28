package Breakout;
import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;


public class Brick extends Rectangle{
	private Color col;
	
	private BreakoutParticles[] particles;
	
	int visible= 0; // 0 is drawn, 1 is undrawn & particles appear, 2 is undrawn and nothing else happens
	
	public static final Color VERY_LIGHT_BLUE = new Color(51,204,255);
	public static final Color LIGHT_BLUE = new Color(51,153,255);
	public static final Color BLUE = new Color(0,0,255);
	public static final Color DARK_BLUE = new Color(0,0,204);
	public static final Color VERY_DARK_BLUE = new Color(0,0,153);
	public static final Color VERY_LIGHT_GREEN = new Color(103,255,102);
	
	boolean drawPart = false;

	private static Color[] colors = new Color[5];
	//static int colorCount = 0;
	
	private int counter;
	private static int maxCount = 240;
	
	public Brick(int x, int y, int width, int height, Color c) {
		super(x, y, GDV5.getMaxWindowX()/6-3, 25);
		colors[0] = VERY_DARK_BLUE;
		colors[1] = DARK_BLUE;
		colors[2] = BLUE;
		colors[3] = LIGHT_BLUE;
		colors[4] = VERY_LIGHT_BLUE;
		
		particles = BreakoutParticles.makeParticle(this);
		
		col = c;
		counter = 0;
	}
	
	static int rows = 5;
	static int numberPerRow = 6;
	public static Brick[] makeBricks(int rows, int numberPerRow) {

		int currentRowNum= 0;
		int x = 3;
		int y = 3;
		int padding = 3;
		int colorCount =0;
		Brick[] brickMaster = new Brick[rows*numberPerRow];
		for(int k=0;k<rows*numberPerRow;k++) {
			brickMaster[k] = new Brick(x,y, GDV5.getMaxWindowX()/6-3,25,colors[colorCount]);
			currentRowNum++;
			x+= brickMaster[0].width+padding;
			if(currentRowNum == numberPerRow) {
				y+=brickMaster[0].height+padding;
				colorCount++;
				currentRowNum = 0;
				x=3;
			}
		}	
		return brickMaster;
	}
	public boolean gameWinCheck() {
		int counter = 0;
		for (Brick b: BreakoutRunner.bricks) {
			if(b.visible == 2) counter++;
		}
		if(counter== rows*numberPerRow) return true;
		else return false;		
	}
	public static int getRows() {
		return rows;
	}
	
	public void brickUpdate() {
		if(visible == 1) {
			for(BreakoutParticles p: particles) {
				p.setActive(true);
			}
		visible =2;
		}
		if(visible==2 && counter<maxCount) {
			for(BreakoutParticles p: particles) {
				p.moveParticle();
				
			}
			counter++;	
			
		}
		if(counter == maxCount) {
			for(BreakoutParticles p: particles) {
				p.setActive(false);
			}
			counter++;
		}
	}
	public static int getNumberPerRow() {
		return numberPerRow;
	}
	public Color getColor() {
		return col;
	}
	
	public static void brickWinCheck() {
		int brickCount = 0;
		for (Brick b: BreakoutRunner.bricks) {
			if(b.visible==2) {
				brickCount++;
			}
		}
		if(brickCount == BreakoutRunner.bricks.length) {
			BreakoutRunner.display = 4;
		}
	}
	
	
	public void draw(Graphics2D pb) {
		if(visible==2 && counter<maxCount) {
			for(BreakoutParticles p: particles) {
				p.drawParticle(pb);
				
			}
		}
		if (visible == 0){
			
		pb.setColor(col);
		pb.fill(this);
		pb.setColor(Color.white);
		pb.draw(this);
		}
	}
}
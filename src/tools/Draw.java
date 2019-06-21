package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

import constant.StringValue;
import model.Node;

public class Draw {
	private String filePath="data\\data.jpg";
	private Graphics2D graphics2d;
	private BufferedImage image=null;
	
	private static int DOT_RADIUS=5;
	private static int LINE_WIDTH=5;
	
	
	
	public Draw() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	public Draw(Graphics2D paint) {
		// TODO Auto-generated constructor stub
		this.graphics2d=paint;
		init();
	}
	
	private void init() {
		File file=new File(filePath);
		try {
			image=ImageIO.read(new FileInputStream(file));
			System.out.println("read image");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setGraphics(Graphics2D graphics) {
		this.graphics2d=graphics;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void paint() {
		graphics2d.drawImage(image, 0, 0, null);
		//graphics2d.drawLine(0, 0, 500, 500);
	}
	
	public void link(List<Node> nodes) {
		new LinkPro(this,(Graphics2D)image.getGraphics(), nodes,new File(StringValue.DATA_PATH)).start();;
		
	}
	
	
	
	public void drawPoints(List<Node> nodes) {
		Graphics2D gra=(Graphics2D) image.getGraphics();
		gra.setColor(Color.RED);
		for(Node node:nodes) {
		
			gra.fillOval(node.location.x-DOT_RADIUS, node.location.y-DOT_RADIUS, DOT_RADIUS*2, DOT_RADIUS*2);
//		graphics2d.fillOval(node.location.x-DOT_RADIUS, node.location.y+DOT_RADIUS, DOT_RADIUS*2, DOT_RADIUS*2);
		}
		
	}
	
	public void drawTempPoint(Point point) {
		graphics2d.fillOval(point.x-DOT_RADIUS, point.y-DOT_RADIUS, DOT_RADIUS*2, DOT_RADIUS*2);
	}

}

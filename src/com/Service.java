package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import model.Node;
import tools.Draw;
import tools.PointManager;

public class Service {
	private List<Node> nodes = null;
	private List<Integer> indexes = new ArrayList<Integer>();
	private Draw draw;

	private void init() {
		nodes = PointManager.readPoints();
		if (nodes == null) {
			nodes = new ArrayList<Node>();
			return;
		}
		for (Node node : nodes) {
			indexes.add(node.index);
		}
		
		draw.drawPoints(nodes);
	}

	public Service(Draw draw) {
		this.draw = draw;
		init();
	}

	public Service() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void addNode(Node node) {
		int index = node.index;
		int i = indexes.indexOf(index);
		if (i == -1) {
			indexes.add(index);
			nodes.add(node);
			draw.drawPoints(nodes);
		} else {
			JOptionPane.showMessageDialog(null, "序号" + index + "重复，请重新输入！", "系统警告",
					JOptionPane.WARNING_MESSAGE + JOptionPane.OK_OPTION);
		}

	}

	public void link() {
		draw.link(nodes);
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void saveNodes() {
		 PointManager.savePoints(nodes);
	}

	public void saveImage() {
		FileOutputStream outputStream=null;
		try {
			 outputStream=new FileOutputStream(new File("data\\result.jpg"));
			ImageIO.write(draw.getImage(), "jpg",outputStream);
			JOptionPane.showMessageDialog(null, "save finished", "system info", JOptionPane.OK_OPTION+JOptionPane.INFORMATION_MESSAGE);
			System.out.println("save finished");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

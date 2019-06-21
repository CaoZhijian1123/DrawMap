package test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Node;
import tools.PointManager;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Node> nodes=new ArrayList<Node>();
		nodes.add(new Node(1, new Point(2, 2)));
		nodes.add(new Node(2, new Point(2, 2)));
		PointManager.savePoints(nodes);
		System.out.println(PointManager.readPoints());
	}

}

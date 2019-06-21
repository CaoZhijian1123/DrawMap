/**
 * @title
 * @description
 * @update record
  * format:[date][operator] detail<br> 
 * @company csjt
 * @Copyright copyright (c) 2019
 * @version 1.0
 * @since 2019年4月22日
 */
package model;

import java.awt.Point;
import java.io.Serializable;

/**
 * @author Zhijian Cao
 * @version 1.0
 * @since 2019年4月22日
 * @description
 * 
 */
public class Node  implements Serializable {

	/**
	 * @author Zhijian Cao
	 * @version 1.0
	 * @since 2019年4月22日
	 * @description
	 */
	private static final long serialVersionUID = 1L;
	
	public int index;
	public Point location;
	
	public Node(int index, Point location) {
	
		this.index = index;
		this.location = location;
	
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Node) {
			Node node=(Node) obj;
			return this.index==node.index&&this.location==node.location;
		}
		return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "index="+index+"  "+location;
	}
}

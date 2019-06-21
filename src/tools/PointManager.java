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
package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import constant.StringValue;
import model.Node;

/**
 * @author Zhijian Cao
 * @version 1.0
 * @since 2019年4月22日
 * @description
 * 
 */
public class PointManager {
	private static String path=StringValue.POINTS_FILE;//Nodes存储文件的位置;
	
	
	/**
	 * 读取存储在本地的nodes
	 * @return
	 */
	public static List<Node>readPoints(){
		File file=new File(path);
		if (file.exists()) {
			ObjectInputStream in;
			try {
				in=new ObjectInputStream(new FileInputStream(file));
				Node[] nsTemp=(Node[]) in.readObject();
				List<Node>nodes=Arrays.asList(nsTemp);
				in.close();
				return nodes;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 存储节点
	 * @param nodes
	 */
	public static void savePoints(List<Node> nodes) {
		File file=new File(path);
		if (file.isFile()&&!file.exists()) {
			try {
				file.mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ObjectOutputStream out;
		
		try {
			out=new ObjectOutputStream(new FileOutputStream(file));
			Node[] ns=new Node[nodes.size()];
			nodes.toArray(ns);
			out.writeObject(ns);
			out.flush();
			out.close();
			System.out.println("save nodes successfully");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

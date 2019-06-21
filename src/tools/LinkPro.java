package tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.print.DocFlavor.STRING;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import model.Node;

public class LinkPro extends Thread {
	private List<Node> points;
	private Graphics2D graphics;
	private File data;
	private Draw draw;
	private int workSheetIndex=0;
	public LinkPro(Draw draw,Graphics2D graphics,List<Node> nodes, File data) {
		super();
		this.points = nodes;
		this.graphics=graphics;
		this.data = data;
		this.draw=draw;
		
		
	}
	
	private void readIndex() {
		File file=new File("data\\sheetNo.txt");
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(new FileReader(file));
			String string=null;
			string=reader.readLine();
			workSheetIndex=Integer.parseInt(string);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	

	@Override
	public void run() {
		super.run();
		Sheet sheet;
		try {
			Workbook book = Workbook.getWorkbook(data);
			readIndex();
			sheet = book.getSheet(workSheetIndex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "数据文件损坏！", "系统提示", JOptionPane.OK_OPTION + JOptionPane.ERROR_MESSAGE);
			return;
		}
		System.out.println("open data");

		Cell[] rowCells = sheet.getRow(0);
		Cell[] colCells = sheet.getColumn(0);

		for (int i = 0; i < points.size(); i++) {
			Cell cell1 = null;
			int index1 = points.get(i).index;

			for (int k = 0; k < colCells.length; k++) {
				if (("" + index1).equals(colCells[k].getContents())) {
					cell1 = colCells[k];
				}
			}
			if (cell1 == null) {
				continue;
			}
			System.out.println("find cell1");
			for (int j = 0; j < points.size(); j++) {
				Cell cell2 = null;
				int index2 = points.get(j).index;
				for (int k = 0; k < rowCells.length; k++) {
					if (("" + index2).equals(rowCells[k].getContents())) {
						cell2 = rowCells[k];
					}
				}
				if (cell2 == null) {
					continue;
				}
				System.out.println("find cell2");

				int row = cell1.getRow();
				int colunm = cell2.getColumn();
				Cell cell3 = sheet.getCell(row, colunm);
				double count = Double.parseDouble(cell3.getContents());
				System.out.println(count);
				if (count>0){
					
		//		((Graphics2D) image.getGraphics()).setStroke(new BasicStroke(getWidth(count)));
					
					graphics.setStroke(new BasicStroke(getWidth(count)));
					graphics.setColor(Color.BLUE);
					graphics.drawLine(points.get(i).location.x, points.get(i).location.y, points.get(j).location.x,
							points.get(j).location.y);
					System.out.println("link: " + points.get(i).index+ "&" + points.get(j).index);
				}

			}
			draw.paint();
		}
	}

	/**
	 * 将数据量按梯度转换成绘图宽度
	 * 
	 * @param count
	 * @return 对应的像素宽度
	 */
	private int getWidth(double count) {
		return (int) (count/1000+1);
	}

	
}

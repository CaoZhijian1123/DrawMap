package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Graphics2D;

import javax.swing.border.LineBorder;

import com.Service;

import model.Node;
import tools.Draw;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import java.awt.Canvas;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JPanel paintPanel = new JPanel();
	private Draw draw =new Draw((Graphics2D) paintPanel.getGraphics());
	private JPanel panel;
	private Point start;
	int x0 = 0, y0 = 0;
	
	private Service service = new Service(draw);

	private Point pointTemp = new Point();
	private boolean point_flag = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 705, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnFile = new JMenu("file");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("open");

		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// draw=new Draw((Graphics2D) paintPanel.getGraphics());

				draw.setGraphics((Graphics2D) paintPanel.getGraphics());
				paintPanel.setSize(draw.getImage().getWidth(), draw.getImage().getHeight());
				draw.paint();

			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("link");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.link();
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmExport = new JMenuItem("export");
		mntmExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.saveImage();
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("save node");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.saveNodes();
			}
		});
		mnFile.add(mntmNewMenuItem_1);
		mnFile.add(mntmExport);

		JMenu mnHelp = new JMenu("help");
		menuBar.add(mnHelp);

		JMenuItem mntmNewMenuItem = new JMenuItem("author");
		mnHelp.add(mntmNewMenuItem);

		panel = new JPanel();
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 51, 255), 2), null));
		panel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		/*
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				System.out.println(start.toString() + "\nx0=" + x0 + "Y0=" + y0);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// start=paintPanel.getLocation();

				start = paintPanel.getLocation();
				x0 = e.getX();
				y0 = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// panel.repaint();
				draw.setGraphics((Graphics2D) paintPanel.getGraphics());
				draw.paint();
				// draw.drawImageOnCan();
			}

		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				paintPanel.setLocation(start.x + e.getX() - x0, start.y + e.getY() - y0);
				// paintPanel.setLocation(start.x+e.getX()-x0, start.y+e.getY()-y0);
				// draw.drawBackground();
				draw.setGraphics((Graphics2D) paintPanel.getGraphics());
				draw.paint();

				System.out.println(e.getX() + "  " + e.getY());
			}
		});
		
		*/

		
		
		paintPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pointTemp = e.getPoint();
				System.out.println(pointTemp);
				draw.drawTempPoint(pointTemp);
				point_flag = true;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// start=paintPanel.getLocation();

				start = paintPanel.getLocationOnScreen();
				x0=e.getXOnScreen();
				y0 = e.getYOnScreen();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
//				 panel.repaint();
//				draw.setGraphics((Graphics2D) paintPanel.getGraphics());
				draw.paint();
			}
			
		});
		
		paintPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				paintPanel.setLocation(start.x + e.getXOnScreen() - x0, start.y + e.getYOnScreen() - y0);
				draw.setGraphics((Graphics2D) paintPanel.getGraphics());
//				panel.repaint();
				draw.paint();

//				System.out.println(e.getXOnScreen() + "  " + e.getYOnScreen());
			}
		});

		paintPanel.setBorder(new CompoundBorder(new LineBorder(new Color(255, 0, 0), 2), null));
		panel.add(paintPanel, BorderLayout.CENTER);
		paintPanel.setLayout(null);

		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		textField = new JTextField();
		panel_2.add(textField);
		textField.setText("1");
		textField.setColumns(10);

		JButton btnNewButton = new JButton("ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point_flag) {
					int index = -1;
					try {
						index = Integer.parseInt(textField.getText());
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "序号解析错误！", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (index != -1) {
						Node node = new Node(index, pointTemp);
						service.addNode(node);
						draw.paint();
						point_flag = false;
					}
				}
			}
		});
		panel_1.add(btnNewButton);

	}
}

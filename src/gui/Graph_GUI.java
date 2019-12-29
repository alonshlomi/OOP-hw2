package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import algorithms.Graph_Algo;
import dataStructure.*;
import utils.Point3D;

public class Graph_GUI extends JFrame implements MouseListener {

	private graph g;
	private Graph_Algo algo;
	private int curr_mc;
	
	public Graph_GUI() {
		g = new DGraph();
		curr_mc = g.getMC();
		algo = new Graph_Algo();
		algo.init(g);
		initGUI();
	}
	
	public Graph_GUI(graph g) {
		this.g = g;
		curr_mc = g.getMC();
		algo = new Graph_Algo();
		algo.init(g);
		initGUI();
	}

	private void initGUI() {
		int width = 1000, height = 800;
		this.setRandLocations(width, height);
		this.setTitle("Graph GUI");
		this.setBounds(200, 0, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		MenuBar menu_bar = new MenuBar();
		
		Menu file_menu = new Menu("File");
		MenuItem save_item = new MenuItem("Save");
		MenuItem load_item = new MenuItem("Load");
		
		Menu graph_menu = new Menu("Graph");
		MenuItem ae_item = new MenuItem("Add Edge");
		MenuItem re_item = new MenuItem("Remove Edge");
		MenuItem rn_item = new MenuItem("Remove Node");
		
		Menu algo_menu = new Menu("Algorithms");
		MenuItem ic_item = new MenuItem("IsConnected");
		MenuItem sp_item = new MenuItem("Shortest Path");
		MenuItem tsp_item = new MenuItem("TSP");

		
		save_item.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				writeFileDialog();
			}
		});	
		load_item.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				readFileDialog();
			}
		});
		ae_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addEdgeGUI();
			}
		});
		re_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeEdgeGUI();
			}
		});
		rn_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeNodeGUI();
			}
		});
		sp_item.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				shortestPathGUI();
			}
		});
		ic_item.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				isConnectedGUI();
			}
		});
		tsp_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tspGUI();
			}
		});
		
		file_menu.add(save_item);
		file_menu.add(load_item);
		
		graph_menu.add(ae_item);
		graph_menu.add(re_item);
		graph_menu.add(rn_item);
		
		algo_menu.add(sp_item);
		algo_menu.add(ic_item);
		algo_menu.add(tsp_item);
		
		menu_bar.add(file_menu);
		menu_bar.add(graph_menu);
		menu_bar.add(algo_menu);
		
		this.setMenuBar(menu_bar);
		
		setVisible(true);
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					synchronized (this) {
						if (g.getMC() != curr_mc) {
							repaint();
							curr_mc = g.getMC();
						}
					}
				}
			}
		});

		t.start();
		
		this.addMouseListener(this);
	}
	
	public void	addEdgeGUI() {
		JFrame frame = new JFrame("Add Edge");
		JLabel src_lbl = new JLabel("src:  ");
		JLabel dest_lbl = new JLabel("dest: ");
		JLabel w_lbl = new JLabel("weight: ");
		JTextField src_text = new JTextField(13);
		JTextField dest_text = new JTextField(13);
		JTextField w_text = new JTextField(11);
		JButton btn = new JButton("Add Edge");
		
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		frame.setBounds(200, 0, 200, 200);
		frame.add(src_lbl);
		frame.add(src_text);
		frame.add(dest_lbl);
		frame.add(dest_text);
		frame.add(w_lbl);
		frame.add(w_text);
		frame.add(btn);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int src = Integer.parseInt(src_text.getText());
					int dest = Integer.parseInt(dest_text.getText());
					double w = Double.parseDouble(w_text.getText());
					g.connect(src, dest, w);
					repaint();
					JOptionPane.showMessageDialog(frame, "Edge has been added", "Add Edge", JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(), "Add Edge", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public void removeEdgeGUI() {
		JFrame frame = new JFrame("Remove Edge");
		JLabel src_lbl = new JLabel("src:  ");
		JLabel dest_lbl = new JLabel("dest: ");
		JTextField src_text = new JTextField(13);
		JTextField dest_text = new JTextField(13);
		JButton btn = new JButton("Remove");
		
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		frame.setBounds(200, 0, 200, 200);
		frame.add(src_lbl);
		frame.add(src_text);
		frame.add(dest_lbl);
		frame.add(dest_text);
		frame.add(btn);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int src = Integer.parseInt(src_text.getText());
					int dest = Integer.parseInt(dest_text.getText());				
					g.removeEdge(src, dest);
					repaint();
					JOptionPane.showMessageDialog(frame, "Edge has been removed", "Remove Edge", JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(), "Remove Edge", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void removeNodeGUI() {
		JFrame frame = new JFrame("Remove Node");
		JLabel node_lbl = new JLabel("Node to remove: ");
		JTextField node_text = new JTextField(5);
		JButton btn = new JButton("Remove");
		
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		frame.setBounds(200, 0, 200, 100);
		frame.add(node_lbl);
		frame.add(node_text);
		frame.add(btn);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int node = Integer.parseInt(node_text.getText());
					if(g.removeNode(node) != null) {
					JOptionPane.showMessageDialog(frame, "Node has been removed!", "Remove Node", JOptionPane.INFORMATION_MESSAGE);
					repaint();
					frame.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(frame, "No such node!", "Remove Node", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(), "Remove Node", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void isConnectedGUI() {
		String messege;
		
		if(algo.isConnected()) {
			messege = "The graph is connected!";
		} else {
			messege = "The graph isn't connected!";
		}
		
		JOptionPane.showMessageDialog(this, messege, "IsConnected", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void shortestPathGUI() {
		JFrame frame = new JFrame("Shortest Path");
		JLabel src_lbl = new JLabel("src:  ");
		JLabel dest_lbl = new JLabel("dest: ");
		JTextField src_text = new JTextField(13);
		JTextField dest_text = new JTextField(13);
		JButton btn = new JButton("Apply");
		
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		frame.setBounds(200, 0, 200, 200);
		frame.add(src_lbl);
		frame.add(src_text);
		frame.add(dest_lbl);
		frame.add(dest_text);
		frame.add(btn);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int src = Integer.parseInt(src_text.getText());
				int dest = Integer.parseInt(dest_text.getText());
				
				double ans = algo.shortestPathDist(src, dest);
				List<node_data> list_ans = algo.shortestPath(src, dest);

				String messege;

				if (list_ans == null) {
					messege = "No Path!";
				} else {
					messege = "Shortest path is: " + ans + "\n";
					for (int i = 0; i < list_ans.size(); i++) {
						messege += list_ans.get(i).getKey() + "";
						if (i != list_ans.size() - 1)
							messege += "->";
					}
				}
				
				JOptionPane.showMessageDialog(frame, messege, "Shortest Path", JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(), "Shortest Path", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
	private void tspGUI() {
		JFrame frame = new JFrame("TSP");
		JLabel lbl = new JLabel("Insert integers: (seperated by space)");
		JTextArea integers = new JTextArea(20,20);
		JButton btn = new JButton("Compute TSP");
		
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		frame.setBounds(200, 0, 300, 450);
		frame.add(lbl);
		frame.add(integers);
		frame.add(btn);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Integer> int_list = new ArrayList<Integer>();
					String[] input = integers.getText().split(" ");
					for(int i = 0;i<input.length;i++) {
						int curr_int = Integer.parseInt(input[i]);
						int_list.add(curr_int);
					}
					String messege = "";
					List<node_data> ans = algo.TSP(int_list);
					if(ans == null) {
						JOptionPane.showMessageDialog(frame, "No Path!", "TSP", JOptionPane.ERROR_MESSAGE);
					} else {
							messege += "The path is: \n";
						for (int i = 0; i < ans.size(); i++) {
							messege += ans.get(i).getKey() + "";
							if (i != ans.size() - 1)
								messege += "->";
						}
						JOptionPane.showMessageDialog(frame, messege, "TSP", JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(), "TSP", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
    public void writeFileDialog() {
        FileDialog fd = new FileDialog(this, "Save Graph", FileDialog.SAVE);
        fd.setFile("*.txt");
        fd.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }

        });
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		System.out.println(folder + fileName);
		if (folder != null && fileName != null) {
			algo.save(folder + fileName);
		}
    }


    public void readFileDialog() {
    	   
        FileDialog fd = new FileDialog(this, "Load Graph", FileDialog.LOAD);
        fd.setFile("*.txt");
        fd.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }

        });
        fd.setVisible(true);
        String folder = fd.getDirectory();
        String fileName = fd.getFile();
        algo.init(folder+fileName);
        this.g = algo.copy();
        repaint();
        
    }
    

	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g1 = (Graphics2D) g;
		
		for (node_data node : this.g.getV()) {
			
			g.setColor(Color.BLUE);
			g.fillOval(node.getLocation().ix() - 5, node.getLocation().iy() - 5, 10, 10);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString(node.getKey() + "", node.getLocation().ix() + 5, node.getLocation().iy() + 5);

			if (this.g.getE(node.getKey()) != null) {
				for (edge_data edge : this.g.getE(node.getKey())) {

					node_data src = this.g.getNode(edge.getSrc());
					node_data dest = this.g.getNode(edge.getDest());

					g.setColor(Color.RED);
					
					g1.setStroke(new BasicStroke(2));
					g1.drawLine(src.getLocation().ix(), src.getLocation().iy(), dest.getLocation().ix(),	dest.getLocation().iy());

					int mid_x = (src.getLocation().ix() + dest.getLocation().ix()) / 2;
					int mid_y = (src.getLocation().iy() + dest.getLocation().iy()) / 2;
					g.setFont(new Font("Arial", Font.BOLD, 12));
					g.drawString(edge.getWeight() + "", mid_x, mid_y);

					g.setColor(Color.YELLOW);

					int src_x = src.getLocation().ix(), src_y = src.getLocation().iy();
					int dest_x = dest.getLocation().ix(), dest_y = dest.getLocation().iy();

					int dir_x = (((((((src_x + dest_x) / 2) + dest_x) / 2) + dest_x) / 2) + dest_x) / 2;
					int dir_y = (((((((src_y + dest_y) / 2) + dest_y) / 2) + dest_y) / 2) + dest_y) / 2;

					g.fillOval(dir_x - 5, dir_y - 5, 10, 10);

				}
			}

		}


	}


	public void setRandLocations(int height, int width) {

		for (node_data node : g.getV()) {
			double x = Math.random() * (width);
			double y = Math.random() * (height / 1.5);
			x += 50;
			y += 50;
			Point3D tmp_lo = new Point3D(x, y);

			node.setLocation(tmp_lo);
		}

	}


	public static void main(String[] args) {
		DGraph g = new DGraph(6);
		g.connect(0, 5, 10);
		g.connect(0, 2, 20);
		g.connect(1, 4, 25);
//		g.connect(1, 6, 7);
//		g.connect(2, 3, 30);
//		g.connect(3, 4 , 4);
//		g.connect(4, 5, 2);
		g.connect(4, 6, 1);
		Graph_GUI gg = new Graph_GUI(g);
		
		List<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(4);
		l.add(5);
		
//		Graph_Algo ga = new Graph_Algo();
//		ga.init(g);
//		System.out.println(ga.TSP(l));
//		System.out.println(ga.TSP(l).size());
		try {
			Thread.sleep(2000);
//			g.removeNode(6);
//			Thread.sleep(1000);
//			g.removeNode(4);
//			g.removeNode(3);
//			g.removeNode(2);
//			Thread.sleep(3000);
//			g.removeNode(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		Point3D loc = new Point3D(e.getX(), e.getY());
		node_data node = new Node();
		node.setLocation(loc);
		this.g.addNode(node);
		repaint();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

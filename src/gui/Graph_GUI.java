package gui;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import javax.swing.JFrame;
import javax.swing.text.StyledEditorKit.BoldAction;

import algorithms.Graph_Algo;
import dataStructure.*;
import utils.Point3D;
import utils.StdDraw;

public class Graph_GUI extends JFrame {

	graph g;
	Graph_Algo algo;

	public Graph_GUI(graph g) {
		this.g = g;
		algo = new Graph_Algo();
		algo.init(g);
		initGUI();
	}

	private void initGUI() {
		int width = 1000, height = 800;
		setRandLocations(width, height);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menu_bar = new MenuBar();
		Menu file_menu = new Menu("File");
		MenuItem save_item = new MenuItem("Save");
		
		save_item.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				writeFileDialog();
			}
		});
		
		file_menu.add(save_item);
		menu_bar.add(file_menu);
		this.setMenuBar(menu_bar);
		
		setVisible(true);
	}
	
    public void writeFileDialog() {
        FileDialog fd = new FileDialog(this, "Save the graph", FileDialog.SAVE);
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
        algo.save(folder+fileName);
        

    }

	public void paint(Graphics g) {
		super.paint(g);

		for (node_data node : this.g.getV()) {

			g.setColor(Color.BLUE);
			g.fillOval(node.getLocation().ix() - 5, node.getLocation().iy() - 5, 10, 10);

			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString(node.getKey() + "", node.getLocation().ix() + 5, node.getLocation().iy() + 5);

			if (this.g.getE(node.getKey()) != null) {
				for (edge_data edge : this.g.getE(node.getKey())) {

					node_data src = this.g.getNode(edge.getSrc());
					node_data dest = this.g.getNode(edge.getDest());

					g.setColor(Color.RED);
					g.drawLine(src.getLocation().ix(), src.getLocation().iy(), dest.getLocation().ix(),	dest.getLocation().iy());

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
		DGraph g = new DGraph(10);
		g.connect(0, 1, 10);
		g.connect(0, 2, 20);
		g.connect(1, 3, 25);
		g.connect(1, 4, 7);
		g.connect(2, 4, 30);
		g.connect(3, 5, 4);
		g.connect(4, 5, 2);
		// g.connect(5, 0, 1);
		Graph_GUI gg = new Graph_GUI(g);

	}

}

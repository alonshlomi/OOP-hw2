package dataStructure;

import java.io.Serializable;

import utils.Point3D;

public class Node implements node_data,Serializable{
	
	private static int ID = 0;
	private int key,tag;
	private Point3D location;
	private double weight;
	private String info;
	public static final Node_Comparator _Comp = new Node_Comparator();

	
	public Node(node_data other) {
		key = other.getKey();
		weight = other.getWeight();
		location = (other.getLocation() == null) ? null : new Point3D(other.getLocation());
		info = (other.getInfo() == null) ? null : new String(other.getInfo());
		tag = other.getTag();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof node_data) {
			node_data other = (node_data) o;
			return this.key == other.getKey();
		}
		return false;
	}
	
	public String toString() {
		return "["+key+"("+info+"){"+tag+"}]";
	}
	
	public Node(Point3D l, int w, String i,int t) {
		this();
		setLocation(l);
		setWeight(w);
		setInfo(i);
		setTag(t);
	}
	
	public Node() {
		this.key = ID++;
		setWeight(Double.MAX_VALUE);
		setTag(-1);
		setInfo(null);
	}
	
	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Point3D getLocation() {
		return location;
	}

	@Override
	public void setLocation(Point3D p) {
		location = new Point3D(p);
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(double w) {
		weight = w;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		info = (s==null) ? null : new String(s);
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		tag = t;
	}

}

package dataStructure;

public class Edge implements edge_data {

	private node_data src;
	private node_data dest;
	private double weight;
	private int tag;
	private String info;
	
	public String toString() {
		return "["+src.getKey()+"->"+dest.getKey()+" ("+weight+")]";
	}
	
	public Edge(node_data src,node_data dest,double weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
	
	@Override
	public int getSrc() {
		return src.getKey();
	}

	@Override
	public int getDest() {
		return dest.getKey();
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getInfo() {
		return new String(info);
	}

	@Override
	public void setInfo(String s) {
		info = new String(s);
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
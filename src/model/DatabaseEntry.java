package model;

public class DatabaseEntry {
	public String name;
	public int played, won, lost;
	
	private DatabaseEntry() {
		
	}
	
	public DatabaseEntry(String line) {
		String[] vals = line.split(",");
		name = vals[0];
		played = Integer.parseInt(vals[1]);
		won = Integer.parseInt(vals[2]);
		lost = Integer.parseInt(vals[3]);
	}
	
	public static DatabaseEntry empty(String name) {
		var result = new DatabaseEntry();
		result.name = name;
		return result;
	}
	
	@Override
	public String toString() {
		return name+","+played+","+won+","+lost;
	}
}

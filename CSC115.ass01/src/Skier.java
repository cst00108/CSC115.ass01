/*
 * Name:      Jason Donald
 * ID:        V00861539
 * Date:      2006-01-23
 * Filename:  Skier.java
 * Details:   CSC115 Assignment 1
 */

/**
 * Class Skier consists of the name of a skier and the current level of 
 * expertise, for the purposes of a Ski school that holds courses for different 
 * levels. In this version, the name of the skier is used as the unique 
 * identifier; in other words, we assume that no two skiers have the same name. 
 * The possible levels and their descriptions follow:
 * <ol start="0">
 * <li> Never skied before
 * <li> Cautious novice: able to snow-plow,stop and make round turns on 
 * easy trails
 * <li> Cautious, yet confident skiers who can link turns at moderate speeds
 * <li> Confident skiers who can ski mostly parallel but may use wedging to 
 * turn or stop on steep or icy trails.
 * <li> Confident skiers who can make parallel turns but do not ski advanced trails.
 * </ol>
 */
public class Skier {
	private String name; // the unique name of the skier
	private int level; // level of skill


	/**
	 * Creates a level 0 Skier.
	 * @param name The name of the skier.
	 */
	public Skier(String name) {
		this.name = name;
	}

	/**
	 * Creates a Skier at the given level.
	 * @param name The name of the skier.
	 * @param level The level of the skier. If the parameter level value is not
	 * within 0 to 4, then the Skier level is set to 0.
	 */
	public Skier(String name, int level) {
		this(name);
		
		if(isLevel(level)){
			this.level = level;
		}
	}

	/**
	 * Determines whether the parameter given is an actual level of the ski 
	 * school.
	 * @param level Level at which the skier's skills are.
	 * @return false if non-valid entry is given.
	 */
	static boolean isLevel(int level){
		if(level > -1 && level < 5){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Updates the name of the skier.
	 * @param name The updated name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return The skier's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the level for the skier.
	 * @param level The new level. If the level not between 0 and 4, then the
	 * current level remains unchanged.
	 */ 
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return The current level of the skier.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Determines if two skier objects are equivalent. For our purposes, they 
	 * are equal if they have the same name.
	 * @param other The skier to compare to this skier.
	 * @return True if other has the same name as this skier.
	 */
	public boolean equals(Skier other) {
		return this.name.equals(other.name) && this.level == other.level;
	}

	/**
	 * @return a String representation of the skier. The format is a single 
	 * line. For example, if the skier's name is Jane Doe and she is at level 0,
	 * then the returned string is:  Jane Doe (level 0)
	 */
	public String toString() {
		return name + " (level " + level + ')';
	}

	public static void main(String[] args) {
		System.out.println("Testing the Skier class.");
		Skier s1 = null;
		try {
			s1 = new Skier("Howie SnowSkier", 4);
		} catch(Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		if (!s1.getName().equals("Howie SnowSkier")) {
			System.out.println("Failed at test one.");
			return;
		}
		if (s1.getLevel() != 4) {
			System.out.println("Failed at test two.");
			return;
		}
		Skier s2 = new Skier("Baby Skier");
		if (!s2.getName().equals("Baby Skier")) {
			System.out.println("Failed at test three.");
			return;
		}
		if (s2.getLevel() != 0) {
			System.out.println("Failed at test four.");
			return;
		}
		s2.setName("Better Skier");
		s2.setLevel(1);
		if (!s2.getName().equals("Better Skier") || s2.getLevel() != 1) {
			System.out.println("Failed at test five.");
			return;
		}
		if (s1.equals(s2)) {
			System.out.println("Failed at test six.");
			return;
		}
		if (!s1.equals(new Skier("Howie SnowSkier",4))) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (s1.toString().equals("Howie SnowSkier (level 0)")) {
			System.out.println("Failed at test eight.");
			System.out.println("Expected: Howie SnowSkier (level 0)");
			System.out.println("Got:      "+s1.toString());
			return;
		}
		System.out.println("All tests passed.");
	}
}

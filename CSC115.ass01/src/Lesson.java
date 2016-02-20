/*
 * Name:      Jason Donald
 * ID:        V00861539
 * Date:      2006-01-23
 * Filename:  Lesson.java
 * Details:   CSC115 Assignment 1
 */

/**
 * Class Lesson is a Ski lesson for a certain level of skiers. It may be empty 
 * or contain a number of ski students. In this version of the class, there is 
 * no limit to the number of students in the lesson.
 */
public class Lesson {
	private static final String[] levelNames = 
		{"Beginner", "Novice", "Snowplower", "Intermediate", "Advanced"};
	private int lessonLevel; // level of the Lesson, not necessarily all the skiers
	private String lessonName; // One of the names in levelNames array above.
	private SkierList students; // The list of skiers registered for this lesson.
	
	/**
	 * Creates a lesson for a given level.
	 * @param level The level of the students who will take this lesson. If the
	 * level is out of range (0 ... 4), then the level is defaulted to 0.
	 */
	public Lesson(int level) {
		this(level, new SkierList());
	}

	/**
	 * Creates a lesson for the given level, and populates the lesson with Skiers.
	 * @param level The level of the students who will take this lesson. If the
	 * level is out of range (0 ... 4), then the level is defaulted to 0.
	 * @param students The SkierList. Note that all skiers in the list are assumed
	 * to be in the correct level. No checking is done for skiers who are in
	 * the wrong level in this version of the class.
	 */
	public Lesson(int level, SkierList students) {
		if(Skier.isLevel(level)){
			lessonLevel = level;			
		}
		
		this.lessonName = levelNames[lessonLevel];
		this.students = students;
	}

	/**
	 * Sets the lesson name based on the level of the students. The level names associated
	 * with the level numbers are as follows:
	 * <ol start="0">
	 * <li> Beginner
	 * <li> Novice
	 * <li> Snowplower
	 * <li> Intermediate
	 * <li> Advanced
	 * </ol>
	 * If the level is out of range (0 ... 4), then the level remains unchanged. This method
	 * does not reset the levels of the individual skiers in the lesson.
	 * @param level The new level.
	 */
	public void setLessonLevel(int level) {
		if(Skier.isLevel(level)){
			this.lessonLevel = level;			
			this.lessonName = levelNames[lessonLevel];
		}
	}

	/**
	 * @return The group name of the lesson. The name is one of the following that is
	 * based on the level number:
	 * <ol start="0">
	 * <li> Beginner
	 * <li> Novice
	 * <li> Snowplower
	 * <li> Intermediate
	 * <li> Advanced
	 * </ol>
	 */ 
	public String getName() {
		return levelNames[lessonLevel];
	}

	/**
	 * @return The number of skiing students in the lesson.
	 */
	public int numStudents() {
		return students.size();
	}

	/**
	 * Adds a new skier to the list. Before adding the skier to the lesson, the 
	 * skier's level is updated to fit the level of the lesson.
	 * @param skier The skier to add to the class. If the skier is already present,
	 * then nothing is added.
	 */
	public void addSkier(Skier skier) {
		students.add(skier);
	}

	/**
	 * Removes a skier from the lesson. If the skier is not in the lesson, then nothing is
	 * removed.
	 * @param skier The skier to remove.
	 */
	public void removeSkier(Skier skier) {
		int index = students.findSkier(skier);
		students.remove(index);
	}

	/**
	 * Determines whether a particular skier is registered for this Lesson.
	 * @param skier The skier in question.
	 * @return true if the skier is registered for the lesson, false if not.
	 */
	public boolean isRegistered(Skier skier) {
		if(students.findSkier(skier) >= 0){
			return true;
		}
		
		return false;
	}

	/**
	 * Prints a list of the students. The list is presented in the following format:
	 * <ul style="list-style-type:none">
	 * <li>&ltlessonName> group:
	 * <li>&ltstring representation of skier object>
	 * <li>&ltstring representation of skier object>
	 * <li>&lt...>
	 * </ul>
	 * For the &ltstring representation of skier object> see the Skier.toString method.
	 * Note that the lesson name and each student is on a separate line. Note also that the
	 * list of skiers is in no particular order.
	 * @return The string containing the information in the above format.
	 */
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		toReturn.append(lessonName + " group:)\n");

		for(int index=0; index<students.size(); index++){
			toReturn.append(students.get(index) + "\n");
		}

		return toReturn.toString();
	}

	/**
	 * Used as a test harness for the class.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		System.out.println("Testing the Lesson class.");
		Lesson lesson = null;
		String[] group = {"Daffy Duck", "Bugs Bunny", "Betty Boop",
			"Roger Rabbit", "Han Solo", "Chewbacca"};
		try {
			lesson = new Lesson(2);
		} catch (Exception e) {
			System.out.println("Failed to construct a Lesson object.");
			e.printStackTrace();
			return;
		}
		if (!lesson.getName().equals("Snowplower")) {
			System.out.println("Failed at test one.");
			return;
		}
		if (lesson.numStudents() != 0) {
			System.out.println("Failed at test two.");
			return;
		}
		lesson.setLessonLevel(3);
		if (!lesson.getName().equals("Intermediate")) {
			System.out.println("Failed at test three.");
			return;
		}
		for (int i=0; i<group.length; i++) {
			lesson.addSkier(new Skier(group[i]));
		}
		if (lesson.numStudents() != 6) {
			System.out.println("Failed at test four.");
			return;
		}
		System.out.print("Checking the toString: Should see a list of ");
		System.out.println("6 skiers, all with level 3");
		System.out.println(lesson);
		
		System.out.println("Checking constructor that takes a list.");
		int[] levels = {0,3,2};
		int levelIndex = 0;
		SkierList list = new SkierList();
		for (int i=0; i<group.length; i++) {
			list.add(new Skier(group[i],levels[levelIndex]));
			levelIndex = (levelIndex+1)%levels.length;
		}
		try {
			lesson = new Lesson(4,list);
		} catch (Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		if (lesson.numStudents() != 6) {
			System.out.println("Failed at test five.");
			return;
		}
		for (int i=0; i<list.size(); i++) {
			if (!lesson.isRegistered(list.get(i))) {
				System.out.println("Failed at test six.");
				return;
			}
		}
		Skier removed = list.get(3);
		lesson.removeSkier(removed);
		if (lesson.isRegistered(removed)) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (lesson.numStudents() != 5) {
			System.out.println("Failed at test eight.");
			return;
		}
		System.out.print("The following printout should consist of 5 ");
		System.out.println("skiers with varying levels:");
		System.out.println(lesson);
		System.out.println("Testing completed.");
	}
}


import java.util.ArrayList;
import java.util.List;

public class SharedData {
	private static ArrayList<Integer> grades = new ArrayList<>();
    private static ArrayList<String> lesson = new ArrayList<>();
    private static ArrayList<String> lesson2 = new ArrayList<>();

    public static void addGrade(int grade) {
        grades.add(grade);
    }

    public static List<Integer> getGrades() {
        return new ArrayList<>(grades); // Επιστρέφει αντίγραφο της λίστας για να προστατεύσει την αρχική λίστα
    }

    public static void clearGrades() {
        grades.clear();
    }
    
    public static void addLesson(String Lesson) {
    	lesson.add(Lesson);
    }

    public static ArrayList<String> getLesson() {
        return lesson; // Επιστρέφει αντίγραφο της λίστας για να προστατεύσει την αρχική λίστα
    }

    public static void clearLesson() {
    	lesson.clear();
    }
    
    public static void addLesson2(String Lesson2) {
        lesson2.add(Lesson2);
    }

    public static ArrayList<String> getLesson2() {
        return lesson2; // Επιστρέφει αντίγραφο της λίστας για να προστατεύσει την αρχική λίστα
    }

    public static void clearLesson2() {
    	lesson2.clear();
    }
}
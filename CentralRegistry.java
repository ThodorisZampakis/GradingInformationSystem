import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class CentralRegistry {


	private static ArrayList<String> studentsIds = new ArrayList<>();
	private static ArrayList<String> studentsPwds = new ArrayList<>();
	
	public static void showStudents(ArrayList<String> ids) 
	{
		 for (String id :ids) 
		 {	 
			 studentsIds.add(id); 
		 }
	}
	
	public static void showStudentsPwds(ArrayList<String> pwd) 
	{
		 for (String pw :pwd) 
		 {	 
			 studentsPwds.add(pw); 
		 }
	}
	
	
	 
	public static ArrayList<String> getStudentsIds()
	{
		return studentsIds;
	}
	
	public static ArrayList<String> getStudentsPwds()
	{
		return studentsPwds;
	}

}

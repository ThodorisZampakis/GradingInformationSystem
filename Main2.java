
import java.util.ArrayList;

public class Main2 {

	public static void main(String[] args) {
		
		//Dhmiourgia pinakwn me tis ores pou mporei o ka8hghths na e3ypyrethsei 
		// kapoion foithth. Oses einai oi 8eseis tou pinaka, toses einai oi dia8esimes
		// hmeres pou 8a mporei na e3yphretei foithtes.
		int [] h1 = {4,2,1,3,0};
		int [] h2 = {3,5,2,2,1};
		int [] h3 = {2,3,0,3,0};
		int [] h4 = {0,0,1,1,2};
		int [] h5 = {3,2,0,0,0};
		
		 
		// Dimiourgia antikeimenwn ka8hghtwn me th bohtheia twn sta8erwn pinakwn h.
	     Teacher t1 = new Teacher("Alexandros Xatzigeorgiou",h1,5);
	     Teacher t2 = new Teacher("Evaggelos Papageorgiou",h2,5);
	     Teacher t3 = new Teacher("Nikolaos Theodorou",h3,5);
	     Teacher t4 = new Teacher("Ioannis Papadopoulos",h4,5);
	     Teacher t5 = new Teacher("Elvira Arvanitou",h5,5);
	     
	     //Dimiourgia mias dynamikhs listas pou periexei kai tous 5 ka8hghtes.
	     
	     ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	    
	    teachers.add(t1);
	    teachers.add(t2);
	    teachers.add(t3);
	    teachers.add(t4);
	    teachers.add(t5);
	     
	     new FindOfficeHoursGUI(teachers);	
	}

}


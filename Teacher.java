

public class Teacher {
   private String fullName;
   private int [] officeAvailableHours;
   private int officeAvailableDays;
  
   
   public Teacher(String fullName,int [] officeHours, int officeDays){
	   this.fullName = fullName;
	   this.officeAvailableHours = officeHours;
	   this.officeAvailableDays = officeDays;
   }
   
   
   public String getName() {
	   return fullName;
   }
   public int [] getOfficeAvailableHours() {
	   return officeAvailableHours;
   }
   public int getOfficeAvailableDays() {
	   return officeAvailableDays;
   }
}


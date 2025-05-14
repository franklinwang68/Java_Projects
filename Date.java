//Franklin Wang
//The Date class represents the date
public class Date{
  
  //day of the month
  private int day;
  
  //month of the year
  private int month;
  
  //year of the date
  private int year;
  
  //Date display in USFormat (month/day/year)
  private boolean USFormat = false;
  
  //constructor for the Date
  public Date(int day, int month, int year){
    this.day = day;
    this.month = month;
    this.year = year;
  }
  
  //returns the day
  public int getDay(){
    return this.day;
  }
  
  //returns the month
  public int getMonth(){
    return this.month;
  }
  
  //returns the year
  public int getYear(){
    return this.year;
  }
  
  // changes the display format to US or not
  public void setUSFormat(boolean myBoo){
    this.USFormat = myBoo;
  }
  
  //checks if the display format should be US or not
  public boolean isUsFormat(){ //is the 's' supposed to be lowercase?
    return this.USFormat;
  }
  
  //calculates the number of days the date is from Jan 1st
  public int daysFromJan1(){
    int totalDays = 0; //stores the cumulative days from Jan 1st
    
    if(this.month == 1)
      totalDays = this.day-1;
    else if(this.month == 2)
      totalDays = 31 + this.day-1;
    else if(this.month == 3)
      totalDays = 31 + 28 + this.day-1;
    else if(this.month == 4)
      totalDays = 31 + 28 + 31 + this.day-1;
    else if(this.month == 5)
      totalDays = 31 + 28 + 31 + 30 + this.day-1;
    else if(this.month == 6)
      totalDays = 31 + 28 + 31 + 30 + 31 + this.day-1;
    else if(this.month == 7)
      totalDays = 31 + 28 + 31 + 30 + 31 + 30 + this.day-1;
    else if(this.month == 8)
      totalDays = 31 + 28 + 31 + 30 + 31 + 30 + 31 + this.day-1;
    else if(this.month == 9)
      totalDays = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + this.day-1;
    else if(this.month == 10)
      totalDays = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + this.day-1;
    else if(this.month == 11)
      totalDays = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + this.day-1;
    else
      totalDays = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + this.day-1;
 
    return totalDays;
  }
  
  //calculates the difference in days between to dates
  public static int difference(Date date1, Date date2){
    return (date1.daysFromJan1() - date2.daysFromJan1()) + (365*(date1.year - date2.year));
  }
  
  //overrides toString() so the date is displayed in US format or not instead of the address
  @Override
  public String toString(){
    if(USFormat){
      return this.month + "/" + this.day + "/" + this.year;
    }
    else{
      return this.day + "/" + this.month + "/" + this.year;
    }
  }
  
  //overrides equals() so it returns true if the date is the same
  @Override
  public boolean equals(Object o){
    if (o instanceof Date){
      Date d = (Date)o; //type casted so it can be compared
      if(this.getDay() == d.getDay() &&
         this.getMonth() == d.getMonth() &&
         this.getYear() == d.getYear() ) {
        return true;
      }
      else 
        return false;
    } 
    else 
      return false;
  }
}
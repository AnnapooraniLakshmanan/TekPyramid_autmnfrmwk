package generic_webdriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;



public class JavaUtility 
{
	Date d=new Date();
	SimpleDateFormat sim;
public int generateRandomNumber()
{
	Random r=new Random();
	int data = r.nextInt(1000);
	return data;
}

public String currentDate()
{
	
	String date = d.toString().replace(" ","").replace(":","");
	return date;
}

public String dateInReqFormat(String format)
{
	sim=new SimpleDateFormat(format);
	String date = sim.format(d);
	return date;
}

public String particularDateFromNow(int num)
{
	Calendar cal = sim.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH, num);
	String data = sim.format(cal.getTime());
	return data;
}
}

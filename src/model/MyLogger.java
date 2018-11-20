package model;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;



public class MyLogger 
{
	private static final Logger LOGGER = Logger.getLogger("");
  
	public static void logInfo(String name,Exception ee)
		{
	     try {
	    	  File f=new File("myLogFile.txt");
	    	  if(f.exists())
	    	  {
	    		  System.out.println("exists");
	    	  }
	    	 Boolean b= f.createNewFile();
	    	 System.out.println(b);
			FileHandler fh=new FileHandler("myLogFile.txt",true);
			fh.setFormatter(new SimpleFormatter());

			 LOGGER.addHandler(fh);
			// ee.getStackTrace()
			 LOGGER.info("\nEXCEPTION IN: "+name+" "+ee.getStackTrace()[0].getClassName()+"\nEXCEPTION IS : "+ee.getMessage()+"\nIN METHOD : "+ee.getStackTrace()[0].getMethodName()
					 +"\n---------------------------------------");
			 fh.close();
			

		} catch (SecurityException | IOException e) 
	     {
			e.printStackTrace();
		 }
	    
		}

}

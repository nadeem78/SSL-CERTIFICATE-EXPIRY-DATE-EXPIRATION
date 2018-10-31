package certificates_info;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MainClass {

	static Logger ExceptionLog = Logger.getRootLogger();
	static Logger ErrorLog = Logger.getLogger("logging");

	public static void main(String[] args) {
		
		 ErrorLog.debug("IN MAIN CLASS \n");
		 
		//SETTING THE log4j.properties FILE TO CURRENT DIRECTORY----------------------------------------------------
		 String str = System.getProperty("user.dir");
		 Properties prop1 = new Properties();
			InputStream input = null;
			try {
				input = new FileInputStream(str+"\\log4j.properties");
				// load a properties file
				prop1.load(input);
				
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		
			OutputStream output = null;
			
			try {

				output = new FileOutputStream(str+"\\log4j.properties");

				// set the properties value
				prop1.setProperty("log", str);

				// save properties to project root folder
				prop1.store(output, null);

			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		//-----------------------------------------------------------------------------------------------------*********	
			
			
			//READ CLASS OBJECT
			ErrorLog.debug("MAKING READ CLASS OBJECT \n");
		 File_Read_Class read = new File_Read_Class();
		 
		 //READING PATH GIVEN BY THE USER
		 ErrorLog.debug("READING PATH GIVEN BY THE USER \n");
		read.Read_Path();
		
		//READS THE ATTRIBUTES THAT USER WANTS
		ErrorLog.debug("READING THE ATTRIBUTES THAT USER WANTS \n");
		read.Read_User_Attributes();
		
		ArrayList<Attributes> list  = new ArrayList<Attributes>();
		
		//ADD MSPKI CERTS TO "list" IF USER HAS SET PATH FOR IT
		if(read.MSPKI()){
			MS_PKI mspki = new MS_PKI(read.get_MSPKI_path());
			list.addAll(mspki.get_ArrayList_Of_Attributes());
		}
		
		//ADD JAVA CERTS TO "list" IF USER HAS SET PATH FOR IT
		if(read.JAVA()){
			JavaCertsClass java = new JavaCertsClass(read.get_JAVA_path());
			list.addAll(java.get_ArrayList_Of_Attributes());
		}
		
		//SORTING CERTS ACCORDING TO DAYS LEFT OF EXPIRY
		ErrorLog.debug("SORTING CERTS ACCORDING TO DAYS LEFT OF EXPIRY \n");
		for(int i=0;i<list.size()-1;i++){
			for(int j=0;j<list.size()-1;j++){
				if(list.get(j).Get_Days_Left()>list.get(j+1).Get_Days_Left()){
					Collections.swap(list, j, j+1);
				}
			}
		}
		
		
		ArrayList<String> arr = read.get_User_Attributes();
		
		//COMPARES AND GETS THE FINAL LIST OF CERTS WITH THE ATTRIBUTES THAT USER WANTS
	   ArrayList<Object[]> FinalList = new Compare_Class(arr,list).Get_Final_List();
	   
	   //SENDING FINAL LIST TO WRITE CLASS
	    new File_Write_Class(FinalList);
	    
	    System.out.println("END OF PROGRAM");
	   

	}

}

package certificates_info;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.*;

 public class File_Read_Class {

	static Logger ExceptionLog = Logger.getRootLogger();
	static Logger ErrorLog = Logger.getLogger("logging");

	private String MSPKI_Path = "";
	private String JAVA_Path = "";
	private boolean MSPKI = false;
	private boolean JAVA = false;
	private ArrayList<String> User_List = new ArrayList<String>();

	public void Read_Path() {
		ErrorLog.debug("IN FUNCTION Read_Path OF READ CLASS"+"\n");
		Properties prop = new Properties();
		InputStream input = null;



		try {
			input = new FileInputStream("config.properties");
			ErrorLog.debug("CONFIG FILE READ"+"\n");
		} catch (FileNotFoundException e) {
			ErrorLog.error(
					"In class - File_Read_Class ---- In function - Read_Path() ---- config.properties file not found"+"\n");
		}

		// load a properties file
		try {
			prop.load(input);
		} catch (IOException e) {
			ErrorLog.error(
					"In class - File_Read_Class ---- In function - Read_Path() ---- IOException in properties.load"+"\n");
		}

		// get the property value and print it out
		String temp = null;
		temp = prop.getProperty("MSPKI");

		if (temp == null) {
			ErrorLog.error(
					"In class - File_Read_Class ---- In function - Read_Path() ---- config.properties file changed --- not able to find MSPKI keyword --- please write MSPKI keyword in config.properties file"+"\n");
		}else{
			ErrorLog.debug("MSPKI KEYWORD READ IN CONFIG"+"\n");
		}


		this.MSPKI_Path = prop.getProperty("MSPKI_path");

		if (temp.equals("1")) {
			MSPKI = true;
		} else if (temp.equals("0")) {
			if (!MSPKI_Path.equals("")) {
				ExceptionLog.warn("MSPKI_Path is specified but MSPKI not set"+"\n");
			}
		} else {
			ErrorLog.error(
					"In class - File_Read_Class ---- In function - Read_Path() ---- MSPKI keyword not set properly ---- please enter 0 or 1"+"\n");
		}
		if (MSPKI && MSPKI_Path.equals("")) {
			ErrorLog.error("MSPKI = 1 --- but MSPKI_Path not specified"+"\n");
		}
		temp = null;
		temp = prop.getProperty("JAVA");
		if (temp == null) {
			ErrorLog.error(
					"In class - File_Read_Class ---- In function - Read_Path() ---- config.properties file changed --- not able to find JAVA keyword --- please write JAVA keyword in config.properties file"+"\n");
		}else{
			ErrorLog.debug("JAVA KEYWORD READ IN CONFIG"+"\n");
		}

		this.JAVA_Path = prop.getProperty("JAVA_path");
		if (temp.equals("1")) {
			JAVA = true;
		} else if (temp.equals("0")) {
			if (!JAVA_Path.equals("")) {
				ExceptionLog.warn("JAVA_Path is specified but JAVA not set"+"\n");
			}
		} else {
			ErrorLog.error(
					"In class - File_Read_Class ---- In function - Read_Path() ---- JAVA keyword not set properly ---- please enter 0 or 1"+"\n");
		}

		if (JAVA && JAVA_Path.equals("")) {
			ErrorLog.error("JAVA = 1 --- but JAVA_Path not specified"+"\n");
		}

	}

	public void Read_User_Attributes() {
		ErrorLog.debug("IN Read_User_Attributes FUNCTION OF READ CLASS"+"\n");
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
			ErrorLog.debug("READ CONGIF FOR USER ATTRIBUTES"+"\n");
		} catch (FileNotFoundException e) {
			ErrorLog.error(
					"In class - File_Read_Class ---- In function - Read_User_Attributes() ---- config.properties file not found"+"\n");
		}

		// load a properties file
		try {
			prop.load(input);
		} catch (IOException e) {
			ErrorLog.error(
					"In class - File_Read_Class ---- In function - Read_User_Attributes() ---- IOException in properties.load"+"\n");
		}

		String temp = prop.getProperty("Version");
		if (temp.equals("1")) {
			User_List.add("Version");
			ErrorLog.debug("VERSION KEYWORD READ IN CONFIG"+"\n");
		}else if (temp.equals("0")) {
			ExceptionLoggingFunction("Version");
		} else {
			ErrorLoggingFunction("Version");
		}

		 temp = prop.getProperty("Path_Length");
		if (temp.equals("1")) {
			User_List.add("Path_Length");
			ErrorLog.debug("PATH LENGTH KEYWORD READ IN CONFIG"+"\n");
		} else if (temp.equals("0")) {
			ExceptionLoggingFunction("Path_Length");
		} else {
			ErrorLoggingFunction("Path_Length");
		}



		temp = prop.getProperty("Serial_Number");
		if (temp.equals("1")) {
			User_List.add("Serial_Number");
			ErrorLog.debug("SERIAL NUMBER KEYWORD READ IN CONFIG"+"\n");
		}else if (temp.equals("0")) {
			ExceptionLoggingFunction("Serial_Number");
		} else {
			ErrorLoggingFunction("Serial_Number");
		}


		temp = prop.getProperty("Valid_From");
		if (temp.equals("1")) {
			User_List.add("Valid_From");
			ErrorLog.debug("VALID FROM KEYWORD READ IN CONFIG"+"\n");
		}else if (temp.equals("0")) {
			ExceptionLoggingFunction("Valid_From");
		} else {
			ErrorLoggingFunction("Valid_From");
		}


		temp = prop.getProperty("Valid_To");
		if (temp.equals("1")) {
			User_List.add("Valid_To");
			ErrorLog.debug("VALID TO KEYWORD READ IN CONFIG"+"\n");
		}else if (temp.equals("0")) {
			ExceptionLoggingFunction("Valid_To");
		} else {
			ErrorLoggingFunction("Valid_To");
		}


		User_List.add("Days_Left");
		temp = prop.getProperty("Public_Key");
		if (temp.equals("1")) {
			User_List.add("Public_Key");
			ErrorLog.debug("PUBLIC KEY KEYWORD READ IN CONFIG"+"\n");
		}else if (temp.equals("0")) {
			ExceptionLoggingFunction("Public_Key");
		} else {
			ErrorLoggingFunction("Public_Key");
		}


		temp = prop.getProperty("Signature_Algo_Name");
		if (temp.equals("1")) {
			User_List.add("Signature_Algo_Name");
			ErrorLog.debug("SIGNATURE ALGO NAME KEYWORD READ IN CONFIG"+"\n");
		}else if (temp.equals("0")) {
			ExceptionLoggingFunction("Signature_Algo_Name");
		} else {
			ErrorLoggingFunction("Signature_Algo_Name");
		}


		temp = prop.getProperty("Signature");
		if (temp.equals("1")) {
			User_List.add("Signature");
			ErrorLog.debug("SIGNATURE KEYWORD READ IN CONFIG"+"\n");
		}else if (temp.equals("0")) {
			ExceptionLoggingFunction("Signature");
		} else {
			ErrorLoggingFunction("Signature");
		}


	}

	public String get_MSPKI_path() {
		return MSPKI_Path;
	}

	public String get_JAVA_path() {
		return JAVA_Path;
	}

	public boolean MSPKI() {
		return MSPKI;
	}

	public boolean JAVA() {
		return JAVA;
	}

	public ArrayList<String> get_User_Attributes() {

		return User_List;
	}

public String read_pass(String File_Name) throws IOException {
	ErrorLog.debug("IN read_pass FUNCTION OF READ CLASS"+"\n");
	String str = System.getProperty("user.dir");
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream(str+"\\certsinfo.properties");
		prop.load(input);
		String temp = prop.getProperty(File_Name);
		temp = decrypt(temp);
		return temp;

}

	public void ErrorLoggingFunction(String str) {
		ErrorLog.error(str + " keyword not set properly ---- please enter 0 or 1"
				+ "  (In class - File_Read_Class ---- In function - Read_User_Attributes() ---- )"+"\n");
	}

	public void ExceptionLoggingFunction(String str) {
		ExceptionLog.warn(str + " is set to 0"+"\n");
	}

	static String decrypt(String s) {
		if(s==null)
			return s;
		String t = "";
		int i = 0;
		while (i < s.length()) {
			t += (char) (s.charAt(i) - (i + 5) % 10);
			i++;

		}
		return t;
	}

//	static public void set_pass(String fileName, String pass) {
//		// TODO Auto-generated method stub
//		System.out.println(fileName+" -- "+pass);
//		hm.put(fileName, pass);
//		System.out.println(hm.get(fileName));
//	}
//	static public void printhm(){
//		System.out.println("PRINT HM");
//		System.out.println("SIZE OF HM---" + hm.size());
//		for(int i=0;i<hm.size();i++){
//			System.out.println(hm.get("johnnie.jks"));
//		}
//		System.out.println("END PRINT HM");
//	}
}

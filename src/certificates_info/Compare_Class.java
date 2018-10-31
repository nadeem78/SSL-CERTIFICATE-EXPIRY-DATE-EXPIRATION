package certificates_info;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class Compare_Class {

	ArrayList<Object[]> temp = new ArrayList<Object[]>();
	Set<String> hash_Set = new HashSet<String>();
	static Logger ExceptionLog = Logger.getRootLogger();
	static Logger ErrorLog = Logger.getLogger("logging");

	public Compare_Class(ArrayList<String> User_List, ArrayList<Attributes> List) {
		ErrorLog.debug("IN COMPARE CLASS"+"\n");
		Compare(User_List, List);
	}

	public void Compare(ArrayList<String> User_List, ArrayList<Attributes> List) {
		ErrorLog.debug("IN Compare FUNCTION OF COMPARE CLASS"+"\n");
		Object[] obj = new Object[25];
		obj[0] = new String("Serial Number");
		obj[1] = new String("Cert Name");
		for (int i = 0; i < User_List.size(); i++) {
			obj[i + 2] = new String(User_List.get(i));
			hash_Set.add(User_List.get(i));
		}
		temp.add(obj);

		for (int i = 0; i < List.size(); i++) {
			Object[] obj1 = new Object[25];
			obj1[0] = new Integer(i + 1);
			int j = 2;
			obj1[1]=new String(List.get(i).Get_Cert_Name());
			ErrorLog.debug("SETTING ATTRIBUTES FOR "+List.get(i).Get_Cert_Name()+"\n");
			if (hash_Set.contains("Version")) {
				obj1[j] = new Integer(List.get(i).Get_Version());
				j++;
			}

			if (hash_Set.contains("Path_Length")) {
				obj1[j] = new Integer(List.get(i).Get_Path_Length());
				j++;
			}

			if (hash_Set.contains("Serial_Number")) {
				obj1[j] = new String(List.get(i).Get_Serial_number().toString());
				j++;
			}
			if (hash_Set.contains("Valid_From")) {
				obj1[j] = new String(List.get(i).Get_Valid_From_Date().toString());
				j++;
			}

			if (hash_Set.contains("Valid_To")) {
				obj1[j] = new String(List.get(i).Get_Valid_To_Date().toString());
				j++;
			}
			
              obj1[j] = new Integer((int) List.get(i).Get_Days_Left());
              j++;
              
              
			if (hash_Set.contains("Public_Key")) {
				obj1[j] = new String(List.get(i).Get_Public_Key().toString());
				j++;
			}

			if (hash_Set.contains("Signature_Algo_Name")) {
				obj1[j] = new String(List.get(i).Get_Signature_Algo_Name());
				j++;
			}

			if (hash_Set.contains("Signature")) {
				obj1[j] = new String(List.get(i).Get_Signature().toString());
				j++;
			}

			temp.add(obj1);
		}
		ErrorLog.debug("SUCCESSFULLY ADDED USER ATTRIBUTES FOR GIVEN FILES"+"\n");

	}

	public ArrayList<Object[]> Get_Final_List() {
		return temp;
	}

}

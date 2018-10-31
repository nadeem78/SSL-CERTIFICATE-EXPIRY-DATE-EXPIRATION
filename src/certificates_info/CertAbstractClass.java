package certificates_info;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

public abstract class CertAbstractClass {
	private String path;
	private Date current_date = new Date();
	static Logger ExceptionLog = Logger.getRootLogger();
	static Logger ErrorLog = Logger.getLogger("logging");

	public abstract void get_Cert(String path);

	public void set_path(String path) {
		this.path = path;
	}

	public String get_path() {
		return path;
	}

	public Date get_CurrentDate() {
		return current_date;
	}

	public ArrayList<FileInputStream> get_file(String path) {
		ErrorLog.debug("IN get_file FUNCTON OF CertAbstract CLASS"+"\n");
		ArrayList<FileInputStream> Array_list = new ArrayList<FileInputStream>();
		File folder = new File(path);
		if (!folder.exists()) {
			ErrorLog.error("folder does not exists at path -- " + path+"\n");
		} else {
			ErrorLog.debug("FILE EXISTS"+"\n");
		}

		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			FileInputStream inStream = null;
			if (listOfFiles[i].isFile()) {
				try {
					inStream = new FileInputStream(listOfFiles[i]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					ErrorLog.error(e + " error (Error in CertAbstractClass) (Error in reading file)"+"\n");
				}
				Array_list.add(inStream);
			}

		}
		ErrorLog.debug("LIST OF FILES SUCCESSFULLY CONVERTED TO FILE INPUT STREAM"+"\n");
		return Array_list;
	}

	public ArrayList<String> get_pass(String Path) {
		ErrorLog.debug("IN get_PASS FUNCTON OF CertAbstract CLASS"+"\n");
		ArrayList<String> Pass_Set = new ArrayList<String>();
		File folder = new File(Path);
		if (!folder.exists()) {
			ErrorLog.error("folder does not exists at path -- " + Path+"\n");
		}
		File[] listOfFiles = folder.listFiles();
		// System.out.println(listOfFiles.length);
		for (int i = 0; i < listOfFiles.length; i++) {
			File_Read_Class read = new File_Read_Class();
			String Pass = null;
			try {
				Pass = read.read_pass(listOfFiles[i].getName());
				ErrorLog.debug("PASSWORD FOR " + listOfFiles[i].getName() + " READ SUCCESSFULLY"+"\n");
			} catch (Exception e) {
				ErrorLog.error(e + " error in " + listOfFiles[i].getName()
						+ " (Error in CertAbstract Class) (Error in reading read_pass(listOfFiles[i].getName()))"+"\n");
			}
			Pass_Set.add(Pass);
		}
		ErrorLog.debug(" ALL PASSWORD READ SUCCESSFULLY"+"\n");
		return Pass_Set;
	}

	public ArrayList<String> get_Cert_Names(String path) {
		ErrorLog.debug("IN get_Cert_Names FUNCTON OF CertAbstract CLASS"+"\n");
		ArrayList<String> Cert_Names = new ArrayList<String>();
		File folder = new File(path);
		if (!folder.exists()) {
			ErrorLog.error(
					"error in Certabstract class get_cert function--------No file found at given location->  " + path+"\n");
		}
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {

				Cert_Names.add(listOfFiles[i].getName());

			}

		}
		ErrorLog.debug("LIST OF CERTS NAME SUCCESSFULLY CREATED"+"\n");
		return Cert_Names;
	}

	public Attributes get_Attributes(X509Certificate cert, String Cert_Name) {
		ErrorLog.debug("IN get_Attributes FUNCTON OF CertAbstract CLASS"+"\n");
		Attributes AttributeObject = new Attributes();
		AttributeObject.Set_Path_Length(cert.getBasicConstraints());
		//AttributeObject.Set_Path_Length(1);
		AttributeObject.Set_Version(cert.getVersion());
		//AttributeObject.Set_Version(1);
		AttributeObject.Set_Serial_number(cert.getSerialNumber().abs());
		AttributeObject.Set_Valid_From_Date(cert.getNotBefore());
		AttributeObject.Set_Valid_To_Date(cert.getNotAfter());
		AttributeObject.Set_Signature_Algo_Name(cert.getSigAlgName());
		AttributeObject.Set_Signature(cert.getSignature());
		AttributeObject.Set_Public_Key(cert.getPublicKey());
		AttributeObject.Set_Cert_Name(Cert_Name);
		ErrorLog.debug("ATTRIBUTES FOR " + Cert_Name + " EXTRACTED PROPERLY"+"\n");
		System.out.println("attributes extracted");
		return AttributeObject;

	}

}

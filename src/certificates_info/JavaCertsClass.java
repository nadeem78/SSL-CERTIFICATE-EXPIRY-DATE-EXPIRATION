package certificates_info;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;

public class JavaCertsClass extends CertAbstractClass {

	private ArrayList<Attributes> list = new ArrayList<Attributes>();

	public JavaCertsClass(String path) {
		ErrorLog.debug("IN JavaCerts CLASS \n");
		get_Cert(path);
	}

	@Override
	public void get_Cert(String path) {
		ErrorLog.debug("IN get_Cert FUNCTON OF JavaCerts CLASS \n");
		ArrayList<FileInputStream> listOfFiles = get_file(path);
		ArrayList<String> Pass_list = get_pass(path);
		ArrayList<String> Cert_Names = get_Cert_Names(path);
		for (int i = 0; i < listOfFiles.size(); i++) {
			FileInputStream inStream = listOfFiles.get(i);

			KeyStore keystore = null;
			try {
				keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			} catch (KeyStoreException e) {
				ErrorLog.error(e + " error (Error in JAVA Class) (KeyStore.getInstance(KeyStore.getDefaultType())) \n");
			}
			try {
				if (Pass_list.get(i) == null) {
					ErrorLog.error("No Password found for  " + Cert_Names.get(i).toString() + " add password for  "
							+ Cert_Names.get(i).toString() +"\n");
					continue;
				}
				
				keystore.load(inStream, Pass_list.get(i).toCharArray());
				ErrorLog.debug("PASSWORD WAS CORRECT FOR "+ Cert_Names.get(i)+"\n");
			} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
				ErrorLog.error(e + "you may have entered a wrong password for " + Cert_Names.get(i)+"\n");
				continue;
			}
			Enumeration<String> aliases = null;
			try {
				aliases = keystore.aliases();
			} catch (KeyStoreException e) {
				ErrorLog.error(e + " error (Error in JAVA Class) ( keystore.aliases())"+"\n");
			}
			
			while (aliases.hasMoreElements()) {
				String alias = aliases.nextElement();
				Attributes AttributeObject = null;
				try {
					AttributeObject = get_Attributes(((X509Certificate) keystore.getCertificate(alias)),
							Cert_Names.get(i));
					ErrorLog.debug("NO ERROR IN THE CERTIFICATE "+ Cert_Names.get(i)+"\n");
				} catch (KeyStoreException e) {
					ErrorLog.error(e
							+ " error (Error in JAVA Class) ( get_Attributes(((X509Certificate) keystore.getCertificate(alias)))"+"\n");
				}
				// if(AttributeObject.Get_Days_Left()<=30)
				list.add(AttributeObject);

			}
		}
	}

	public ArrayList<Attributes> get_ArrayList_Of_Attributes() {
		return list;
	}
}

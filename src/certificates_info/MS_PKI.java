package certificates_info;

import java.io.FileInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class MS_PKI extends CertAbstractClass {
	
	private ArrayList<Attributes> list = new ArrayList<Attributes>();
	static Logger ExceptionLog = Logger.getRootLogger();
	static Logger ErrorLog = Logger.getLogger("logging");

	public MS_PKI(String Path) {
		ErrorLog.debug("IN MSPKI CLASS"+"\n");
		get_Cert(Path);
	}

	@Override
	public void get_Cert(String path) {
		ErrorLog.debug("IN get_Cert FUNCTION"+"\n");
		ArrayList<FileInputStream> inStream_list = get_file(path);
		ArrayList<String> Cert_Names = get_Cert_Names(path);
		for (int i = 0; i < inStream_list.size(); i++) {
			CertificateFactory cf = null;
			try {
				cf = CertificateFactory.getInstance("X.509");
			} catch (CertificateException e) {
				ErrorLog.error(e+" error (Error in MSPKI Class) (Error in reading CertificateFactory.getInstance(X.509))"+"\n");
			}
			X509Certificate cert = null;
			try {
				cert = (X509Certificate) cf.generateCertificate(inStream_list.get(i));
				ErrorLog.debug(Cert_Names.get(i)+" SUCCESSFULLY CREATED INTO X509CERTIFICATE"+"\n");
			} catch (CertificateException e) {
				ErrorLog.error(e+" error (Error in MSPKI Class) (X509Certificate) cf.generateCertificate(inStream_list.get(i))"+"\n");
			}
			Attributes Attribute_Object = get_Attributes(cert,Cert_Names.get(i));
			// if(Attribute_Object.Get_Days_Left()<=30)
			list.add(Attribute_Object);
		}
	}

	public ArrayList<Attributes> get_ArrayList_Of_Attributes() {
		return list;

	}
}

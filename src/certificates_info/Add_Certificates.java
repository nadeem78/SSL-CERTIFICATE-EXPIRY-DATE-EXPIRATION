package certificates_info;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;

public class Add_Certificates {

	public static void main(String[] args) {
		String str = System.getProperty("user.dir");
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(str + "\\certsinfo.properties");
			// load a properties file
			prop.load(input);

		} catch (IOException ex) {
			System.out.println(ex + " error");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println(e + " error");
				}
			}
		}

		Properties prop1 = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(str + "\\certsinfo.properties");
			prop.store(output, null);
			// set the properties value
			System.out.println("Enter number of certificates");
			Scanner in = new Scanner(System.in);
			int num = in.nextInt();
			for (int i = 0; i < num; i++) {
				System.out.println("Enter name of certificates");
				String certificate = in.next();
				System.out.println("Enter password for " + certificate);
				String password = in.next();
				prop1.setProperty(certificate, encrypt(password));
			}
			in.close();

			prop1.store(output, null);

		} catch (IOException io) {
			System.out.println(io + " error");
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					System.out.println(e + " error");
				}
			}

		}

	}

	static String encrypt(String s) {
		String t = "";
		int i = 0;
		while (i < s.length()) {
			t += (char) (s.charAt(i) + (i + 5) % 10);
			i++;

		}

		return t;
	}

}

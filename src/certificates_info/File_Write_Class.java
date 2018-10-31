package certificates_info;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.log4j.*;
public class File_Write_Class {
	
	static Logger ExceptionLog = Logger.getRootLogger();
	static Logger ErrorLog = Logger.getLogger("logging");
 
	
	public File_Write_Class(ArrayList<Object[]> list) {
		ErrorLog.debug("IN WRITE CLASS"+"\n");
		write_excel(list);
	}
	
	public static void write_excel(ArrayList<Object[]> list){
		ErrorLog.debug("IN write_excel FUNCTION OF WRITE CLASS"+"\n");
		// Blank workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
 
        // Create a blank sheet
        HSSFSheet sheet = workbook.createSheet("Certificates Details");
 
        // This data needs to be written (Object[])
        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        int k=1;
        for(int i=0;i<list.size();i++){
        	data.put(k++,list.get(i));
        }
        ErrorLog.debug("SUCCESSFULLY ADDED CERTIFICATE OBJECTS IN HASHMAP"+"\n");
 
        // Iterate over data and write to sheet
        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        for (Integer key : keyset) {
            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // this line creates a cell in the next column of that row
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String)obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        ErrorLog.debug("SUCCESSFULLY ADDED INFO IN SHEET"+"\n");
            FileOutputStream out = null;
			try {
				out = new FileOutputStream(new File("gahlawat.xls"));
				ErrorLog.debug("SUCCESSFULLY CREATED EXCEL FILE"+"\n");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				 ErrorLog.error("File Not Found (Excel file not found) (Error in File_Write_Class)"+"\n");
			}
            try {
				workbook.write(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ErrorLog.error("IOException (Error in workbook.write() func) (Error in File_Write_Class"+"\n");
			}
            try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ErrorLog.error("IOException (Error in out.close() func) (Error in File_Write_Class"+"\n");
			}
            ErrorLog.debug("FILE WRITTEN SUCCESSFULLY ON DISK"+"\n");
        
	}
}



package certificates_info;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Date;

public class Attributes {
	
      private int Version;
      
      private int Path_Length;
      
      private BigInteger Serial_Number;
      
      private Date Valid_From;
      
      private Date Valid_To;
      
      private long Days_Left;
      
      private String Signature_Algo_Name;
      
      private byte[] Signature;
      
      private PublicKey Public_Key;
      
      private String Cert_Name;
      
      public void Set_Version(int Version)
      {
            this.Version=Version;
      }
      
      public void Set_Path_Length(int Path_Length)
      {
            this.Path_Length=Path_Length;
      }
      
      public void Set_Serial_number(BigInteger Serial_Number)
      {
            this.Serial_Number=Serial_Number;
      }
      public void Set_Valid_From_Date(Date Valid_From)
      {
            this.Valid_From=Valid_From;
      }
      public void Set_Valid_To_Date(Date Valid_To)
      {
            this.Valid_To=Valid_To;
            Days_Left = Valid_To.getTime() - (new Date()).getTime();
            Days_Left = Days_Left/(24 * 60 * 60 * 1000);
      }
      public void Set_Signature_Algo_Name(String Signature_Algo_Name)
      {
            this.Signature_Algo_Name=Signature_Algo_Name;
      }
      public void Set_Signature(byte[] Signature)
      {
            this.Signature=Signature;
      }
      public void Set_Public_Key(PublicKey Public_Key)
      {
            this.Public_Key=Public_Key;
      }
      public int Get_Version()
      {
            return Version;
      }
      
      public int  Get_Path_Length()
      {
            return Path_Length;
      }
      
      public BigInteger Get_Serial_number()
      {
            return Serial_Number;
      }
      public Date Get_Valid_From_Date()
      {
            return Valid_From;
      }
      public Date Get_Valid_To_Date()
      {
            return Valid_To;
      }
      public String Get_Signature_Algo_Name()
      {
            return Signature_Algo_Name;
      }
      public byte[] Get_Signature()
      {
            return Signature;
      }
      public PublicKey Get_Public_Key()
      {
            return Public_Key;
      }
      public long Get_Days_Left(){
    	  return Days_Left;
      }

	public void Set_Cert_Name(String Cert_Name) {
		
		this.Cert_Name=Cert_Name;
	}

	public String Get_Cert_Name() {
		// TODO Auto-generated method stub
		return this.Cert_Name;
	}

	
	
     
}



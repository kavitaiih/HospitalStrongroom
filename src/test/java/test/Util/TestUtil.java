package test.Util;

import java.util.ArrayList;

import com.excel.reader.Xls_Reader;

public class TestUtil {
static Xls_Reader reader;
	
	public static ArrayList<Object[]> DataTransferInWithPatient(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("Sheet4"); rownum++) {
			 String Medication_name = reader.getCellData("Sheet4", "Medication name", rownum);
			 String Patient_Name = reader.getCellData("Sheet4", "Patient Name", rownum);
			 String Prescriber_Name = reader.getCellData("Sheet4", "Prescriber Name", rownum);
			 String Quantity = reader.getCellData("Sheet4", "Quantity", rownum);
			 String Current_Stock = reader.getCellData("Sheet4", "Current Stock", rownum);
			 String Total_Balance = reader.getCellData("Sheet4", "Total Balance", rownum);
			 Object ab[]= {Medication_name, Patient_Name, Prescriber_Name, Quantity, Current_Stock, Total_Balance};
			 myData.add(ab);
			 
			 
		 }
		 return myData;
	}
	public static ArrayList<Object[]> credantials(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/LoginCred.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("TransferInWithPatient"); rownum =2) {
			 String URL = reader.getCellData("Credentials", "URL", rownum);
			 String Location = reader.getCellData("Credentials", "Location", rownum);
			 String UserName = reader.getCellData("Credentials", "UserName", rownum);
			 String Password = reader.getCellData("Credentials", "Password", rownum);
			 Object ab[]= {URL, Location, UserName, Password};
			 myData.add(ab);
			 
		 }
		 return myData;
	}
}

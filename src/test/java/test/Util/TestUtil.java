package test.Util;

import java.util.ArrayList;

import com.excel.reader.Xls_Reader;

public class TestUtil {
public static Xls_Reader reader;
	
	public static ArrayList<Object[]> DataTransferInWithPatient(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("Transfer In With Patient"); rownum++) {
			 String Medication_name = reader.getCellData("Transfer In With Patient", "Medication name", rownum);
			 String Patient_Name = reader.getCellData("Transfer In With Patient", "Patient Name", rownum);
			 String Prescriber_Name = reader.getCellData("Transfer In With Patient", "Prescriber Name", rownum);
			 String Quantity = reader.getCellData("Transfer In With Patient", "Quantity", rownum);
			 String Current_Stock = reader.getCellData("Transfer In With Patient", "Current Stock", rownum);
			 String Total_Balance = reader.getCellData("Transfer In With Patient", "Total Balance", rownum);
			 String pin = reader.getCellData("Transfer In With Patient", "PIN", rownum);
			 String note =reader.getCellData("Transfer In With Patient", "Note", rownum);
			 String location =reader.getCellData("Transfer In With Patient", "Location", rownum);
			 String Status =reader.getCellData("Transfer In With Patient", "Status", rownum);
			 Object ab[]= {Medication_name, Patient_Name, Prescriber_Name, Quantity, Current_Stock, Total_Balance, pin, note,location,Status};
			 myData.add(ab);
			 
			 
		 }
		 return myData;
	}
	
	public static boolean writeDataToExcelTransferInWithPatient(int rownum, String currentStock, String sum, String Status) {
        try {
            // Assuming the Excel file path is the same as the one used for reading
            reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");

            // Update Quantity, Current Stock, and Total Balance in the Excel file
            reader.setCellData("Transfer In With Patient", "Current Stock", rownum, currentStock);
            reader.setCellData("Transfer In With Patient", "Total Balance", rownum, sum);
            reader.setCellData("Transfer In With Patient", "Status", rownum, Status);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	public static ArrayList<Object[]> TransferoutImprestFromExcelSheet(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("Transfer out Imprest"); rownum++) {
			 String Medication_name = reader.getCellData("Transfer out Imprest", "Medication name", rownum);
			 String Quantity = reader.getCellData("Transfer out Imprest", "Quantity", rownum);
			 String Current_Stock = reader.getCellData("Transfer out Imprest", "Current Stock", rownum);
			 String Remaining_Balance = reader.getCellData("Transfer out Imprest", "Remaining Balance", rownum);
			 String pin = reader.getCellData("Transfer out Imprest", "PIN", rownum);
			 String note =reader.getCellData("Transfer out Imprest", "Note", rownum);
			 String location =reader.getCellData("Transfer out Imprest", "Location", rownum);
			 String Status =reader.getCellData("Transfer out Imprest", "Status", rownum);
			 Object ab[]= {Medication_name, Quantity, Current_Stock, Remaining_Balance,pin,note,location,Status };
			 myData.add(ab);
			 
			 
		 }
		 return myData;
	}
	
	public static boolean writeDataTransferoutImprestFromExcelSheet(int rownum, String currentStock, String RemainingAsString, String Status) {
        try {
            // Assuming the Excel file path is the same as the one used for reading
            reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");

            // Update Quantity, Current Stock, and Total Balance in the Excel file
            reader.setCellData("Transfer out Imprest", "Current Stock", rownum, currentStock);
            reader.setCellData("Transfer out Imprest", "Remaining Balance", rownum, RemainingAsString);
           reader.setCellData("Transfer out Imprest", "Status", rownum, Status);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public static ArrayList<Object[]> TRansferINImprest(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("TRansfer IN Imprest"); rownum++) {
			 String Medication_name = reader.getCellData("TRansfer IN Imprest", "Medication name", rownum);
			 String Quantity = reader.getCellData("TRansfer IN Imprest", "Quantity", rownum);
			 String Current_Stock = reader.getCellData("TRansfer IN Imprest", "Current Stock", rownum);
			 String Total_Balance = reader.getCellData("TRansfer IN Imprest", "Total Balance", rownum);
			 String pin = reader.getCellData("TRansfer IN Imprest", "PIN", rownum);
			 String note =reader.getCellData("TRansfer IN Imprest", "Note", rownum);
			 String location =reader.getCellData("TRansfer IN Imprest", "Location", rownum);
			 String Status =reader.getCellData("TRansfer IN Imprest", "Status", rownum);
			 Object ab[]= {Medication_name, Quantity, Current_Stock, Total_Balance,pin,note,location,Status };
			 myData.add(ab);
			 
			 
		 }
		 return myData;
	}
	
	public static boolean writeDataTRansferINImprest(int rownum, String Stock, String SumAsString, String Status) {
        try {
            // Assuming the Excel file path is the same as the one used for reading
            reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");

            // Update Quantity, Current Stock, and Total Balance in the Excel file
            reader.setCellData("TRansfer IN Imprest", "Current Stock", rownum, Stock);
            reader.setCellData("TRansfer IN Imprest", "Total Balance", rownum, SumAsString);
           reader.setCellData("TRansfer IN Imprest", "Status", rownum, Status);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	
	public static ArrayList<Object[]> TRnasferoutPATIENT(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("TRnasfer out PATIENT"); rownum++) {
			 String Medication_name = reader.getCellData("TRnasfer out PATIENT", "Medication name", rownum);
			 String Patient_Name = reader.getCellData("TRnasfer out PATIENT", "Patient Name", rownum);
			 String Prescriber_Name = reader.getCellData("TRnasfer out PATIENT", "Prescriber Name", rownum);
			 String Quantity = reader.getCellData("TRnasfer out PATIENT", "Quantity", rownum);
			 String Current_Stock = reader.getCellData("TRnasfer out PATIENT", "Current Stock", rownum);
			 String Remaining_Balance = reader.getCellData("TRnasfer out PATIENT", "Remaining Balance", rownum);
			 String pin = reader.getCellData("TRnasfer out PATIENT", "PIN", rownum);
			 String note =reader.getCellData("TRnasfer out PATIENT", "Note", rownum);
			 String location =reader.getCellData("TRnasfer out PATIENT", "Location", rownum);
			 String Status =reader.getCellData("TRnasfer out PATIENT", "Status", rownum);
			 Object ab[]= {Medication_name, Patient_Name, Prescriber_Name, Quantity, Current_Stock, Remaining_Balance, pin, note, location, Status };
			 myData.add(ab);
			 
			 
		 }
		 return myData;
	}
	
	public static boolean writeDataTRnasferoutPATIENT(int rownum, String Stock, String SumAsString, String Status) {
        try {
            // Assuming the Excel file path is the same as the one used for reading
            reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");

            // Update Quantity, Current Stock, and Total Balance in the Excel file
            reader.setCellData("TRnasfer out PATIENT", "Current Stock", rownum, Stock);
            reader.setCellData("TRnasfer out PATIENT", "Remaining Balance", rownum, SumAsString);
           reader.setCellData("TRnasfer out PATIENT", "Status", rownum, Status);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	public static ArrayList<Object[]> Destruction(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("Destruction"); rownum++) {
			 String Medication_name = reader.getCellData("Destruction", "Medication name", rownum);
			 String Patient_Name = reader.getCellData("Destruction", "Patient Name", rownum);
			 String Prescriber_Name = reader.getCellData("Destruction", "Prescriber Name", rownum);
			 String Quantity = reader.getCellData("Destruction", "Quantity", rownum);
			 String Current_Stock = reader.getCellData("Destruction", "Current Stock", rownum);
			 String Remaining_Balance = reader.getCellData("Destruction", "Remaining Balance", rownum);
			 String pin = reader.getCellData("Destruction", "PIN", rownum);
			 String note =reader.getCellData("Destruction", "Note", rownum);
			 String location =reader.getCellData("Destruction", "Location", rownum);
			 String Status =reader.getCellData("Destruction", "Status", rownum);
			 Object ab[]= {Medication_name, Patient_Name, Prescriber_Name, Quantity, Current_Stock, Remaining_Balance, pin, note, location, Status };
			 myData.add(ab);
			 
			 
		 }
		 return myData;
	}
	
	public static boolean writeDataDestruction(int rownum, String Stock, String SumAsString, String Status) {
        try {
            // Assuming the Excel file path is the same as the one used for reading
            reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");

            // Update Quantity, Current Stock, and Total Balance in the Excel file
            reader.setCellData("Destruction", "Current Stock", rownum, Stock);
            reader.setCellData("Destruction", "Remaining Balance", rownum, SumAsString);
           reader.setCellData("Destruction", "Status", rownum, Status);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	public static ArrayList<Object[]> OtgoingImprest(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("Outgoing imprest"); rownum++) {
			 String Medication_name = reader.getCellData("Outgoing imprest", "Medication name", rownum);
			 String note = reader.getCellData("Outgoing imprest", "Note", rownum);
			 String quantity = reader.getCellData("Outgoing imprest", "Quantity", rownum);
			 String pin = reader.getCellData("Outgoing imprest", "PIN", rownum);
			 Object ab[]= {Medication_name, note, quantity, pin};
			 myData.add(ab);
			 
			 
		 }
		 return myData;
	}
	
	
	public static boolean writeDataOtgoingImprest(int rownum, String Stock, String modifiedString1, String Status) {
        try {
            // Assuming the Excel file path is the same as the one used for reading
            reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");

            // Update Quantity, Current Stock, and Total Balance in the Excel file
            reader.setCellData("Outgoing imprest", "Current Stock", rownum, Stock);
            reader.setCellData("Outgoing imprest", "Remaining Balance", rownum, modifiedString1);
           reader.setCellData("Outgoing imprest", "Status", rownum, Status);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	public static ArrayList<Object[]> outgoingpatient(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("Outgoing Patient"); rownum++) {
			 String Medication_name = reader.getCellData("Outgoing Patient", "Medication name", rownum);
			 String Patient_Name = reader.getCellData("Outgoing Patient", "Patient Name", rownum);
			 String note = reader.getCellData("Outgoing Patient", "Note", rownum);
			 String quantity = reader.getCellData("Outgoing Patient", "Quantity", rownum);
			 String pin = reader.getCellData("Outgoing Patient", "PIN", rownum);
			 Object ab[]= {Medication_name, Patient_Name, note, quantity, pin};
			 myData.add(ab);
			 
			 
		 }
		 return myData;
	}
	

	public static boolean writeDataoutgoingpatient(int rownum, String Stock, String modifiedString1, String Status) {
        try {
            // Assuming the Excel file path is the same as the one used for reading
            reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");

            // Update Quantity, Current Stock, and Total Balance in the Excel file
            reader.setCellData("Outgoing Patient", "Current Stock", rownum, Stock);
            reader.setCellData("Outgoing Patient", "Remaining Balance", rownum, modifiedString1);
           reader.setCellData("Outgoing Patient", "Status", rownum, Status);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	public static ArrayList<Object[]> destruction(){
		ArrayList<Object[]> myData = new  ArrayList<Object[]>();
		 try {
			 reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 for(int rownum=2; rownum<= reader.getRowCount("New Destruction Imprest"); rownum++) {
			 String Medication_name = reader.getCellData("New Destruction Imprest", "Medication name", rownum);
			 String note = reader.getCellData("New Destruction Imprest", "Note", rownum);
			 String quantity = reader.getCellData("New Destruction Imprest", "Quantity", rownum);
			 String pin = reader.getCellData("New Destruction Imprest", "PIN", rownum);
			 Object ab[]= {Medication_name, note, quantity, pin};
			 myData.add(ab);
			 
			 
		 }
		 return myData;
	}
	
	
	public static boolean writeDatadestruction(int rownum, String Stock, String modifiedString1, String Status) {
        try {
            // Assuming the Excel file path is the same as the one used for reading
            reader = new Xls_Reader("C:/Users/bhako/Downloads/file.xlsx");

            // Update Quantity, Current Stock, and Total Balance in the Excel file
            reader.setCellData("New Destruction Imprest", "Current Stock", rownum, Stock);
            reader.setCellData("New Destruction Imprest", "Remaining Balance", rownum, modifiedString1);
           reader.setCellData("New Destruction Imprest", "Status", rownum, Status);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

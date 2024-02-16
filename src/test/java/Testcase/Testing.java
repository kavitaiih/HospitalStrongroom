package Testcase;

import java.util.ArrayList;
import java.util.Iterator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.Util.TestUtil;

public class Testing extends Base {

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = TestUtil.transferinImprest();
		return testData.iterator();

	} 

	@Test(dataProvider = "getTestData")
	public void automation(String action, String Reference_Number,  String location, String Medication_name, String transaction_id, String Patient_Name, String Prescriber_Name,
			String Quantity, String note, String username, String pin, String username1, String pin1) throws InterruptedException {

		if ("Transfer in Patient".equals(action)) {
			TransferinPatient.transferwithpatient(action, location, note, Medication_name,  Patient_Name, Prescriber_Name, Quantity,  username,pin);
		} 
		else if ("Transfer in Imprest".equals(action)) {
			TRansferINImprest.transferInIpmrest(action, location, note, Medication_name,  Quantity, username, pin);
		} 
		else if ("Transfer out Patient".equals(action)) {
			TrnasferOutpatient.transferoutpatient(action, location, note,  Patient_Name, Prescriber_Name,Medication_name,  Quantity, username, pin);
		} 
		else if ("Transfer out Imprest".equals(action)) {
			TransferoutImprest.transferOutImprest(action, location, note, Medication_name,  Quantity, username, pin );
		} 
		else if ("Destruction Patient".equals(action)) {
			DestructionPatient.destructionPatient(action, location, Medication_name, transaction_id, note, Patient_Name,Prescriber_Name, Quantity, username, pin);
		} 
		else if ("Destruction imprest".equals(action)) {
			DestructionImprest.DestructionIMPREST(action, note, Medication_name,  Quantity,  username, pin);
		} 
		else if ("Outgoing Patient".equals(action)) {
			OutgoingPatient.outgoingpatient(action, Medication_name, transaction_id, Patient_Name, note, Quantity, username, pin);
		} 
		else if ("Outgoing imprest".equals(action)) {
			Outgoingimprest.outgoingimprest(action, Medication_name, transaction_id, Quantity, note, username, pin);
		} 
		else if ("Dispensing".equals(action)) {
		    Dispensing.Dispensingmedication(action, Reference_Number, note, Patient_Name, Prescriber_Name, Medication_name, Quantity, username, pin);
		} 
		else if ("Adjustment Imprest".equals(action)) {
			AdjustmentWithImprest.AdjustmentWITHimprest(action, transaction_id, note, Medication_name, Quantity, username, pin, username1, pin1);
		} 
		else if ("Adjustment Patient".equals(action)) {
			AdjustmentwithPatient.AdjustmentWITHpatient(action, transaction_id, note,Patient_Name, Medication_name, Quantity, username, pin, username1, pin1);	
		} 
		else if ("Requisition Imprest".equals(action)) {
			RequisitionImprest.RequisitionwithImprest(action, location,note, Medication_name, Quantity, username, pin,username1, pin1);	
		} else {
			System.out.println("No data");
		}
	}
}
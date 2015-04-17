package com.tsa.puridiom.requisitionheader.tasks;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.tsa.puridiom.entity.RequisitionHeader;

public class RequisitionHeaderDeserializeMe
{
	public static void main(String[] args)
	{
		   FileInputStream fIn=null;
		   ObjectInputStream oIn=null;

		   try
		   {
		    fIn= new FileInputStream("c:\\RequisitionHeader.ser");
		    oIn = new ObjectInputStream(fIn);

		    //de-serializing reqHeader
		    RequisitionHeader rqh = (RequisitionHeader) oIn.readObject();

		    System.out.println("Deserialized " + rqh.getRequisitionNumber() + " " + rqh.getUdf2Code() + " from NewEmployee.ser ");
		   }
		   catch(IOException e)
		   {
			  e.printStackTrace();
		   }catch(ClassNotFoundException e){
		        e.printStackTrace();
		   }finally{
			try {
		        oIn.close();
			  fIn.close();
			} catch (IOException e1) {
			  e1.printStackTrace();
			}
		   }
		 }

}

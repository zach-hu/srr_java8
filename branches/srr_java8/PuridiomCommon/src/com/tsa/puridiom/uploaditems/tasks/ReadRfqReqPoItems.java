package com.tsa.puridiom.uploaditems.tasks;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

//import javax.servlet.ServletException;

public class ReadRfqReqPoItems extends Task
{
	String FileNameXLS=null;
	String Path=null;
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			FileNameXLS = (String) incomingRequest.get("FilenameXls");
			//FileNameXLS = "Req_Rfq_PO_Upload";
			Path = DictionaryManager.getInstance("host", organizationId).getProperty("internal-document-path", "/");

			Bid_XLS Excel=new Bid_XLS();
			result=Excel.RfqReqPoReadFileXls();

			this.status = Status.SUCCEEDED;

		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}

	public class Bid_XLS {

	    public Bid_XLS()
	    {

	    }

//*************************************** Multiplo 4 **************************************************//

		public int multiploCuatro (int num)
		{
			if (num %4==0)
			{return num;}
			else
			{return multiploCuatro(num+1);}
		}


//*************************************** UpLoad Files XLS *********************************************************//

		public List RfqReqPoReadFileXls()
		{
			List listReadRpta=new ArrayList();
	  		try {
				FileInputStream fileIn= new FileInputStream(Path+"/"+FileNameXLS);
	  			POIFSFileSystem fs = new POIFSFileSystem(fileIn);
		        HSSFWorkbook wb = new HSSFWorkbook(fs);

		        HSSFSheet Hoja= wb.getSheetAt(0);

		        int numFilas = Hoja.getLastRowNum();
		        Log.debug(this, "Hoja Last Num Row= "+Hoja.getLastRowNum());
		        Log.debug(this, "NumFilas: "+ numFilas);

		        for(int fil=2; fil<=numFilas; fil++)
		        {
		        HSSFRow Row = Hoja.getRow(fil);
		        List listRow=new ArrayList();

		        for(int col=0; col<5; col++)
				  {
					if(Row.getCell((short)col)!=null)
					{
		        	HSSFCell CellRow = Row.getCell((short)col);
		        	Log.debug(this, "Posicion" +fil +","+col +"= "+CellRow.getCellType());

					if(CellRow.getCellType()==3)
					{break;	}
					if (CellRow.getCellType()==1)
					{listRow.add(CellRow.getStringCellValue());
					Log.debug(this, "Valor"+ fil+";"+col+ ":= " +CellRow.getStringCellValue());}
					if (CellRow.getCellType()==0)
					{listRow.add(String.valueOf((new Double(CellRow.getNumericCellValue())).intValue()));
					Log.debug(this, "Valor"+ fil+";"+col+ ":= " +Double.toString(CellRow.getNumericCellValue()));}
			        }
					else{listRow.add("0");}
				  }
		          listReadRpta.add(listRow);

		        }

	  		 }
		        catch (Exception e) {
		        Log.debug(this, "Error de Lectura: " + e);

			 }

		 return listReadRpta;
		}

  }


}
/**
 * 
 */
package com.tsa.puridiom.xlsdata.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.xlsdata.XlsDataType;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class XlsDataReadFile extends Task
{
	/*
	 * (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			File xlsFile = (File) incomingRequest.get("xlsFile");
			List labels = (List) incomingRequest.get("XlsData_labels");
			List types = (List) incomingRequest.get("XlsData_types");

			result = this.getDataFromXlsFile(xlsFile, labels, types);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "XlsDataReadFile error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private List getDataFromXlsFile(File xlsFile, List labels, List types) throws Exception
	{
		List xlsData = new ArrayList();
		FileInputStream fileIn = new FileInputStream(xlsFile);
		POIFSFileSystem fs = new POIFSFileSystem(fileIn);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		int[] firstCoordinates = this.getFirstCoordinates(sheet, labels);

		Log.debug(this, "Sheet, Last Num Row = " + sheet.getLastRowNum());

		for (int i = firstCoordinates[0]; i <= sheet.getLastRowNum(); i++)
		{
			HSSFRow row = sheet.getRow(i);
			List xlsRowData = new ArrayList();

			for (int j = firstCoordinates[1]; j < (types.size() + firstCoordinates[1]); j++)
			{
				HSSFCell cell = row.getCell((short) j);

				if (cell != null)
				{
					String cellData = "0";
					String columnType = (String) types.get(j - firstCoordinates[1]);

					switch (cell.getCellType())
					{
						case HSSFCell.CELL_TYPE_STRING:
							cellData = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							int scale = 2;
							
							if (columnType.equalsIgnoreCase(XlsDataType.STRING) || columnType.equalsIgnoreCase(XlsDataType.INTEGER))
							{
								scale = 0;
							}
							
							cellData = new BigDecimal(cell.getNumericCellValue()).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
					}

					Log.debug(this, "Position in Sheet " + i + "," + j + " - cellType: " + cell.getCellType() + " - cellValue: " + cellData);

					xlsRowData.add(cellData);
				} else
				{
					xlsRowData.add("0");
				}
			}
			xlsData.add(xlsRowData);
		}

		return xlsData;
	}

	private int[] getFirstCoordinates(HSSFSheet sheet, List labels)
	{
		int[] coordinates = { 0, 0 };
		String firstLabel = (String) labels.get(0);
		boolean firstCell = false;

		for (int i = 0; i <= sheet.getLastRowNum(); i++)
		{
			HSSFRow row = sheet.getRow(i);

			if (row != null)
			{
				for (int j = 0; j < labels.size(); j++)
				{
					HSSFCell cell = row.getCell((short) j);

					if (cell != null)
					{
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
						{
							String cellValue = cell.getStringCellValue().trim();

							if ((!HiltonUtility.isEmpty(cellValue)) && cellValue.equalsIgnoreCase(firstLabel))
							{
								coordinates[1] = j;
								firstCell = true;
								break;
							}
						}
					}
				}

				if (firstCell)
				{
					coordinates[0] = i + 1;
					break;
				}
			}
		}

		Log.debug(this, "First coordinates for Sheet, x = " + coordinates[0] + ", y = " + coordinates[0]);

		return coordinates;
	}

}
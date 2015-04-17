/**
 *
 */
package com.tsa.puridiom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.etymon.pjx.PdfInputFile;
import com.etymon.pjx.PdfManager;
import com.etymon.pjx.PdfReader;
import com.etymon.pjx.PdfWriter;
import com.etymon.pjx.util.PdfAppender;


/**
 * @author renzo
 *
 */
public class MergePdfs {

	public static void merge(String outFile, List pdfList)
	{
		PdfWriter pdfWriter = null;
		PdfAppender pdfAppender = null;
		try
		{
			List pdfManagerList = new ArrayList();
			for(int i = 0; i < pdfList.size(); i++)
			{
				File tmpFile = new File((String)pdfList.get(i));
				pdfManagerList.add( new PdfManager(new PdfReader(new PdfInputFile(tmpFile))) );
			}
			pdfWriter = new PdfWriter(new File(outFile));
			pdfAppender = new PdfAppender(pdfManagerList, pdfWriter);
			pdfAppender.append();
		}
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
				pdfWriter.close();
        	}
        	catch (Exception e)
        	{
        		e.printStackTrace();
				// TODO: handle exception
			}
        }

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

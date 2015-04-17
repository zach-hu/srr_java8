/**
 *
 */
package com.tsa.puridiom.report.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny Zapana
 */
public class ReportZipFileCreate extends Task
{
	public static int DATA_BLOCK_SIZE = 1024;

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		File report = new File((String) incomingRequest.get("report"));
		ZipEntry theEntry = new ZipEntry(report.getName());
		FileInputStream fis = new FileInputStream(report);
		BufferedInputStream sourceStream = new BufferedInputStream(fis);

		String zipFileName = this.getReportZipFileName(report);
		ZipOutputStream targetStream = new ZipOutputStream(new FileOutputStream(zipFileName));
		byte data[] = new byte[DATA_BLOCK_SIZE];
		int len;

		try
		{
			targetStream.putNextEntry(theEntry);

			while ((len = sourceStream.read(data)) >= 0)
			{
				targetStream.write(data, 0, len);
			}

			sourceStream.close();

			targetStream.flush();

			targetStream.closeEntry();

			targetStream.close();

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return zipFileName;
	}

	private String getReportZipFileName(File report)
	{
		final String EXTENSION_FILE = ".zip";

		String reportZipFileName;
		String reportName = report.getName();

		reportZipFileName = reportName.substring(0, reportName.lastIndexOf("."));

		return (report.getParent() + File.separator + reportZipFileName + EXTENSION_FILE);
	}
}

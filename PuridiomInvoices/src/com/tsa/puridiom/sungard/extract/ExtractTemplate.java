/*
 * Created on Oct. 19, 2005
 */
package com.tsa.puridiom.sungard.extract;

import com.tsa.puridiom.common.utility.HiltonUtility;

/**
 * @author Kelli
 */
public class ExtractTemplate {
	private String extractFileName;
	private String filePrefix;
	private String fileExtension;
	private String fileName;
	private String delimiter ;
	private boolean delimited = false ;
	private String extractDirectory;
	private String labelName;
	private ExtractColumn extractHeader[] ;
	private ExtractColumn extractDetail[] ;
	private ExtractColumn extractColumns[];
	private boolean accountRollup = false;

	public String getExtractFileName() {
		return this.extractFileName;
	}
	public void setExtractFileName(String value) {
		this.extractFileName = value;
	}
	public String getFilePrefix() {
		return this.filePrefix;
	}
	public void setFilePrefix(String value) {
		this.filePrefix = value;
	}
	public String getFileExtension() {
		return this.fileExtension;
	}
	public void setFileExtension(String value) {
		this.fileExtension = value;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getExtractDirectory() {
		return this.extractDirectory;
	}
	public void setExtractDirectory(String value) {
		this.extractDirectory = value;
	}
	public String getLabelName() {
		return this.labelName;
	}
	public void setLabelName(String value) {
		this.labelName = value;
	}
	public ExtractColumn[] getExtractColumns() {
		return this.extractColumns;
	}
	public void setExtractColumns(ExtractColumn[] x) {
		this.extractColumns = x;
	}
	public ExtractColumn[] getExtractHeader() {
		return extractHeader;
	}
	public void setExtractHeader(ExtractColumn[] extractHeader) {
		this.extractHeader = extractHeader;
	}
	public ExtractColumn[] getExtractDetail() {
		return extractDetail;
	}
	public void setExtractDetail(ExtractColumn[] extractDetail) {
		this.extractDetail = extractDetail;
	}
	public boolean isAccountRollup() {
		return this.accountRollup;
	}
	public void setAccountRollup(boolean x) {
		this.accountRollup = x;
	}
	public String getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(String delimiter) {
		if (HiltonUtility.ckNull(delimiter).trim().length() > 0) this.setDelimited(true) ;
		this.delimiter = delimiter;
	}
	public boolean isDelimited() {
		return delimited;
	}
	public void setDelimited(boolean delimited) {
		this.delimited = delimited;
	}


}
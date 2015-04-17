package com.tsa.puridiom.sungard.extract.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.sungard.extract.ExtractColumn;
import com.tsa.puridiom.sungard.extract.ExtractTemplate;
import com.tsagate.properties.DictionaryManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.utility.XmlFile;
import org.jdom.Element;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GenerateCheckRequestExtractTemplate extends Task
{
	public Object  executeTask (Object object) throws Exception {
		Object result = null;

		try {
			Map incomingRequest = (Map)object;
			String organizationId = (String) incomingRequest.get("organizationId");
			ExtractTemplate extractTemplate = new ExtractTemplate();

			try {
				String	path = DictionaryManager.getInstance("host", organizationId).getProperty("extracts-xml-path", "\\xml\\extracts\\");
				if (!path.endsWith("\\") && !path.endsWith("//")) {
				    path = path + "\\";
				}
				String filename = path + "checkrequest-extract.xml";

				XmlFile xmlFile = new XmlFile(filename, organizationId) ;

				List headerList = xmlFile.getRootChild("extract-header").getChildren("column");
				List detailList = xmlFile.getRootChild("extract-detail").getChildren("column");
				List columnList = xmlFile.getRootChild("extract-columns").getChildren("column");

				extractTemplate.setExtractHeader(this.generateColumns(extractTemplate, headerList));
				extractTemplate.setExtractDetail(this.generateColumns(extractTemplate, detailList));
				extractTemplate.setExtractColumns(this.generateColumns(extractTemplate, columnList));

				try {
				    String	accountRollup = xmlFile.getRootChild("account-rollup").getText();
				    if (Utility.ckNull(accountRollup).equalsIgnoreCase("Y")) {
				        extractTemplate.setAccountRollup(true);
				    } else {
				        extractTemplate.setAccountRollup(false);
				    }
				}
				catch (Exception e) {
				    extractTemplate.setAccountRollup(false);
				}

				try {
				    String	filePrefix = xmlFile.getRootChild("file-prefix").getText();
				    if (Utility.isEmpty(filePrefix)) {
				        filePrefix = "SINV";
				    }
				    extractTemplate.setFilePrefix(filePrefix);
				}
				catch (Exception e) {
				    extractTemplate.setFilePrefix("SINV");
				}

				try {
				    String	fileName = xmlFile.getRootChild("file-name").getText();
				    if (Utility.isEmpty(fileName)) {
				        fileName = "";
				    }
				    extractTemplate.setFileName(fileName) ;
				}
				catch (Exception e) {
				    extractTemplate.setExtractFileName("EXTRACT") ;
				}

				try {
				    String	fileExtension = xmlFile.getRootChild("file-extension").getText();
				    if (Utility.isEmpty(fileExtension)) {
				        fileExtension = "TXT";
				    }
				    extractTemplate.setFileExtension(fileExtension);
				}
				catch (Exception e) {
				    extractTemplate.setFileExtension("TXT");
				}

				try {
				    String	extractDirectory = xmlFile.getRootChild("extract-directory").getText();
				    if (Utility.isEmpty(extractDirectory)) {
				        extractDirectory = "/";
				    }
				    extractTemplate.setExtractDirectory(extractDirectory);
				}
				catch (Exception e) {
				    extractTemplate.setFileExtension("/");
				}

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			result = extractTemplate;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			throw e;
		}

		return result ;
	}

	private ExtractColumn[] generateColumns(ExtractTemplate extractTemplate, List columnList) {
		// Get each column with specs

		int	columnCount = 0;

		if (columnList != null) {
		    columnCount = columnList.size();
		}

		ExtractColumn[] extractColumns = new ExtractColumn[columnCount] ;

		Iterator it = columnList.iterator() ;
		int ix = 0 ;
		int selectColumnIndex = 0;
		String labelName = "";
		String labelAux = "";
		try {
		while (it.hasNext())  {
			Element column = ((Element)it.next()) ;
			List clist = column.getChildren() ;
			Iterator itc = clist.iterator() ;
			String   attributeType = null ;
			String   attributeValue = null ;
			while (itc.hasNext())
			{
				Element columnAttribute = ((Element) itc.next()) ;
				attributeType = attributeType = columnAttribute.getName();
				attributeValue = columnAttribute.getText() ;
				if (attributeType.equalsIgnoreCase("label")) {
					labelName = labelName + attributeValue;
					labelAux = attributeValue;
				}
				if (attributeType.equalsIgnoreCase("size")) {
					int size = Integer.valueOf(attributeValue).intValue();
					if (size > labelAux.length()) {
						size = size - labelAux.length();
						for (int ip=0; ip < size; ip++) {
							labelName = labelName + " ";
	                    }
		            }
					else
					{
						labelName = labelName + " ";
					}
				}
			}
		}
		if(!HiltonUtility.isEmpty(labelName))
		{
			extractTemplate.setLabelName(labelName);
		}
		it = columnList.iterator() ;

		while (it.hasNext())  {
			Element column = ((Element)it.next()) ;
			List clist = column.getChildren() ;
			Iterator itc = clist.iterator() ;
			String   attributeType = null ;
			String   attributeValue = null ;

			extractColumns[ix] = new ExtractColumn();

			while (itc.hasNext())
			{
			    Element columnAttribute = ((Element) itc.next()) ;
				attributeType = columnAttribute.getName() ;
				attributeValue = columnAttribute.getText() ;

				if (attributeType.equalsIgnoreCase("name")) {
				    String	columnName = attributeValue;
				    int	ind = columnName.indexOf("_");
					String className = columnName.substring(0, ind);

					if (columnName.indexOf("_id") > 0) {
						ind = columnName.indexOf("_id") + 3;
					}

					String methodName = "get" + columnName.substring(ind + 1, ind+2).toUpperCase() + columnName.substring(ind + 2);

					extractColumns[ix].setColumnName(columnName);
					extractColumns[ix].setMethodName(methodName);
					extractColumns[ix].setClassName(className);
				}
				if (attributeType.equalsIgnoreCase("type")) {
				    extractColumns[ix].setType(attributeValue) ;
				    if (attributeValue.equalsIgnoreCase("concatenate")) {
					    extractColumns[ix].setComputed(true);
					    extractColumns[ix].setComputeType(attributeType);

					    List subColumnList = column.getChildren("sub-column");
					    ArrayList columnsToUse = new ArrayList();

						// Get each sub column with specs
						Iterator subColumnIterator = subColumnList.iterator() ;

						while (subColumnIterator.hasNext())  {
							Element subColumnElement = ((Element) subColumnIterator.next()) ;
							List subColumnAttributeList = subColumnElement.getChildren() ;
							Iterator subAttributeIterator = subColumnAttributeList.iterator() ;
							String   subAttributeType = null ;
							String   subAttributeValue = null ;
							ExtractColumn subColumn = new ExtractColumn();

							while (subAttributeIterator.hasNext())
							{
							    Element subColumnAttribute = ((Element) subAttributeIterator.next()) ;
							    subAttributeType = subColumnAttribute.getName() ;
							    subAttributeValue = subColumnAttribute.getText() ;

								if (subAttributeType.equalsIgnoreCase("name")) {
								    String	columnName = subAttributeValue;
								    if (!Utility.isEmpty(columnName)) {
										String className = "";
										String	methodName = "";
										int	ind = columnName.indexOf("_");

										if (ind > 0) {
										    className = columnName.substring(0, ind);
										    subColumn.setClassName(className);

										    if (columnName.indexOf("_id") > 0) {
												ind = columnName.indexOf("_id") + 3;
											}

											methodName = "get" + columnName.substring(ind + 1, ind+2).toUpperCase() + columnName.substring(ind + 2);
											subColumn.setMethodName(methodName);
										}

										subColumn.setColumnName(columnName);
								    }
								} else if (subAttributeType.equalsIgnoreCase("default-value")) {
									String	columnName = subAttributeValue;
									subColumn.setColumnName(columnName);
									subColumn.setType(subAttributeType);
								} else if (subAttributeType.equalsIgnoreCase("type")) {
								    subColumn.setType(subAttributeValue);
								} else if (subAttributeType.equalsIgnoreCase("value")) {
								    subColumn.setValue(subAttributeValue);
								} else if (subAttributeType.equalsIgnoreCase("format")) {
								    subColumn.setFormat(subAttributeValue);
								} else if (subAttributeType.equalsIgnoreCase("rule")) {
								    subColumn.setRule(subColumnAttribute);
								} else if (subAttributeType.equalsIgnoreCase("size")) {
								    subColumn.setSize(Integer.valueOf(subAttributeValue).intValue());
								} else if (subAttributeType.equalsIgnoreCase("decimals")) {
								    subColumn.setDecimals(Integer.valueOf(subAttributeValue).intValue());
								} else if (attributeType.equalsIgnoreCase("decimal-point")) {
									subColumn.setDecimalPoint(!  (subAttributeValue.equalsIgnoreCase("false") || subAttributeValue.equalsIgnoreCase("none") || subAttributeValue.equalsIgnoreCase("N"))) ;
								}
							}
							columnsToUse.add(subColumn);
						}
						extractColumns[ix].setConcatenateColumns(columnsToUse);
				    }
				} else if (attributeType.equalsIgnoreCase("default-value")) {
//					String	columnName = attributeValue;
					extractColumns[ix].setValue(attributeValue) ;
//					extractColumns[ix].setColumnName(columnName);
//					extractColumns[ix].setType(attributeType);
				} else if (attributeType.equalsIgnoreCase("format")) {
				    extractColumns[ix].setFormat(attributeValue);
				} else if (attributeType.equalsIgnoreCase("rule")) {
				    extractColumns[ix].setFormat(attributeValue);
				} else if (attributeType.equalsIgnoreCase("decimal-point")) {
					extractColumns[ix].setDecimalPoint(!  (attributeValue.equalsIgnoreCase("false") || attributeValue.equalsIgnoreCase("none") || attributeValue.equalsIgnoreCase("N"))) ;
				} else if(attributeType.equalsIgnoreCase("justify")) {
					extractColumns[ix].setJustify(attributeValue) ;
				} else if (attributeType.equalsIgnoreCase("size")) {
				    try {
				        extractColumns[ix].setSize(Integer.valueOf(attributeValue).intValue());
				    } catch (Exception e) {
				        throw new Exception ("An error occurred setting the size [" + attributeValue + "] for " + extractColumns[ix].getColumnName());
				    }
				} else if (attributeType.equalsIgnoreCase("decimals")) {
				    try {
				        extractColumns[ix].setDecimals(Integer.valueOf(attributeValue).intValue()) ;
				    } catch (Exception e) {
				        throw new Exception ("An error occurred setting the decimals [" + attributeValue + "] for " + extractColumns[ix].getColumnName());
				    }
				} else if (attributeType.equalsIgnoreCase("position")) {
				    try {
				        extractColumns[ix].setStartPosition(Integer.valueOf(attributeValue).intValue());
				    } catch (Exception e) {
				        throw new Exception ("An error occurred setting the position [" + attributeValue + "] for " + extractColumns[ix].getColumnName());
				    }
				}
			}
			ix++ ;
		}
		}
		catch (Exception e) {
			e.printStackTrace() ;
		}

		return (extractColumns) ;
	}
}
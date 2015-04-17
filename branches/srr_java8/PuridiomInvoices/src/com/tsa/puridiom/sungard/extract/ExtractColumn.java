/*
 * Created on Oct. 19, 2005
 */
package com.tsa.puridiom.sungard.extract;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.RuleHelper;
import java.util.List;
import org.jdom.Element;

/**
 * @author Kelli
 */
public class ExtractColumn
{
	private String columnName = "";
	private String className = "";
	private String methodName = "";
	private String type = "";
	private String format = "";
	private String justify = "L" ;
	private RuleHelper rule = null;
	private int decimals = 0;
	private boolean decimalPoint = true  ;
	private int size = 0;
	private int startPosition = 0;
	private Object value;
	private boolean computed = false;
	private String computeType = "";
	private List concatenateColumns = null;

	public String getColumnName() {
		return HiltonUtility.ckNull(this.columnName);
	}
	public void setColumnName(String value) {
		this.columnName = value;
	}
	public String getClassName() {
		return HiltonUtility.ckNull(this.className);
	}
	public void setClassName(String value) {
		this.className = value;
	}
	public String getMethodName() {
		return HiltonUtility.ckNull(this.methodName);
	}
	public void setMethodName(String value) {
		this.methodName = value;
	}
	public String getType() {
		return HiltonUtility.ckNull(this.type);
	}
	public void setType(String value) {
		this.type = value;
	}
	public void setFormat(String value) {
		this.format = value;
	}
	public String getFormat() {
		return HiltonUtility.ckNull(this.format);
	}
	public void setRule(Element ruleElement) {
		this.rule = new RuleHelper(ruleElement);
	}
	public RuleHelper getRule() {
		return this.rule;
	}
	public int getDecimals() {
		return this.decimals;
	}
	public void setDecimals(int value) {
		this.decimals = value;
	}
	public int getSize() {
		return this.size;
	}
	public void setSize(int value) {
		this.size = value;
	}
	public int getStartPosition() {
		return this.startPosition;
	}
	public void setStartPosition(int value) {
		this.startPosition = value;
	}
	public Object getValue() {
		return HiltonUtility.ckNull(this.value);
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public boolean isComputed() {
		return computed;
	}
	public void setComputed(boolean computed) {
		this.computed = computed;
	}
	public List getConcatenateColumns() {
		return concatenateColumns;
	}
	public void setConcatenateColumns(List concatenateColumns) {
		this.concatenateColumns = concatenateColumns;
	}
    public String getComputeType() {
        return computeType;
    }
    public void setComputeType(String computeType) {
        this.computeType = computeType;
    }

    public String getJustify() {
		return justify;
	}
	public void setJustify(String justify) {
		this.justify = justify;
	}

	public boolean isDecimalPoint() {
		return decimalPoint;
	}
	public void setDecimalPoint(boolean decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Column Name: " + this.getColumnName() + "; Type: "+  this.getType());
        if (this.getType().equalsIgnoreCase("CONCATENATE")) {
            List subColumns = this.getConcatenateColumns();
            if (subColumns != null && subColumns.size() > 0) {
                for (int i = 0; i < subColumns.size(); i++) {
                    ExtractColumn subColumn = (ExtractColumn) subColumns.get(i);
                    sb.append(" [SubColumnName: " + subColumn.getColumnName() + "; Type: " + subColumn.getType() + "] ");
                }
            } else {
                sb.append(" [No Sub Columns Set] ");
            }
        }
        return sb.toString();
    }
}
/**
 * Selection.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.selector;


/**
 * This element contains the selected
 *                 data, which is returned from the
 *             server.
 */
public class Selection  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.selectormaster.SelectorType selType;

    /* Contains vocabulary items
     *                         (representing columns) for the display of
     * data,
     *                         which is determined by the display criteria
     * in
     *                         the presenter master which was specified.
     * Only
     *                         those vocabulary items included in the presenter
     * are used in the header. */
    private com.coda.www.efinance.schemas.selector.SelectionRow header;

    /* Contains the actual data that
     *                         has been selected. */
    private com.coda.www.efinance.schemas.selector.SelectionRow[] dataSet;

    /* If
     *                         TRUE, specifies that the data returned is
     *                         subject to a limit, since the response to
     * your
     *                         selection criteria exceeded the allowed number
     * of rows. */
    private boolean rowLimitExceeded;

    public Selection() {
    }

    public Selection(
           com.coda.www.efinance.schemas.selectormaster.SelectorType selType,
           com.coda.www.efinance.schemas.selector.SelectionRow header,
           com.coda.www.efinance.schemas.selector.SelectionRow[] dataSet,
           boolean rowLimitExceeded) {
           this.selType = selType;
           this.header = header;
           this.dataSet = dataSet;
           this.rowLimitExceeded = rowLimitExceeded;
    }


    /**
     * Gets the selType value for this Selection.
     * 
     * @return selType
     */
    public com.coda.www.efinance.schemas.selectormaster.SelectorType getSelType() {
        return selType;
    }


    /**
     * Sets the selType value for this Selection.
     * 
     * @param selType
     */
    public void setSelType(com.coda.www.efinance.schemas.selectormaster.SelectorType selType) {
        this.selType = selType;
    }


    /**
     * Gets the header value for this Selection.
     * 
     * @return header   * Contains vocabulary items
     *                         (representing columns) for the display of
     * data,
     *                         which is determined by the display criteria
     * in
     *                         the presenter master which was specified.
     * Only
     *                         those vocabulary items included in the presenter
     * are used in the header.
     */
    public com.coda.www.efinance.schemas.selector.SelectionRow getHeader() {
        return header;
    }


    /**
     * Sets the header value for this Selection.
     * 
     * @param header   * Contains vocabulary items
     *                         (representing columns) for the display of
     * data,
     *                         which is determined by the display criteria
     * in
     *                         the presenter master which was specified.
     * Only
     *                         those vocabulary items included in the presenter
     * are used in the header.
     */
    public void setHeader(com.coda.www.efinance.schemas.selector.SelectionRow header) {
        this.header = header;
    }


    /**
     * Gets the dataSet value for this Selection.
     * 
     * @return dataSet   * Contains the actual data that
     *                         has been selected.
     */
    public com.coda.www.efinance.schemas.selector.SelectionRow[] getDataSet() {
        return dataSet;
    }


    /**
     * Sets the dataSet value for this Selection.
     * 
     * @param dataSet   * Contains the actual data that
     *                         has been selected.
     */
    public void setDataSet(com.coda.www.efinance.schemas.selector.SelectionRow[] dataSet) {
        this.dataSet = dataSet;
    }


    /**
     * Gets the rowLimitExceeded value for this Selection.
     * 
     * @return rowLimitExceeded   * If
     *                         TRUE, specifies that the data returned is
     *                         subject to a limit, since the response to
     * your
     *                         selection criteria exceeded the allowed number
     * of rows.
     */
    public boolean isRowLimitExceeded() {
        return rowLimitExceeded;
    }


    /**
     * Sets the rowLimitExceeded value for this Selection.
     * 
     * @param rowLimitExceeded   * If
     *                         TRUE, specifies that the data returned is
     *                         subject to a limit, since the response to
     * your
     *                         selection criteria exceeded the allowed number
     * of rows.
     */
    public void setRowLimitExceeded(boolean rowLimitExceeded) {
        this.rowLimitExceeded = rowLimitExceeded;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Selection)) return false;
        Selection other = (Selection) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.selType==null && other.getSelType()==null) || 
             (this.selType!=null &&
              this.selType.equals(other.getSelType()))) &&
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.dataSet==null && other.getDataSet()==null) || 
             (this.dataSet!=null &&
              java.util.Arrays.equals(this.dataSet, other.getDataSet()))) &&
            this.rowLimitExceeded == other.isRowLimitExceeded();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getSelType() != null) {
            _hashCode += getSelType().hashCode();
        }
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getDataSet() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDataSet());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDataSet(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isRowLimitExceeded() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Selection.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "Selection"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "SelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "SelectorType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "Header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "SelectionRow"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "DataSet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "SelectionRow"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "Row"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rowLimitExceeded");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "RowLimitExceeded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

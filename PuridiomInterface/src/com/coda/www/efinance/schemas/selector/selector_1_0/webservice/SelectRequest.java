/**
 * SelectRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.selector.selector_1_0.webservice;

public class SelectRequest  implements java.io.Serializable {
    /* Specifies the source of the
     *                             data from which the selection is made.
     * The
     *                             data source is determined by the combination
     * of data class and data location. */
    private com.coda.www.efinance.schemas.selectormaster.SelectorType selType;

    /* The code of the selector
     *                             master you want to use. */
    private java.lang.String gslCode;

    /* The maximum number of rows
     *                             you want your search to return */
    private java.lang.Integer rowLimit;

    /* The search criteria to be
     *                             applied by the application when selecting
     * the data. These are as set up in the
     *                             selector master. */
    private com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] applicationFilter;

    /* The additional search
     *                             criteria specified by the user, to be
     *                             applied when selecting the data. This
     * may
     *                             be, for example, a document code or a
     * range
     *                             of codes. */
    private com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] userFilter;

    /* The columns representing
     *                             vocabulary items that you want the server
     * to return. */
    private java.lang.String[] columns;

    public SelectRequest() {
    }

    public SelectRequest(
           com.coda.www.efinance.schemas.selectormaster.SelectorType selType,
           java.lang.String gslCode,
           java.lang.Integer rowLimit,
           com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] applicationFilter,
           com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] userFilter,
           java.lang.String[] columns) {
           this.selType = selType;
           this.gslCode = gslCode;
           this.rowLimit = rowLimit;
           this.applicationFilter = applicationFilter;
           this.userFilter = userFilter;
           this.columns = columns;
    }


    /**
     * Gets the selType value for this SelectRequest.
     * 
     * @return selType   * Specifies the source of the
     *                             data from which the selection is made.
     * The
     *                             data source is determined by the combination
     * of data class and data location.
     */
    public com.coda.www.efinance.schemas.selectormaster.SelectorType getSelType() {
        return selType;
    }


    /**
     * Sets the selType value for this SelectRequest.
     * 
     * @param selType   * Specifies the source of the
     *                             data from which the selection is made.
     * The
     *                             data source is determined by the combination
     * of data class and data location.
     */
    public void setSelType(com.coda.www.efinance.schemas.selectormaster.SelectorType selType) {
        this.selType = selType;
    }


    /**
     * Gets the gslCode value for this SelectRequest.
     * 
     * @return gslCode   * The code of the selector
     *                             master you want to use.
     */
    public java.lang.String getGslCode() {
        return gslCode;
    }


    /**
     * Sets the gslCode value for this SelectRequest.
     * 
     * @param gslCode   * The code of the selector
     *                             master you want to use.
     */
    public void setGslCode(java.lang.String gslCode) {
        this.gslCode = gslCode;
    }


    /**
     * Gets the rowLimit value for this SelectRequest.
     * 
     * @return rowLimit   * The maximum number of rows
     *                             you want your search to return
     */
    public java.lang.Integer getRowLimit() {
        return rowLimit;
    }


    /**
     * Sets the rowLimit value for this SelectRequest.
     * 
     * @param rowLimit   * The maximum number of rows
     *                             you want your search to return
     */
    public void setRowLimit(java.lang.Integer rowLimit) {
        this.rowLimit = rowLimit;
    }


    /**
     * Gets the applicationFilter value for this SelectRequest.
     * 
     * @return applicationFilter   * The search criteria to be
     *                             applied by the application when selecting
     * the data. These are as set up in the
     *                             selector master.
     */
    public com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] getApplicationFilter() {
        return applicationFilter;
    }


    /**
     * Sets the applicationFilter value for this SelectRequest.
     * 
     * @param applicationFilter   * The search criteria to be
     *                             applied by the application when selecting
     * the data. These are as set up in the
     *                             selector master.
     */
    public void setApplicationFilter(com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] applicationFilter) {
        this.applicationFilter = applicationFilter;
    }


    /**
     * Gets the userFilter value for this SelectRequest.
     * 
     * @return userFilter   * The additional search
     *                             criteria specified by the user, to be
     *                             applied when selecting the data. This
     * may
     *                             be, for example, a document code or a
     * range
     *                             of codes.
     */
    public com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] getUserFilter() {
        return userFilter;
    }


    /**
     * Sets the userFilter value for this SelectRequest.
     * 
     * @param userFilter   * The additional search
     *                             criteria specified by the user, to be
     *                             applied when selecting the data. This
     * may
     *                             be, for example, a document code or a
     * range
     *                             of codes.
     */
    public void setUserFilter(com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] userFilter) {
        this.userFilter = userFilter;
    }


    /**
     * Gets the columns value for this SelectRequest.
     * 
     * @return columns   * The columns representing
     *                             vocabulary items that you want the server
     * to return.
     */
    public java.lang.String[] getColumns() {
        return columns;
    }


    /**
     * Sets the columns value for this SelectRequest.
     * 
     * @param columns   * The columns representing
     *                             vocabulary items that you want the server
     * to return.
     */
    public void setColumns(java.lang.String[] columns) {
        this.columns = columns;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectRequest)) return false;
        SelectRequest other = (SelectRequest) obj;
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
            ((this.gslCode==null && other.getGslCode()==null) || 
             (this.gslCode!=null &&
              this.gslCode.equals(other.getGslCode()))) &&
            ((this.rowLimit==null && other.getRowLimit()==null) || 
             (this.rowLimit!=null &&
              this.rowLimit.equals(other.getRowLimit()))) &&
            ((this.applicationFilter==null && other.getApplicationFilter()==null) || 
             (this.applicationFilter!=null &&
              java.util.Arrays.equals(this.applicationFilter, other.getApplicationFilter()))) &&
            ((this.userFilter==null && other.getUserFilter()==null) || 
             (this.userFilter!=null &&
              java.util.Arrays.equals(this.userFilter, other.getUserFilter()))) &&
            ((this.columns==null && other.getColumns()==null) || 
             (this.columns!=null &&
              java.util.Arrays.equals(this.columns, other.getColumns())));
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
        if (getGslCode() != null) {
            _hashCode += getGslCode().hashCode();
        }
        if (getRowLimit() != null) {
            _hashCode += getRowLimit().hashCode();
        }
        if (getApplicationFilter() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getApplicationFilter());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getApplicationFilter(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUserFilter() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserFilter());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUserFilter(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getColumns() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getColumns());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getColumns(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SelectRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", ">SelectRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "SelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "SelectorType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gslCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "GslCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rowLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "RowLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "ApplicationFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "VocListDataElement"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "Vocab"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "UserFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "VocListDataElement"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "Vocab"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columns");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "Columns"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "Cell"));
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

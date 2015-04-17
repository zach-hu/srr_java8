/**
 * SelectDetailsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.selector.selector_1_0.webservice;

public class SelectDetailsRequest  implements java.io.Serializable {
    /* Indicates the source of the
     *                             data, which may be retrieved from Books,
     * Intray, Archive or Self. */
    private com.coda.www.efinance.schemas.selectormaster.DetailsLocation selType;

    /* The code of the selector
     *                             master you want to use. */
    private java.lang.String gslCode;

    /* If TRUE, specifies that you
     *                             want to browse whole documents for the
     * lines selected. */
    private boolean wholeDocuments;

    /* The maximum number of rows
     *                             you want your search to return. */
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

    /* The additional search
     *                             criteria which will be applied to select
     * data within specified hierarchies. */
    private com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] hierarchyFilter;

    /* The columns representing
     *                             vocabulary items that you want the server
     * to return. */
    private java.lang.String[] columns;

    public SelectDetailsRequest() {
    }

    public SelectDetailsRequest(
           com.coda.www.efinance.schemas.selectormaster.DetailsLocation selType,
           java.lang.String gslCode,
           boolean wholeDocuments,
           java.lang.Integer rowLimit,
           com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] applicationFilter,
           com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] userFilter,
           com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] hierarchyFilter,
           java.lang.String[] columns) {
           this.selType = selType;
           this.gslCode = gslCode;
           this.wholeDocuments = wholeDocuments;
           this.rowLimit = rowLimit;
           this.applicationFilter = applicationFilter;
           this.userFilter = userFilter;
           this.hierarchyFilter = hierarchyFilter;
           this.columns = columns;
    }


    /**
     * Gets the selType value for this SelectDetailsRequest.
     * 
     * @return selType   * Indicates the source of the
     *                             data, which may be retrieved from Books,
     * Intray, Archive or Self.
     */
    public com.coda.www.efinance.schemas.selectormaster.DetailsLocation getSelType() {
        return selType;
    }


    /**
     * Sets the selType value for this SelectDetailsRequest.
     * 
     * @param selType   * Indicates the source of the
     *                             data, which may be retrieved from Books,
     * Intray, Archive or Self.
     */
    public void setSelType(com.coda.www.efinance.schemas.selectormaster.DetailsLocation selType) {
        this.selType = selType;
    }


    /**
     * Gets the gslCode value for this SelectDetailsRequest.
     * 
     * @return gslCode   * The code of the selector
     *                             master you want to use.
     */
    public java.lang.String getGslCode() {
        return gslCode;
    }


    /**
     * Sets the gslCode value for this SelectDetailsRequest.
     * 
     * @param gslCode   * The code of the selector
     *                             master you want to use.
     */
    public void setGslCode(java.lang.String gslCode) {
        this.gslCode = gslCode;
    }


    /**
     * Gets the wholeDocuments value for this SelectDetailsRequest.
     * 
     * @return wholeDocuments   * If TRUE, specifies that you
     *                             want to browse whole documents for the
     * lines selected.
     */
    public boolean isWholeDocuments() {
        return wholeDocuments;
    }


    /**
     * Sets the wholeDocuments value for this SelectDetailsRequest.
     * 
     * @param wholeDocuments   * If TRUE, specifies that you
     *                             want to browse whole documents for the
     * lines selected.
     */
    public void setWholeDocuments(boolean wholeDocuments) {
        this.wholeDocuments = wholeDocuments;
    }


    /**
     * Gets the rowLimit value for this SelectDetailsRequest.
     * 
     * @return rowLimit   * The maximum number of rows
     *                             you want your search to return.
     */
    public java.lang.Integer getRowLimit() {
        return rowLimit;
    }


    /**
     * Sets the rowLimit value for this SelectDetailsRequest.
     * 
     * @param rowLimit   * The maximum number of rows
     *                             you want your search to return.
     */
    public void setRowLimit(java.lang.Integer rowLimit) {
        this.rowLimit = rowLimit;
    }


    /**
     * Gets the applicationFilter value for this SelectDetailsRequest.
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
     * Sets the applicationFilter value for this SelectDetailsRequest.
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
     * Gets the userFilter value for this SelectDetailsRequest.
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
     * Sets the userFilter value for this SelectDetailsRequest.
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
     * Gets the hierarchyFilter value for this SelectDetailsRequest.
     * 
     * @return hierarchyFilter   * The additional search
     *                             criteria which will be applied to select
     * data within specified hierarchies.
     */
    public com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] getHierarchyFilter() {
        return hierarchyFilter;
    }


    /**
     * Sets the hierarchyFilter value for this SelectDetailsRequest.
     * 
     * @param hierarchyFilter   * The additional search
     *                             criteria which will be applied to select
     * data within specified hierarchies.
     */
    public void setHierarchyFilter(com.coda.www.efinance.schemas.selectormaster.VocListDataElement[] hierarchyFilter) {
        this.hierarchyFilter = hierarchyFilter;
    }


    /**
     * Gets the columns value for this SelectDetailsRequest.
     * 
     * @return columns   * The columns representing
     *                             vocabulary items that you want the server
     * to return.
     */
    public java.lang.String[] getColumns() {
        return columns;
    }


    /**
     * Sets the columns value for this SelectDetailsRequest.
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
        if (!(obj instanceof SelectDetailsRequest)) return false;
        SelectDetailsRequest other = (SelectDetailsRequest) obj;
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
            this.wholeDocuments == other.isWholeDocuments() &&
            ((this.rowLimit==null && other.getRowLimit()==null) || 
             (this.rowLimit!=null &&
              this.rowLimit.equals(other.getRowLimit()))) &&
            ((this.applicationFilter==null && other.getApplicationFilter()==null) || 
             (this.applicationFilter!=null &&
              java.util.Arrays.equals(this.applicationFilter, other.getApplicationFilter()))) &&
            ((this.userFilter==null && other.getUserFilter()==null) || 
             (this.userFilter!=null &&
              java.util.Arrays.equals(this.userFilter, other.getUserFilter()))) &&
            ((this.hierarchyFilter==null && other.getHierarchyFilter()==null) || 
             (this.hierarchyFilter!=null &&
              java.util.Arrays.equals(this.hierarchyFilter, other.getHierarchyFilter()))) &&
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
        _hashCode += (isWholeDocuments() ? Boolean.TRUE : Boolean.FALSE).hashCode();
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
        if (getHierarchyFilter() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHierarchyFilter());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHierarchyFilter(), i);
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
        new org.apache.axis.description.TypeDesc(SelectDetailsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", ">SelectDetailsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "SelType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "DetailsLocation"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gslCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "GslCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wholeDocuments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "WholeDocuments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("hierarchyFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector/selector-1.0/webservice", "HierarchyFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "VocListDataElement"));
        elemField.setMinOccurs(0);
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

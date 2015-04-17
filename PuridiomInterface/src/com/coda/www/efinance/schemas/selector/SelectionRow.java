/**
 * SelectionRow.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.selector;


/**
 * A row of data that has been
 *             selected.
 */
public class SelectionRow  implements java.io.Serializable {
    private java.lang.String rowType;

    /* Contains the list of columns
     *                         for holding the selected
     *                     data. */
    private java.lang.String[] cells;

    public SelectionRow() {
    }

    public SelectionRow(
           java.lang.String rowType,
           java.lang.String[] cells) {
           this.rowType = rowType;
           this.cells = cells;
    }


    /**
     * Gets the rowType value for this SelectionRow.
     * 
     * @return rowType
     */
    public java.lang.String getRowType() {
        return rowType;
    }


    /**
     * Sets the rowType value for this SelectionRow.
     * 
     * @param rowType
     */
    public void setRowType(java.lang.String rowType) {
        this.rowType = rowType;
    }


    /**
     * Gets the cells value for this SelectionRow.
     * 
     * @return cells   * Contains the list of columns
     *                         for holding the selected
     *                     data.
     */
    public java.lang.String[] getCells() {
        return cells;
    }


    /**
     * Sets the cells value for this SelectionRow.
     * 
     * @param cells   * Contains the list of columns
     *                         for holding the selected
     *                     data.
     */
    public void setCells(java.lang.String[] cells) {
        this.cells = cells;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectionRow)) return false;
        SelectionRow other = (SelectionRow) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rowType==null && other.getRowType()==null) || 
             (this.rowType!=null &&
              this.rowType.equals(other.getRowType()))) &&
            ((this.cells==null && other.getCells()==null) || 
             (this.cells!=null &&
              java.util.Arrays.equals(this.cells, other.getCells())));
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
        if (getRowType() != null) {
            _hashCode += getRowType().hashCode();
        }
        if (getCells() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCells());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCells(), i);
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
        new org.apache.axis.description.TypeDesc(SelectionRow.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "SelectionRow"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rowType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "RowType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cells");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selector", "Cells"));
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

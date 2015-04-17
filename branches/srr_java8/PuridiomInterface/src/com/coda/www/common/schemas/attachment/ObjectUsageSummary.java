/**
 * ObjectUsageSummary.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.attachment;


/**
 * The request for
 *                 the Find Attachment dialog.
 */
public class ObjectUsageSummary  implements java.io.Serializable {
    private java.math.BigDecimal id;

    /* The name of the attached
     *                     file. */
    private java.lang.String filename;

    /* The
     *                         code of the user who added the
     *                     attachment. */
    private java.lang.String user;

    /* The time the attachment was
     *                     added. */
    private java.util.Calendar added;

    /* Specifies the number of times
     *                         the object is used. */
    private int addedCount;

    /* The MIME type of the
     *                     attachment. */
    private java.lang.String mimeType;

    /* The
     *                         name of the application. If multiple
     *                         applications use this object then the value
     * of
     *                         this field is null. */
    private java.lang.String application;

    /* Specifies the number of
     *                         applications that use this repository
     *                     object. */
    private int applicationCount;

    /* A
     *                         sample Attachment object that uses this
     *                         repository object. This is used because the
     * software can only download things by attachment
     *                         and not directly from the repository
     *                     object. */
    private com.coda.www.common.schemas.attachment.Attachment sampleAttachment;

    public ObjectUsageSummary() {
    }

    public ObjectUsageSummary(
           java.math.BigDecimal id,
           java.lang.String filename,
           java.lang.String user,
           java.util.Calendar added,
           int addedCount,
           java.lang.String mimeType,
           java.lang.String application,
           int applicationCount,
           com.coda.www.common.schemas.attachment.Attachment sampleAttachment) {
           this.id = id;
           this.filename = filename;
           this.user = user;
           this.added = added;
           this.addedCount = addedCount;
           this.mimeType = mimeType;
           this.application = application;
           this.applicationCount = applicationCount;
           this.sampleAttachment = sampleAttachment;
    }


    /**
     * Gets the id value for this ObjectUsageSummary.
     * 
     * @return id
     */
    public java.math.BigDecimal getId() {
        return id;
    }


    /**
     * Sets the id value for this ObjectUsageSummary.
     * 
     * @param id
     */
    public void setId(java.math.BigDecimal id) {
        this.id = id;
    }


    /**
     * Gets the filename value for this ObjectUsageSummary.
     * 
     * @return filename   * The name of the attached
     *                     file.
     */
    public java.lang.String getFilename() {
        return filename;
    }


    /**
     * Sets the filename value for this ObjectUsageSummary.
     * 
     * @param filename   * The name of the attached
     *                     file.
     */
    public void setFilename(java.lang.String filename) {
        this.filename = filename;
    }


    /**
     * Gets the user value for this ObjectUsageSummary.
     * 
     * @return user   * The
     *                         code of the user who added the
     *                     attachment.
     */
    public java.lang.String getUser() {
        return user;
    }


    /**
     * Sets the user value for this ObjectUsageSummary.
     * 
     * @param user   * The
     *                         code of the user who added the
     *                     attachment.
     */
    public void setUser(java.lang.String user) {
        this.user = user;
    }


    /**
     * Gets the added value for this ObjectUsageSummary.
     * 
     * @return added   * The time the attachment was
     *                     added.
     */
    public java.util.Calendar getAdded() {
        return added;
    }


    /**
     * Sets the added value for this ObjectUsageSummary.
     * 
     * @param added   * The time the attachment was
     *                     added.
     */
    public void setAdded(java.util.Calendar added) {
        this.added = added;
    }


    /**
     * Gets the addedCount value for this ObjectUsageSummary.
     * 
     * @return addedCount   * Specifies the number of times
     *                         the object is used.
     */
    public int getAddedCount() {
        return addedCount;
    }


    /**
     * Sets the addedCount value for this ObjectUsageSummary.
     * 
     * @param addedCount   * Specifies the number of times
     *                         the object is used.
     */
    public void setAddedCount(int addedCount) {
        this.addedCount = addedCount;
    }


    /**
     * Gets the mimeType value for this ObjectUsageSummary.
     * 
     * @return mimeType   * The MIME type of the
     *                     attachment.
     */
    public java.lang.String getMimeType() {
        return mimeType;
    }


    /**
     * Sets the mimeType value for this ObjectUsageSummary.
     * 
     * @param mimeType   * The MIME type of the
     *                     attachment.
     */
    public void setMimeType(java.lang.String mimeType) {
        this.mimeType = mimeType;
    }


    /**
     * Gets the application value for this ObjectUsageSummary.
     * 
     * @return application   * The
     *                         name of the application. If multiple
     *                         applications use this object then the value
     * of
     *                         this field is null.
     */
    public java.lang.String getApplication() {
        return application;
    }


    /**
     * Sets the application value for this ObjectUsageSummary.
     * 
     * @param application   * The
     *                         name of the application. If multiple
     *                         applications use this object then the value
     * of
     *                         this field is null.
     */
    public void setApplication(java.lang.String application) {
        this.application = application;
    }


    /**
     * Gets the applicationCount value for this ObjectUsageSummary.
     * 
     * @return applicationCount   * Specifies the number of
     *                         applications that use this repository
     *                     object.
     */
    public int getApplicationCount() {
        return applicationCount;
    }


    /**
     * Sets the applicationCount value for this ObjectUsageSummary.
     * 
     * @param applicationCount   * Specifies the number of
     *                         applications that use this repository
     *                     object.
     */
    public void setApplicationCount(int applicationCount) {
        this.applicationCount = applicationCount;
    }


    /**
     * Gets the sampleAttachment value for this ObjectUsageSummary.
     * 
     * @return sampleAttachment   * A
     *                         sample Attachment object that uses this
     *                         repository object. This is used because the
     * software can only download things by attachment
     *                         and not directly from the repository
     *                     object.
     */
    public com.coda.www.common.schemas.attachment.Attachment getSampleAttachment() {
        return sampleAttachment;
    }


    /**
     * Sets the sampleAttachment value for this ObjectUsageSummary.
     * 
     * @param sampleAttachment   * A
     *                         sample Attachment object that uses this
     *                         repository object. This is used because the
     * software can only download things by attachment
     *                         and not directly from the repository
     *                     object.
     */
    public void setSampleAttachment(com.coda.www.common.schemas.attachment.Attachment sampleAttachment) {
        this.sampleAttachment = sampleAttachment;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObjectUsageSummary)) return false;
        ObjectUsageSummary other = (ObjectUsageSummary) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.filename==null && other.getFilename()==null) || 
             (this.filename!=null &&
              this.filename.equals(other.getFilename()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.added==null && other.getAdded()==null) || 
             (this.added!=null &&
              this.added.equals(other.getAdded()))) &&
            this.addedCount == other.getAddedCount() &&
            ((this.mimeType==null && other.getMimeType()==null) || 
             (this.mimeType!=null &&
              this.mimeType.equals(other.getMimeType()))) &&
            ((this.application==null && other.getApplication()==null) || 
             (this.application!=null &&
              this.application.equals(other.getApplication()))) &&
            this.applicationCount == other.getApplicationCount() &&
            ((this.sampleAttachment==null && other.getSampleAttachment()==null) || 
             (this.sampleAttachment!=null &&
              this.sampleAttachment.equals(other.getSampleAttachment())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getFilename() != null) {
            _hashCode += getFilename().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getAdded() != null) {
            _hashCode += getAdded().hashCode();
        }
        _hashCode += getAddedCount();
        if (getMimeType() != null) {
            _hashCode += getMimeType().hashCode();
        }
        if (getApplication() != null) {
            _hashCode += getApplication().hashCode();
        }
        _hashCode += getApplicationCount();
        if (getSampleAttachment() != null) {
            _hashCode += getSampleAttachment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObjectUsageSummary.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "ObjectUsageSummary"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filename");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Filename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("added");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Added"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addedCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "AddedCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mimeType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "MimeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("application");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Application"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "ApplicationCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sampleAttachment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "SampleAttachment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Attachment"));
        elemField.setMinOccurs(0);
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

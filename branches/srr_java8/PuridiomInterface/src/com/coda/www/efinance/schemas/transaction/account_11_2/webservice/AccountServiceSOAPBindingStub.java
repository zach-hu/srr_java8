/**
 * AccountServiceSOAPBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction.account_11_2.webservice;

public class AccountServiceSOAPBindingStub extends org.apache.axis.client.Stub implements com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServicePortTypes {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[7];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetResponsibleUsers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetResponsibleUsersRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersRequest"), com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetResponsibleUsersResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAccessLevel");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetAccessLevelRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetAccessLevelRequest"), com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetAccessLevelResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetAccessLevelResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SubstituteAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "SubstituteAccountRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">SubstituteAccountRequest"), com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">SubstituteAccountResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "SubstituteAccountResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetResponsibleUsersWithTolerance");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetResponsibleUsersWithToleranceRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersWithToleranceRequest"), com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersWithToleranceResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetResponsibleUsersWithToleranceResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CheckCode");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "CheckCodeRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">CheckCodeRequest"), com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">CheckCodeResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "CheckCodeResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetDefaultTaxCode");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetDefaultTaxCodeRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetDefaultTaxCodeRequest"), com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetDefaultTaxCodeResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetDefaultTaxCodeResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetDestCmp");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetDestCmpRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetDestCmpRequest"), com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetDestCmpResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "GetDestCmpResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

    }

    public AccountServiceSOAPBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AccountServiceSOAPBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AccountServiceSOAPBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Reason");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.Reason.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReasonText");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ReasonText.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAcCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeChkAccode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtAcCodeAccessLevel");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtResponsibilityUsage");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeMoneyB");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePeriod");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeShortNameB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">CheckCodeRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">CheckCodeResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetAccessLevelRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetAccessLevelResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetDefaultTaxCodeRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetDefaultTaxCodeResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetDestCmpRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetDestCmpResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersWithToleranceRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersWithToleranceResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">SubstituteAccountRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">SubstituteAccountResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccessLevelParams");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.AccessLevelParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeAnswer");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.ChkAccCodeAnswer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.ChkAccCodeData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeFailed");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.ChkAccCodeFailed.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeResult");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeShortNameB");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ShortName");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DestAndAccount");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.DestAndAccount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DestAndAccountList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.DestAndAccount[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DestAndAccount");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Account");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetDestSource");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.GetDestSource.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "SubstituteAccountParams");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.SubstituteAccountParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserAndTolerance");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.UserAndTolerance.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserAndToleranceList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.UserAndTolerance[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserAndTolerance");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserAndTolerance");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserCodeList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserCode");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UsersAndTolerances");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.UsersAndTolerances.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }


    /**
     * Retrieves a list of responsible users.
     */
    public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersResponse getResponsibleUsers(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersRequest getResponsibleUsersRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Account/GetResponsibleUsers");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetResponsibleUsers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getResponsibleUsersRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves access level details.
     */
    public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelResponse getAccessLevel(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelRequest getAccessLevelRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Account/GetAccessLevel");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetAccessLevel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getAccessLevelRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves details of a substitute account.
     */
    public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountResponse substituteAccount(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountRequest substituteAccountRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Account/SubstituteAccount");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "SubstituteAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {substituteAccountRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves details of responsible users and associated tolerance
     * masters.
     */
    public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceResponse getResponsibleUsersWithTolerance(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceRequest getResponsibleUsersWithToleranceRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Account/GetResponsibleUsersWithTolerance");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetResponsibleUsersWithTolerance"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getResponsibleUsersWithToleranceRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Validates the account code.
     */
    public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse checkCode(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeRequest checkCodeRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Account/CheckCode");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CheckCode"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {checkCodeRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves details of the default tax code.
     */
    public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeResponse getDefaultTaxCode(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeRequest getDefaultTaxCodeRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Account/GetDefaultTaxCode");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetDefaultTaxCode"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getDefaultTaxCodeRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves the specified InterCompany destination company from
     * the database.
     */
    public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpResponse getDestCmp(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpRequest getDestCmpRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Account/GetDestCmp");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetDestCmp"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getDestCmpRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}

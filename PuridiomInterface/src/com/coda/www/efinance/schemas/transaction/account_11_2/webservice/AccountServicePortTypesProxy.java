package com.coda.www.efinance.schemas.transaction.account_11_2.webservice;

public class AccountServicePortTypesProxy implements com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServicePortTypes {
  private String _endpoint = null;
  private com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServicePortTypes accountServicePortTypes = null;
  
  public AccountServicePortTypesProxy() {
    _initAccountServicePortTypesProxy();
  }
  
  public AccountServicePortTypesProxy(String endpoint) {
    _endpoint = endpoint;
    _initAccountServicePortTypesProxy();
  }
  
  private void _initAccountServicePortTypesProxy() {
    try {
      accountServicePortTypes = (new com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceLocator()).getAccountServicePort();
      if (accountServicePortTypes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)accountServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)accountServicePortTypes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (accountServicePortTypes != null)
      ((javax.xml.rpc.Stub)accountServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServicePortTypes getAccountServicePortTypes() {
    if (accountServicePortTypes == null)
      _initAccountServicePortTypesProxy();
    return accountServicePortTypes;
  }
  
  public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersResponse getResponsibleUsers(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersRequest getResponsibleUsersRequest) throws java.rmi.RemoteException{
    if (accountServicePortTypes == null)
      _initAccountServicePortTypesProxy();
    return accountServicePortTypes.getResponsibleUsers(getResponsibleUsersRequest);
  }
  
  public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelResponse getAccessLevel(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetAccessLevelRequest getAccessLevelRequest) throws java.rmi.RemoteException{
    if (accountServicePortTypes == null)
      _initAccountServicePortTypesProxy();
    return accountServicePortTypes.getAccessLevel(getAccessLevelRequest);
  }
  
  public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountResponse substituteAccount(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.SubstituteAccountRequest substituteAccountRequest) throws java.rmi.RemoteException{
    if (accountServicePortTypes == null)
      _initAccountServicePortTypesProxy();
    return accountServicePortTypes.substituteAccount(substituteAccountRequest);
  }
  
  public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceResponse getResponsibleUsersWithTolerance(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetResponsibleUsersWithToleranceRequest getResponsibleUsersWithToleranceRequest) throws java.rmi.RemoteException{
    if (accountServicePortTypes == null)
      _initAccountServicePortTypesProxy();
    return accountServicePortTypes.getResponsibleUsersWithTolerance(getResponsibleUsersWithToleranceRequest);
  }
  
  public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse checkCode(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeRequest checkCodeRequest) throws java.rmi.RemoteException{
    if (accountServicePortTypes == null)
      _initAccountServicePortTypesProxy();
    return accountServicePortTypes.checkCode(checkCodeRequest);
  }
  
  public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeResponse getDefaultTaxCode(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDefaultTaxCodeRequest getDefaultTaxCodeRequest) throws java.rmi.RemoteException{
    if (accountServicePortTypes == null)
      _initAccountServicePortTypesProxy();
    return accountServicePortTypes.getDefaultTaxCode(getDefaultTaxCodeRequest);
  }
  
  public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpResponse getDestCmp(com.coda.www.efinance.schemas.transaction.account_11_2.webservice.GetDestCmpRequest getDestCmpRequest) throws java.rmi.RemoteException{
    if (accountServicePortTypes == null)
      _initAccountServicePortTypesProxy();
    return accountServicePortTypes.getDestCmp(getDestCmpRequest);
  }
  
  
}
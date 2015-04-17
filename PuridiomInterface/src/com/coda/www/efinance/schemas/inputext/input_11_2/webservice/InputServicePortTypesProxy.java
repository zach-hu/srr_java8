package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class InputServicePortTypesProxy implements com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServicePortTypes {
  private String _endpoint = null;
  private com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServicePortTypes inputServicePortTypes = null;
  
  public InputServicePortTypesProxy() {
    _initInputServicePortTypesProxy();
  }
  
  public InputServicePortTypesProxy(String endpoint) {
    _endpoint = endpoint;
    _initInputServicePortTypesProxy();
  }
  
  private void _initInputServicePortTypesProxy() {
    try {
      inputServicePortTypes = (new com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServiceLocator()).getInputServicePort();
      if (inputServicePortTypes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)inputServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)inputServicePortTypes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (inputServicePortTypes != null)
      ((javax.xml.rpc.Stub)inputServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServicePortTypes getInputServicePortTypes() {
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes;
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersResponse getMissingDocNumbers(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersRequest getMissingDocNumbersRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.getMissingDocNumbers(getMissingDocNumbersRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateResponse selectTemplate(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateRequest selectTemplateRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.selectTemplate(selectTemplateRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksResponse getElmBanks(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksRequest getElmBanksRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.getElmBanks(getElmBanksRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderResponse setHeader(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderRequest setHeaderRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.setHeader(setHeaderRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesResponse getHardenedDates(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesRequest getHardenedDatesRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.getHardenedDates(getHardenedDatesRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateResponse generateTaxWithoutTemplate(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateRequest generateTaxWithoutTemplateRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.generateTaxWithoutTemplate(generateTaxWithoutTemplateRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusResponse getUserStatus(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusRequest getUserStatusRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.getUserStatus(getUserStatusRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayResponse postToIntray(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayRequest postToIntrayRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.postToIntray(postToIntrayRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxResponse generateTax(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxRequest generateTaxRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.generateTax(generateTaxRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveResponse resolve(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveRequest resolveRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.resolve(resolveRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewResponse balancingReview(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewRequest balancingReviewRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.balancingReview(balancingReviewRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusResponse getMissingStatus(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusRequest getMissingStatusRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.getMissingStatus(getMissingStatusRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostResponse intrayAutoPost(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostRequest intrayAutoPostRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.intrayAutoPost(intrayAutoPostRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewResponse currReview(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewRequest currReviewRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.currReview(currReviewRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseResponse intrayUnAuthorise(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseRequest intrayUnAuthoriseRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.intrayUnAuthorise(intrayUnAuthoriseRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocResponse cancelDoc(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocRequest cancelDocRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.cancelDoc(cancelDocRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteResponse intrayDelete(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteRequest intrayDeleteRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.intrayDelete(intrayDeleteRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateResponse checkPostWithoutTemplate(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateRequest checkPostWithoutTemplateRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.checkPostWithoutTemplate(checkPostWithoutTemplateRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostResponse post(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostRequest postRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.post(postRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryResponse getCustomerSupplierElmSummary(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryRequest getCustomerSupplierElmSummaryRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.getCustomerSupplierElmSummary(getCustomerSupplierElmSummaryRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksResponse postToBooks(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequest postToBooksRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.postToBooks(postToBooksRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadResponse intrayLoad(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadRequest intrayLoadRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.intrayLoad(intrayLoadRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocResponse copyDoc(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocRequest copyDocRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.copyDoc(copyDocRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsResponse getCopyDocParams(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsRequest getCopyDocParamsRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.getCopyDocParams(getCopyDocParamsRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostResponse checkPost(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequest checkPostRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.checkPost(checkPostRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesResponse getElmAddresses(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesRequest getElmAddressesRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.getElmAddresses(getElmAddressesRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsResponse refreshTotals(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsRequest refreshTotalsRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.refreshTotals(refreshTotalsRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesResponse calcCurrencies(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesRequest calcCurrenciesRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.calcCurrencies(calcCurrenciesRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayResponse selectIntray(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayRequest selectIntrayRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.selectIntray(selectIntrayRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesResponse generateBalancingLines(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesRequest generateBalancingLinesRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.generateBalancingLines(generateBalancingLinesRequest);
  }
  
  public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxResponse autoBalanceTax(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxRequest autoBalanceTaxRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.autoBalanceTax(autoBalanceTaxRequest);
  }
  
  
}
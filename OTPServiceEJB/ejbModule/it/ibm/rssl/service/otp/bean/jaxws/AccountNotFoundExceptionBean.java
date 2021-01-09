/*    */ package it.ibm.rssl.service.otp.bean.jaxws;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @XmlRootElement(name = "AccountNotFoundException", namespace = "http://bean.otp.service.rssl.ibm.it/")
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name = "AccountNotFoundException", namespace = "http://bean.otp.service.rssl.ibm.it/", propOrder = {"message", "userId"})
/*    */ public class AccountNotFoundExceptionBean
/*    */ {
/*    */   private String message;
/*    */   private String userId;
/*    */   
/*    */   public String getMessage() {
/* 37 */     return this.message;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMessage(String message) {
/* 46 */     this.message = message;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUserId() {
/* 55 */     return this.userId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setUserId(String userId) {
/* 64 */     this.userId = userId;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\bean\jaxws\AccountNotFoundExceptionBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
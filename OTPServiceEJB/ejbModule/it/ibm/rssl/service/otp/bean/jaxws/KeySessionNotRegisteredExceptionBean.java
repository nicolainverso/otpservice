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
/*    */ 
/*    */ @XmlRootElement(name = "KeySessionNotRegisteredException", namespace = "http://bean.otp.service.rssl.ibm.it/")
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name = "KeySessionNotRegisteredException", namespace = "http://bean.otp.service.rssl.ibm.it/", propOrder = {"keySession", "message", "userId"})
/*    */ public class KeySessionNotRegisteredExceptionBean
/*    */ {
/*    */   private String keySession;
/*    */   private String message;
/*    */   private String userId;
/*    */   
/*    */   public String getKeySession() {
/* 39 */     return this.keySession;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setKeySession(String keySession) {
/* 48 */     this.keySession = keySession;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 57 */     return this.message;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMessage(String message) {
/* 66 */     this.message = message;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUserId() {
/* 75 */     return this.userId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setUserId(String userId) {
/* 84 */     this.userId = userId;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\bean\jaxws\KeySessionNotRegisteredExceptionBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
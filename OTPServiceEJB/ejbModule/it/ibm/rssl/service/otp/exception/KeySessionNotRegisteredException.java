/*    */ package it.ibm.rssl.service.otp.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeySessionNotRegisteredException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 7396469140003965910L;
/* 11 */   private String userId = null;
/*    */   
/* 13 */   private String keySession = null;
/*    */ 
/*    */   
/*    */   public KeySessionNotRegisteredException() {}
/*    */ 
/*    */   
/*    */   public KeySessionNotRegisteredException(String message, Throwable cause) {
/* 20 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public KeySessionNotRegisteredException(String message) {
/* 24 */     super(message);
/*    */   }
/*    */   
/*    */   public String getUserId() {
/* 28 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(String userId) {
/* 32 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public String getKeySession() {
/* 36 */     return this.keySession;
/*    */   }
/*    */   
/*    */   public void setKeySession(String keySession) {
/* 40 */     this.keySession = keySession;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\KeySessionNotRegisteredException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
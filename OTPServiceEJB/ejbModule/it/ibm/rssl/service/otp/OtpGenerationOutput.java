/*    */ package it.ibm.rssl.service.otp;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class OtpGenerationOutput
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = -5344303642878152218L;
/*  8 */   private String userId = null;
/*  9 */   private String keySession = null;
/* 10 */   private String oneTimePassword = null;
/* 11 */   private String remainingAttempts = null;
/* 12 */   private String initialCounter = null;
/*    */ 
/*    */   
/*    */   public OtpGenerationOutput() {}
/*    */   
/*    */   public OtpGenerationOutput(String userId, String otp, String remainingAttempts, String initialCounter, String keySession) {
/* 18 */     this.userId = userId;
/* 19 */     this.oneTimePassword = otp;
/* 20 */     this.keySession = keySession;
/* 21 */     this.remainingAttempts = remainingAttempts;
/* 22 */     this.initialCounter = initialCounter;
/*    */   }
/*    */   
/*    */   public String getUserId() {
/* 26 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(String userId) {
/* 30 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public String getOneTimePassword() {
/* 34 */     return this.oneTimePassword;
/*    */   }
/*    */   
/*    */   public void setOneTimePassword(String oneTimePassword) {
/* 38 */     this.oneTimePassword = oneTimePassword;
/*    */   }
/*    */   
/*    */   public String getRemainingAttempts() {
/* 42 */     return this.remainingAttempts;
/*    */   }
/*    */   
/*    */   public void setRemainingAttempts(String remainingAttempts) {
/* 46 */     this.remainingAttempts = remainingAttempts;
/*    */   }
/*    */   
/*    */   public String getInitialCounter() {
/* 50 */     return this.initialCounter;
/*    */   }
/*    */   
/*    */   public void setInitialCounter(String initialCounter) {
/* 54 */     this.initialCounter = initialCounter;
/*    */   }
/*    */   
/*    */   public String getKeySession() {
/* 58 */     return this.keySession;
/*    */   }
/*    */   
/*    */   public void setKeySession(String keySession) {
/* 62 */     this.keySession = keySession;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     String TAB = "    ";
/*    */     
/* 74 */     String retValue = "";
/*    */     
/* 76 */     retValue = "OtpGenerationOutput ( " + super.toString() + "    " + "userId = " + this.userId + "    " + 
/* 77 */       "oneTimePassword = *********" + "    " + "remainingAttempts = " + this.remainingAttempts + 
/* 78 */       "    " + "initialCounter = " + this.initialCounter + "    " + "keySession = " + this.keySession + "    " + " )";
/*    */     
/* 80 */     return retValue;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\OtpGenerationOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
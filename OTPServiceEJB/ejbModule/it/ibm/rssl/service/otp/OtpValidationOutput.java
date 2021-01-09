/*    */ package it.ibm.rssl.service.otp;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class OtpValidationOutput
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = -7135734329537999758L;
/*  8 */   private String userId = null;
/*  9 */   private String outputCode = null;
/* 10 */   private String keySession = null;
/* 11 */   private String remainingAttempts = null;
/* 12 */   private String initialCounter = null;
/*    */ 
/*    */   
/*    */   public OtpValidationOutput() {}
/*    */   
/*    */   public OtpValidationOutput(String userId, String outputCode, String keySession, String remainingAttempts, String initialCounter) {
/* 18 */     this.userId = userId;
/* 19 */     this.outputCode = outputCode;
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
/*    */   public String getKeySession() {
/* 34 */     return this.keySession;
/*    */   }
/*    */   
/*    */   public String getOutputCode() {
/* 38 */     return this.outputCode;
/*    */   }
/*    */   
/*    */   public void setOutputCode(String outputCode) {
/* 42 */     this.outputCode = outputCode;
/*    */   }
/*    */   
/*    */   public void setKeySession(String keySession) {
/* 46 */     this.keySession = keySession;
/*    */   }
/*    */   
/*    */   public String getRemainingAttempts() {
/* 50 */     return this.remainingAttempts;
/*    */   }
/*    */   
/*    */   public void setRemainingAttempts(String remainingAttempts) {
/* 54 */     this.remainingAttempts = remainingAttempts;
/*    */   }
/*    */   
/*    */   public String getInitialCounter() {
/* 58 */     return this.initialCounter;
/*    */   }
/*    */   
/*    */   public void setInitialCounter(String initialCounter) {
/* 62 */     this.initialCounter = initialCounter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String TAB = "    ";
/*    */     
/* 76 */     String retValue = "";
/*    */     
/* 78 */     retValue = "OtpValidationOutput ( " + 
/* 79 */       super.toString() + "    " + 
/* 80 */       "userId = " + this.userId + "    " + 
/* 81 */       "outputCode = " + this.outputCode + "    " + 
/* 82 */       "keySession = " + this.keySession + "    " + 
/* 83 */       "remainingAttempts = " + this.remainingAttempts + "    " + 
/* 84 */       "initialCounter = " + this.initialCounter + "    " + 
/* 85 */       " )";
/*    */     
/* 87 */     return retValue;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\OtpValidationOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
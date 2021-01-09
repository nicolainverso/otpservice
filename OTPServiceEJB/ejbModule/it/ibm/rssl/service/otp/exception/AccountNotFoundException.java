/*    */ package it.ibm.rssl.service.otp.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AccountNotFoundException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 9197855433817936754L;
/* 11 */   private String userId = null;
/*    */ 
/*    */   
/*    */   public AccountNotFoundException() {}
/*    */ 
/*    */   
/*    */   public AccountNotFoundException(String message, Throwable cause) {
/* 18 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public AccountNotFoundException(String message) {
/* 22 */     super(message);
/*    */   }
/*    */   
/*    */   public String getUserId() {
/* 26 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(String userId) {
/* 30 */     this.userId = userId;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\AccountNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
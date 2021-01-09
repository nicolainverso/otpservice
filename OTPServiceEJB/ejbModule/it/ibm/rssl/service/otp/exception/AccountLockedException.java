/*    */ package it.ibm.rssl.service.otp.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AccountLockedException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 6983738799172665382L;
/*    */   
/*    */   public AccountLockedException() {}
/*    */   
/*    */   public AccountLockedException(String message, Throwable cause) {
/* 19 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public AccountLockedException(String message) {
/* 23 */     super(message);
/*    */   }
/*    */   
/*    */   public AccountLockedException(Throwable cause) {
/* 27 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\AccountLockedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
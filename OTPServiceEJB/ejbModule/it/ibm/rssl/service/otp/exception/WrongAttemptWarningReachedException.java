/*    */ package it.ibm.rssl.service.otp.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WrongAttemptWarningReachedException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -1324460631267657471L;
/*    */   
/*    */   public WrongAttemptWarningReachedException() {}
/*    */   
/*    */   public WrongAttemptWarningReachedException(String message, Throwable cause) {
/* 16 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public WrongAttemptWarningReachedException(String message) {
/* 20 */     super(message);
/*    */   }
/*    */   
/*    */   public WrongAttemptWarningReachedException(Throwable cause) {
/* 24 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\WrongAttemptWarningReachedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
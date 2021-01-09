/*    */ package it.ibm.rssl.service.otp.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WrongMaxAttemptReachedException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -1324460631267657471L;
/*    */   
/*    */   public WrongMaxAttemptReachedException() {}
/*    */   
/*    */   public WrongMaxAttemptReachedException(String message, Throwable cause) {
/* 18 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public WrongMaxAttemptReachedException(String message) {
/* 22 */     super(message);
/*    */   }
/*    */   
/*    */   public WrongMaxAttemptReachedException(Throwable cause) {
/* 26 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\WrongMaxAttemptReachedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
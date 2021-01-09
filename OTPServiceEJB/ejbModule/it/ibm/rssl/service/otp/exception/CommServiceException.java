/*    */ package it.ibm.rssl.service.otp.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommServiceException
/*    */   extends PasswordServiceUnavailableException
/*    */ {
/*    */   private static final long serialVersionUID = -2847470864521421799L;
/*    */   
/*    */   public CommServiceException() {}
/*    */   
/*    */   public CommServiceException(String message, Throwable cause) {
/* 17 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public CommServiceException(String message) {
/* 21 */     super(message);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\CommServiceException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
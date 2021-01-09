/*    */ package it.ibm.rssl.service.otp.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PasswordExpiredException
/*    */   extends InvalidPasswordException
/*    */ {
/*    */   private static final long serialVersionUID = -4229947233806917617L;
/*    */   
/*    */   public PasswordExpiredException() {}
/*    */   
/*    */   public PasswordExpiredException(String message, Throwable cause) {
/* 16 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public PasswordExpiredException(String message) {
/* 20 */     super(message);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\PasswordExpiredException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
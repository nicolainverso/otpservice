/*    */ package it.ibm.rssl.service.otp.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PasswordNotSetException
/*    */   extends InvalidPasswordException
/*    */ {
/*    */   private static final long serialVersionUID = 5818281587303326873L;
/*    */   
/*    */   public PasswordNotSetException() {}
/*    */   
/*    */   public PasswordNotSetException(String message, Throwable cause) {
/* 17 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public PasswordNotSetException(String message) {
/* 21 */     super(message);
/*    */   }
/*    */   
/*    */   public PasswordNotSetException(Throwable cause) {
/* 25 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\PasswordNotSetException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
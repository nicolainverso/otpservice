/*    */ package it.ibm.rssl.service.otp.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WrongPasswordException
/*    */   extends InvalidPasswordException
/*    */ {
/*    */   private static final long serialVersionUID = -2078532443763749747L;
/*    */   
/*    */   public WrongPasswordException() {}
/*    */   
/*    */   public WrongPasswordException(String message, Throwable cause) {
/* 16 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public WrongPasswordException(String message) {
/* 20 */     super(message);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\WrongPasswordException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
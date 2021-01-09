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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvalidPasswordException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -8633996152691972987L;
/*    */   
/*    */   public InvalidPasswordException() {}
/*    */   
/*    */   public InvalidPasswordException(String message, Throwable cause) {
/* 29 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public InvalidPasswordException(String message) {
/* 33 */     super(message);
/*    */   }
/*    */   
/*    */   public InvalidPasswordException(Throwable cause) {
/* 37 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\InvalidPasswordException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
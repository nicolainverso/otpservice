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
/*    */ public class InternalOperationException
/*    */   extends PasswordServiceUnavailableException
/*    */ {
/*    */   private static final long serialVersionUID = 5339719402665360594L;
/*    */   
/*    */   public InternalOperationException() {}
/*    */   
/*    */   public InternalOperationException(String message, Throwable cause) {
/* 25 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public InternalOperationException(String message) {
/* 29 */     super(message);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\InternalOperationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
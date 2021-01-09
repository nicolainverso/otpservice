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
/*    */ public class AccountPersistenceException
/*    */   extends PasswordServiceUnavailableException
/*    */ {
/*    */   private static final long serialVersionUID = 4856452570316937773L;
/*    */   
/*    */   public AccountPersistenceException() {}
/*    */   
/*    */   public AccountPersistenceException(String message, Throwable cause) {
/* 25 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public AccountPersistenceException(String message) {
/* 29 */     super(message);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\exception\AccountPersistenceException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
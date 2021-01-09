/*    */ package it.ibm.rssl.service.otp.ldapusermgr.exception;
/*    */ 
/*    */ import it.ibm.rssl.service.otp.exception.PasswordServiceUnavailableException;
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
/*    */ public class LdapUserProxyException
/*    */   extends PasswordServiceUnavailableException
/*    */ {
/*    */   private static final long serialVersionUID = -8801052885884860890L;
/*    */   
/*    */   public LdapUserProxyException() {}
/*    */   
/*    */   public LdapUserProxyException(String message, Throwable cause) {
/* 25 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public LdapUserProxyException(String message) {
/* 29 */     super(message);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\ldapusermgr\exception\LdapUserProxyException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
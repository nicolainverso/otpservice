/*    */ package it.ibm.rssl.service.otp.ldapusermgr;
/*    */ 
/*    */ import it.ibm.rssl.service.otp.OtpUserInfo;
/*    */ import it.ibm.rssl.service.otp.ldapusermgr.exception.LdapUserProxyException;
/*    */ import it.ibm.rssl.service.otp.ldapusermgr.proxy.LdapUserProxy;
/*    */ import java.util.Date;
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
/*    */ public class LdapUserMgrServiceImpl
/*    */   implements LdapUserMgrServiceIF
/*    */ {
/* 21 */   private LdapUserProxy ldapUserProxy = new LdapUserProxy();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void incrementCountOtp(String userId) throws LdapUserProxyException {
/* 27 */     this.ldapUserProxy.updateFailureAttempts(userId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void lockAccount(String userId) throws LdapUserProxyException {
/* 34 */     this.ldapUserProxy.lockAccount(userId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public OtpUserInfo readOtp(String userId) throws LdapUserProxyException {
/* 41 */     OtpUserInfo otpInfo = this.ldapUserProxy.getOTPUserAttributeByUsername(userId);
/* 42 */     return otpInfo;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetCountOtp(String userId) throws LdapUserProxyException {
/* 49 */     this.ldapUserProxy.resetCountOtp(userId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetOtp(String userId) throws LdapUserProxyException {
/* 56 */     this.ldapUserProxy.resetOTP(userId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void saveOtp(String userId, String otp, String keySession, Date expDate) throws LdapUserProxyException {
/* 63 */     this.ldapUserProxy.setOneTimePassword(userId, otp);
/* 64 */     this.ldapUserProxy.setOTPExpireTime(userId, expDate);
/* 65 */     this.ldapUserProxy.setOTPOperationIdentifier(userId, keySession);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\ldapusermgr\LdapUserMgrServiceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
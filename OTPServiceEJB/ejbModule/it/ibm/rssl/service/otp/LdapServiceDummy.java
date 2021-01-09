/*    */ package it.ibm.rssl.service.otp;
/*    */ 
/*    */ import it.ibm.rssl.service.otp.exception.CommServiceException;
/*    */ import it.ibm.rssl.service.otp.util.LogUtil;
/*    */ import java.util.Date;
/*    */ import java.util.logging.Level;
/*    */ 
/*    */ public class LdapServiceDummy
/*    */   implements LdapServiceIF
/*    */ {
/* 11 */   private static OtpUserInfo user = new OtpUserInfo("user1", null, null, (short)0, false, "profile1", "operId1");
/*    */ 
/*    */   
/*    */   public OtpUserInfo readOtp(String userId) throws CommServiceException {
/* 15 */     if (!user.getUserId().equals(userId)) {
/* 16 */       return null;
/*    */     }
/* 18 */     LogUtil.LOGMGR.log(Level.INFO, "LdapServiceDummy (readOtp): userInfo=" + user);
/* 19 */     return user;
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveOtp(String userId, String otp, String keySession, Date expDate) {
/* 24 */     user.setOneTimePassword(otp);
/* 25 */     user.setExpirationDate(expDate);
/* 26 */     LogUtil.LOGMGR.log(Level.INFO, "LdapServiceDummy (saveOtp): userInfo=" + user);
/*    */   }
/*    */ 
/*    */   
/*    */   public void incrementCountOtp(String userId) throws CommServiceException {
/* 31 */     short currCount = user.getRetryCount();
/* 32 */     user.setRetryCount((short)(currCount + 1));
/* 33 */     LogUtil.LOGMGR.log(Level.INFO, "LdapServiceDummy (incrementCountOtp): userInfo=" + user);
/*    */   }
/*    */ 
/*    */   
/*    */   public void lockAccount(String userId) throws CommServiceException {
/* 38 */     user.setLocked(true);
/* 39 */     LogUtil.LOGMGR.log(Level.INFO, "LdapServiceDummy (lockAccount): userInfo=" + user);
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetCountOtp(String userId) throws CommServiceException {
/* 44 */     user.setRetryCount((short)0);
/* 45 */     LogUtil.LOGMGR.log(Level.INFO, "LdapServiceDummy (resetCountOtp): userInfo=" + user);
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetOtp(String userId) throws CommServiceException {
/* 50 */     user.setExpirationDate(null);
/* 51 */     user.setOneTimePassword(null);
/* 52 */     LogUtil.LOGMGR.log(Level.INFO, "LdapServiceDummy (resetOtp): userInfo=" + user);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unlockAccount(String userId) throws CommServiceException {
/* 57 */     LogUtil.LOGMGR.log(Level.INFO, "LdapServiceDummy (unlockAccount): userInfo=" + user);
/* 58 */     user.setLocked(false);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\LdapServiceDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package it.ibm.rssl.service.otp.bean;
/*     */ 
/*     */ import it.ibm.rssl.service.otp.ChannelInfo;
/*     */ import it.ibm.rssl.service.otp.OperationInfo;
/*     */ import it.ibm.rssl.service.otp.OtpGenerationOutput;
/*     */ import it.ibm.rssl.service.otp.OtpUserInfo;
/*     */ import it.ibm.rssl.service.otp.OtpValidationOutput;
/*     */ import it.ibm.rssl.service.otp.exception.AccountLockedException;
/*     */ import it.ibm.rssl.service.otp.exception.AccountNotFoundException;
/*     */ import it.ibm.rssl.service.otp.exception.IllegalParameterException;
/*     */ import it.ibm.rssl.service.otp.exception.InvalidPasswordException;
/*     */ import it.ibm.rssl.service.otp.exception.KeySessionNotRegisteredException;
/*     */ import it.ibm.rssl.service.otp.exception.PasswordExpiredException;
/*     */ import it.ibm.rssl.service.otp.exception.PasswordNotSetException;
/*     */ import it.ibm.rssl.service.otp.exception.PasswordServiceUnavailableException;
/*     */ import it.ibm.rssl.service.otp.exception.WrongAttemptWarningReachedException;
/*     */ import it.ibm.rssl.service.otp.exception.WrongMaxAttemptReachedException;
/*     */ import it.ibm.rssl.service.otp.exception.WrongPasswordException;
/*     */ import it.ibm.rssl.service.otp.ldapusermgr.LdapUserMgrServiceIF;
/*     */ import it.ibm.rssl.service.otp.util.ConfigProperties;
/*     */ import it.ibm.rssl.service.otp.util.LogUtil;
/*     */ import it.ibm.rssl.service.otp.util.OtpClientServiceManager;
/*     */ import it.ibm.rssl.service.otp.util.OtpServiceUtils;
/*     */ import it.ibm.rssl.service.otp.util.OtpValidator;
/*     */ import it.ibm.rssl.service.otp.util.PasswordService;
/*     */ import it.ibm.rssl.service.otp.util.PropertyKey;
/*     */ import java.util.Date;
/*     */ import java.util.logging.Level;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.jws.WebService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebService
/*     */ @Stateless
/*     */ public class OneTimePasswordServiceBean
/*     */   implements OneTimePasswordServiceBeanLocal
/*     */ {
/*  42 */   private static final String CLASS_NAME = OneTimePasswordServiceBean.class.getSimpleName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OtpGenerationOutput generateOtp(String userId, String keySession, ChannelInfo channel, OperationInfo operation) throws PasswordServiceUnavailableException, IllegalParameterException, AccountNotFoundException, AccountLockedException {
/*  54 */     String METHOD_NAME = "generateOtp";
/*  55 */     LogUtil.LOGMGR.entering(CLASS_NAME, "generateOtp", userId);
/*     */ 
/*     */     
/*  58 */     OtpServiceUtils.checkParam(userId);
/*  59 */     OtpServiceUtils.checkParam(keySession);
/*     */     
/*  61 */     if (channel != null) {
/*  62 */       OtpServiceUtils.checkParam(channel.getDeliveryType());
/*     */     } else {
/*  64 */       throw new IllegalParameterException("The input parameter is null or empty");
/*     */     } 
/*     */     
/*  67 */     if (operation != null) {
/*  68 */       OtpServiceUtils.checkParam(operation.getMcOperationId());
/*  69 */       OtpServiceUtils.checkParam(operation.getMcSessionId());
/*     */     } else {
/*  71 */       throw new IllegalParameterException("The input parameter is null or empty");
/*     */     } 
/*     */ 
/*     */     
/*  75 */     LogUtil.LOGMGR.logp(Level.FINE, CLASS_NAME, "generateOtp", "Session ID: " + operation.getMcSessionId() + 
/*  76 */         ",  Operation ID: " + operation.getMcOperationId() + ",  User ID: " + userId);
/*     */     
/*  78 */     LdapUserMgrServiceIF ldapSvc = OtpClientServiceManager.getInstance().geLdapService();
/*  79 */     OtpUserInfo otpInfo = ldapSvc.readOtp(userId);
/*     */ 
/*     */     
/*  82 */     if (otpInfo == null) {
/*  83 */       String msg = "Requested account is not registered in the system, userId=" + userId;
/*  84 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "generateOtp", msg);
/*  85 */       throw new AccountNotFoundException(msg);
/*     */     } 
/*     */ 
/*     */     
/*  89 */     if (otpInfo.isLocked()) {
/*  90 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "generateOtp", "Requested account is locked, userId=" + userId);
/*  91 */       throw new AccountLockedException("Requested account is locked, userId=" + userId);
/*     */     } 
/*     */ 
/*     */     
/*  95 */     String otp = PasswordService.getInstance().createNumericPwd(otpInfo.getProfileId(), 
/*  96 */         ConfigProperties.getIntegerValue(PropertyKey.OTP_LENGTH));
/*     */ 
/*     */ 
/*     */     
/* 100 */     int expSecs = ConfigProperties.getIntegerValue(PropertyKey.OTP_EXPIRATION_TIME_SECS_FOR_MOBILE);
/* 101 */     if (channel.getDeliveryType().equalsIgnoreCase("mail")) {
/* 102 */       expSecs = ConfigProperties.getIntegerValue(PropertyKey.OTP_EXPIRATION_TIME_SECS_FOR_MAIL);
/*     */     }
/* 104 */     LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "generateOtp", "To calculate the otp expiration time has been added " + expSecs + "seconds");
/* 105 */     Date expirationDate = OtpServiceUtils.addSecondsToDate(new Date(), expSecs);
/*     */ 
/*     */     
/* 108 */     String cryptedOtp = PasswordService.getInstance().encrypt(otp);
/*     */ 
/*     */     
/* 111 */     ldapSvc.saveOtp(userId, cryptedOtp, keySession, expirationDate);
/*     */     
/* 113 */     Short failedAttempts = new Short(otpInfo.getRetryCount());
/*     */     
/* 115 */     int allowedAttempts = ConfigProperties.getIntegerValue(PropertyKey.MAX_ALLOWED_FAILED_LOGIN);
/*     */     
/* 117 */     OtpGenerationOutput returnValue = new OtpGenerationOutput(userId, otp, String.valueOf(allowedAttempts - failedAttempts.shortValue()), String.valueOf(allowedAttempts), keySession);
/*     */     
/* 119 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "generateOtp");
/* 120 */     return returnValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OtpValidationOutput validateOtp(String userId, String keySession, String otpPassword, OperationInfo operation) throws PasswordServiceUnavailableException, IllegalParameterException, AccountNotFoundException, AccountLockedException, InvalidPasswordException, WrongMaxAttemptReachedException, KeySessionNotRegisteredException, WrongAttemptWarningReachedException {
/* 128 */     String METHOD_NAME = "validateOtp";
/* 129 */     LogUtil.LOGMGR.entering(CLASS_NAME, "validateOtp");
/*     */     
/* 131 */     if (LogUtil.LOGMGR.isLoggable(Level.FINEST)) {
/* 132 */       LogUtil.LOGMGR.logp(Level.FINEST, CLASS_NAME, "validateOtp", "userId=" + userId + ", otp=XXX" + ", keySession=" + keySession);
/*     */     }
/*     */     
/* 135 */     OtpServiceUtils.checkParam(userId);
/* 136 */     OtpServiceUtils.checkParam(keySession);
/* 137 */     OtpServiceUtils.checkParam(otpPassword);
/*     */     
/* 139 */     if (operation != null) {
/* 140 */       OtpServiceUtils.checkParam(operation.getMcOperationId());
/* 141 */       OtpServiceUtils.checkParam(operation.getMcSessionId());
/*     */     } else {
/* 143 */       throw new IllegalParameterException("The input parameter is null or empty");
/*     */     } 
/*     */ 
/*     */     
/* 147 */     LogUtil.LOGMGR.logp(Level.FINE, CLASS_NAME, "validateOtp", "Session ID: " + operation.getMcSessionId() + 
/* 148 */         ",  Operation ID: " + operation.getMcOperationId() + ",  User ID: " + userId);
/*     */ 
/*     */     
/* 151 */     LdapUserMgrServiceIF ldapSvc = OtpClientServiceManager.getInstance().geLdapService();
/* 152 */     OtpUserInfo otpInfo = ldapSvc.readOtp(userId);
/*     */ 
/*     */     
/* 155 */     if (otpInfo == null) {
/* 156 */       String msg = "Requested account is not registered in the system, userId=" + userId;
/* 157 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validateOtp", msg);
/* 158 */       throw new AccountNotFoundException(msg);
/*     */     } 
/*     */ 
/*     */     
/* 162 */     if (otpInfo.isLocked()) {
/* 163 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validateOtp", "Requested account is locked, userId=" + userId);
/* 164 */       throw new AccountLockedException("Requested account is locked, userId=" + userId);
/*     */     } 
/*     */ 
/*     */     
/* 168 */     String storedOtp = otpInfo.getOneTimePassword();
/* 169 */     if (OtpServiceUtils.isEmpty(storedOtp)) {
/* 170 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validateOtp", 
/* 171 */           "The account does not have an associated One Time Password, userId=" + userId);
/* 172 */       throw new PasswordNotSetException("Account does not have an associated One Time Password, userId=" + userId);
/*     */     } 
/*     */     
/* 175 */     int maxFailureThreshold = ConfigProperties.getIntegerValue(PropertyKey.MAX_ALLOWED_FAILED_LOGIN);
/* 176 */     int failureWarningThreshold = ConfigProperties.getIntegerValue(PropertyKey.FAILED_LOGIN_WARNING_THRESHOLD);
/*     */ 
/*     */     
/* 179 */     String storedKeyS = otpInfo.getOperationId();
/*     */     
/* 181 */     if (OtpServiceUtils.isEmpty(storedKeyS)) {
/* 182 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validateOtp", 
/* 183 */           "The account does not have an associated Key Session, userId=" + userId);
/* 184 */       throw new KeySessionNotRegisteredException("Account does not have an associated Key Session registered on the repository, userId=" + userId);
/*     */     } 
/*     */     
/* 187 */     String otpRemainingAttempts = String.valueOf(maxFailureThreshold);
/* 188 */     String outputCode = "OK";
/*     */     
/*     */     try {
/* 191 */       OtpValidator validator = new OtpValidator(otpInfo.getOneTimePassword(), otpInfo.getExpirationDate(), otpInfo.getOperationId());
/* 192 */       validator.validate(otpPassword, keySession);
/*     */ 
/*     */       
/* 195 */       ldapSvc.resetCountOtp(userId);
/* 196 */       ldapSvc.resetOtp(userId);
/*     */     }
/* 198 */     catch (WrongPasswordException e) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 203 */       if (otpInfo.getRetryCount() == failureWarningThreshold - 1) {
/* 204 */         ldapSvc.incrementCountOtp(userId);
/* 205 */         String msg = "Warning level of subsequent failed login attempts reached (userId=" + 
/* 206 */           userId + ")";
/* 207 */         LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validateOtp", msg);
/* 208 */         throw new WrongAttemptWarningReachedException(msg);
/*     */       } 
/* 210 */       if (otpInfo.getRetryCount() >= maxFailureThreshold - 1) {
/* 211 */         ldapSvc.incrementCountOtp(userId);
/* 212 */         String msg = "Maximum number of subsequent failed login attempts reached, the account has been locked (userId=" + 
/* 213 */           userId + ")";
/* 214 */         LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validateOtp", msg);
/* 215 */         ldapSvc.lockAccount(userId);
/* 216 */         throw new WrongMaxAttemptReachedException(msg);
/*     */       } 
/* 218 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validateOtp", 
/* 219 */           "Password is wrong: incrementing login failure counter...");
/* 220 */       ldapSvc.incrementCountOtp(userId);
/* 221 */       outputCode = "KO";
/* 222 */       otpRemainingAttempts = String.valueOf(maxFailureThreshold - otpInfo.getRetryCount() + 1);
/*     */ 
/*     */     
/*     */     }
/* 226 */     catch (PasswordExpiredException e1) {
/*     */       
/* 228 */       ldapSvc.resetOtp(userId);
/* 229 */       throw e1;
/*     */     } 
/*     */     
/* 232 */     LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validateOtp", "OTP is valid, userId=" + userId);
/*     */     
/* 234 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "validateOtp");
/*     */     
/* 236 */     return new OtpValidationOutput(userId, outputCode, keySession, otpRemainingAttempts, String.valueOf(maxFailureThreshold));
/*     */   }
/*     */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\bean\OneTimePasswordServiceBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
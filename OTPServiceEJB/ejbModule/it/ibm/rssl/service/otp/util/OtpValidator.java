/*     */ package it.ibm.rssl.service.otp.util;
/*     */ 
/*     */ import it.ibm.rssl.service.otp.exception.InternalOperationException;
/*     */ import it.ibm.rssl.service.otp.exception.PasswordExpiredException;
/*     */ import it.ibm.rssl.service.otp.exception.WrongPasswordException;
/*     */ import java.util.Date;
/*     */ import java.util.logging.Level;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OtpValidator
/*     */ {
/*  18 */   private static final String CLASS_NAME = OtpValidator.class.getSimpleName();
/*     */   
/*  20 */   private String expectedPassword = null;
/*     */   
/*  22 */   private Date expirationDate = null;
/*     */   
/*  24 */   private String expectedOperId = null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OtpValidator(String otpPassword, Date expirationDate, String operationId) throws IllegalArgumentException {
/*  41 */     if (OtpServiceUtils.isEmpty(otpPassword) || expirationDate == null) {
/*  42 */       throw new IllegalArgumentException("Some of the provided input parameters is null.");
/*     */     }
/*  44 */     this.expectedPassword = otpPassword;
/*  45 */     this.expirationDate = expirationDate;
/*  46 */     this.expectedOperId = operationId;
/*     */   }
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
/*     */ 
/*     */   
/*     */   public void validate(String otpPassword, String operationId) throws IllegalArgumentException, PasswordExpiredException, InternalOperationException, WrongPasswordException {
/*  71 */     String METHOD_NAME = "validate";
/*  72 */     LogUtil.LOGMGR.entering(CLASS_NAME, "validate");
/*     */     
/*  74 */     if (OtpServiceUtils.isEmpty(otpPassword)) {
/*  75 */       throw new IllegalArgumentException("Provided input password is null or empty.");
/*     */     }
/*     */ 
/*     */     
/*  79 */     Date now = new Date();
/*  80 */     if (now.after(this.expirationDate)) {
/*  81 */       String msg = "One Time Password is expired, please generate a new one.";
/*  82 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validate", msg);
/*  83 */       throw new PasswordExpiredException(msg);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  88 */     String cryptedPwd = PasswordService.getInstance().encrypt(otpPassword);
/*  89 */     if (!this.expectedPassword.equals(cryptedPwd)) {
/*  90 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validate", "Password does not match the expected one.");
/*  91 */       throw new WrongPasswordException("Password does not match the expected one.");
/*     */     } 
/*     */ 
/*     */     
/*  95 */     if (!this.expectedOperId.equals(operationId)) {
/*  96 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "validate", "Operation Identifier does not match the expected one");
/*  97 */       throw new WrongPasswordException("Operation Identifier does not match the expected one");
/*     */     } 
/*     */     
/* 100 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "validate");
/*     */   }
/*     */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\ot\\util\OtpValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
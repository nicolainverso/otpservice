/*     */ package it.ibm.rssl.service.otp;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class OtpUserInfo
/*     */ {
/*   7 */   private String userId = "";
/*     */   
/*   9 */   private String oneTimePassword = "";
/*     */   
/*  11 */   private Date expirationDate = null;
/*     */   
/*  13 */   private short retryCount = 0;
/*     */   
/*     */   private boolean isLocked = false;
/*     */   
/*  17 */   private String profileId = "";
/*     */   
/*  19 */   private String operationId = "";
/*     */   
/*     */   public OtpUserInfo(String userId, String otp, Date expDate, short retryCount, boolean isLocked, String profileId, String operationId) {
/*  22 */     this.userId = userId;
/*  23 */     this.oneTimePassword = otp;
/*  24 */     this.expirationDate = expDate;
/*  25 */     this.retryCount = retryCount;
/*  26 */     this.isLocked = isLocked;
/*  27 */     this.profileId = profileId;
/*  28 */     this.operationId = operationId;
/*     */   }
/*     */   
/*     */   public String getUserId() {
/*  32 */     return this.userId;
/*     */   }
/*     */   
/*     */   public String getOneTimePassword() {
/*  36 */     return this.oneTimePassword;
/*     */   }
/*     */   
/*     */   public Date getExpirationDate() {
/*  40 */     return this.expirationDate;
/*     */   }
/*     */   
/*     */   public short getRetryCount() {
/*  44 */     return this.retryCount;
/*     */   }
/*     */   
/*     */   public boolean isLocked() {
/*  48 */     return this.isLocked;
/*     */   }
/*     */   
/*     */   public String getProfileId() {
/*  52 */     return this.profileId;
/*     */   }
/*     */   
/*     */   public String getOperationId() {
/*  56 */     return this.operationId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserId(String userId) {
/*  66 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public void setOneTimePassword(String oneTimePassword) {
/*  70 */     this.oneTimePassword = oneTimePassword;
/*     */   }
/*     */   
/*     */   public void setExpirationDate(Date expirationDate) {
/*  74 */     this.expirationDate = expirationDate;
/*     */   }
/*     */   
/*     */   public void setRetryCount(short retryCount) {
/*  78 */     this.retryCount = retryCount;
/*     */   }
/*     */   
/*     */   public void setLocked(boolean isLocked) {
/*  82 */     this.isLocked = isLocked;
/*     */   }
/*     */   
/*     */   public void setProfileId(String profileId) {
/*  86 */     this.profileId = profileId;
/*     */   }
/*     */   
/*     */   public void setOperationId(String operationId) {
/*  90 */     this.operationId = operationId;
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
/*     */   public String toString() {
/* 102 */     String TAB = "    ";
/*     */     
/* 104 */     String retValue = "";
/*     */     
/* 106 */     retValue = "OtpUserInfo ( " + 
/* 107 */       super.toString() + "    " + 
/* 108 */       "userId = " + this.userId + "    " + 
/* 109 */       "oneTimePassword = *********" + "    " + 
/* 110 */       "expirationDate = " + this.expirationDate + "    " + 
/* 111 */       "retryCount = " + this.retryCount + "    " + 
/* 112 */       "isLocked = " + this.isLocked + "    " + 
/* 113 */       "profileId = " + this.profileId + "    " + 
/* 114 */       "operationId = " + this.operationId + "    " + 
/* 115 */       " )";
/*     */     
/* 117 */     return retValue;
/*     */   }
/*     */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\OtpUserInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package it.ibm.rssl.service.otp.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum PropertyKey
/*     */ {
/*  12 */   OTP_EXPIRATION_TIME_SECS_FOR_MAIL("otp.service.password.expiration.mail.secs", "180"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  18 */   OTP_EXPIRATION_TIME_SECS_FOR_MOBILE("otp.service.password.expiration.mobile.secs", "180"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   OTP_LENGTH("otp.service.password.length", "6"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  29 */   MAX_ALLOWED_FAILED_LOGIN("otp.service.password.wrong.threshold", "6"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   FAILED_LOGIN_WARNING_THRESHOLD("otp.service.password.wrong.warning", "3"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   LDAP_SRV_HOST("otp.ldap.connection.hostname", "hostname"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   LDAP_SRV_PORT("otp.ldap.connection.port", "389"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   LDAP_ADMIN_USER("otp.ldap.connection.admin.user", "cn=root"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   LDAP_ADMIN_PWD("otp.ldap.connection.admin.password", "password"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   LDAP_BASE_DN("otp.ldap.connection.schema.userBase", "cn=Users,SecAuthority=Default"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   LDAP_USER_FORMAT("otp.ldap.connection.schema.userDataFormat", "standard"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   LDAP_ATT_OTP("otp.ldap.attribute.password", "oneTimePassword"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   LDAP_ATT_EXPIRE_OTP("otp.ldap.attribute.expiration.time", "OTPExpirationTime"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   LDAP_ATT_OTP_WRONG_ATTEMPT("otp.ldap.attribute.attempt.unsuccessful", "unsuccessfulOtpAttemptCount"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   LDAP_ATT_OTP_OPER_ID("otp.ldap.attribute.operation.identifier", "OTPOperationIdentifier"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   LDAP_ATT_OTP_ALG("otp.ldap.attribute.algorithm", "uid"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   LDAP_CONNECTION_POOL_TIMEOUT("otp.ldap.connection.pool.connect.timeout", ""),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   LDAP_CONNECTION_TIMEOUT("otp.ldap.connection.pool.timeout", ""),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   LDAP_CONNECTION_POOL_MAXSIZE("otp.ldap.connection.pool.maxsize", "");
/*     */ 
/*     */ 
/*     */   
/*     */   private String keyName;
/*     */ 
/*     */ 
/*     */   
/*     */   private String defValue;
/*     */ 
/*     */ 
/*     */   
/*     */   PropertyKey(String keyName, String defValue) {
/* 118 */     this.keyName = keyName;
/* 119 */     this.defValue = defValue;
/*     */   }
/*     */   
/*     */   public String getKeyName() {
/* 123 */     return this.keyName;
/*     */   }
/*     */   
/*     */   public String getDefaultValue() {
/* 127 */     return this.defValue; } public static PropertyKey keyNameValueOf(String keyName) {
/*     */     byte b;
/*     */     int i;
/*     */     PropertyKey[] arrayOfPropertyKey;
/* 131 */     for (i = (arrayOfPropertyKey = values()).length, b = 0; b < i; ) { PropertyKey property = arrayOfPropertyKey[b];
/* 132 */       if (property.getKeyName().equals(keyName))
/* 133 */         return property; 
/*     */       b++; }
/*     */     
/* 136 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceCommon.jar!\it\ibm\rssl\service\ot\\util\PropertyKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
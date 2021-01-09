/*     */ package it.ibm.rssl.service.otp.ldapusermgr.proxy;
/*     */ 
/*     */ import it.ibm.rssl.service.otp.OtpUserInfo;
/*     */ import it.ibm.rssl.service.otp.ldapusermgr.exception.LdapUserProxyException;
/*     */ import it.ibm.rssl.service.otp.util.ConfigProperties;
/*     */ import it.ibm.rssl.service.otp.util.LdapConnectionPool;
/*     */ import it.ibm.rssl.service.otp.util.LogUtil;
/*     */ import it.ibm.rssl.service.otp.util.PropertyKey;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.logging.Level;
/*     */ import javax.naming.NamingEnumeration;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.directory.Attribute;
/*     */ import javax.naming.directory.Attributes;
/*     */ import javax.naming.directory.BasicAttribute;
/*     */ import javax.naming.directory.DirContext;
/*     */ import javax.naming.directory.ModificationItem;
/*     */ import javax.naming.directory.SearchControls;
/*     */ import javax.naming.directory.SearchResult;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LdapUserProxy
/*     */ {
/*  29 */   private static final String CLASS_NAME = LdapUserProxy.class.getSimpleName();
/*     */   
/*     */   private static final String FORMAT_LDAP_TIME = "yyyyMMddHHmmssZ";
/*     */   
/*     */   private static final String DEFAULT_TIME_VALUE = "00010101000000+0000";
/*  34 */   private final String USER_ID_ATTRIBUTE = "uid";
/*  35 */   private final String ACCOUNT_VALIDITY_ATT = "secAcctValid";
/*  36 */   private final String SECURITY_DN_ATT = "SecDN";
/*  37 */   private final String SECUUID = "secUUID";
/*  38 */   private final String SECAUTHORITY = "cn=Users,SecAuthority=Default";
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
/*     */   public OtpUserInfo getOTPUserAttributeByUsername(String userId) throws LdapUserProxyException {
/*  52 */     String METHOD_NAME = "getOTPUserAttributeByUsername";
/*  53 */     LogUtil.LOGMGR.entering(CLASS_NAME, "getOTPUserAttributeByUsername");
/*     */     
/*  55 */     OtpUserInfo otpUserInfo = null;
/*     */     
/*  57 */     NamingEnumeration<SearchResult> userSearchResults = null;
/*  58 */     NamingEnumeration<SearchResult> principalSearchResults = null;
/*  59 */     DirContext ldapConn = null;
/*     */ 
/*     */     
/*     */     try {
/*  63 */       SearchControls sc = new SearchControls();
/*  64 */       sc.setSearchScope(2);
/*  65 */       sc.setCountLimit(0L);
/*     */       
/*  67 */       ldapConn = LdapConnectionPool.getConnection();
/*     */       
/*  69 */       String baseDN = ConfigProperties.getStringValue(PropertyKey.LDAP_BASE_DN);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  75 */       String[] otpAttributes = {
/*  76 */           "uid", 
/*  77 */           ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP), 
/*  78 */           ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_EXPIRE_OTP), 
/*  79 */           ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_WRONG_ATTEMPT), 
/*  80 */           ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_OPER_ID), 
/*  81 */           ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_ALG) };
/*  82 */       sc.setReturningAttributes(otpAttributes);
/*  83 */       userSearchResults = ldapConn.search(baseDN, "uid=" + userId, sc);
/*     */ 
/*     */       
/*  86 */       String[] securityAttributes = { "secAcctValid" };
/*  87 */       sc.setReturningAttributes(securityAttributes);
/*  88 */       principalSearchResults = ldapConn.search(baseDN, "(&(principalName=" + userId + ")(secHasPolicy=*))", sc);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       if (userSearchResults.hasMore() && principalSearchResults.hasMore())
/*     */       {
/*  96 */         LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "getOTPUserAttributeByUsername", "Retrieved OTP info for userId=" + userId);
/*     */         
/*  98 */         SearchResult userSearchItem = userSearchResults.next();
/*  99 */         Attributes otpAttributeValues = userSearchItem.getAttributes();
/*     */         
/* 101 */         SearchResult securitySearchItem = principalSearchResults.next();
/* 102 */         Attributes securityAttributeValue = securitySearchItem.getAttributes();
/*     */         
/* 104 */         otpUserInfo = populateOtpUser(otpAttributeValues, securityAttributeValue);
/*     */       }
/*     */       else
/*     */       {
/* 108 */         LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "getOTPUserAttributeByUsername", "Retrieved NO OTP info for userId=" + userId + ". This user does not exists");
/*     */       }
/*     */     
/* 111 */     } catch (NamingException e) {
/* 112 */       String msg = "It is no possible to retrieve OTP info for userId=" + userId + 
/* 113 */         ". The following problem is occurred: " + e.getMessage();
/* 114 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "getOTPUserAttributeByUsername", msg);
/* 115 */       throw new LdapUserProxyException(msg, e);
/*     */     } finally {
/*     */       
/*     */       try {
/* 119 */         if (principalSearchResults != null) {
/* 120 */           principalSearchResults.close();
/*     */         }
/*     */         
/* 123 */         if (userSearchResults != null) {
/* 124 */           userSearchResults.close();
/*     */         }
/*     */         
/* 127 */         if (ldapConn != null) {
/* 128 */           LdapConnectionPool.closeConnection(ldapConn);
/*     */         }
/* 130 */       } catch (NamingException in) {
/* 131 */         String msg = "It is no possible to close the context. The following problem is occurred: " + in.getMessage();
/* 132 */         LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "getOTPUserAttributeByUsername", msg);
/* 133 */         throw new LdapUserProxyException(msg, in);
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "getOTPUserAttributeByUsername");
/* 138 */     return otpUserInfo;
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
/*     */   public void setOneTimePassword(String userId, String otp) throws LdapUserProxyException {
/* 154 */     String METHOD_NAME = "setOneTimePassword";
/* 155 */     LogUtil.LOGMGR.entering(CLASS_NAME, "setOneTimePassword");
/*     */     
/* 157 */     setAttribute(userId, ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP), new Object[] { otp }, false);
/*     */     
/* 159 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "setOneTimePassword");
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
/*     */   public void setOTPExpireTime(String userId, Date otpExpire) throws LdapUserProxyException {
/* 175 */     String METHOD_NAME = "setOTPExpireTime";
/* 176 */     LogUtil.LOGMGR.entering(CLASS_NAME, "setOTPExpireTime");
/*     */     
/* 178 */     String otpExpTime = getFormattedTime(otpExpire);
/*     */     
/* 180 */     setAttribute(userId, ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_EXPIRE_OTP), new Object[] { otpExpTime }, false);
/*     */     
/* 182 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "setOTPExpireTime");
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
/*     */   public void setOTPOperationIdentifier(String userId, String operationId) throws LdapUserProxyException {
/* 197 */     String METHOD_NAME = "setOTPOperationIdentifier";
/* 198 */     LogUtil.LOGMGR.entering(CLASS_NAME, "setOTPOperationIdentifier");
/*     */     
/* 200 */     setAttribute(userId, ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_OPER_ID), new Object[] { operationId }, false);
/*     */     
/* 202 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "setOTPOperationIdentifier");
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
/*     */   public void resetCountOtp(String userId) throws LdapUserProxyException {
/* 215 */     String METHOD_NAME = "resetCountOtp";
/* 216 */     LogUtil.LOGMGR.entering(CLASS_NAME, "resetCountOtp");
/*     */     
/* 218 */     setAttribute(userId, ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_WRONG_ATTEMPT), new Object[] { Integer.toString(0) }, false);
/*     */     
/* 220 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "resetCountOtp");
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
/*     */   public void updateFailureAttempts(String userId) throws LdapUserProxyException {
/* 233 */     String METHOD_NAME = "updateFailureAttempts";
/* 234 */     LogUtil.LOGMGR.entering(CLASS_NAME, "updateFailureAttempts");
/*     */     
/* 236 */     DirContext ldapConn = null;
/* 237 */     NamingEnumeration<SearchResult> pwdPolicyResult = null;
/*     */ 
/*     */     
/*     */     try {
/* 241 */       SearchControls sc = new SearchControls();
/* 242 */       sc.setSearchScope(2);
/* 243 */       sc.setReturningAttributes(new String[] { ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_WRONG_ATTEMPT) });
/* 244 */       sc.setCountLimit(0L);
/*     */       
/* 246 */       String baseDN = ConfigProperties.getStringValue(PropertyKey.LDAP_BASE_DN);
/*     */       
/* 248 */       ldapConn = LdapConnectionPool.getConnection();
/* 249 */       pwdPolicyResult = ldapConn.search(baseDN, 
/* 250 */           "uid=" + userId, 
/* 251 */           sc);
/*     */ 
/*     */       
/* 254 */       if (pwdPolicyResult.hasMore())
/*     */       {
/* 256 */         SearchResult _searchItem = pwdPolicyResult.next();
/* 257 */         Attributes attribute = _searchItem.getAttributes();
/* 258 */         Attribute loginAttribute = attribute.get(ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_WRONG_ATTEMPT));
/*     */         
/* 260 */         if (loginAttribute != null)
/*     */         {
/* 262 */           String _failure = (String)loginAttribute.get();
/* 263 */           int loginFailure = Integer.valueOf(_failure).intValue();
/* 264 */           loginFailure++;
/* 265 */           setAttribute(userId, ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_WRONG_ATTEMPT), new Object[] { Integer.toString(loginFailure) }, false);
/*     */         }
/*     */         else
/*     */         {
/* 269 */           setAttribute(userId, ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_WRONG_ATTEMPT), new Object[] { Integer.toString(1) }, false);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 274 */     } catch (NamingException e) {
/* 275 */       String msg = "It is not possible to update the failed attempt count on the repository for userId:" + userId + 
/* 276 */         ". The following problem is occurred: " + e.getMessage();
/* 277 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "updateFailureAttempts", msg);
/* 278 */       throw new LdapUserProxyException(msg, e);
/*     */     } finally {
/*     */       
/*     */       try {
/* 282 */         if (pwdPolicyResult != null) {
/* 283 */           pwdPolicyResult.close();
/*     */         }
/* 285 */         if (ldapConn != null) {
/* 286 */           LdapConnectionPool.closeConnection(ldapConn);
/*     */         }
/* 288 */       } catch (NamingException in) {
/* 289 */         String msg = "It is no possible to close the context. The following problem is occurred: " + in.getMessage();
/* 290 */         LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "updateFailureAttempts", msg);
/* 291 */         throw new LdapUserProxyException(msg, in);
/*     */       } 
/*     */     } 
/*     */     
/* 295 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "updateFailureAttempts");
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
/*     */   public void lockAccount(String userId) throws LdapUserProxyException {
/* 307 */     String METHOD_NAME = "lockAccount";
/* 308 */     LogUtil.LOGMGR.entering(CLASS_NAME, "lockAccount");
/*     */     
/* 310 */     setAttribute(userId, "secAcctValid", new Object[] { "FALSE" }, true);
/*     */     
/* 312 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "lockAccount");
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
/*     */   public void resetOTP(String userId) throws LdapUserProxyException {
/* 326 */     String METHOD_NAME = "resetOTP";
/* 327 */     LogUtil.LOGMGR.entering(CLASS_NAME, "resetOTP");
/*     */     
/* 329 */     setAttribute(userId, ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP), new Object[] { "" }, false);
/* 330 */     setAttribute(userId, ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_OPER_ID), new Object[] { "" }, false);
/* 331 */     setAttribute(userId, ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_EXPIRE_OTP), new Object[] { "00010101000000+0000" }, false);
/*     */     
/* 333 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "resetOTP");
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
/*     */   private OtpUserInfo populateOtpUser(Attributes otpUserValues, Attributes securityUserValues) throws LdapUserProxyException {
/* 347 */     String METHOD_NAME = "populateOtpUser";
/* 348 */     LogUtil.LOGMGR.entering(CLASS_NAME, "populateOtpUser");
/*     */     
/* 350 */     String userId = "";
/* 351 */     String otpValue = "";
/* 352 */     Date expireOtpDate = null;
/* 353 */     short retryCount = 0;
/* 354 */     boolean isLocked = false;
/* 355 */     String algValue = "";
/* 356 */     String operIDValue = "";
/*     */     
/*     */     try {
/* 359 */       Attribute username = otpUserValues.get("uid");
/* 360 */       userId = (String)username.get();
/*     */       
/* 362 */       Attribute otpValueAtt = otpUserValues.get(ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP));
/* 363 */       if (otpValueAtt != null) {
/* 364 */         otpValue = (String)otpValueAtt.get();
/*     */       }
/*     */       
/* 367 */       Attribute expireValueAtt = otpUserValues.get(ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_EXPIRE_OTP));
/* 368 */       if (expireValueAtt != null) {
/* 369 */         String expireValue = (String)expireValueAtt.get();
/*     */         
/* 371 */         Integer year = new Integer(expireValue.substring(0, 4));
/* 372 */         Integer month = new Integer(expireValue.substring(4, 6));
/* 373 */         Integer day = new Integer(expireValue.substring(6, 8));
/* 374 */         Integer hour = new Integer(expireValue.substring(8, 10));
/* 375 */         Integer min = new Integer(expireValue.substring(10, 12));
/* 376 */         Integer sec = new Integer(expireValue.substring(12, 14));
/*     */         
/* 378 */         Calendar expirationDate = 
/* 379 */           new GregorianCalendar(
/* 380 */             year.intValue(), 
/* 381 */             month.intValue() - 1, 
/* 382 */             day.intValue(), 
/* 383 */             hour.intValue(), 
/* 384 */             min.intValue(), 
/* 385 */             sec.intValue());
/*     */         
/* 387 */         long millis = expirationDate.getTimeInMillis();
/* 388 */         expireOtpDate = new Date(millis);
/*     */       } 
/*     */ 
/*     */       
/* 392 */       Attribute operIDValueAtt = otpUserValues.get(ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_OPER_ID));
/* 393 */       if (operIDValueAtt != null) {
/* 394 */         operIDValue = (String)operIDValueAtt.get();
/*     */       }
/*     */       
/* 397 */       Attribute wrongAttemptValueAtt = otpUserValues.get(ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_WRONG_ATTEMPT));
/* 398 */       if (wrongAttemptValueAtt != null) {
/* 399 */         String wrongAttemptValue = (String)wrongAttemptValueAtt.get();
/* 400 */         retryCount = (new Short(wrongAttemptValue)).shortValue();
/*     */       } 
/*     */ 
/*     */       
/* 404 */       Attribute algValueAtt = otpUserValues.get(ConfigProperties.getStringValue(PropertyKey.LDAP_ATT_OTP_ALG));
/* 405 */       if (algValueAtt != null) {
/* 406 */         algValue = (String)algValueAtt.get();
/*     */       }
/*     */       
/* 409 */       Attribute activeValueAtt = securityUserValues.get("secAcctValid");
/* 410 */       if (activeValueAtt != null) {
/* 411 */         NamingEnumeration<?> values = activeValueAtt.getAll();
/* 412 */         Boolean accountValid = new Boolean((String)values.next());
/* 413 */         isLocked = !accountValid.booleanValue();
/*     */       }
/*     */     
/* 416 */     } catch (NamingException e) {
/* 417 */       String msg = "Unable to populate the structure for the user:" + userId + ". The following problem is occurred: " + e.getMessage();
/* 418 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "populateOtpUser", msg, e);
/* 419 */       throw new LdapUserProxyException(msg, e);
/*     */     } 
/*     */ 
/*     */     
/* 423 */     OtpUserInfo otpInfo = new OtpUserInfo(userId, otpValue, expireOtpDate, retryCount, isLocked, algValue, operIDValue);
/*     */     
/* 425 */     LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "populateOtpUser", "OtpUserInfo for " + userId + ": " + otpInfo.toString());
/*     */     
/* 427 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "populateOtpUser");
/* 428 */     return otpInfo;
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
/*     */   private void setAttribute(String userId, String attributeName, Object[] attributeValueArray, boolean isPrincipalAttribute) throws LdapUserProxyException {
/* 451 */     String METHOD_NAME = "setAttribute";
/* 452 */     LogUtil.LOGMGR.entering(CLASS_NAME, "setAttribute");
/* 453 */     ModificationItem[] mods = new ModificationItem[1];
/*     */     
/* 455 */     Attribute mod0 = new BasicAttribute(attributeName);
/*     */     
/* 457 */     for (int i = 0; attributeValueArray != null && i < attributeValueArray.length; i++) {
/*     */       
/* 459 */       if (attributeValueArray[i] != null) {
/*     */         
/* 461 */         mod0.add(attributeValueArray[i].toString());
/*     */       }
/*     */       else {
/*     */         
/* 465 */         mod0.add(null);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 470 */     mods[0] = new ModificationItem(2, mod0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 477 */       DirContext ldapConn = LdapConnectionPool.getConnection();
/*     */       
/* 479 */       String secureDN = getSecurityDn(userId, ldapConn, isPrincipalAttribute);
/*     */       
/* 481 */       ldapConn.modifyAttributes(secureDN, mods);
/*     */       
/* 483 */       LdapConnectionPool.closeConnection(ldapConn);
/*     */     }
/* 485 */     catch (NamingException e) {
/* 486 */       String msg = "Unable to set the : " + attributeName + " attribute for userId=" + userId + ". The following problem is occurred: " + 
/* 487 */         e.getMessage();
/* 488 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "setAttribute", msg, e);
/* 489 */       throw new LdapUserProxyException(msg, e);
/*     */     } 
/*     */     
/* 492 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "setAttribute");
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
/*     */   private String getFormattedTime(Date date) {
/* 505 */     String METHOD_NAME = "getFormattedTime";
/* 506 */     LogUtil.LOGMGR.entering(CLASS_NAME, "getFormattedTime");
/*     */     
/* 508 */     String time = null;
/* 509 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssZ");
/* 510 */     time = dateFormat.format(date);
/*     */     
/* 512 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "getFormattedTime");
/* 513 */     return time;
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
/*     */   private String getSecurityDn(String userId, DirContext ldapConn, boolean isPrincipal) throws LdapUserProxyException {
/* 533 */     String METHOD_NAME = "getSecurityDn";
/* 534 */     LogUtil.LOGMGR.entering(CLASS_NAME, "getSecurityDn");
/*     */     
/* 536 */     String secDN = null;
/*     */     
/*     */     try {
/* 539 */       if (ConfigProperties.getStringValue(PropertyKey.LDAP_USER_FORMAT).equalsIgnoreCase("minimal")) {
/* 540 */         if (isPrincipal) {
/*     */           
/* 542 */           secDN = "principalName=" + userId + "," + ConfigProperties.getStringValue(PropertyKey.LDAP_BASE_DN);
/*     */         } else {
/*     */           
/* 545 */           SearchControls sc = new SearchControls();
/* 546 */           sc.setSearchScope(2);
/* 547 */           sc.setCountLimit(0L);
/*     */           
/* 549 */           NamingEnumeration<SearchResult> principalSearchResults = null;
/* 550 */           String baseDN = ConfigProperties.getStringValue(PropertyKey.LDAP_BASE_DN);
/*     */ 
/*     */           
/* 553 */           String[] securityAttributes = { "SecDN" };
/* 554 */           sc.setReturningAttributes(securityAttributes);
/* 555 */           principalSearchResults = ldapConn.search(baseDN, "(&(principalName=" + userId + ")(secHasPolicy=*))", sc);
/*     */           
/* 557 */           SearchResult securitySearchItem = principalSearchResults.next();
/* 558 */           Attributes securityAttributeValue = securitySearchItem.getAttributes();
/*     */           
/* 560 */           Attribute activeValueAtt = securityAttributeValue.get("SecDN");
/*     */           
/* 562 */           if (activeValueAtt != null) {
/* 563 */             NamingEnumeration<?> values = activeValueAtt.getAll();
/* 564 */             secDN = (String)values.next();
/*     */           } 
/*     */           
/* 567 */           principalSearchResults.close();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 572 */         SearchControls sc = new SearchControls();
/* 573 */         sc.setSearchScope(2);
/* 574 */         sc.setCountLimit(0L);
/*     */         
/* 576 */         NamingEnumeration<SearchResult> principalSearchResults = null;
/* 577 */         NamingEnumeration<SearchResult> secUUIDSearchResults = null;
/* 578 */         String baseDN = ConfigProperties.getStringValue(PropertyKey.LDAP_BASE_DN);
/*     */ 
/*     */         
/* 581 */         String[] securityAttributes = { "secUUID" };
/* 582 */         sc.setReturningAttributes(securityAttributes);
/* 583 */         principalSearchResults = ldapConn.search(baseDN, "(&(principalName=" + userId + ")(secHasPolicy=*))", sc);
/*     */         
/* 585 */         SearchResult securitySearchItem = principalSearchResults.next();
/* 586 */         Attributes securityAttributeValue = securitySearchItem.getAttributes();
/*     */         
/* 588 */         Attribute secUUidValueAtt = securityAttributeValue.get("secUUID");
/*     */         
/* 590 */         String secUUidVal = "";
/* 591 */         if (secUUidValueAtt != null) {
/* 592 */           NamingEnumeration<?> values = secUUidValueAtt.getAll();
/* 593 */           secUUidVal = (String)values.next();
/*     */         } 
/*     */ 
/*     */         
/* 597 */         String[] secUUIDAttributes = { "SecDN" };
/* 598 */         sc.setReturningAttributes(secUUIDAttributes);
/* 599 */         secUUIDSearchResults = ldapConn.search("secUUID=" + secUUidVal + "," + "cn=Users,SecAuthority=Default", "secUUID=" + secUUidVal, sc);
/*     */         
/* 601 */         SearchResult secUUIDSearchItem = secUUIDSearchResults.next();
/* 602 */         Attributes secUUIDAttributeValue = secUUIDSearchItem.getAttributes();
/*     */         
/* 604 */         Attribute secUUIDValueAtt = secUUIDAttributeValue.get("SecDN");
/* 605 */         if (secUUIDValueAtt != null) {
/* 606 */           NamingEnumeration<?> values = secUUIDValueAtt.getAll();
/* 607 */           secDN = (String)values.next();
/*     */         } 
/*     */         
/* 610 */         if (secDN == null) {
/* 611 */           String msg = "Unable to get the Destinguish Name for userId:" + userId + ". The following problem is occurred: Attribute " + "SecDN" + " not found.";
/* 612 */           LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "getSecurityDn", msg);
/* 613 */           throw new LdapUserProxyException(msg);
/*     */         } 
/*     */         
/* 616 */         if (isPrincipal) {
/* 617 */           secDN = "secAuthority=Default," + secDN;
/*     */         }
/*     */         
/* 620 */         principalSearchResults.close();
/* 621 */         secUUIDSearchResults.close();
/*     */       }
/*     */     
/* 624 */     } catch (NamingException e) {
/* 625 */       String msg = "Unable to get the Destinguish Name for userId:" + userId + ". The following problem is occurred: " + e.getMessage();
/* 626 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "getSecurityDn", msg, e);
/* 627 */       throw new LdapUserProxyException(msg, e);
/*     */     } 
/*     */     
/* 630 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "getSecurityDn");
/* 631 */     return secDN;
/*     */   }
/*     */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\ldapusermgr\proxy\LdapUserProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
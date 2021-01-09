/*    */ package it.ibm.rssl.service.otp.util;
/*    */ 
/*    */ import it.ibm.rssl.service.otp.ldapusermgr.LdapUserMgrServiceIF;
/*    */ 
/*    */ public class OtpClientServiceManager
/*    */ {
/*    */   static class ServiceImpl {
/*  8 */     private String key = null;
/*  9 */     private String defValue = null;
/*    */     public ServiceImpl(String key, String defValue) {
/* 11 */       this.key = key;
/* 12 */       this.defValue = defValue;
/*    */     }
/*    */     public String getKey() {
/* 15 */       return this.key;
/*    */     }
/*    */     public String getDefValue() {
/* 18 */       return this.defValue;
/*    */     }
/*    */   }
/*    */   
/* 22 */   private static final ServiceImpl LDAP_SVC = new ServiceImpl("otp.client.service.impl", "it.ibm.rssl.service.otp.ldapusermgr.LdapUserMgrServiceImpl");
/* 23 */   private static OtpClientServiceManager me = new OtpClientServiceManager();
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
/*    */   public static OtpClientServiceManager getInstance() {
/* 36 */     return me;
/*    */   }
/*    */   
/*    */   public LdapUserMgrServiceIF geLdapService() {
/* 40 */     return (LdapUserMgrServiceIF)ServiceUtils.getServiceImpl(LDAP_SVC.getKey(), LDAP_SVC.getDefValue());
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\ot\\util\OtpClientServiceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
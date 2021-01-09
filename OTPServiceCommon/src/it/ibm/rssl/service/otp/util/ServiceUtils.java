/*    */ package it.ibm.rssl.service.otp.util;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ 
/*    */ public class ServiceUtils
/*    */ {
/*  7 */   private static final String CLASS_NAME = ServiceUtils.class.getSimpleName();
/*    */ 
/*    */   
/*    */   public static <T> T getServiceImpl(String serviceImplKey, String defaultServiceImpl) {
/* 11 */     String METHOD_NAME = "getServiceImpl";
/* 12 */     LogUtil.LOGCMM.entering(CLASS_NAME, "getServiceImpl");
/*    */     
/* 14 */     T srv = null;
/* 15 */     String propVal = null;
/* 16 */     Class<? extends T> cls = null;
/*    */     try {
/* 18 */       propVal = ConfigProperties.getConfig().getProperties().getProperty(serviceImplKey, defaultServiceImpl);
/*    */       try {
/* 20 */         LogUtil.LOGCMM.logp(Level.INFO, CLASS_NAME, "getServiceImpl", "Attempting to load class: " + propVal + " from current classloader");
/* 21 */         cls = (Class)Class.forName(propVal);
/* 22 */       } catch (ClassNotFoundException e) {
/* 23 */         ClassLoader cl = ClassLoader.getSystemClassLoader();
/* 24 */         if (cl != null) {
/*    */           try {
/* 26 */             LogUtil.LOGCMM.logp(Level.INFO, CLASS_NAME, "getServiceImpl", "Attempting to load class: " + propVal + " from System Classloader");
/* 27 */             cls = (Class)cl.loadClass(propVal);
/* 28 */           } catch (ClassNotFoundException ee) {
/* 29 */             String errorMsg = "Service class not found: " + serviceImplKey + "=" + propVal;
/* 30 */             LogUtil.LOGCMM.logp(Level.SEVERE, CLASS_NAME, "getServiceImpl", errorMsg, e);
/* 31 */             throw new RuntimeException(errorMsg);
/*    */           } 
/*    */         }
/*    */       } 
/* 35 */       srv = cls.newInstance();
/* 36 */       LogUtil.LOGCMM.finest("Class successfully instantiated: " + srv.getClass().getCanonicalName());
/* 37 */     } catch (InstantiationException e) {
/* 38 */       LogUtil.LOGCMM.throwing(CLASS_NAME, "getServiceImpl", e);
/* 39 */       String errorMsg = "Impossible to instatiate class: " + serviceImplKey + "=" + propVal;
/* 40 */       LogUtil.LOGCMM.logp(Level.SEVERE, CLASS_NAME, "getServiceImpl", errorMsg, e);
/* 41 */       throw new RuntimeException(errorMsg);
/* 42 */     } catch (IllegalAccessException e) {
/* 43 */       String errorMsg = "Access not authorized to instatiate class: " + serviceImplKey + "=" + propVal;
/* 44 */       LogUtil.LOGCMM.logp(Level.SEVERE, CLASS_NAME, "getServiceImpl", errorMsg, e);
/* 45 */       throw new RuntimeException(errorMsg);
/*    */     } 
/*    */     
/* 48 */     LogUtil.LOGCMM.exiting(CLASS_NAME, "getServiceImpl");
/* 49 */     return srv;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceCommon.jar!\it\ibm\rssl\service\ot\\util\ServiceUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
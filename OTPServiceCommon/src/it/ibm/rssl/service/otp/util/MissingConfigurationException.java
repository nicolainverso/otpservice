/*    */ package it.ibm.rssl.service.otp.util;
/*    */ 
/*    */ public class MissingConfigurationException
/*    */   extends RuntimeException {
/*    */   private static final long serialVersionUID = 2902699825665208146L;
/*    */   
/*    */   public MissingConfigurationException(String message) {
/*  8 */     super(message);
/*    */   }
/*    */   
/*    */   public MissingConfigurationException(String message, Throwable cause) {
/* 12 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceCommon.jar!\it\ibm\rssl\service\ot\\util\MissingConfigurationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
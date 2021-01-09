/*    */ package it.ibm.rssl.service.otp.util;
/*    */ 
/*    */ import it.ibm.rssl.service.otp.exception.IllegalParameterException;
/*    */ import java.util.Date;
/*    */ import java.util.logging.Level;
/*    */ 
/*    */ 
/*    */ public class OtpServiceUtils
/*    */ {
/* 10 */   private static final String CLASS_NAME = OtpServiceUtils.class.getSimpleName();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static final long SECOND_MILLIS = 1000L;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isEmpty(String strIn) {
/* 22 */     strIn = trimToEmpty(strIn);
/* 23 */     return strIn.isEmpty();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String trimToEmpty(String strIn) {
/* 30 */     return (strIn == null) ? "" : strIn.trim();
/*    */   }
/*    */   
/*    */   public static void checkParam(String paramValue) throws IllegalParameterException {
/* 34 */     if (isEmpty(paramValue)) {
/* 35 */       LogUtil.LOGMGR.logp(Level.FINE, CLASS_NAME, "checkParam", "Provided parameter is empty or null");
/* 36 */       throw new IllegalParameterException("The input parameter is null or empty");
/*    */     } 
/*    */   }
/*    */   
/*    */   public static final Date addSecondsToDate(Date previousDate, int numSeconds) {
/* 41 */     long millis = previousDate.getTime();
/* 42 */     long newMillis = millis + numSeconds * 1000L;
/* 43 */     return new Date(newMillis);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final String zeroPad(String str, int size) {
/* 50 */     if (str.length() < size) {
/* 51 */       StringBuffer buf = new StringBuffer();
/* 52 */       for (int j = 0; j < size - str.length(); j++) {
/* 53 */         buf.append("0");
/*    */       }
/* 55 */       buf.append(str);
/* 56 */       return buf.toString();
/*    */     } 
/* 58 */     return str;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\ot\\util\OtpServiceUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
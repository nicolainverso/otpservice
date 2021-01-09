/*    */ package it.ibm.rssl.service.otp.util;
/*    */ 
/*    */ import java.util.logging.ConsoleHandler;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.LogManager;
/*    */ import java.util.logging.Logger;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogConfigurator
/*    */ {
/*    */   public static Logger getLogger(String name) {
/* 25 */     Logger log = Logger.getLogger(name);
/* 26 */     if (LogManager.getLogManager().getClass().getSimpleName().indexOf("Ws") < 0) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 33 */       ConsoleHandler ch = new ConsoleHandler();
/* 34 */       ch.setLevel(Level.ALL);
/* 35 */       ch.setFormatter(new LogFormatterJavaSE());
/* 36 */       log.addHandler(ch);
/* 37 */       log.setLevel(Level.ALL);
/* 38 */       log.setUseParentHandlers(false);
/*    */     } 
/* 40 */     return log;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceCommon.jar!\it\ibm\rssl\service\ot\\util\LogConfigurator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
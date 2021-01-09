/*     */ package it.ibm.rssl.service.otp.util;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.logging.Formatter;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LogFormatterJavaSE
/*     */   extends Formatter
/*     */ {
/*  22 */   private static final String LINE_SEP = System.getProperty("line.separator");
/*     */ 
/*     */   
/*     */   public String format(LogRecord logRecord) {
/*  26 */     StringBuffer buffer = new StringBuffer();
/*     */     
/*  28 */     formatTimeAndThread(logRecord, buffer);
/*  29 */     String name = logRecord.getLoggerName();
/*  30 */     if (name == null)
/*  31 */       name = ""; 
/*  32 */     int index = name.lastIndexOf('.') + 1;
/*  33 */     if (index + 13 >= name.length()) {
/*  34 */       name = name.substring(index);
/*     */     } else {
/*  36 */       name = name.substring(index, index + 13);
/*  37 */     }  buffer.append(name);
/*  38 */     int tmpLen = 13 - name.length();
/*  39 */     for (int i = tmpLen; i > 0; i--) {
/*  40 */       buffer.append(' ');
/*     */     }
/*  42 */     buffer.append(mapLevelToType(logRecord));
/*  43 */     String classname = logRecord.getSourceClassName();
/*  44 */     String methodname = logRecord.getSourceMethodName();
/*  45 */     if (classname != null)
/*  46 */       buffer.append(classname); 
/*  47 */     buffer.append(' ');
/*  48 */     if (methodname != null)
/*  49 */       buffer.append(methodname); 
/*  50 */     buffer.append(' ');
/*     */     
/*  52 */     buffer.append(formatMessage(logRecord)).append(LINE_SEP);
/*     */     
/*  54 */     if (logRecord.getThrown() != null) {
/*  55 */       buffer.append("Throwable occurred: ");
/*  56 */       Throwable t = logRecord.getThrown();
/*  57 */       PrintWriter pw = null;
/*     */       try {
/*  59 */         StringWriter sw = new StringWriter();
/*  60 */         pw = new PrintWriter(sw);
/*  61 */         t.printStackTrace(pw);
/*  62 */         buffer.append(sw.toString());
/*     */       } finally {
/*  64 */         if (pw != null) {
/*     */           try {
/*  66 */             pw.close();
/*  67 */           } catch (Exception exception) {}
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  74 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   private static void formatTimeAndThread(LogRecord logRecord, StringBuffer buffer) {
/*  78 */     buffer.setLength(0);
/*  79 */     buffer.append('[');
/*  80 */     long millis = logRecord.getMillis();
/*  81 */     Date d = new Date();
/*  82 */     d.setTime(millis);
/*  83 */     buffer.append(DateFormat.getDateTimeInstance(3, 1).format(d));
/*  84 */     buffer.append("] ");
/*     */   }
/*     */   
/*     */   private static String mapLevelToType(LogRecord logRecord) {
/*  88 */     Level l = logRecord.getLevel();
/*     */     
/*  90 */     if (l == Level.SEVERE)
/*  91 */       return " E "; 
/*  92 */     if (l == Level.WARNING)
/*  93 */       return " W "; 
/*  94 */     if (l == Level.INFO)
/*  95 */       return " I "; 
/*  96 */     if (l == Level.CONFIG)
/*  97 */       return " C "; 
/*  98 */     if (l == Level.FINE) {
/*  99 */       String s = logRecord.getLoggerName();
/* 100 */       if (s != null) {
/* 101 */         if (s.equals("SystemOut"))
/* 102 */           return " O "; 
/* 103 */         if (s.equals("SystemErr"))
/* 104 */           return " R "; 
/*     */       } 
/* 106 */       return " 1 ";
/*     */     } 
/* 108 */     if (l == Level.FINER) {
/* 109 */       String message = logRecord.getMessage();
/* 110 */       if (message != null) {
/* 111 */         if (message.indexOf("Entry") != -1 || message.indexOf("ENTRY") != -1)
/* 112 */           return " > "; 
/* 113 */         if (message.indexOf("Exit") != -1 || message.indexOf("RETURN") != -1)
/* 114 */           return " < "; 
/*     */       } 
/* 116 */       return " 2 ";
/*     */     } 
/* 118 */     if (l == Level.FINEST) {
/* 119 */       return " 3 ";
/*     */     }
/* 121 */     return " Z ";
/*     */   }
/*     */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceCommon.jar!\it\ibm\rssl\service\ot\\util\LogFormatterJavaSE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package it.ibm.rssl.service.otp.util;
/*     */ 
/*     */ import com.ibm.ws.util.Base64;
/*     */ import it.ibm.rssl.service.otp.exception.IllegalParameterException;
/*     */ import it.ibm.rssl.service.otp.exception.InternalOperationException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.logging.Level;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.SecretKey;
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
/*     */ public class PasswordService
/*     */ {
/*  27 */   private static final String CLASS_NAME = PasswordService.class.getSimpleName();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String KEYGEN_ALGORITHM = "AES";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final short KEYGEN_LENGTH = 128;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String ENCODING_TYPE = "UTF-8";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final short MAX_PWD_LENGTH = 18;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final short MIN_PWD_LENGTH = 1;
/*     */ 
/*     */ 
/*     */   
/*  55 */   private static PasswordService iSvc = new PasswordService();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PasswordService getInstance() {
/*  62 */     return iSvc;
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
/*     */   public String encrypt(String plainPwd) throws InternalOperationException {
/*  76 */     String METHOD_NAME = "encrypt";
/*  77 */     LogUtil.LOGMGR.entering(CLASS_NAME, "encrypt");
/*     */     
/*  79 */     MessageDigest md = null;
/*     */     try {
/*  81 */       md = MessageDigest.getInstance("MD5");
/*  82 */     } catch (NoSuchAlgorithmException e) {
/*  83 */       LogUtil.LOGMGR
/*  84 */         .logp(Level.FINE, CLASS_NAME, "encrypt", "Encryption algorithm not found in the system.", e);
/*  85 */       throw new InternalOperationException("Encryption algorithm not available in the system.", e);
/*     */     } 
/*     */     
/*  88 */     String encryptedPwdStr = null;
/*     */     try {
/*  90 */       md.update(plainPwd.getBytes("UTF-8"));
/*  91 */       byte[] encryptedPwd = md.digest();
/*     */ 
/*     */       
/*  94 */       encryptedPwdStr = new String(Base64.encode(encryptedPwd));
/*     */     }
/*  96 */     catch (UnsupportedEncodingException e) {
/*  97 */       LogUtil.LOGMGR.logp(Level.FINE, CLASS_NAME, "encrypt", "Encoding not found in the system.", e);
/*  98 */       throw new InternalOperationException("Encoding not available in the system.", e);
/*     */     } finally {
/* 100 */       md.reset();
/*     */     } 
/*     */     
/* 103 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "encrypt");
/* 104 */     return encryptedPwdStr;
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
/*     */   public String createNumericPwd(String salt, int size) throws IllegalParameterException, InternalOperationException {
/* 129 */     String METHOD_NAME = "createNumericPwd";
/* 130 */     LogUtil.LOGMGR.entering(CLASS_NAME, "createNumericPwd");
/*     */     
/* 132 */     OtpServiceUtils.checkParam(salt);
/*     */     
/* 134 */     if (size < 1 || size > 18) {
/* 135 */       String msg = "Provided size parameter is not in the expected range [1, 18], size=" + size;
/* 136 */       LogUtil.LOGMGR.logp(Level.FINER, CLASS_NAME, "createNumericPwd", msg);
/* 137 */       throw new IllegalParameterException(msg);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     SecureRandom random = new SecureRandom();
/*     */ 
/*     */     
/* 149 */     byte[] seedBytes = new byte[20];
/* 150 */     random.nextBytes(seedBytes);
/*     */ 
/*     */ 
/*     */     
/* 154 */     KeyGenerator keyGen = null;
/*     */     try {
/* 156 */       keyGen = KeyGenerator.getInstance("AES");
/* 157 */     } catch (NoSuchAlgorithmException e) {
/* 158 */       String msg = "Key generator provider algorithm not available in the system.";
/* 159 */       LogUtil.LOGMGR.logp(Level.FINE, CLASS_NAME, "createNumericPwd", msg, e);
/* 160 */       throw new InternalOperationException(msg, e);
/*     */     } 
/* 162 */     keyGen.init(128, random);
/* 163 */     SecretKey key = keyGen.generateKey();
/* 164 */     byte[] keyBytes = key.getEncoded();
/*     */ 
/*     */     
/* 167 */     byte[] saltBytes = (byte[])null;
/*     */     try {
/* 169 */       saltBytes = salt.getBytes("UTF-8");
/* 170 */     } catch (UnsupportedEncodingException e) {
/* 171 */       LogUtil.LOGMGR
/* 172 */         .logp(Level.FINE, CLASS_NAME, "createNumericPwd", "Encryption algorithm not found in the system.", e);
/* 173 */       throw new InternalOperationException("Encryption algorithm not available in the system.", e);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 178 */     byte[] saltedSeedBytes = new byte[seedBytes.length + saltBytes.length + keyBytes.length];
/* 179 */     System.arraycopy(seedBytes, 0, saltedSeedBytes, 0, seedBytes.length);
/* 180 */     System.arraycopy(saltBytes, 0, saltedSeedBytes, seedBytes.length, saltBytes.length);
/* 181 */     System.arraycopy(keyBytes, 0, saltedSeedBytes, seedBytes.length + saltBytes.length, keyBytes.length);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     random.setSeed(saltedSeedBytes);
/*     */ 
/*     */     
/* 191 */     long rdn = (long)Math.abs(random.nextLong() % (Math.pow(10.0D, size) - 1.0D));
/*     */     
/* 193 */     String strOtp = OtpServiceUtils.zeroPad(Long.toString(rdn), size);
/* 194 */     LogUtil.LOGMGR.exiting(CLASS_NAME, "createNumericPwd");
/*     */     
/* 196 */     return strOtp;
/*     */   }
/*     */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\ot\\util\PasswordService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
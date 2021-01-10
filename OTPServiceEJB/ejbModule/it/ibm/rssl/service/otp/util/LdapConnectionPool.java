/*    */ package it.ibm.rssl.service.otp.util;
/*    */ 
/*    */ import com.ibm.ws.security.util.InvalidPasswordEncodingException;
import com.ibm.ws.security.util.PasswordUtil;
import com.ibm.ws.security.util.UnsupportedCryptoAlgorithmException;

/*    */ import java.util.Hashtable;
/*    */ import java.util.logging.Level;
/*    */ import javax.naming.NamingException;
/*    */ import javax.naming.directory.DirContext;
/*    */ import javax.naming.directory.InitialDirContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LdapConnectionPool{
/* 17 */   private static final String CLASS_NAME = LdapConnectionPool.class.getSimpleName();
/* 19 */   private static Hashtable<String, String> env = new Hashtable<String, String>(); 
/* 21 */   private static LdapConnectionPool cvInstance = new LdapConnectionPool();
/*    */ 
/*    */   @SuppressWarnings("deprecation")
			protected LdapConnectionPool() {
/* 25 */     String connectionUrl = "ldap://" + ConfigProperties.getStringValue(PropertyKey.LDAP_SRV_HOST) + ":" + ConfigProperties.getStringValue(PropertyKey.LDAP_SRV_PORT);
/* 26 */     String obfuscatedPwd = ConfigProperties.getStringValue(PropertyKey.LDAP_ADMIN_PWD);
/* 27 */     String connectionTimeout = ConfigProperties.getStringValue(PropertyKey.LDAP_CONNECTION_TIMEOUT);
/* 28 */     String poolTimeout = ConfigProperties.getStringValue(PropertyKey.LDAP_CONNECTION_POOL_TIMEOUT);
/* 29 */     String maxPoolSize = ConfigProperties.getStringValue(PropertyKey.LDAP_CONNECTION_POOL_MAXSIZE);
/*    */     
/* 31 */     env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
/* 32 */     env.put("java.naming.security.authentication", "simple");
/* 33 */     env.put("java.naming.security.principal", ConfigProperties.getStringValue(PropertyKey.LDAP_ADMIN_USER));
/*    */     try {
/* 35 */       env.put("java.naming.security.credentials", PasswordUtil.decode(obfuscatedPwd));
/* 36 */     } catch (Exception e) {
/* 37 */       LogUtil.LOGMGR.logp(Level.FINE, CLASS_NAME, "LdapConnectionPool", "There was problem in LDAP password decoding. It is necessary to verify the LDAP password in the otp.properties file: ", e);
/* 38 */       e.printStackTrace();
/*    */     } 	

			if(ConfigProperties.getStringValue(PropertyKey.PASSWORD_NOT_ENCRYPTED)!=null &&ConfigProperties.getStringValue(PropertyKey.PASSWORD_NOT_ENCRYPTED).length()>0 ){
				//HOOK FOR CLEAN PASSWORD CONFIG
				env.put("java.naming.security.credentials", ConfigProperties.getStringValue(PropertyKey.PASSWORD_NOT_ENCRYPTED));	
				System.out.println("----------------------------------");
				System.out.println("[ENCODED PASSWORD]");
				try {
					System.out.println(PasswordUtil.encode(ConfigProperties.getStringValue(PropertyKey.PASSWORD_NOT_ENCRYPTED)));
					System.out.println("----------------------------------");
				} catch (InvalidPasswordEncodingException e) {
					e.printStackTrace();
				} catch (UnsupportedCryptoAlgorithmException e) {
					e.printStackTrace();
				}
			}
/* 40 */     env.put("java.naming.provider.url", connectionUrl);
/* 41 */     env.put("java.naming.referral", "follow");
/*    */ 
/*    */ 
/*    */     
/* 45 */     env.put("com.sun.jndi.ldap.connect.pool", "true");
/*    */ 
/*    */ 
/*    */     
/* 49 */     if (!connectionTimeout.trim().equals("")) {
/* 50 */       env.put("com.sun.jndi.ldap.connect.timeout", connectionTimeout);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 55 */     if (!poolTimeout.trim().equals("")) {
/* 56 */       env.put("com.sun.jndi.ldap.connect.pool.timeout", poolTimeout);
/*    */     }
/*    */ 
/*    */     
/* 60 */     if (!maxPoolSize.trim().equals("")) {
/* 61 */       env.put("com.sun.jndi.ldap.connect.pool.maxsize", maxPoolSize);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static synchronized LdapConnectionPool getInstance() {
/* 67 */     if (cvInstance == null) {
/* 68 */       cvInstance = new LdapConnectionPool();
/*    */     }
/* 70 */     return cvInstance;
/*    */   }
/*    */   
/*    */   public static DirContext getConnection() throws NamingException {
/*    */     try {
				System.out.println("-------------------------");
				System.out.println("[Parametri di connessione]");
			    System.out.println(env.toString());
/* 75 */        System.out.println("-------------------------");
			    return new InitialDirContext(env);
/* 76 */     } catch (NamingException e) {
/* 77 */       LogUtil.LOGMGR.logp(Level.FINE, CLASS_NAME, "getConnection", "Cannot get an unavailable connection instance: ", e);
/* 78 */       throw e;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void closeConnection(DirContext ctx) throws NamingException {
/* 83 */     if (ctx != null)
/*    */       try {
/* 85 */         ctx.close();
/* 86 */       } catch (NamingException e) {
/* 87 */         LogUtil.LOGMGR.logp(Level.FINE, CLASS_NAME, "closeConnection", "Cannot close connection instance: ", e);
/* 88 */         throw e;
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\ot\\util\LdapConnectionPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
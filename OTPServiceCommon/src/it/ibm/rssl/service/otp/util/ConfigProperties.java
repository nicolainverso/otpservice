/*     */ package it.ibm.rssl.service.otp.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*     */ import java.util.PropertyResourceBundle;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NamingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigProperties
/*     */ {
/*  21 */   private static final String CLASS_NAME = ConfigProperties.class.getSimpleName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String CONFIG_BUNDLE_NAME = "otp";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String CONFIG_FILE_JNDI_URL = "url/otp/config";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static transient ConfigProperties cvInstance;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private Properties ivProperties = new Properties();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private File ivConfigDir;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ConfigProperties() {
/*  55 */     loadProperties();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  64 */     StringBuffer buf = new StringBuffer();
/*  65 */     for (Enumeration<Object> keys = this.ivProperties.keys(); keys.hasMoreElements(); ) {
/*  66 */       String key = (String)keys.nextElement();
/*  67 */       buf.append(String.valueOf(key) + " := [" + this.ivProperties.get(key) + "]\n");
/*     */     } 
/*  69 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final ConfigProperties getConfig() {
/*  80 */     synchronized (ConfigProperties.class) {
/*  81 */       if (cvInstance == null) {
/*  82 */         synchronized (ConfigProperties.class) {
/*  83 */           cvInstance = new ConfigProperties();
/*     */         } 
/*     */       }
/*     */     } 
/*  87 */     return cvInstance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final ConfigProperties reloadConfig() {
/*  95 */     synchronized (ConfigProperties.class) {
/*  96 */       cvInstance = new ConfigProperties();
/*     */     } 
/*  98 */     return cvInstance;
/*     */   }
/*     */   
/*     */   private void loadProperties() {
/* 102 */     String configFilePath, METHOD = "loadProperties";
/*     */ 
/*     */ 
/*     */     
/* 106 */     URL configUrl = lookupURL("url/otp/config");
/* 107 */     if (configUrl != null) {
/* 108 */       configFilePath = configUrl.getFile();
/* 109 */       LogUtil.LOGCMM.info("Configuration settings: configFileName=" + configFilePath);
/*     */     } else {
/*     */       
/* 112 */       configFilePath = "../OTPDeployment/conf/otp.properties";
/* 113 */       LogUtil.LOGCMM.warning("Default configuration settings: configFileName= " + configFilePath);
/*     */     } 
/*     */     
/* 116 */     File configFile = lookupFile(configFilePath);
/* 117 */     this.ivConfigDir = configFile.getParentFile();
/*     */     
/*     */     try {
/* 120 */       loadPropertiesFromBundle(configFile);
/* 121 */     } catch (IOException e) {
/* 122 */       LogUtil.LOGCMM.throwing(CLASS_NAME, "loadProperties", e);
/* 123 */       throw new MissingConfigurationException("Property file cannot be loaded");
/*     */     } 
/*     */   }
/*     */   
/*     */   private static File lookupFile(String filePath) throws MissingConfigurationException {
/* 128 */     System.out.println(System.getProperty("user.dir"));
/* 129 */     File file = new File(filePath);
/* 130 */     if (!file.exists()) {
/* 131 */       LogUtil.LOGCMM.info("Configuration file not found " + filePath);
/* 132 */       throw new MissingConfigurationException("Configuration file " + filePath + " cannot be found");
/*     */     } 
/* 134 */     return file;
/*     */   }
/*     */   
/*     */   private static URL lookupURL(String jndiName) {
/*     */     try {
/* 139 */       InitialContext ctx = new InitialContext();
/* 140 */       return (URL)ctx.lookup(jndiName);
/* 141 */     } catch (NamingException e) {
/* 142 */       LogUtil.LOGCMM.warning("Unable to lookup URL with JNDI name " + jndiName + ": " + e.getMessage());
/*     */       
/* 144 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void loadPropertiesFromBundle(File file) throws IOException {
/* 149 */     InputStream is = null;
/*     */     try {
/* 151 */       is = new FileInputStream(file);
/* 152 */       PropertyResourceBundle bundle = new PropertyResourceBundle(is);
/* 153 */       if (bundle == null)
/* 154 */         throw new MissingConfigurationException("Property file cannot be found"); 
/* 155 */       for (Enumeration<String> keys = bundle.getKeys(); keys.hasMoreElements(); ) {
/* 156 */         String key = keys.nextElement();
/* 157 */         String value = bundle.getString(key);
/* 158 */         this.ivProperties.setProperty(key, (value == null) ? null : value.trim());
/*     */       } 
/*     */     } finally {
/* 161 */       if (is != null) {
/* 162 */         is.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public File getConfigDir() {
/* 170 */     return this.ivConfigDir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Properties getProperties() {
/* 177 */     return this.ivProperties;
/*     */   }
/*     */   
/*     */   public static String getPropertyValue(PropertyKey property) {
/* 181 */     if (property == null) {
/* 182 */       throw new IllegalArgumentException("The required configuration parameter is null");
/*     */     }
/* 184 */     return getPropertyValue(property.getKeyName(), property.getDefaultValue());
/*     */   }
/*     */   
/*     */   public static String getPropertyValue(String propertyName) {
/* 188 */     PropertyKey key = PropertyKey.keyNameValueOf(propertyName);
/* 189 */     String value = (key != null) ? getPropertyValue(key) : getPropertyValue(propertyName, null);
/* 190 */     if (value == null) {
/* 191 */       throw new MissingConfigurationException(String.valueOf(propertyName) + " is a mandatory configuration parameter");
/*     */     }
/* 193 */     return value;
/*     */   }
/*     */   
/*     */   public static String getPropertyValue(String propertyName, String defaultValue) {
/* 197 */     return getConfig().getProperties().getProperty(propertyName, defaultValue);
/*     */   }
/*     */   
/*     */   public static String getStringValue(PropertyKey property) {
/* 201 */     return getPropertyValue(property);
/*     */   }
/*     */   
/*     */   public static int getIntegerValue(PropertyKey property) {
/* 205 */     String intStr = getPropertyValue(property);
/*     */     try {
/* 207 */       return Integer.valueOf(intStr).intValue();
/* 208 */     } catch (NumberFormatException e) {
/* 209 */       throw new NumberFormatException(String.valueOf(intStr) + " is not valid integer string for property " + 
/* 210 */           property.getKeyName());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean getBooleanValue(PropertyKey property) {
/* 215 */     return Boolean.valueOf(getPropertyValue(property)).booleanValue();
/*     */   }
/*     */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceCommon.jar!\it\ibm\rssl\service\ot\\util\ConfigProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
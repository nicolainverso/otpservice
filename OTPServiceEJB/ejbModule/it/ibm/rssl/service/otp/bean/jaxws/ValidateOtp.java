/*     */ package it.ibm.rssl.service.otp.bean.jaxws;
/*     */ 
/*     */ import it.ibm.rssl.service.otp.OperationInfo;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ import javax.xml.bind.annotation.XmlType;
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
/*     */ @XmlRootElement(name = "validateOtp", namespace = "http://bean.otp.service.rssl.ibm.it/")
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name = "validateOtp", namespace = "http://bean.otp.service.rssl.ibm.it/", propOrder = {"arg0", "arg1", "arg2", "arg3"})
/*     */ public class ValidateOtp
/*     */ {
/*     */   @XmlElement(name = "arg0", namespace = "")
/*     */   private String arg0;
/*     */   @XmlElement(name = "arg1", namespace = "")
/*     */   private String arg1;
/*     */   @XmlElement(name = "arg2", namespace = "")
/*     */   private String arg2;
/*     */   @XmlElement(name = "arg3", namespace = "")
/*     */   private OperationInfo arg3;
/*     */   
/*     */   public String getArg0() {
/*  39 */     return this.arg0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArg0(String arg0) {
/*  48 */     this.arg0 = arg0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getArg1() {
/*  57 */     return this.arg1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArg1(String arg1) {
/*  66 */     this.arg1 = arg1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getArg2() {
/*  75 */     return this.arg2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArg2(String arg2) {
/*  84 */     this.arg2 = arg2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OperationInfo getArg3() {
/*  93 */     return this.arg3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArg3(OperationInfo arg3) {
/* 102 */     this.arg3 = arg3;
/*     */   }
/*     */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\bean\jaxws\ValidateOtp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
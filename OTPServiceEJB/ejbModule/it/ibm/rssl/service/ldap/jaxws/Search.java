/*    */ package it.ibm.rssl.service.ldap.jaxws;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ import javax.xml.bind.annotation.XmlType;
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
/*    */ @XmlRootElement(name = "search", namespace = "http://ldap.service.rssl.ibm.it/")
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name = "search", namespace = "http://ldap.service.rssl.ibm.it/", propOrder = {"arg0", "arg1"})
/*    */ public class Search
/*    */ {
/*    */   @XmlElement(name = "arg0", namespace = "")
/*    */   private String arg0;
/*    */   @XmlElement(name = "arg1", namespace = "")
/*    */   private String arg1;
/*    */   
/*    */   public String getArg0() {
/* 33 */     return this.arg0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setArg0(String arg0) {
/* 42 */     this.arg0 = arg0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getArg1() {
/* 51 */     return this.arg1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setArg1(String arg1) {
/* 60 */     this.arg1 = arg1;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\ldap\jaxws\Search.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
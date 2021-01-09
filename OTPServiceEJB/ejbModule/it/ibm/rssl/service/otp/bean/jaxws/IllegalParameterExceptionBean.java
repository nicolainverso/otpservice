/*    */ package it.ibm.rssl.service.otp.bean.jaxws;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @XmlRootElement(name = "IllegalParameterException", namespace = "http://bean.otp.service.rssl.ibm.it/")
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name = "IllegalParameterException", namespace = "http://bean.otp.service.rssl.ibm.it/")
/*    */ public class IllegalParameterExceptionBean
/*    */ {
/*    */   private String message;
/*    */   
/*    */   public String getMessage() {
/* 33 */     return this.message;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMessage(String message) {
/* 42 */     this.message = message;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\bean\jaxws\IllegalParameterExceptionBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
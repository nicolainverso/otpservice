/*    */ package it.ibm.rssl.service.otp.bean.jaxws;
/*    */ 
/*    */ import it.ibm.rssl.service.otp.OtpGenerationOutput;
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
/*    */ @XmlRootElement(name = "generateOtpResponse", namespace = "http://bean.otp.service.rssl.ibm.it/")
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name = "generateOtpResponse", namespace = "http://bean.otp.service.rssl.ibm.it/")
/*    */ public class GenerateOtpResponse
/*    */ {
/*    */   @XmlElement(name = "return", namespace = "")
/*    */   private OtpGenerationOutput _return;
/*    */   
/*    */   public OtpGenerationOutput getReturn() {
/* 28 */     return this._return;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setReturn(OtpGenerationOutput _return) {
/* 37 */     this._return = _return;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\bean\jaxws\GenerateOtpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package it.ibm.rssl.service.otp;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ChannelInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3875521332073811302L;
/*  9 */   private String sender = null;
/*    */   
/* 11 */   private String deliveryType = null;
/*    */ 
/*    */   
/*    */   public ChannelInfo() {}
/*    */   
/*    */   public ChannelInfo(String deliveryType, String sender) {
/* 17 */     this.sender = sender;
/* 18 */     this.deliveryType = deliveryType;
/*    */   }
/*    */   
/*    */   public String getDeliveryType() {
/* 22 */     return this.deliveryType;
/*    */   }
/*    */   
/*    */   public void setDeliveryType(String deliveryType) {
/* 26 */     this.deliveryType = deliveryType;
/*    */   }
/*    */   
/*    */   public String getSender() {
/* 30 */     return this.sender;
/*    */   }
/*    */   
/*    */   public void setSender(String sender) {
/* 34 */     this.sender = sender;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     String TAB = "    ";
/*    */     
/* 48 */     String retValue = "";
/*    */     
/* 50 */     retValue = "ChannelInfo ( " + 
/* 51 */       super.toString() + "    " + 
/* 52 */       "sender = " + this.sender + "    " + 
/* 53 */       "deliveryType = " + this.deliveryType + "    " + 
/* 54 */       " )";
/*    */     
/* 56 */     return retValue;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\ChannelInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package it.ibm.rssl.service.otp;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class OperationInfo
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = -5611470314754348072L;
/*  8 */   private String mcSessionId = null;
/*  9 */   private String mcOperationId = null;
/*    */ 
/*    */   
/*    */   public OperationInfo() {}
/*    */   
/*    */   public OperationInfo(String sessionId, String operationId) {
/* 15 */     this.mcOperationId = operationId;
/* 16 */     this.mcSessionId = sessionId;
/*    */   }
/*    */   
/*    */   public String getMcSessionId() {
/* 20 */     return this.mcSessionId;
/*    */   }
/*    */   
/*    */   public void setMcSessionId(String mcSessionId) {
/* 24 */     this.mcSessionId = mcSessionId;
/*    */   }
/*    */   
/*    */   public String getMcOperationId() {
/* 28 */     return this.mcOperationId;
/*    */   }
/*    */   
/*    */   public void setMcOperationId(String mcOperationId) {
/* 32 */     this.mcOperationId = mcOperationId;
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
/* 44 */     String TAB = "    ";
/*    */     
/* 46 */     String retValue = "";
/*    */     
/* 48 */     retValue = "OperationInfo ( " + 
/* 49 */       super.toString() + "    " + 
/* 50 */       "mcSessionId = " + this.mcSessionId + "    " + 
/* 51 */       "mcOperationId = " + this.mcOperationId + "    " + 
/* 52 */       " )";
/*    */     
/* 54 */     return retValue;
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\OperationInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
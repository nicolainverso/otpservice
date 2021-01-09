/*    */ package it.ibm.rssl.service.otp;
/*    */ 
/*    */ import it.ibm.rssl.service.otp.ldapusermgr.proxy.LdapUserProxy;

import java.util.Date;

import com.ibm.websphere.crypto.InvalidPasswordDecodingException;
import com.ibm.websphere.crypto.PasswordUtil;
import com.ibm.websphere.crypto.UnsupportedCryptoAlgorithmException;
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
/*    */ public class testLdapService
/*    */ {
/*    */   public static void main(String[] args) {
/* 22 */     System.out.println("MAIN START");
//{xor}Pjg3ZzU2Ojw
try {
	System.out.println(PasswordUtil.decode("{xor}Pjg3ZzU2Ojw"));
} catch (InvalidPasswordDecodingException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
} catch (UnsupportedCryptoAlgorithmException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
//System.out.println(PasswordUtil.passwordDecode("Pjg3ZzU2Ojw",PasswordUtil.DEFAULT_CRYPTO_ALGORITHM));
/* 23 */     LdapUserProxy ldap = new LdapUserProxy();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 30 */       ldap.setOTPExpireTime("colulu", new Date());
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
/*    */     }
/* 42 */     catch (Exception e) {
/*    */       
/* 44 */       System.out.println("ECCEZIONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
/* 45 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 48 */     System.out.println("MAIN STOP");
/*    */   }
/*    */ }


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\testLdapService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
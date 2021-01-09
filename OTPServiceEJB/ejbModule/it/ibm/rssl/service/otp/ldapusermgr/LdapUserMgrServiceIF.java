package it.ibm.rssl.service.otp.ldapusermgr;

import it.ibm.rssl.service.otp.OtpUserInfo;
import it.ibm.rssl.service.otp.ldapusermgr.exception.LdapUserProxyException;
import java.util.Date;

public interface LdapUserMgrServiceIF {
  OtpUserInfo readOtp(String paramString) throws LdapUserProxyException;
  
  void saveOtp(String paramString1, String paramString2, String paramString3, Date paramDate) throws LdapUserProxyException;
  
  void resetCountOtp(String paramString) throws LdapUserProxyException;
  
  void incrementCountOtp(String paramString) throws LdapUserProxyException;
  
  void lockAccount(String paramString) throws LdapUserProxyException;
  
  void resetOtp(String paramString) throws LdapUserProxyException;
}


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\ldapusermgr\LdapUserMgrServiceIF.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
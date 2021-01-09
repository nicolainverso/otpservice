package it.ibm.rssl.service.otp;

import it.ibm.rssl.service.otp.exception.CommServiceException;
import java.util.Date;

public interface LdapServiceIF {
  OtpUserInfo readOtp(String paramString) throws CommServiceException;
  
  void saveOtp(String paramString1, String paramString2, String paramString3, Date paramDate) throws CommServiceException;
  
  void resetCountOtp(String paramString) throws CommServiceException;
  
  void incrementCountOtp(String paramString) throws CommServiceException;
  
  void lockAccount(String paramString) throws CommServiceException;
  
  void unlockAccount(String paramString) throws CommServiceException;
  
  void resetOtp(String paramString) throws CommServiceException;
}


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\LdapServiceIF.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
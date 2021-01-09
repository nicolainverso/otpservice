package it.ibm.rssl.service.otp;

import it.ibm.rssl.service.otp.exception.AccountLockedException;
import it.ibm.rssl.service.otp.exception.AccountNotFoundException;
import it.ibm.rssl.service.otp.exception.IllegalParameterException;
import it.ibm.rssl.service.otp.exception.InvalidPasswordException;
import it.ibm.rssl.service.otp.exception.KeySessionNotRegisteredException;
import it.ibm.rssl.service.otp.exception.PasswordServiceUnavailableException;
import it.ibm.rssl.service.otp.exception.WrongAttemptWarningReachedException;
import it.ibm.rssl.service.otp.exception.WrongMaxAttemptReachedException;

public interface OneTimePasswordServiceIF {
  OtpGenerationOutput generateOtp(String paramString1, String paramString2, ChannelInfo paramChannelInfo, OperationInfo paramOperationInfo) throws PasswordServiceUnavailableException, IllegalParameterException, AccountNotFoundException, AccountLockedException;
  
  OtpValidationOutput validateOtp(String paramString1, String paramString2, String paramString3, OperationInfo paramOperationInfo) throws PasswordServiceUnavailableException, IllegalParameterException, AccountNotFoundException, AccountLockedException, InvalidPasswordException, WrongMaxAttemptReachedException, KeySessionNotRegisteredException, WrongAttemptWarningReachedException;
}


/* Location:              C:\DatiNicola\Bper\otpservice-main\otpservice-main\OTPServiceEJB.jar!\it\ibm\rssl\service\otp\OneTimePasswordServiceIF.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
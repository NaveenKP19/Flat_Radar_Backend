package com.PersonalProject.Flat.Radar.exception;

import com.PersonalProject.Flat.Radar.dto.ErroeResponsedto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErroeResponsedto> handleEmailException(EmailAlreadyExistsException email){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroeResponsedto(email.getMessage(),400));
    }

    @ExceptionHandler(MobileNumberAlreadyExistsException.class)
    public ResponseEntity<ErroeResponsedto> handleMobileException(MobileNumberAlreadyExistsException mobile){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroeResponsedto(mobile.getMessage(), 400));
    }

    @ExceptionHandler(AadhaarAlreadyExistsException.class)
    public ResponseEntity<ErroeResponsedto> handleAadhaarException(AadhaarAlreadyExistsException aadhaar){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ErroeResponsedto(aadhaar.getMessage(), 400));
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErroeResponsedto> handleInvalidPasswordException(InvalidPasswordException pass){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErroeResponsedto(pass.getMessage(), 401));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErroeResponsedto> handleUserNotFoundException(UserNotFoundException user){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroeResponsedto(user.getMessage(), 404));
    }


}

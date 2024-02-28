package com.seed_planner.schedule_center.common.exceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.seed_planner.schedule_center.common.exceptionHandler.customException.BadRequestException;
import com.seed_planner.schedule_center.common.exceptionHandler.customException.NotFoundAuthException;
import com.seed_planner.schedule_center.common.exceptionHandler.customException.NotFoundResourceException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;

/**
 * Global Exception Handler <p>
 * 전역적으로 발생하는 예외를 체크한다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 올바르지 않은 모든 Request 에 대해.
     * @param e
     * @return HTTP code : 400, error message
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException e) {
        logger.error("BadRequestException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
    }

    /**
     * Request data value 로 조회되는 결과가 없을 경우.
     * @param e
     * @return HTTP code : 404, error message
     */
    @ExceptionHandler(value = NotFoundResourceException.class)
    public ResponseEntity<?> notFoundException(NotFoundResourceException e) {
        logger.error("NotFoundException: ", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
    }

    /**
     * 로그인을 시도한 사용자 정보를 찾을 수 없을 경우.
     * @param e
     * @return HTTP code : 401, error message
     */
    @ExceptionHandler(value = NotFoundAuthException.class)
    public ResponseEntity<?> notFoundUserException(NotFoundAuthException e) {
        logger.error("NotFoundAuthException: ", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getErrorMessage());
    }

    /**
     * Request URL의 param 타입 체크.
     * @param e
     * @return HTTP code : 400, error message
     */
    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity<?> missingPathVariableExceptionException(MissingPathVariableException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionCode.E00002, e.getMessage());
        logger.error("MissingPathVariableException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    /**
     * 필수 data 체크.
     * @param e
     * @return HTTP code : 400, error message
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionCode.E00001, e.getMessage());
        logger.error("MissingServletRequestParameterException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    /**
     * data type validation check
     * @param e
     * @return HTTP code : 400, error message
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchExceptionException(MethodArgumentTypeMismatchException e) {
        ExceptionResponse exceptionResponse =
            new ExceptionResponse(ExceptionCode.E00002, "'" + e.getName() + "' is must be '" + e.getRequiredType() + "' type.");
        logger.error("MethodArgumentTypeMismatchException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    /**
     * Request DTO data Validation check
     * @param e
     * @return HTTP code : 400, error message
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        HashMap<String, String> params = new HashMap<>();
        for (FieldError fieldError: e.getBindingResult().getFieldErrors()) {
            params.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionCode.E00001, params);
        logger.error("MethodArgumentNotValidException: ", e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    /**
     * Request body data 에서 발생하는 예외를 체크. <p>
     * (ex. data type mismatch, deserialization error .. )
     * @param e
     * @return HTTP code : 400, error message
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        Throwable throwable = e.getMostSpecificCause();
        HashMap<String, Object> params = new HashMap<>();
        ExceptionResponse exceptionResponse;
        if(throwable instanceof InvalidFormatException exception) {
            logger.error("InvalidFormatException: ", exception);
            params.put("Value" , exception.getValue());
            params.put("Target Type", exception.getTargetType());
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00003, params);
        } else if(throwable instanceof MismatchedInputException exception) {
            logger.error("MismatchedInputException: ", exception);
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00003, exception.getOriginalMessage());
        } else if(throwable instanceof JsonParseException exception) {
            logger.error("JsonParseException: ", exception);
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00003, exception.getOriginalMessage());
        } else {
            logger.error("HttpMessageNotReadableException: ", e);
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00003, e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    /**
     * jwt 토큰 검증시 발생할 수 있는 예외를 체크.
     * @param e
     * @return HTTP code : 401, error message
     */
    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<?> jwtException(JwtException e) {
        ExceptionResponse exceptionResponse;
        if (e instanceof ExpiredJwtException exception) {
            // 토큰 만료
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00004, exception.getMessage());
            logger.error("ExpiredJwtException: ", exception);
        } else if (e instanceof UnsupportedJwtException exception) {
            // 토큰 형태 인증 불가
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00005, exception.getMessage());
            logger.error("UnsupportedJwtException: ", exception);
        } else if (e instanceof MalformedJwtException exception) {
            // 토큰 구조 불일치
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00005, exception.getMessage());
            logger.error("MalformedJwtException: ", exception);
        } else if (e instanceof SignatureException exception) {
            // jwt 서명실패
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00005, exception.getMessage());
            logger.error("SignatureException: ", exception);
        } else {
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00005, e.getMessage());
            logger.error("JwtException: ", e);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionResponse);
    }

    /**
     * 예측할 수 없는 모든 예외를 체크
     * @param e
     * @return HTTP code : 500, error message
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> serverException(Exception e) {
        logger.error("Server Exception: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}

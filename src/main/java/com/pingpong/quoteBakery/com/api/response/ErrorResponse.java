package com.pingpong.quoteBakery.com.api.response;

import com.pingpong.quoteBakery.com.api.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler에서 발생한 에러에 대한 응답 처리를 관리
 * 클라이언트에서 요청한 값에 대해 '실패한 응답' 반환 값의 형태를 구성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
  private int status;                 // 에러 상태 코드
  private String divisionCode;        // 에러 구분 코드
  private String resultMsg;           // 에러 메시지
  private List<FieldError> errors;    // 상세 에러 메시지
  private String reason;              // 에러 이유

  /**
   * ErrorResponse 생성자
   * Plain ErrorCode만을 인자로 받는 생성자
   *
   * @param code ErrorCode
   */
  @Builder
  protected ErrorResponse(final ErrorCode code) {
    this.resultMsg = code.getMessage();
    this.status = code.getStatus();
    this.divisionCode = code.getDivisionCode();
    this.errors = new ArrayList<>();
  }

  /**
   * Global Exception 전송 타입
   *
   * @param code ErrorCode
   * @return ErrorResponse
   */
  public static ErrorResponse of(final ErrorCode code) {
    return new ErrorResponse(code);
  }

  /**
   * ErrorResponse 생성자
   * ErrorCode와 Reason을 인자로 받는 생성자
   *
   * @param code   ErrorCode
   * @param reason String
   */
  @Builder
  protected ErrorResponse(final ErrorCode code, final String reason) {
    this.resultMsg = code.getMessage();
    this.status = code.getStatus();
    this.divisionCode = code.getDivisionCode();
    this.reason = reason;
  }

  /**
   * Global Exception 전송 타입
   *
   * @param code   ErrorCode
   * @param reason String
   * @return ErrorResponse
   */
  public static ErrorResponse of(final ErrorCode code, final String reason) {
    return new ErrorResponse(code, reason);
  }

  /**
   * ErrorResponse 생성자
   * ErrorCode와 상세 필드별 에러 리스트를 인자로 받는 생성자
   *
   * @param code   ErrorCode
   * @param errors List<FieldError>
   */
  @Builder
  protected ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
    this.resultMsg = code.getMessage();
    this.status = code.getStatus();
    this.errors = errors;
    this.divisionCode = code.getDivisionCode();
  }

  /**
   * Global Exception 전송 타입
   *
   * @param code          ErrorCode
   * @param bindingResult BindingResult
   * @return ErrorResponse
   */
  public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
    return new ErrorResponse(code, FieldError.of(bindingResult));
  }



  /**
   * 에러를 e.getBindingResult() 형태로 전달 받는 경우 해당 내용을 상세 내용으로 변경하는 기능을 수행한다.
   * FieldError 하나 또는 여러개를 갖는 List를 리턴하는 정적 생성자 대체 메서드(of)를 두 개 제공한다.
   * ex) MethodArgumentNotValidException
   */
  @Getter
  public static class FieldError {
    private final String field;
    private final String value;
    private final String reason;

    public static List<FieldError> of(final String field, final String value, final String reason) {
      List<FieldError> fieldErrors = new ArrayList<>();
      fieldErrors.add(new FieldError(field, value, reason));
      return fieldErrors;
    }

    private static List<FieldError> of(final BindingResult bindingResult) {
      final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
      return fieldErrors.stream()
          .map(error -> new FieldError(
              error.getField(),
              error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
              error.getDefaultMessage()))
          .collect(Collectors.toList());
    }

    @Builder
    FieldError(String field, String value, String reason) {
      this.field = field;
      this.value = value;
      this.reason = reason;
    }
  }
}
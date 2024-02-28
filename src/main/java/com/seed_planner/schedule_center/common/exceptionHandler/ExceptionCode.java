package com.seed_planner.schedule_center.common.exceptionHandler;

import lombok.Getter;

/**
 * 에러 코드를 정의한 class.
 * @Value E0000x : common
 *
 */
@Getter
public enum ExceptionCode {

    E00001("필수 파라미터가 없습니다."),
    E00002("파라미터 타입이 일치하지 않습니다."),
    E00003("요청 데이터가 올바르지 않습니다."),
    E00004("만료된 토큰입니다."),
    E00005("유효하지 않은 토큰입니다."),
    E00006("토큰이 없습니다.");


    private final String code;
    @Getter
    private final String message;

    ExceptionCode(String message) {
        this.code = this.name();
        this.message = message;
    }
}
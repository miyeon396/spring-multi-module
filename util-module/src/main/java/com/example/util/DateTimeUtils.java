package com.example.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 날짜 및 시간 관련 유틸리티 클래스
 * 
 * <p>이 클래스는 날짜와 시간 처리를 위한 유틸리티 메서드를 제공합니다.</p>
 * 
 * @author Spring Multi Module Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class DateTimeUtils {
    
    /**
     * 기본 날짜 포맷 (yyyy-MM-dd)
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    
    /**
     * 기본 날짜시간 포맷 (yyyy-MM-dd HH:mm:ss)
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 현재 날짜와 시간을 기본 포맷으로 반환합니다.
     * 
     * @return 포맷팅된 현재 날짜시간 문자열
     */
    public String getCurrentDateTime() {
        return formatDateTime(LocalDateTime.now(), DEFAULT_DATETIME_FORMAT);
    }
    
    /**
     * LocalDateTime을 지정된 포맷으로 변환합니다.
     * 
     * @param dateTime 변환할 LocalDateTime 객체
     * @param pattern 날짜 포맷 패턴
     * @return 포맷팅된 날짜시간 문자열
     * @throws IllegalArgumentException dateTime이나 pattern이 null인 경우
     */
    public String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null || pattern == null) {
            throw new IllegalArgumentException("DateTime and pattern cannot be null");
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
    
    /**
     * 두 날짜 사이의 일수를 계산합니다.
     * 
     * @param start 시작 날짜
     * @param end 종료 날짜
     * @return 두 날짜 사이의 일수
     */
    public long daysBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end dates cannot be null");
        }
        
        return java.time.Duration.between(start, end).toDays();
    }
}

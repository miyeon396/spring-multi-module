package com.example.util;

import org.springframework.stereotype.Component;

/**
 * 문자열 관련 유틸리티 클래스
 * 
 * <p>이 클래스는 문자열 처리와 관련된 다양한 유틸리티 메서드를 제공합니다.</p>
 * 
 * @author Spring Multi Module Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class StringUtils {
    
    /**
     * 문자열이 비어있는지 확인합니다.
     * 
     * @param str 확인할 문자열
     * @return 문자열이 null이거나 빈 문자열이면 true, 그렇지 않으면 false
     * @see #isNotEmpty(String)
     */
    public boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * 문자열이 비어있지 않은지 확인합니다.
     * 
     * @param str 확인할 문자열
     * @return 문자열이 null이 아니고 비어있지 않으면 true, 그렇지 않으면 false
     * @see #isEmpty(String)
     */
    public boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * 문자열을 카멜 케이스로 변환합니다.
     * 
     * <p>예제:</p>
     * <pre>
     * toCamelCase("hello_world") -> "helloWorld"
     * toCamelCase("hello-world") -> "helloWorld"
     * </pre>
     * 
     * @param str 변환할 문자열
     * @return 카멜 케이스로 변환된 문자열
     * @throws IllegalArgumentException str이 null인 경우
     */
    public String toCamelCase(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        
        String[] parts = str.split("[_\\-]");
        StringBuilder camelCase = new StringBuilder(parts[0].toLowerCase());
        
        for (int i = 1; i < parts.length; i++) {
            camelCase.append(capitalize(parts[i]));
        }
        
        return camelCase.toString();
    }
    
    /**
     * 문자열의 첫 글자를 대문자로 변환합니다.
     * 
     * @param str 변환할 문자열
     * @return 첫 글자가 대문자로 변환된 문자열
     */
    private String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

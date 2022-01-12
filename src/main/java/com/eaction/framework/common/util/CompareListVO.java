package com.eaction.framework.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eaction.framework.business.common.code.OtherCodeTableMng;

public class CompareListVO {

	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(CompareListVO.class);    
	
	/**
     * List<VO> 정렬
     *
     * @param list 비교할 리스트(List<VO> 만 가능)
     * @param getterMethodText 비교할 getter 함수
     * @param sortInfo 정렬 문자열 (ASC, DESC)
     */
    public static void sortListVO(List<?> list, String getterMethodText, String sortInfo) {
 
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object firstObject, Object secondObject) {
                int rtn = 0;
                int compareIndex = 0; // 비교 인덱스 (작은 문자열 수)
                String firstData = "";
                String secondData = "";
                int firstValue = 0;
                int secondValue = 0;
 
                // 비교할 대상
                try {
                    Method firstDeclaredMethod = firstObject.getClass().getDeclaredMethod(getterMethodText);
                    firstData = (String) firstDeclaredMethod.invoke(firstObject, new Object[] {});
                    Method secondDeclaredMethod = secondObject.getClass().getDeclaredMethod(getterMethodText);
                    secondData = (String) secondDeclaredMethod.invoke(secondObject, new Object[] {});
                } catch (NoSuchMethodException | SecurityException e) {
                    logger.error(StringUtil.stackTraceToString(e));
                } catch (IllegalAccessException e) {
                	logger.error(StringUtil.stackTraceToString(e));
                } catch (IllegalArgumentException e) {
                	logger.error(StringUtil.stackTraceToString(e));
                } catch (InvocationTargetException e) {
                	logger.error(StringUtil.stackTraceToString(e));
                }
 
                if (firstData == null || firstData == "" || secondData == null || secondData == "") { return rtn; }
 
                // 기본 정렬값 설정
                if ("DESC".equals(sortInfo)) {
                    if (firstData.length() > secondData.length()) {
                        rtn = -1;
                        compareIndex = secondData.length();
                    } else if (firstData.length() < secondData.length()) {
                        rtn = 1;
                        compareIndex = firstData.length();
                    } else {
                        compareIndex = firstData.length();
                    }
                } else {
                    if (firstData.length() < secondData.length()) {
                        rtn = -1;
                        compareIndex = firstData.length();
                    } else if (firstData.length() > secondData.length()) {
                        rtn = 1;
                        compareIndex = secondData.length();
                    } else {
                        compareIndex = firstData.length();
                    }
                }
 
                for (int i = 0; i < compareIndex; i++) {
                    firstValue = Integer.valueOf(firstData.charAt(i));
                    secondValue = Integer.valueOf(secondData.charAt(i));
 
                    if ("DESC".equals(sortInfo)) {
                        if (firstValue > secondValue) {
                            rtn = -1;
                            break;
                        } else if (firstValue < secondValue) {
                            rtn = 1;
                            break;
                        }
                    } else {
                        if (firstValue < secondValue) {
                            rtn = -1;
                            break;
                        } else if (firstValue > secondValue) {
                            rtn = 1;
                            break;
                        }
                    }
                }
 
                return rtn;
            }
        });
    }

}

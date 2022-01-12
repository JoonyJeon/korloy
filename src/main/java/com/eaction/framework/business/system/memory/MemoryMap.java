/*****************************************************************************
 * 프로그램명  : MemoryConstant.java
 * 설     명  : 메모리데이터
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.09  LYS      1.0
 *****************************************************************************/
package com.eaction.framework.business.system.memory;

import java.util.HashMap;

public class MemoryMap extends HashMap{
	private static MemoryMap instance = new MemoryMap();
	
	private MemoryMap(){
		
	}
	
	public static MemoryMap getInstance(){
		if(instance == null) {
			instance = new MemoryMap();
		}
		return instance;
	}
}

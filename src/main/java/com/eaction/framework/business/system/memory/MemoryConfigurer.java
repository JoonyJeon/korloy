/*****************************************************************************
 * 프로그램명  : MemoryConfigurer.java
 * 설     명  : 메모리관리 실행
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.09  LYS      1.0
 *****************************************************************************/
package com.eaction.framework.business.system.memory;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;


public class MemoryConfigurer {
	@Autowired	
	private MemoryComponent memoryComponent;
	
	/**
	 * 초기화
	 */
	public synchronized void init() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		try{
			//서버 시작
			//run(MemoryConstant.MEM_STATE_START);
			memoryComponent.run(MemoryConstant.MEM_STATE_START);
		}catch(Exception e){

			memoryComponent.selectInitData(); // DB데이터 조회
		}
		
		stopWatch.stop();
	}
	
}

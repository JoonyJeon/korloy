/*****************************************************************************
 * 프로그램명  : MemoryComponent.java
 * 설     명  : 메모리관리 컨트롤러
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.09  LYS      1.0
 *****************************************************************************/
package com.eaction.framework.business.system.memory;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.code.OtherCodeTableMng;
import com.eaction.framework.business.common.code.UpCodeTableMng;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.common.menu.MenuMng;


@Component
public class MemoryComponent {	
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(MemoryComponent.class);    
//	@Autowired
//	private MenuPageMng menuPageMng;	
	@Autowired
	private CodeTableMng codeTableMng;
	@Autowired
	private LangMng langMng;
//	@Autowired
//	private OtherCodeTableMng otherCodeTableMng ;
//	@Autowired
//	private UpCodeTableMng upCodeTableMng;
	@Autowired
	private MenuMng menuMng;
	
	private MemoryMap clientData = MemoryMap.getInstance();
	
	
	public synchronized void run(String state) {
		clientData.put(MemoryConstant.MEM_STATE_KEY, state);

		logger.debug("client> DB 데이터 조회후 전송....");
		sendData(state, null);
					
	}
	
	public void selectInitData(){
		try {
			logger.debug("Memory Loaded Start!!");
			codeTableMng.initialize();
			langMng.initialize();
//			menuMng.initialize();
		} catch (Exception e) {
			logger.error("Memory Loaded Fail!!");
			logger.error(e.getStackTrace());
		}
	}
	
	
	/**
	 * HashMap에 메모리 정보 저장 
	 */
	private void setClientData(){
//		clientData.put(MemoryConstant.MEM_KEY_MENUPAGEMNG, MenuPageMng.getProps());
		
		clientData.put(MemoryConstant.MEM_KEY_CODETABLEMNG_PROPS, CodeTableMng.getProps());
		
//		clientData.put(MemoryConstant.MEM_KEY_OTHERCODETABLEMNG_PROPS, OtherCodeTableMng.getProps());
//		clientData.put(MemoryConstant.MEM_KEY_OTHERCODETABLEMNG_LISTPROPS, OtherCodeTableMng.getListProps());
//		
//		clientData.put(MemoryConstant.MEM_KEY_OTHERCODETABLEMNG_PROPS, UpCodeTableMng.getProps());
//		clientData.put(MemoryConstant.MEM_KEY_OTHERCODETABLEMNG_LISTPROPS, UpCodeTableMng.getListProps());
		
		clientData.put(MemoryConstant.MEM_KEY_LANGMNG_LANG_CODE, langMng.getLangCode());
		clientData.put(MemoryConstant.MEM_KEY_LANGMNG_PROPS_D, langMng.getTB_ECAT_LANG_TRAN_DProps());
		clientData.put(MemoryConstant.MEM_KEY_LANGMNG_PROPS_M, langMng.getTB_ECAT_LANG_TRAN_MProps());
		clientData.put(MemoryConstant.MEM_KEY_MENUMNG_PROPS, MenuMng.getProps());
		clientData.put(MemoryConstant.MEM_KEY_MENUMNG_HMENULIST, MenuMng.gethMenuList());
	}
	
	/**
	 * 서버에서 가져온 메모리 정보를 각각의 Properties에 저장
	 */
//	private void setMemoryProp(HashMap serverData){
////		MenuPageMng.setProps((Properties)serverData.get(MemoryConstant.MEM_KEY_MENUPAGEMNG));
//		
//		CodeTableMng.setProps((Properties)serverData.get(MemoryConstant.MEM_KEY_CODETABLEMNG_PROPS));
//		CodeTableMng.setListProps((Properties)serverData.get(MemoryConstant.MEM_KEY_CODETABLEMNG_LISTPROPS));
//		CodeTableMng.setChildProps((Properties)serverData.get(MemoryConstant.MEM_KEY_CODETABLEMNG_CHILDPROPS));
//		
////		OtherCodeTableMng.setProps((Properties)serverData.get(MemoryConstant.MEM_KEY_OTHERCODETABLEMNG_PROPS));
////		OtherCodeTableMng.setListProps((Properties)serverData.get(MemoryConstant.MEM_KEY_OTHERCODETABLEMNG_LISTPROPS));
////		
////		UpCodeTableMng.setProps((Properties)serverData.get(MemoryConstant.MEM_KEY_UPCODETABLEMNG_PROPS));
////		UpCodeTableMng.setListProps((Properties)serverData.get(MemoryConstant.MEM_KEY_UPCODETABLEMNG_LISTPROPS));
//		
//		MenuMng.setProps((Properties)serverData.get(MemoryConstant.MEM_KEY_MENUMNG_PROPS));
//		MenuMng.sethMenuList((Hashtable)serverData.get(MemoryConstant.MEM_KEY_MENUMNG_HMENULIST));				
//	}
	
	/**
	 * DB 조회 데이터 임시 서버에 전송
	 */
	private void sendData(String state, HashMap serverData) {
		//기동시 서버데이터가 없으면 데이터 조회
		if(MemoryConstant.MEM_STATE_START.equals(state) && serverData == null){
			selectInitData();
		}
		
		setClientData(); //메모리 정보 저장하여 전송
		
		if(MemoryConstant.MEM_STATE_END.equals(state)){
			HashMap clientEndMsg = new HashMap();
			clientEndMsg.put(MemoryConstant.MEM_STATE_KEY, state);
		}else{
			clientData.put(MemoryConstant.MEM_STATE_KEY, state);
		}
			
			
	}
}

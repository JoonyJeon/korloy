/*****************************************************************************
 * 프로그램명  : AssemblyConstKey.java
 * 설     명  : Assembly 상수 정의 클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.09.01  왕윤아    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.assembly.constant;

/**
 * Assembly 상수 정의
 * @author  eaction
 * @version 1.0
 */

public class AssemblyConstKey {
	
	public static final String ACTION_ASSEMBLY_TEST = "ACTION_ASSEMBLY_TEST";
	
	
	/** Assembly 목록 개수 처리 */
	public static final String ACTION_ASSEMBLY_LIST_CNT = "ACTION_ASSEMBLY_LIST_CNT";
	
	/** Assembly 목록 취득 처리 */
	public static final String ACTION_ASSEMBLY_LIST = "ACTION_ASSEMBLY_LIST";
	
	/** Assembly 개별 정보 취득 처리 */
	public static final String ACTION_ASSEMBLY_DETAIL = "ACTION_ASSEMBLY_DETAIL";
	
	/** Assembly 그룹 삭제 처리 */
	public static final String ACTION_ASSEMBLY_DEL_OK = "ACTION_ASSEMBLY_DEL_OK";
	
	/** Assembly 그룹내 아이템 삭제 처리 */
	public static final String ACTION_ASSEMBLY_ITEM_DEL_OK = "ACTION_ASSEMBLY_ITEM_DEL_OK";
	
	/** Assembly 등록 처리 */
	public static final String ACTION_ASSEMBLY_ADD_OK = "ACTION_ASSEMBLY_ADD_OK";
	
	/** Assembly 수정 처리 */
	public static final String ACTION_ASSEMBLY_EDIT_OK = "ACTION_ASSEMBLY_EDIT_OK";
	
	/** Cart 리스트 취득하는 처리 */
	public static final String ACTION_CART_LIST = "ACTION_CART_LIST";	
	
	/** Cart에 item 등록하는 처리 */
	public static final String ACTION_CART_ADD_OK = "ACTION_CART_ADD_OK";
	
	/** GTC 파일 다운로드하는 처리 */
	public static final String ACTION_GET_GTC_ZIP_FILE = "ACTION_GET_GTC_ZIP_FILE";	
	
	/** Assembly 정보 취득 처리 */
	public static final String ACTION_ASSEMBLY_INFO = "ACTION_ASSEMBLY_INFO";
	
	/** Assembly 생성 완료 건 Location정보 취득 처리 */
	public static final String ACTION_ASSEMBLY_BUILD_COMP_LOCATION = "ACTION_ASSEMBLY_BUILD_COMP_LOCATION";
	
	
	
	/** Assembly 생성 */
	public static final String ACTION_ASSEMBLY_BUILD_OK = "ACTION_ASSEMBLY_BUILD_OK";
	public static final String UPLOAD_ECAT_ASSEMBLY_FILE = "/data/assembly"; // 대표이미지, ISO 치수도면 이미지, Non-ISO 치수도면 이미지
	public static final String[] ARR_ASSEMBLY_SUFFIX = {"_detail.stp", "_basic.stp", ".dxf", ".txt", ".log", ".xml" };
	public static final String UPLOAD_ECAT_ASSEMBLY_XML = "/ecat/assembly"; //GTC Package
	public static final String UPLOAD_ECAT_ASSEMBLY_TEMP = "/ecat/assembly_temp";	
	public static final String UPLOAD_3DVIEWER_FILE_EXT = ".vizw"; //3d viewer file ext
	public static final String UPLOAD_3DVIEWER_FILE = "/data/3d_viewer"; //GTC Package 압축 파일명 prefix. korloy.gtc-package.자재번호.zip
	public static final String UPLOAD_GTC_DATA_FILE = "/data/data_files"; // P21 파일
	public static final String UPLOAD_3DVIEWER_FILE_BASIC_SUFFIC = "_basic.vizw"; //3d viewer file basic suffix
	public static final String UPLOAD_3DVIEWER_FILE_DETAIL_SUFFIC = "_detail.vizw"; //3d viewer file detail suffix
	public static final String GTC_PACKAGE_ZIP_PREFIX = "korloy.gtc-package."; //GTC Package 압축 파일명 prefix. korloy.gtc-package.자재번호.zip
	public static final String[] ARR_GTC_FOLDER_KEY = {"9", "10", "11", "12" }; //GTC 단품 PACKAGE KEY, DB에서 키 매칭용.
	public static final String GTC_PACKAGE_COPY_FOLDER = "/ecat/gtc"; //GTC Package
	public static final boolean COPY_GTC_PACKAGE_ASSEMBLY_BASIC_FILE = false; //어셈블리 GTC Package 만들때 basic.stp 파일을 복사할건지 여부.
	public static final String ACTION_ASSEMBLY_CREATE_CANCEL = "ACTION_ASSEMBLY_CREATE_CANCEL"; //생성 취소
}
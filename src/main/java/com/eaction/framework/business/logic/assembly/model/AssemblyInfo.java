/*****************************************************************************
 * 프로그램명  : AssemblyInfo.java
 * 설     명  : Assembly 정보 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 *2021.09.01   왕윤아    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.assembly.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


@Alias("AssemblyInfo")
public class AssemblyInfo extends EactReqInfo {
	
	
	/** 어셈블리 일련번호 */
	private String assem_no = "";
	/** 어셈블리명 */
	private String assem_nm = "";
	/** 어셈블리 설명 */
	private String assem_desc = "";
	/** Basic STP(3D)파일 */
	private String stp_b_file = "";
	/** Detail STP(3D) 파일 */
	private String stp_d_file = "";
	/** DXF(2D)파일 */
	private String dxf_file = "";
	/** GTC파일 생성 경로 */
	private String gtc_file = "";
	/** P21파일 생성 경로 */
	private String p21_file = "";
	/** 사용여부 */
	private String use_yn = "";
	/** 사용자아이디 */
	private String user_id = "";
	/** 아이템 이미지 */
	private String it_img = "";
	/** 아이템 그룹 코드 */
	private String ig_cd = "";
	/** 아이템재질 */
	private String grade = "";
	/** 수량 */
	private String quantity = "";
	

	/** Assembly그룹에 해당하는 아이템 목록취득을 위한 collection */
	List<AssemblyInfo> assembly_detail_list = new ArrayList<>();

	/*다중 추가 수정 삭제를 위한 파라미터*/
	private String addParam = "";
	private String editParam = "";
	private String[] deleteParam = null;
	
	/** CSW목록 */
	private List<String> arr_csw = null;
	
	/** 추가 버튼 여부 */
	private boolean add_btn = false;
	
	/** 어셈블리 검색 여부 */
	private boolean assem_yn = false;

	/**
	 * 아이템그룹 코드 설정
	 * @param ig_cd 아이템그룹 코드
	 */
	public void setIg_cd(String ig_cd) {
		this.ig_cd = ig_cd;
	}
	/**
	 * 아이템그룹 코드 취득
	 * @return ig_cd 아이템그룹 코드
	 */
	public String getIg_cd() {
		return StringUtil.nvl(this.ig_cd);
	}
	
	
	
	/**  */
	private static final long serialVersionUID = 1L;
	/**  */
	private String gtc_depth = "";
	/**  */
	private String gtc_vd_id = "";
	/**  */
	private String gtc_vd_pid = "";
	/**  */
	private String gtc_vd_rt = "";
	/**  */
	private String designation = "";
	/**  */
	private String matnr = "";
	/**  */
	private String symbol = "";
	/**  */
	private String val = "";
	/** ctype */
	private String ctype = "";
	
	private String search_unit_cd = "";

	public void setSearch_Unit_Cd(String unit_cd) {
			this.search_unit_cd = unit_cd;
		}
		
		public String getSearch_Unit_Cd() {
			return StringUtil.nvl(this.search_unit_cd);
		}	
	
	/*****************************************************
	 * GTC Hierarchy 구조
	 * : Assembly 순서 : 1 + 2 + 3
	 *****************************************************
	 * 1. Adaptive 	: CTL > ADP 
	 *****************************************************
	 * 2. Tool     	: CTL > TL
	 *****************************************************
	 *    Turning  	: CTL > TL > TRN 인경우
	 *    Solid    	: GTC_VD_ROOT값의 마지막 문자가 "S" 인경우 
	 *    Indexable	: GTC_VD_ROOT값의 마지막 문자가 "S" 인경우
	 *****************************************************
	 * 3. Cutting   : CTL > INS 
	 *****************************************************/
	/**  */
	private String assy_type = ""; // ADP, TL, INS	
	/**  */
	private String item_type = ""; // Turning, Solid, Indexable
	
	// 체결될 아이템 정보
	private List<AssemblyItemInfo> itemADP = null;
	private List<AssemblyItemInfo> itemTL = null;
	private List<AssemblyItemInfo> itemINS = null;
	
	/** Workpiece Side (오른쪽) */
	private List<AssemblyItemInfo> itemWS = null;
	/** Machine Side (왼쪽) */
	private List<AssemblyItemInfo> itemMS = null;

	
	/**  */
	private String itemADP_str = "";
	/**  */
	private String itemTL_str = "";
	/**  */
	private String itemINS_str = "";

	
	// 체결될 아이템 그룹코드 정보
	private List<AssemblyItemInfo> itGroupADP = null;
	private List<AssemblyItemInfo> itGroupTL = null;
	private List<AssemblyItemInfo> itGroupINS = null;
	private List<AssemblyItemInfo> itGroupWS = null;
	private List<AssemblyItemInfo> itGroupMS = null;
	

	/** 카트번호 */
	private String cart_no = "";
	/** 카트명 */
	private String cart_nm = "";
	
		
	
	/** xml에서 필요한 정보. */	
	//생성될 파일명 확장자 등 제외 만들 이름만.(stp, dxf)
	private String fileName = "";
	//복제된 디렉토리. (추후 삭제를 위하여)
	private String tempFolderPath = "";
	//Assembly xml 풀 경로. 파일 실행을 위한 위치.
	private String xmlFileFullPath = "";
	//Assembly 결과 Detail filename.
	private String assyDetailFileName = "";
	//Assembly 결과 basic filename.
	private String assyBasicFileName = "";
	//Assembly 결과 Dxf filename. 
	private String assyDxfFileName = "";
	//3D viewer용 파일. (fileName_basic.vizw). 파일 생성 여부 확인용.
	private String vizwBasicFilePath = "";
	//3D viewer용 파일. (fileName_detail.vizw). 파일 생성 여부 확인용.
	private String vizwDetailFilePath = "";
	

	/** 다운로드파일경로(웹) */
	private String file_dwl_path = "";
	/** 원본파일명 */
	private String file_org_nm = "";
	/** 썸네일파일명 */
	private String file_thn_nm = "";
	
	
	/** 아답터 갯수 */
	private int adp_cnt = 0;
	/** 아이템 갯수 */
	private int item_cnt = 0;
	
	/** Location위치 */
	private String location = "";



	/**
	 * Location위치 설정
	 * @param location Location위치
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * Location위치 취득
	 * @return location Location위치
	 */
	public String getLocation() {
		return StringUtil.nvl(this.location);
	}	
	
	/**
	 * 다운로드파일경로(웹) 설정
	 * @param file_dwl_path 다운로드파일경로(웹)
	 */
	public void setFile_dwl_path(String file_dwl_path) {
		this.file_dwl_path = file_dwl_path;
	}
	/**
	 * 다운로드파일경로(웹) 취득
	 * @return file_dwl_path 다운로드파일경로(웹)
	 */
	public String getFile_dwl_path() {
		return StringUtil.nvl(this.file_dwl_path);
	}

	/**
	 * 원본파일명 설정
	 * @param file_org_nm 원본파일명
	 */
	public void setFile_org_nm(String file_org_nm) {
		this.file_org_nm = file_org_nm;
	}
	/**
	 * 원본파일명 취득
	 * @return file_org_nm 원본파일명
	 */
	public String getFile_org_nm() {
		return StringUtil.nvl(this.file_org_nm);
	}

	/**
	 * 썸네일파일명 설정
	 * @param file_thn_nm 썸네일파일명
	 */
	public void setFile_thn_nm(String file_thn_nm) {
		this.file_thn_nm = file_thn_nm;
	}
	/**
	 * 썸네일파일명 취득
	 * @return file_thn_nm 썸네일파일명
	 */
	public String getFile_thn_nm() {
		return StringUtil.nvl(this.file_thn_nm);
	}
	
	/**
	 * 아이템재질 설정
	 * @param grade 아이템재질
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * 아이템재질 취득
	 * @return grade 아이템재질
	 */
	public String getGrade() {
		return StringUtil.nvl(this.grade);
	}
	
	/**
	 * 수량 설정
	 * @param quantity 수량
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	/**
	 * 수량 취득
	 * @return quantity 수량
	 */
	public String getQuantity() {
		return StringUtil.nvl(this.quantity);
	}
	
	/**
	 *  설정
	 * @param serialVersionUID 
	 */
//	public void setSerialVersionUID(long serialVersionUID) {
//		this.serialVersionUID = serialVersionUID;
//	}
	/**
	 *  취득
	 * @return serialVersionUID 
	 */
//	public long getSerialVersionUID() {
//		return this.serialVersionUID;
//	}

	/**
	 *  설정
	 * @param gtc_depth 
	 */
	public void setGtc_depth(String gtc_depth) {
		this.gtc_depth = gtc_depth;
	}
	/**
	 *  취득
	 * @return gtc_depth 
	 */
	public String getGtc_depth() {
		return StringUtil.nvl(this.gtc_depth);
	}

	/**
	 *  설정
	 * @param gtc_vd_id 
	 */
	public void setGtc_vd_id(String gtc_vd_id) {
		this.gtc_vd_id = gtc_vd_id;
	}
	/**
	 *  취득
	 * @return gtc_vd_id 
	 */
	public String getGtc_vd_id() {
		return StringUtil.nvl(this.gtc_vd_id);
	}

	/**
	 *  설정
	 * @param gtc_vd_pid 
	 */
	public void setGtc_vd_pid(String gtc_vd_pid) {
		this.gtc_vd_pid = gtc_vd_pid;
	}
	/**
	 *  취득
	 * @return gtc_vd_pid 
	 */
	public String getGtc_vd_pid() {
		return StringUtil.nvl(this.gtc_vd_pid);
	}

	/**
	 *  설정
	 * @param gtc_vd_rt 
	 */
	public void setGtc_vd_rt(String gtc_vd_rt) {
		this.gtc_vd_rt = gtc_vd_rt;
	}
	/**
	 *  취득
	 * @return gtc_vd_rt 
	 */
	public String getGtc_vd_rt() {
		return StringUtil.nvl(this.gtc_vd_rt);
	}

	/**
	 *  설정
	 * @param designation 
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 *  취득
	 * @return designation 
	 */
	public String getDesignation() {
		return StringUtil.nvl(this.designation);
	}

	/**
	 *  설정
	 * @param matnr 
	 */
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	/**
	 *  취득
	 * @return matnr 
	 */
	public String getMatnr() {
		return StringUtil.nvl(this.matnr);
	}

	/**
	 *  설정
	 * @param symbol 
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 *  취득
	 * @return symbol 
	 */
	public String getSymbol() {
		return StringUtil.nvl(this.symbol);
	}

	/**
	 *  설정
	 * @param val 
	 */
	public void setVal(String val) {
		this.val = val;
	}
	/**
	 *  취득
	 * @return val 
	 */
	public String getVal() {
		return StringUtil.nvl(this.val);
	}

	/**
	 *  설정
	 * @param assy_type 
	 */
	public void setAssy_type(String assy_type) {
		this.assy_type = assy_type;
	}
	/**
	 *  취득
	 * @return assy_type 
	 */
	public String getAssy_type() {
		return StringUtil.nvl(this.assy_type);
	}

	/**
	 *  설정
	 * @param item_type 
	 */
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	/**
	 *  취득
	 * @return item_type 
	 */
	public String getItem_type() {
		return StringUtil.nvl(this.item_type);
	}

	/**
	 *  설정
	 * @param itemADP 
	 */
	public void setItemADP(List<AssemblyItemInfo> itemADP) {
		this.itemADP = itemADP;
	}
	/**
	 *  취득
	 * @return itemADP 
	 */
	public List<AssemblyItemInfo> getItemADP() {
		return this.itemADP;
	}

	/**
	 *  설정
	 * @param itemTL 
	 */
	public void setItemTL(List<AssemblyItemInfo> itemTL) {
		this.itemTL = itemTL;
	}
	/**
	 *  취득
	 * @return itemTL 
	 */
	public List<AssemblyItemInfo> getItemTL() {
		return this.itemTL;
	}

	/**
	 *  설정
	 * @param itemINS 
	 */
	public void setItemINS(List<AssemblyItemInfo> itemINS) {
		this.itemINS = itemINS;
	}
	/**
	 *  취득
	 * @return itemINS 
	 */
	public List<AssemblyItemInfo> getItemINS() {
		return this.itemINS;
	}
	
	/**
	 *  설정
	 * @param itemADP_str 
	 */
	public void setItemADP_str(String itemADP_str) {
		this.itemADP_str = itemADP_str;
	}
	/**
	 *  취득
	 * @return itemADP_str 
	 */
	public String getItemADP_str() {
		return StringUtil.nvl(this.itemADP_str);
	}

	/**
	 *  설정
	 * @param itemTL_str 
	 */
	public void setItemTL_str(String itemTL_str) {
		this.itemTL_str = itemTL_str;
	}
	/**
	 *  취득
	 * @return itemTL_str 
	 */
	public String getItemTL_str() {
		return StringUtil.nvl(this.itemTL_str);
	}

	/**
	 *  설정
	 * @param itemINS_str 
	 */
	public void setItemINS_str(String itemINS_str) {
		this.itemINS_str = itemINS_str;
	}
	/**
	 *  취득
	 * @return itemINS_str 
	 */
	public String getItemINS_str() {
		return StringUtil.nvl(this.itemINS_str);
	}
	
	/**
	 *  설정
	 * @param itGroupADP 
	 */
	public void setItGroupADP(List<AssemblyItemInfo> itGroupADP) {
		this.itGroupADP = itGroupADP;
	}
	/**
	 *  취득
	 * @return itGroupADP 
	 */
	public List<AssemblyItemInfo> getItGroupADP() {
		return this.itGroupADP;
	}

	/**
	 *  설정
	 * @param itGroupTL 
	 */
	public void setItGroupTL(List<AssemblyItemInfo> itGroupTL) {
		this.itGroupTL = itGroupTL;
	}
	/**
	 *  취득
	 * @return itGroupTL 
	 */
	public List<AssemblyItemInfo> getItGroupTL() {
		return this.itGroupTL;
	}

	/**
	 *  설정
	 * @param itGroupINS 
	 */
	public void setItGroupINS(List<AssemblyItemInfo> itGroupINS) {
		this.itGroupINS = itGroupINS;
	}
	/**
	 *  취득
	 * @return itGroupINS 
	 */
	public List<AssemblyItemInfo> getItGroupINS() {
		return this.itGroupINS;
	}
	
	/**
	 *  설정
	 * @param itGroupWS 
	 */
	public void setItGroupWS(List<AssemblyItemInfo> itGroupWS) {
		this.itGroupWS = itGroupWS;
	}
	/**
	 *  취득
	 * @return itGroupWS 
	 */
	public List<AssemblyItemInfo> getItGroupWS() {
		return this.itGroupWS;
	}

	/**
	 *  설정
	 * @param itGroupMS 
	 */
	public void setItGroupMS(List<AssemblyItemInfo> itGroupMS) {
		this.itGroupMS = itGroupMS;
	}
	/**
	 *  취득
	 * @return itGroupMS 
	 */
	public List<AssemblyItemInfo> getItGroupMS() {
		return this.itGroupMS;
	}
	
	/**
	 * Workpiece Side (오른쪽) 설정
	 * @param itemWS Workpiece Side (오른쪽)
	 */
	public void setItemWS(List<AssemblyItemInfo> itemWS) {
		this.itemWS = itemWS;
	}
	/**
	 * Workpiece Side (오른쪽) 취득
	 * @return itemWS Workpiece Side (오른쪽)
	 */
	public List<AssemblyItemInfo> getItemWS() {
		return this.itemWS;
	}

	/**
	 * Machine Side (왼쪽) 설정
	 * @param itemMS Machine Side (왼쪽)
	 */
	public void setItemMS(List<AssemblyItemInfo> itemMS) {
		this.itemMS = itemMS;
	}
	/**
	 * Machine Side (왼쪽) 취득
	 * @return itemMS Machine Side (왼쪽)
	 */
	public List<AssemblyItemInfo> getItemMS() {
		return this.itemMS;
	}

	/**
	 *  설정
	 * @param fileName 
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 *  취득
	 * @return fileName 
	 */
	public String getFileName() {
		return StringUtil.nvl(this.fileName);
	}

	/**
	 *  설정
	 * @param tempFolderPath 
	 */
	public void setTempFolderPath(String tempFolderPath) {
		this.tempFolderPath = tempFolderPath;
	}
	/**
	 *  취득
	 * @return tempFolderPath 
	 */
	public String getTempFolderPath() {
		return StringUtil.nvl(this.tempFolderPath);
	}

	/**
	 *  설정
	 * @param xmlFileFullPath 
	 */
	public void setXmlFileFullPath(String xmlFileFullPath) {
		this.xmlFileFullPath = xmlFileFullPath;
	}
	/**
	 *  취득
	 * @return xmlFileFullPath 
	 */
	public String getXmlFileFullPath() {
		return StringUtil.nvl(this.xmlFileFullPath);
	}

	/**
	 *  설정
	 * @param assyDetailFileName 
	 */
	public void setAssyDetailFileName(String assyDetailFileName) {
		this.assyDetailFileName = assyDetailFileName;
	}
	/**
	 *  취득
	 * @return assyDetailFileName 
	 */
	public String getAssyDetailFileName() {
		return StringUtil.nvl(this.assyDetailFileName);
	}

	/**
	 *  설정
	 * @param assyBasicFileName 
	 */
	public void setAssyBasicFileName(String assyBasicFileName) {
		this.assyBasicFileName = assyBasicFileName;
	}
	/**
	 *  취득
	 * @return assyBasicFileName 
	 */
	public String getAssyBasicFileName() {
		return StringUtil.nvl(this.assyBasicFileName);
	}

	/**
	 *  설정
	 * @param assyDxfFileName 
	 */
	public void setAssyDxfFileName(String assyDxfFileName) {
		this.assyDxfFileName = assyDxfFileName;
	}
	/**
	 *  취득
	 * @return assyDxfFileName 
	 */
	public String getAssyDxfFileName() {
		return StringUtil.nvl(this.assyDxfFileName);
	}

	/**
	 *  설정
	 * @param vizwBasicFilePath 
	 */
	public void setVizwBasicFilePath(String vizwBasicFilePath) {
		this.vizwBasicFilePath = vizwBasicFilePath;
	}
	/**
	 *  취득
	 * @return vizwBasicFilePath 
	 */
	public String getVizwBasicFilePath() {
		return StringUtil.nvl(this.vizwBasicFilePath);
	}

	/**
	 *  설정
	 * @param vizwDetailFilePath 
	 */
	public void setVizwDetailFilePath(String vizwDetailFilePath) {
		this.vizwDetailFilePath = vizwDetailFilePath;
	}
	/**
	 *  취득
	 * @return vizwDetailFilePath 
	 */
	public String getVizwDetailFilePath() {
		return StringUtil.nvl(this.vizwDetailFilePath);
	}
	
	

	
	/**
	 * Assembly그룹에 해당하는 아이템 목록 설정
	 * @param assembly_detail_list Assembly그룹에 해당하는 아이템 목록
	 */
	public void setAssembly_detail_list(List<AssemblyInfo> assembly_detail_list) {
		this.assembly_detail_list = assembly_detail_list;
	}
	/**
	 * Assembly그룹에 해당하는 아이템 목록 취득
	 * @return assembly_detail_list Assembly그룹에 해당하는 아이템 목록
	 */
	public List<AssemblyInfo> getAssembly_detail_list() {
		return this.assembly_detail_list;
	}

	/**
	 * 어셈블리 일련번호 설정
	 * @param assem_no 어셈블리 일련번호
	 */
	public void setAssem_no(String assem_no) {
		this.assem_no = assem_no;
	}
	/**
	 * 어셈블리 일련번호 취득
	 * @return assem_no 어셈블리 일련번호
	 */
	public String getAssem_no() {
		return StringUtil.nvl(this.assem_no);
	}

	/**
	 * 어셈블리명 설정
	 * @param assem_nm 어셈블리명
	 */
	public void setAssem_nm(String assem_nm) {
		this.assem_nm = assem_nm;
	}
	/**
	 * 어셈블리명 취득
	 * @return assem_nm 어셈블리명
	 */
	public String getAssem_nm() {
		return StringUtil.nvl(this.assem_nm);
	}

	/**
	 * 어셈블리 설명 설정
	 * @param assem_desc 어셈블리 설명
	 */
	public void setAssem_desc(String assem_desc) {
		this.assem_desc = assem_desc;
	}
	/**
	 * 어셈블리 설명 취득
	 * @return assem_desc 어셈블리 설명
	 */
	public String getAssem_desc() {
		return StringUtil.nvl(this.assem_desc);
	}

	/**
	 * Basic STP(3D)파일 설정
	 * @param stp_b_file Basic STP(3D)파일
	 */
	public void setStp_b_file(String stp_b_file) {
		this.stp_b_file = stp_b_file;
	}
	/**
	 * Basic STP(3D)파일 취득
	 * @return stp_b_file Basic STP(3D)파일
	 */
	public String getStp_b_file() {
		return StringUtil.nvl(this.stp_b_file);
	}

	/**
	 * Detail STP(3D) 파일 설정
	 * @param stp_d_file Detail STP(3D) 파일
	 */
	public void setStp_d_file(String stp_d_file) {
		this.stp_d_file = stp_d_file;
	}
	/**
	 * Detail STP(3D) 파일 취득
	 * @return stp_d_file Detail STP(3D) 파일
	 */
	public String getStp_d_file() {
		return StringUtil.nvl(this.stp_d_file);
	}

	/**
	 * DXF(2D)파일 설정
	 * @param dxf_file DXF(2D)파일
	 */
	public void setDxf_file(String dxf_file) {
		this.dxf_file = dxf_file;
	}
	/**
	 * DXF(2D)파일 취득
	 * @return dxf_file DXF(2D)파일
	 */
	public String getDxf_file() {
		return StringUtil.nvl(this.dxf_file);
	}

	/**
	 * GTC파일 생성 경로 설정
	 * @param gtc_file GTC파일 생성 경로
	 */
	public void setGtc_file(String gtc_file) {
		this.gtc_file = gtc_file;
	}
	/**
	 * GTC파일 생성 경로 취득
	 * @return gtc_file GTC파일 생성 경로
	 */
	public String getGtc_file() {
		return StringUtil.nvl(this.gtc_file);
	}

	/**
	 * P21파일 생성 경로 설정
	 * @param p21_file P21파일 생성 경로
	 */
	public void setP21_file(String p21_file) {
		this.p21_file = p21_file;
	}
	/**
	 * P21파일 생성 경로 취득
	 * @return p21_file P21파일 생성 경로
	 */
	public String getP21_file() {
		return StringUtil.nvl(this.p21_file);
	}

	/**
	 * 사용여부 설정
	 * @param use_yn 사용여부
	 */
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	/**
	 * 사용여부 취득
	 * @return use_yn 사용여부
	 */
	public String getUse_yn() {
		return StringUtil.nvl(this.use_yn);
	}

	/**
	 * 사용자아이디 설정
	 * @param user_id 사용자아이디
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * 사용자아이디 취득
	 * @return user_id 사용자아이디
	 */
	public String getUser_id() {
		return StringUtil.nvl(this.user_id);
	}
	
	/**
	 * 아이템 이미지 설정
	 * @param it_img 아이템 이미지
	 */
	public void setIt_img(String it_img) {
		this.it_img = it_img;
	}
	/**
	 * 아이템 이미지 취득
	 * @return it_img 아이템 이미지
	 */
	public String getIt_img() {
		return StringUtil.nvl(this.it_img);
	}

	/**
	 * 추가할 장바구니 정보 취득
	 * @return addParam 추가할 장바구니 정보
	 */
	public String getAddParam() {
		return StringUtil.nvl(addParam);
	}
	/**
	 * 추가할 장바구니 정보 설정
	 * @return addParam 추가할 장바구니 정보
	 */
	public void setAddParam(String addParam) {
		this.addParam = addParam;
	}
	/**
	 * 수정할 장바구니 정보 취득
	 * @return editParam 수정할 장바구니 정보
	 */
	public String getEditParam() {
		return StringUtil.nvl(editParam);
	}
	/**
	 * 수정할 장바구니 정보 설정
	 * @return editParam 수정할 장바구니 정보
	 */
	public void setEditParam(String editParam) {
		this.editParam = editParam;
	}
	/**
	 * 삭제할 장바구니 배열 설정
	 * @param ma_cd_arr Main Application 검색 배열
	 */
	public String[] getDeleteParam() {
		if(this.deleteParam != null){
			return Arrays.copyOf(this.deleteParam, this.deleteParam.length);
		} else {
			return null;
		}
	}
	/**
	 * 삭제할 장바구니 배열 설정
	 * @param deleteParam Main Application 검색 배열
	 */
	public void setDeleteParam(String[] deleteParam) {
		if(deleteParam != null){
			this.deleteParam = Arrays.copyOf(deleteParam, deleteParam.length);
		}
	}
	
	/**
	 * 카트명 설정
	 * @param cart_nm 카트명
	 */
	public void setCart_nm(String cart_nm) {
		this.cart_nm = cart_nm;
	}
	/**
	 * 카트명 취득
	 * @return cart_nm 카트명
	 */
	public String getCart_nm() {
		return StringUtil.nvl(this.cart_nm);
	}
	
	/**
	 * 카트번호 설정
	 * @param cart_no 카트번호
	 */
	public void setCart_no(String cart_no) {
		this.cart_no = cart_no;
	}
	/**
	 * 카트번호 취득
	 * @return cart_no 카트번호
	 */
	public String getCart_no() {
		return StringUtil.nvl(this.cart_no);
	}
	
	/**
	 * 아답터 갯수 설정
	 * @param adp_cnt 아답터 갯수
	 */
	public void setAdp_cnt(int adp_cnt) {
		this.adp_cnt = adp_cnt;
	}
	/**
	 * 아답터 갯수 취득
	 * @return adp_cnt 아답터 갯수
	 */
	public int getAdp_cnt() {
		return this.adp_cnt;
	}
	
	/**
	 * ctype 설정
	 * @param ctype ctype
	 */
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	/**
	 * ctype 취득
	 * @return ctype ctype
	 */
	public String getCtype() {
		return StringUtil.nvl(this.ctype);
	}
	
	/**
	 * 아이템 갯수 설정
	 * @param item_cnt 아이템 갯수
	 */
	public void setItem_cnt(int item_cnt) {
		this.item_cnt = item_cnt;
	}
	/**
	 * 아이템 갯수 취득
	 * @return item_cnt 아이템 갯수
	 */
	public int getItem_cnt() {
		return this.item_cnt;
	}
	
	/**
	 * CSW목록 설정
	 * @param arr_csw CSW목록
	 */
	public void setArr_csw(List<String> arr_csw) {
		this.arr_csw = arr_csw;
	}
	/**
	 * CSW목록 취득
	 * @return arr_csw CSW목록
	 */
	public List<String> getArr_csw() {
		return this.arr_csw;
	}
	
	/**
	 * 추가 버튼 여부 설정
	 * @param add_btn 추가 버튼 여부
	 */
	public void setAdd_btn(boolean add_btn) {
		this.add_btn = add_btn;
	}
	/**
	 * 추가 버튼 여부 취득
	 * @return add_btn 추가 버튼 여부
	 */
	public boolean getAdd_btn() {
		return this.add_btn;
	}
	
	/**
	 * 어셈블리 검색 여부 설정
	 * @param assem_yn 어셈블리 검색 여부
	 */
	public void setAssem_yn(boolean assem_yn) {
		this.assem_yn = assem_yn;
	}
	/**
	 * 어셈블리 검색 여부 취득
	 * @return assem_yn 어셈블리 검색 여부
	 */
	public boolean getAssem_yn() {
		return this.assem_yn;
	}
	
	/** 서브어플리케이션코드 리스트 */
	private List<AssemblyItemInfo> saCdList = null;



	/**
	 * 서브어플리케이션코드 리스트 설정
	 * @param saCdList 서브어플리케이션코드 리스트
	 */
	public void setSaCdList(List<AssemblyItemInfo> saCdList) {
		this.saCdList = saCdList;
	}
	/**
	 * 서브어플리케이션코드 리스트 취득
	 * @return saCdList 서브어플리케이션코드 리스트
	 */
	public List<AssemblyItemInfo> getSaCdList() {
		return this.saCdList;
	}
	
	/** 생성상태 */
	private String buld_sta = "";
	/** 생성에러메시지 */
	private String buld_msg = "";
	/** 생성시작시간 */
	private String buld_st = "";
	/** 생성종료시간 */
	private String buld_et = "";



	/**
	 * 생성상태 설정
	 * @param buld_sta 생성상태
	 */
	public void setBuld_sta(String buld_sta) {
		this.buld_sta = buld_sta;
	}
	/**
	 * 생성상태 취득
	 * @return buld_sta 생성상태
	 */
	public String getBuld_sta() {
		return StringUtil.nvl(this.buld_sta);
	}

	/**
	 * 생성에러메시지 설정
	 * @param buld_msg 생성에러메시지
	 */
	public void setBuld_msg(String buld_msg) {
		this.buld_msg = buld_msg;
	}
	/**
	 * 생성에러메시지 취득
	 * @return buld_msg 생성에러메시지
	 */
	public String getBuld_msg() {
		return StringUtil.nvl(this.buld_msg);
	}

	/**
	 * 생성시작시간 설정
	 * @param buld_st 생성시작시간
	 */
	public void setBuld_st(String buld_st) {
		this.buld_st = buld_st;
	}
	/**
	 * 생성시작시간 취득
	 * @return buld_st 생성시작시간
	 */
	public String getBuld_st() {
		return StringUtil.nvl(this.buld_st);
	}

	/**
	 * 생성종료시간 설정
	 * @param buld_et 생성종료시간
	 */
	public void setBuld_et(String buld_et) {
		this.buld_et = buld_et;
	}
	/**
	 * 생성종료시간 취득
	 * @return buld_et 생성종료시간
	 */
	public String getBuld_et() {
		return StringUtil.nvl(this.buld_et);
	}

}

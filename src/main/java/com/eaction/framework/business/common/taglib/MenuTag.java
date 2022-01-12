/*****************************************************************************
 * 프로그램명  : MenuTag.java
 * 설     명  : HTML 태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.taglib;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.menu.MenuMng;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.MenuInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;
/**
 * 왼쪽메뉴표시태그라이브러리 설정
 * @author  eaction
 * @version 1.0
 */
public class MenuTag extends TagSupport {
	
	/** 보여줄 루트메뉴 값 전달받는다. (top에서는 "top" 값이 넘어온다) */
	private String root = "";
	private String menu_url = "";
	private String top_root = "";
	/**
     * 시작 태그 처리
     * @return String 권한별표시여부태그문자열
     */
	public int doStartTag() throws JspException {
		try {

			StringBuffer sbMenu = new StringBuffer();
			HttpSession session = (HttpSession)pageContext.getSession();
			User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);

			List arList = new ArrayList();//MenuMng.getMenuList(userInfo);
			MenuInfo menuInfo = new MenuInfo();
			int rootLevel = 0;
			String preLevel = "";
			String preGourp = "";
			String rootName = "";
			int isFirstGroup = 0;
			boolean firstMenuYn = true;
			
			String preMenuId = "";
			String preMenuType = "";
			String menuIdChk = "";
			String topMenuId = "";
			
			String menuIcon = "";

			if ((arList != null) && (arList.size() > 0)) {
				//top메뉴? left메뉴?
				if(this.root.equals("top_menu")){
					for (int i = 0 ; i < arList.size(); i++) {
						menuInfo = (MenuInfo)arList.get(i);
						if(menuInfo.getMenuType().equals("G")){
							if(menuInfo.getLvl().equals("2")){
								if(preLevel.equals("")){
									sbMenu.append("<li id=\"top\"");
									if(top_root != null && top_root.equals(menuInfo.getMenuId())){
										sbMenu.append(" class=\"clicked\" ");
									}
									sbMenu.append(">");
								}else if(preLevel.equals("3") || preLevel.equals("2")){
									sbMenu.append("</ul></li><li id=\"top\"");
									if(top_root != null && top_root.equals(menuInfo.getMenuId())){
										sbMenu.append(" class=\"clicked\" ");
									}
									sbMenu.append(">");
								}
								preLevel = menuInfo.getLvl();
								sbMenu.append("<a onclick=\"goMenu('");
								sbMenu.append(menuInfo.getMenuId());
								sbMenu.append("','");
								sbMenu.append(menuInfo.getUrl());	    //추가 2015.12.09 KYM
								sbMenu.append("','');return false;\">");
								sbMenu.append(menuInfo.getMenuNm());
								sbMenu.append("</a>");
								sbMenu.append("<ul style=\"width:380px;\">");
							}else if(menuInfo.getLvl().equals("3")){
								preLevel = menuInfo.getLvl();

								sbMenu.append("<li id=\"sub\"><a onclick=\"goMenu('");
								sbMenu.append(menuInfo.getMenuId());
								sbMenu.append("','");
								sbMenu.append(menuInfo.getUrl());	    //추가 2015.12.09 KYM
								sbMenu.append("','");
								sbMenu.append(menuInfo.getUpMenuId());
								sbMenu.append("');return false;\">");
								sbMenu.append(menuInfo.getMenuNm());
								sbMenu.append("</a></li>");
							}
						}
					}
				}else{
					StringBuffer sb = new StringBuffer();
					sb.append(root);
					sb.append(";");
					
					/***************클릭한 URL의 최상위 메뉴 아이디 취득*******************/
					if(menu_url != null && !"".equals(menu_url)){
						menuIdChk = MenuMng.getMenuId(menu_url);
					}						
					if(!"".equals(menuIdChk)){						
						topMenuId= getTopUpMenuId(menuIdChk, 0);
						topMenuId = topMenuId.split("&")[0];
					}
					/***************클릭한 URL의 최상위 메뉴 아이디 취득*******************/
					
					
										
					for (int i = 0 ; i < arList.size(); i++) {
						menuInfo = (MenuInfo)arList.get(i);
						if(userInfo.getUser_lang().equals("KOR")){
							menuInfo.setMenuNm(menuInfo.getMenuNmKor());
						}else if(userInfo.getUser_lang().equals("ENG")){
							menuInfo.setMenuNm(menuInfo.getMenuNmEng());
						}else{
							menuInfo.setMenuNm(menuInfo.getMenuNmJpn());
						}
						
						if(menuInfo.getMenuId().equals(this.root)){
							rootLevel = Integer.parseInt(menuInfo.getLvl());
							rootName = menuInfo.getMenuNm();
							sbMenu.append("<nav>");
							sbMenu.append("<ul>");
						//1depth
						}else if(!menuInfo.getUpMenuId().equals("")  && sb.toString().indexOf(menuInfo.getUpMenuId()+";")!=-1 &&  menuInfo.getLvl().equals(String.valueOf(rootLevel+1))){

							if(!preLevel.equals("") && Integer.parseInt(preLevel) > Integer.parseInt(menuInfo.getLvl())){
								for(int z=0;z<Integer.parseInt(preLevel) - Integer.parseInt(menuInfo.getLvl());z++)
									sbMenu.append("</ul></li>");
							}
													
							if(menuInfo.getMenuType().equals("G")){
								//메뉴 아이콘
								menuIcon = menuInfo.getMenu_icon();
								
								sbMenu.append("<li ");
								if(menuInfo.getMenuId().equals(topMenuId)){
									sbMenu.append(" class=\"active\" >");
								}else{
									sbMenu.append(">");
								}
								
								sbMenu.append("<a href=\"#\" >");
								sbMenu.append("<i class=\"fa fa-lg fa-fw ");
								sbMenu.append(menuIcon);
								sbMenu.append("\">");
								sbMenu.append("</i>");
								sbMenu.append("<span class=\"menu-item-parent\"> ");
								sbMenu.append(menuInfo.getMenuNm());
								sbMenu.append("</span>");
								sbMenu.append("</a>");
								sbMenu.append("<ul>");
							}else{
								//메뉴 아이콘
								menuIcon = menuInfo.getMenu_icon();
								
								sbMenu.append("<li");
								if(menu_url != null && menu_url.equals(menuInfo.getUrl())){
									sbMenu.append(" class=\"active\" >");
								}else{
									sbMenu.append(">");
								}
								sbMenu.append("<a href=\"" + menuInfo.getUrl() );								
								sbMenu.append("\">");
								if(!"".equals(menuIcon)){
									sbMenu.append("<i class=\"fa fa-lg fa-fw ");
									sbMenu.append(menuIcon);
									sbMenu.append("\">");
									sbMenu.append("</i>");
								}
								sbMenu.append("<span class=\"menu-item-parent\"> ");
								sbMenu.append(menuInfo.getMenuNm());
								sbMenu.append("</span>");
								
								sbMenu.append("</a>");
								sbMenu.append("</li>");

								preGourp = "";								
							}
							sbMenu.append("");
							preLevel = menuInfo.getLvl();
							preMenuId = menuInfo.getMenuId();
							preMenuType = menuInfo.getMenuType();
							sb.append(menuInfo.getMenuId()+";");
						//2depth 이상
						}else if(!menuInfo.getUpMenuId().equals("")  && sb.toString().indexOf(menuInfo.getUpMenuId()+";")!=-1 && rootLevel < Integer.parseInt(menuInfo.getLvl())-1){

 							if(Integer.parseInt(preLevel) > Integer.parseInt(menuInfo.getLvl())){
								for(int z=0;z<Integer.parseInt(preLevel) - Integer.parseInt(menuInfo.getLvl());z++)
									sbMenu.append("</ul></li>");
							}
							
							if(menuInfo.getMenuType().equals("G")){
								//메뉴 아이콘
								menuIcon = menuInfo.getMenu_icon();
								
								boolean selFlg2 = false;
								for (int j = 0 ; j < arList.size(); j++) {
									MenuInfo menuInfo2 = (MenuInfo)arList.get(j);
									if(menuInfo.getMenuId().equals(menuInfo2.getUpMenuId())){
										if(menu_url != null && menu_url.equals(menuInfo2.getUrl())){
											selFlg2 = true;
										}
									}
								}
								sbMenu.append("<li ");
								if(selFlg2){
									sbMenu.append(" class=\"active\" >");
								}else{
									sbMenu.append(">");
								}
																
								
								sbMenu.append("<a href=\"#\" >");								
								sbMenu.append("<i class=\"fa fa-lg fa-fw ");
								sbMenu.append(menuIcon);
								sbMenu.append("\">");
								sbMenu.append("</i>");
								sbMenu.append("<span class=\"menu-item-parent\"> ");
								sbMenu.append(menuInfo.getMenuNm());
								sbMenu.append("</span>");
								sbMenu.append("</a>");
								sbMenu.append("<ul>");
							}else if(menuInfo.getMenuType().equals("W")) {
								String width = "";
								String height = "";
								String url = "";
								if(menuInfo.getUrl().matches(".*,.*")){
						        	url = menuInfo.getUrl().substring(0, menuInfo.getUrl().indexOf(","));
							    }else{
									url = menuInfo.getUrl();	
								}
								
								if(menuInfo.getUrl().matches(".*W=.*")){
									width = menuInfo.getUrl().substring(menuInfo.getUrl().indexOf("W=")+2, menuInfo.getUrl().indexOf(",H="));
								}else{
									width = "1025";
						        }
								
								if(menuInfo.getUrl().matches(".*H=.*")){
									height = menuInfo.getUrl().substring(menuInfo.getUrl().indexOf("H=")+2);
								}else{
									height = "700";
								}
								
								sbMenu.append("<li");	
								if(menu_url != null && menu_url.equals(menuInfo.getUrl())){
									sbMenu.append(" class=\"active\" >");
								}else{
									sbMenu.append(">");
								}
								sbMenu.append("<a onclick=\"javascript:goPagePop('");							
						        sbMenu.append(url);
								sbMenu.append("','" + width +"','"+ height +"');");
						        sbMenu.append("\" style=\"cursor: pointer;\">");
								sbMenu.append(menuInfo.getMenuNm());
								sbMenu.append("</a>");
								sbMenu.append("</li>");
								
								preGourp = "";
								firstMenuYn = false;
								
							}else if(menuInfo.getMenuType().equals("W")) {
								String width = "";
								String height = "";
								String url = "";
								if(menuInfo.getUrl().matches(".*,.*")){
						        	url = menuInfo.getUrl().substring(0, menuInfo.getUrl().indexOf(","));
							    }else{
									url = menuInfo.getUrl();	
								}
								
								if(menuInfo.getUrl().matches(".*W=.*")){
									width = menuInfo.getUrl().substring(menuInfo.getUrl().indexOf("W=")+2, menuInfo.getUrl().indexOf(",H="));
								}else{
									width = "1025";
						        }
								
								if(menuInfo.getUrl().matches(".*H=.*")){
									height = menuInfo.getUrl().substring(menuInfo.getUrl().indexOf("H=")+2);
								}else{
									height = "700";
								}
								
								sbMenu.append("<li");	
								if(menu_url != null && menu_url.equals(menuInfo.getUrl())){
									sbMenu.append(" class=\"active\" >");
								}else{
									sbMenu.append(">");
								}
								sbMenu.append("<a onclick=\"javascript:goPagePop('");							
						        sbMenu.append(url);
								sbMenu.append("','");
								sbMenu.append(width);
								sbMenu.append("','");
								sbMenu.append(height);
								sbMenu.append("');");
								sbMenu.append("\" style=\"cursor: pointer;\">");
								sbMenu.append(menuInfo.getMenuNm());
								sbMenu.append("</a>");
								sbMenu.append("</li>");
								
								preGourp = "";
								firstMenuYn = false;
								
							}else{
								sbMenu.append("<li");	
								if(menu_url != null && menu_url.equals(menuInfo.getUrl())){
									sbMenu.append(" class=\"active\" >");
								}else{
									sbMenu.append(">");
								}
								sbMenu.append("<a href=\"");
								sbMenu.append(menuInfo.getUrl());
								sbMenu.append("\">");
								sbMenu.append(menuInfo.getMenuNm());
								sbMenu.append("</a>");
								sbMenu.append("</li>");
								
								preGourp = "";
								firstMenuYn = false;
							}
							preLevel = menuInfo.getLvl();
							preMenuId = menuInfo.getMenuId();
							preMenuType = menuInfo.getMenuType();
							sb.append(menuInfo.getMenuId());
							sb.append(";");
							
							sbMenu.append("");
							sbMenu.append("");
						}
					}
				}
				if(preLevel.equals("")) preLevel = "0";
												
				if(Integer.parseInt(preLevel) >= Integer.parseInt(menuInfo.getLvl())){
					for(int z=0;z<Integer.parseInt(preLevel) - rootLevel-1;z++)
						sbMenu.append("</ul></li>");
				}
			}
			sbMenu.append("</ul></nav>");

			JspWriter out = pageContext.getOut();
			out.print(sbMenu.toString());
		} catch (Exception ex) {
			throw new JspException(ex);
		}

		return SKIP_BODY;
	}


	/**
	 * @return the root
	 */
	public String getRoot() {
		return StringUtil.nvl(root, "MENUID_0000");
	}


	/**
	 * @param root the root to set
	 */
	public void setRoot(String root) {
		this.root = StringUtil.nvl(root, "MENUID_0000");
	}

	/**
	 * 메뉴URL 설정
	 * @param menu_url 메뉴URL
	 */
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	/**
	 * 메뉴URL 취득
	 * @return menu_url 메뉴URL
	 */
	public String getMenu_url() {
		return StringUtil.nvl(this.menu_url);
	}
	
	/**
	 * 탑메뉴값 설정
	 * @param top_root 탑메뉴값
	 */
	public void setTop_root(String top_root) {
		this.top_root = top_root;
	}
	/**
	 * 탑메뉴값 취득
	 * @return top_root 탑메뉴값
	 */
	public String getTop_root() {
		return StringUtil.nvl(this.top_root);
	}
	
	private String getTopUpMenuId(String menuId, int idx) {
		StringBuffer name = new StringBuffer();
		/*String name="";*/
		try {
			Hashtable arMenu = MenuMng.getMenuMap();
			MenuInfo menuInfo = (MenuInfo)arMenu.get(menuId);
			String upMenuId = menuInfo.getUpMenuId();

			if (!"".equals(StringUtil.nvl(upMenuId)) && !"MENUID_0000".equals(StringUtil.nvl(upMenuId))) {
				int nextIdx = idx + 1;
				name.append(getTopUpMenuId(upMenuId, nextIdx));				
				name.append(upMenuId);
				name.append("&");
			}
		} catch (Exception ex) {

		}
		
		return name.toString();

	}
	
	
}

/*****************************************************************************
 * 프로그램명  : UploadFile.java
 * 설     명  : 업로드파일  DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23    LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.model;

import java.io.Serializable;
import java.util.List;

import com.eaction.framework.common.util.StringUtil;

/**
 * 업로드파일 데이터 빈
 * @author eaction
 * @version 1.0
 */
public class UploadFile implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 3223719491530243168L;
	
    /** 업로드 파일 이름 */
    private String fileName;
    /** 업로드 파일 크기 */
    private long fileSize;
	/** 파일 업로드 상대경로 (중복시 다른 이름으로 저장됨) */
	private String relative_path = "";
	/** 파일 업로드 전체경로 (중복시 다른 이름으로 저장됨) */
	private String full_path = "";
	/** 파일필드명 */
	private String fileField = "";


    /** 파일목록 */
	private List file_list = null;

	/**
	 * 파일필드명 설정
	 * @param fileField 파일필드명
	 */
	public void setFileField(String fileField) {
		this.fileField = fileField;
	}
	/**
	 * 파일필드명 취득
	 * @return fileField 파일필드명
	 */
	public String getFileField() {
		return StringUtil.nvl(this.fileField);
	}
	

    /**
     * 업로드 파일이름 취득 
     * @return String 업로드 파일이름 
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * 업로드 파일이름 설정
     * @param fileName 업로드 파일이름 
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 업로드 파일 크기취득 
     * @return long 업로드 파일 크기
     */
    public long getFileSize() {
        return fileSize;
    }
    /**
     * 업로드 파일 크기설정
     * @param fileSize 업로드 파일 크기
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

	/**
	 * 파일 업로드 상대경로 (중복시 다른 이름으로 저장됨) 설정
	 * @param relative_path 파일 업로드 전체경로 (중복시 다른 이름으로 저장됨)
	 */
	public void setRelative_path(String relative_path) {
		this.relative_path = relative_path;
	}
	/**
	 * 파일 업로드 상대경로 (중복시 다른 이름으로 저장됨) 취득
	 * @return relative_path 파일 업로드 전체경로 (중복시 다른 이름으로 저장됨)
	 */
	public String getRelative_path() {
		return StringUtil.nvl(this.relative_path);
	}
        
	/**
	 * 파일 업로드 전체경로 (중복시 다른 이름으로 저장됨) 설정
	 * @param full_path 파일 업로드 전체경로 (중복시 다른 이름으로 저장됨)
	 */
	public void setFull_path(String full_path) {
		this.full_path = full_path;
	}
	/**
	 * 파일 업로드 전체경로 (중복시 다른 이름으로 저장됨) 취득
	 * @return full_path 파일 업로드 전체경로 (중복시 다른 이름으로 저장됨)
	 */
	public String getFull_path() {
		return StringUtil.nvl(this.full_path);
	}


	/**
	 * 파일목록 설정
	 * @param file_list 파일목록
	 */
	public void setFile_list(List file_list) {
		this.file_list = file_list;
	}
	/**
	 * 파일목록 취득
	 * @return file_list 파일목록
	 */
	public List getFile_list() {
		return this.file_list;
	}

}

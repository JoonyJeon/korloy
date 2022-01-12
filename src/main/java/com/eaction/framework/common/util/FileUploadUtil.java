/*****************************************************************************
 * 프로그램명  : FileUploadUtil.java
 * 설     명  : 파일업로드 처리
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.ErrorInfo;
import com.eaction.framework.common.model.UploadFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



/**
 * 업로드 처리 구현 클래스
 * @author eaction
 * @version 1.0
 */
public class FileUploadUtil {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(FileUploadUtil.class);
    /** 클래스 명칭 */
    protected static final String CLASS_NAME = "FileUploadUtil";


    /**
     * 파일 업로드 처리
     * @param request
     * @param path 업로드 경로(environment.properties FILE_UPLOAD_ROOT 밑의 상대경로)
     * @return String 업로드 파일path
     */
    public static List<UploadFile> uploadFormFile(HttpServletRequest request, String path) throws UserSysException {
        int maxFileSize = ConfigMng.getValueInt(IPropertyKey.FILE_UPLOAD_MAX_SIZE);
    	return uploadFormFile(request, path, maxFileSize);
    }
    
    /**
     * 파일 업로드 처리
     * @param request 
     * @param path 업로드 경로(environment.properties FILE_UPLOAD_ROOT 밑의 상대경로)
     * @return String 업로드 파일path
     */
    public static List<UploadFile> uploadFormFile(HttpServletRequest request, String path, int maxFileSize) throws UserSysException {
    	String METHOD_NAME = "uploadFormFile";
    	
    	List<UploadFile> rtnList = new ArrayList<UploadFile>();
    	
    	HttpSession session = request.getSession();
    	MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fileNameIterator = mpRequest.getFileNames();
	    
        String root_path = ConfigMng.getValue(IPropertyKey.FILE_UPLOAD_ROOT);
        String destDir = session.getServletContext().getRealPath(root_path);
//        String destDir = ConfigMng.getValue(IPropertyKey.UPLOAD_ROOT_PATH);
        String uploadDirPath = "";
        String relativeDirPath = "";
        if(path.startsWith(System.getProperty("file.separator"))) {
        	uploadDirPath = destDir + path;
        	relativeDirPath = path;
        } else {
        	uploadDirPath = destDir + System.getProperty("file.separator") + path;
        	relativeDirPath = System.getProperty("file.separator") + path;
        }
        
        String fieldName = "";
        String originalFileName = "";
        String realFileName = "";
        
        if(!fileNameIterator.hasNext()) {
//    		String msg = "file not found";
//			logger.warn(msg);
//        	throw new UserSysException(new ErrorInfo(CLASS_NAME, METHOD_NAME, "EF1001", msg));
        	return rtnList;
        }
        
        while (fileNameIterator.hasNext()) {
	    	//파일정보를 하나씩 취득한다
	    	MultipartFile multiFile = mpRequest.getFile((String) fileNameIterator.next());
	    	if (multiFile.getSize() == 0) {
	    		String msg = "file size is 0.";
				logger.warn(msg);
	    		throw new UserSysException(new ErrorInfo(CLASS_NAME, METHOD_NAME, "EF1002", "file size is 0."));
	    	}
	    	
	    	if (maxFileSize > 0 && multiFile.getSize() > maxFileSize) {
	    		String msg = String.format("File upload maximum size error. Maximum upload size:%1$d, File size:%1$d", maxFileSize, multiFile.getSize());
				logger.warn(msg);
	    		throw new UserSysException(new ErrorInfo(CLASS_NAME, METHOD_NAME, "EF1003", msg));
	    	}
	    	
	    	if (multiFile.getSize() > 0) {
	    		fieldName = multiFile.getName();
	    		originalFileName = multiFile.getOriginalFilename();
	    		realFileName = FileUtil.getUniqueFileName(uploadDirPath, originalFileName);
	        	File file = new File(uploadDirPath  + System.getProperty("file.separator") + realFileName);
	            // 디렉토리가 존재 하지 않을 경우 생성
	            if(!file.exists()) {
	            	file.mkdirs();
	            }
	    		try{
	    			multiFile.transferTo(file);		
	    			
	    			UploadFile uploadFile = new UploadFile();
	    			//파일 업로드 항목 이름
	    			uploadFile.setFileField(fieldName);
	    			//파일 업로드 전체경로 (중복시 다른 이름으로 저장됨)
	    			uploadFile.setFull_path(uploadDirPath  + System.getProperty("file.separator"));
	    			//파일 업로드 상대경로 (중복시 다른 이름으로 저장됨)
	    			uploadFile.setRelative_path(relativeDirPath  + System.getProperty("file.separator"));
	    			//파일 업로드 파일 이름
	    	        uploadFile.setFileName(realFileName);
	    	        //파일 사이즈
	    			uploadFile.setFileSize(multiFile.getSize());
	    			rtnList.add(uploadFile);
	    			
	    		} catch (IllegalStateException e) {
	    			logger.error(e.getMessage());        	
	    			// 에러메세지리턴
		    		throw new UserSysException(CLASS_NAME, METHOD_NAME, e); 
	    		} catch (IOException e) {
	    			logger.error(e.getMessage());        	
	    			// 에러메세지리턴
		    		throw new UserSysException(CLASS_NAME, METHOD_NAME, e); 
	    		}
	    	}
	    	
	    }       
        return rtnList;
    }
    /**
     * 파일 업로드 처리
     * @param request
     * @param path 업로드 경로(environment.properties FILE_UPLOAD_ROOT 밑의 상대경로)
     * @return String 업로드 파일path
     */
/*    
    public static UploadFile uploadFormFile(HttpServletRequest request, String path, int maxFileSize) throws UserSysException {
    	String METHOD_NAME = "uploadFormFile";

    	HttpSession session = request.getSession();
    	MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fileNameIterator = mpRequest.getFileNames();

//        String root_path = ConfigMng.getValue(IPropertyKey.FILE_UPLOAD_ROOT);
//        String destDir = session.getServletContext().getRealPath(root_path);
//        String uploadDirPath = "";
//        String relativeDirPath = "";
//        if(path.startsWith(System.getProperty("file.separator"))) {
//        	uploadDirPath = destDir + path;
//        	relativeDirPath = path;
//        } else {
//        	uploadDirPath = destDir + System.getProperty("file.separator") + path;
//        	relativeDirPath = System.getProperty("file.separator") + path;
//        }

        String originalFileName = "";
        String realFileName = "";
        UploadFile uploadFile = new UploadFile();
        if(!fileNameIterator.hasNext()) {
    		String msg = "file not found";
			logger.warn(msg);
        	throw new UserSysException(new ErrorInfo(CLASS_NAME, METHOD_NAME, "EF1001", msg));
        }

        while (fileNameIterator.hasNext()) {
	    	//파일정보를 하나씩 취득한다
	    	MultipartFile multiFile = mpRequest.getFile((String) fileNameIterator.next());
	    	if (multiFile.getSize() == 0) {
	    		String msg = "file size is 0.";
				logger.warn(msg);
	    		throw new UserSysException(new ErrorInfo(CLASS_NAME, METHOD_NAME, "EF1002", "file size is 0."));
	    	}

	    	if (maxFileSize > 0 && multiFile.getSize() > maxFileSize) {
	    		String msg = String.format("File upload maximum size error. Maximum upload size:%1$d, File size:%1$d", maxFileSize, multiFile.getSize());
				logger.warn(msg);
	    		throw new UserSysException(new ErrorInfo(CLASS_NAME, METHOD_NAME, "EF1003", msg));
	    	}

	    	if (multiFile.getSize() > 0) {
	    		originalFileName = multiFile.getOriginalFilename();
	    		realFileName = FileUtil.getUniqueFileName(path, originalFileName);
	        	File file = new File(path  + System.getProperty("file.separator") + realFileName);
	            // 디렉토리가 존재 하지 않을 경우 생성
	            if(!file.exists()) {
	            	file.mkdirs();
	            }
	    		try{
	    			multiFile.transferTo(file);
	    			//파일 업로드 전체경로 (중복시 다른 이름으로 저장됨)
	    			uploadFile.setFull_path(path  + System.getProperty("file.separator") + realFileName);
	    			//파일 업로드 상대경로 (중복시 다른 이름으로 저장됨)
	    			//uploadFile.setRelative_path(relativeDirPath  + System.getProperty("file.separator") + realFileName);
	    			//파일 업로드 파일 이름
	    	        uploadFile.setFileName(realFileName);
	    	        //파일 사이즈
	    			uploadFile.setFileSize(multiFile.getSize());
	    		} catch (IllegalStateException e) {
	    			logger.error(e.getMessage());
	    			logger.error(StringUtil.stackTraceToString(e));
	    			// 에러메세지리턴
		    		throw new UserSysException(CLASS_NAME, METHOD_NAME, e);
	    		} catch (IOException e) {
	    			logger.error(e.getMessage());
	    			logger.error(StringUtil.stackTraceToString(e));
	    			// 에러메세지리턴
		    		throw new UserSysException(CLASS_NAME, METHOD_NAME, e);
	    		}
	    	}

	    }
        return uploadFile;
    }
*/
    
    /**
     * 파일 업로드 처리
     * @param request name당 하나의 파일로 전송
     * @param path 업로드 경로(environment.properties envPathKey 밑의 상대경로)
     * @param bParamDirMake 파일컨트롤명으로 디렉토리 생성 여부
     * @return Map<name, UploadFile>
     */
    public static Map<String, UploadFile> uploadFormFile(String destDir, HttpServletRequest request, String path, boolean bOriginNameYn) throws UserSysException {
    	String METHOD_NAME = "uploadFormFile";

    	MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
    	Iterator<String> fileNameIterator = mpRequest.getFileNames();
    	
        String uploadDirPath = "";
        String relativeDirPath = "";
        if(StringUtils.isEmpty(path)) {
        	uploadDirPath = destDir;
        	relativeDirPath = System.getProperty("file.separator");
        } else if(path.startsWith(System.getProperty("file.separator"))) {
        	uploadDirPath = destDir + path;
        	relativeDirPath = path;
        } else {
        	uploadDirPath = destDir + System.getProperty("file.separator") + path;
        	relativeDirPath = System.getProperty("file.separator") + path;
        }

    	String originalFileName = "";
    	String realFileName = "";
    	if(!fileNameIterator.hasNext()) {
    		String msg = "file not found";
    		logger.warn(msg);
//    		throw new UserSysException(new ErrorInfo(CLASS_NAME, METHOD_NAME, "EF1001", msg));
    		return new HashMap<>();
    	}

    	Map<String, UploadFile> uploadFiles = new HashMap<>();

    	while (fileNameIterator.hasNext()) {
    		UploadFile uploadFile = new UploadFile();

    		String name = (String) fileNameIterator.next();

    		//파일정보를 하나씩 취득한다
    		MultipartFile multiFile = mpRequest.getFile(name);
    		if (multiFile.getSize() == 0) {
    			continue;
    		}

    		if (multiFile.getSize() > 0) {
    			originalFileName = multiFile.getOriginalFilename();
    			if (bOriginNameYn) {    				
    			    realFileName = FileUtil.getUniqueFileName(uploadDirPath, originalFileName);
    			} else {
    				String firstName = DateTimeUtil.getFullDateTime() + StringUtil.getRandomAlphabet(4);
    				int idx = originalFileName.lastIndexOf(".");
    				
    				if (idx != -1) {
    					realFileName = firstName + originalFileName.substring(idx);
    				} else {
    					realFileName =  firstName;
    				}
    				   				
    			}
    			File file = null;
				//2020-07-23 정세연 : 파일 컨트롤 명으로 디렉토리 생성해 하위에 파일을 저장 한다.
    			file = new File(uploadDirPath  + System.getProperty("file.separator") + realFileName);
    			
    			// 디렉토리가 존재 하지 않을 경우 생성
    			if(!file.exists()) {
    				file.mkdirs();
    			}
    			try{
    				multiFile.transferTo(file);
    				// form name
    				//파일 업로드 전체경로 (중복시 다른 이름으로 저장됨)
    				uploadFile.setFull_path(uploadDirPath  + System.getProperty("file.separator") + realFileName);
    				//파일 업로드 상대경로 (중복시 다른 이름으로 저장됨)
    				uploadFile.setRelative_path(relativeDirPath  + System.getProperty("file.separator") + realFileName);
    				//파일 업로드 파일 이름
    				//uploadFile.setFileName(originalFileName);
    				uploadFile.setFileName(realFileName);
    				//파일 사이즈
    				uploadFile.setFileSize(multiFile.getSize());

    				uploadFiles.put(name, uploadFile);
    			} catch (IllegalStateException e) {
    				logger.error(e.getMessage());
    				logger.error(StringUtil.stackTraceToString(e));
    				// 에러메세지리턴
    				throw new UserSysException(CLASS_NAME, METHOD_NAME, e);
    			} catch (IOException e) {
    				logger.error(e.getMessage());
    				logger.error(StringUtil.stackTraceToString(e));
    				// 에러메세지리턴
    				throw new UserSysException(CLASS_NAME, METHOD_NAME, e);
    			}
    		}

    	}
    	return uploadFiles;
    }

    /**
     * 파일 업로드 처리
     * @param request
     * @param path 업로드 경로(environment.properties 에서 풀경로 지정했을시 빈값으로 넘기면 된다.)
     * @param newFileName 업로드 될 파일이름
     * @return String 업로드 파일path
     */
    public static UploadFile uploadFormFile(HttpServletRequest request, String path, String newFileName) throws UserSysException {
    	HttpSession session = request.getSession();
    	MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fileNameIterator = mpRequest.getFileNames();
        String fullFileName = "";

        String root_path = ConfigMng.getValue(IPropertyKey.FILE_UPLOAD_ROOT);
        String destDir = session.getServletContext().getRealPath(root_path);
        String uploadDirPath = "";
        String relativeDirPath = "";
        if(path.startsWith(System.getProperty("file.separator"))) {
        	uploadDirPath = destDir + path;
        	relativeDirPath = path;
        } else {
        	uploadDirPath = destDir + System.getProperty("file.separator") + path;
        	relativeDirPath = System.getProperty("file.separator") + path;
        }

        String originalFileName = "";
        String realFileName = "";
        UploadFile uploadFile = new UploadFile();
        while (fileNameIterator.hasNext()) {
	    	//파일정보를 하나씩 취득한다
	    	MultipartFile multiFile = mpRequest.getFile((String) fileNameIterator.next());
	    	if (multiFile.getSize() > 0) {
	    		originalFileName = multiFile.getOriginalFilename();
	    		//저장할 파일명 변경
	    		String ext = originalFileName.substring(originalFileName.lastIndexOf(".")+1,originalFileName.length()).toLowerCase();
	    		String fnh = newFileName;
	    		fnh = fnh.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "").replaceAll("\\p{Space}", "");
	    		realFileName = fnh+"."+ext;

	        	File file = new File(uploadDirPath  + System.getProperty("file.separator") + realFileName);
	            // 디렉토리가 존재 하지 않을 경우 생성
	            if(!file.exists()) {
	            	file.mkdirs();
	            }
	    		try{
	    			multiFile.transferTo(file);
	    			//파일 업로드 전체경로 (중복시 다른 이름으로 저장됨)
	    			uploadFile.setFull_path(uploadDirPath  + System.getProperty("file.separator") + realFileName);
	    			//파일 업로드 상대경로 (중복시 다른 이름으로 저장됨)
	    			uploadFile.setRelative_path(relativeDirPath  + System.getProperty("file.separator") + realFileName);
	    			//파일 업로드 파일 이름
	    	        uploadFile.setFileName(originalFileName);
	    	        //파일 사이즈
	    			uploadFile.setFileSize(multiFile.getSize());
	    		} catch (IllegalStateException e) {
	    			logger.error(StringUtil.stackTraceToString(e));
	    			// 에러메세지리턴
	    		} catch (IOException e) {
	    			logger.error(StringUtil.stackTraceToString(e));
	    			// 에러메세지리턴
	    		}
	    	}

	    }
        return uploadFile;
    }

    /**
     * 파일 업로드 처리 - 다중
     * @param request
     * @param path "/" +업로드 경로(environment.properties 에서 풀경로 지정했을시 빈값으로 넘기면 된다.)
     * @return UploadFile  request에서 file객체를 모두 가져와서 업로드 하면서 UploadFile bean에 정보를 넣어 보낸다.
     */
    public static UploadFile multiUploadFormFile(HttpServletRequest request, String path) throws UserSysException {
    	HttpSession session = request.getSession();
    	MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fileNameIterator = mpRequest.getFileNames();

        String root_path = ConfigMng.getValue(IPropertyKey.FILE_UPLOAD_ROOT);
        String destDir = session.getServletContext().getRealPath(root_path);
        String uploadDirPath = "";
        String relativeDirPath = "";
        if(path.startsWith(System.getProperty("file.separator"))) {
        	uploadDirPath = destDir + path;
        	relativeDirPath = path;
        } else {
        	uploadDirPath = destDir + System.getProperty("file.separator") + path;
        	relativeDirPath = System.getProperty("file.separator") + path;
        }

        String originalFileName = "";
        String realFileName = "";
        UploadFile file_info = new UploadFile();
        List fileList = new ArrayList();
        while (fileNameIterator.hasNext()) {
	    	//파일정보를 하나씩 취득한다
	    	MultipartFile multiFile = mpRequest.getFile((String) fileNameIterator.next());
	    	if (multiFile.getSize() > 0) {
	    		originalFileName = multiFile.getOriginalFilename();
	    		realFileName = FileUtil.getUniqueFileName(uploadDirPath, originalFileName);
	        	File file = new File(uploadDirPath  + System.getProperty("file.separator") + realFileName);
	            // 디렉토리가 존재 하지 않을 경우 생성
	            if(!file.exists()) {
	            	file.mkdirs();
	            }
	    		try{
	    			multiFile.transferTo(file);
	    			UploadFile uploadFile = new UploadFile();
	    			//파일 업로드 전체경로 (중복시 다른 이름으로 저장됨)
	    			uploadFile.setFull_path(uploadDirPath  + System.getProperty("file.separator") + realFileName);
	    			//파일 업로드 상대경로 (중복시 다른 이름으로 저장됨)
	    			uploadFile.setRelative_path(relativeDirPath  + System.getProperty("file.separator") + realFileName);
	    			//파일 업로드 파일 이름
	    	        uploadFile.setFileName(originalFileName);
	    	        //파일 사이즈
	    			uploadFile.setFileSize(multiFile.getSize());
	    	        fileList.add(uploadFile);
	    		} catch (IllegalStateException e) {
	    			logger.error(StringUtil.stackTraceToString(e));
	    			// 에러메세지리턴
	    		} catch (IOException e) {
	    			logger.error(StringUtil.stackTraceToString(e));
	    			// 에러메세지리턴
	    		}
	    	}
	    }
        file_info.setFile_list(fileList);
        return file_info;
    }

    /** 
     * 이미지 데이터를 파일로 생성해서 서버에 업로드 저장하는 처리
     * @param sub_root 업무별저장루트
     * @param fileName 저장파일이름
     * @param currentTime 현재날짜
     * @param imageValue 이미지데이터
     * @return String[] 처리결과 데이터 (0:저장URL경로, 1: 업로드파일위치)
     */
    public static String[] uploadImageDataToFile (String sub_root, String fileName, String currentTime, String imageValue) throws IOException{
    	String [] result = new String[2];
    	
    	
    	// URL시작 Root경로
    	String url = ConfigMng.getValue(IPropertyKey.UPLOAD_ROOT_PATH);
    	// 물리적 절대 경로
    	String destDir = ConfigMng.getValue(IPropertyKey.UPLOAD_ROOT_PATH);
    	    			
		String uploadPath = destDir + System.getProperty("file.separator") + sub_root + System.getProperty("file.separator") + currentTime.substring(0,6) + System.getProperty("file.separator") + fileName ;
		String uploadRelatePath = System.getProperty("file.separator") + sub_root + System.getProperty("file.separator") + currentTime.substring(0,6) + System.getProperty("file.separator") + fileName ;
		String uploadUrl = url + "/" + sub_root + "/" + currentTime.substring(0,6) + "/" ;
	
		//이미지 저장
		byte[] byteImg = Base64.getDecoder().decode(imageValue);
		ByteArrayInputStream str=new ByteArrayInputStream(byteImg);
		BufferedImage bm = ImageIO.read(str);
		File file = new File(uploadPath);
		file.getParentFile().mkdirs();
		//File newName = rename(file);
		
		ImageIO.write(bm, "png", file);
		// 파일 URL 경로 (파일명, 확장자포함)				
		result[0] = uploadUrl + file.getName();
		// 파일 저장 경로 (파일명, 확장자포함)
		result[1] = uploadRelatePath;
    	return result;
    }
    /**
     * 파일 이름이 디렉토리에 이미 존재하는지 체크한다
     * @param realPath 업로드디렉토리
     * @param fileName 업로드파일이름
     * @return String 파일이름
     */
//    private static String getUniqueFileName(String realPath, String fileName) {
//    	 //파일이 디렉토리에 존재하는지 확인
//    	fileName = StringUtil.replace(fileName, " ", "");
//        File tempFile =  new File(realPath, fileName);
//        int idx = 0;
//        int cnt = 1;
//        String temp = fileName;
//
//        //이미존재하는 파일인지 확인
//        while(tempFile.exists()) {
//            //기존에 존재하면 확장자랑 파일명을 분리해서 [숫자]를 붙여서 파일이름을 짓는다
//            if((idx=fileName.indexOf(".")) != -1){
//                String left = fileName.substring(0, idx);
//                String right = fileName.substring(idx);
//                temp = left + "[" + cnt + "]" + right;
//            }else{
//                temp = fileName + "[" + cnt + "]";
//            }
//
//            tempFile = new File(realPath, temp);
//            cnt++;
//        }
//        //실제 저장될 파일이름 저장
//        fileName = temp;
//
//        return fileName;
//    }
}

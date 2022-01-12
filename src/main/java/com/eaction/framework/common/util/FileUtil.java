/*****************************************************************************
 * 프로그램명  : FileUtil.java
 * 설     명  : 파일처리
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;	
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eaction.framework.common.exception.UserSysException;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * 파일 처리 구현 클래스
 * @author eaction
 * @version 1.0
 */
public class FileUtil {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(FileUtil.class);
    /** 클래스 명칭 */
    protected static final String CLASS_NAME = "FileUtil";
    
    /**
     * 기존에 등록되어있는 첨부파일을 삭제한다
     * @param rootPath 디렉토리명칭
     */
    public static void createDirectory (String rootPath) throws UserSysException{
    	File dFile = new File(rootPath);

    	if (!dFile.isDirectory()) {
    		if (!dFile.mkdir()){
    			throw new UserSysException(CLASS_NAME, "createDirectory");
            }
    	}
    }

    /**
     * 디렉토리를 삭제한다
     * @param fileName 삭제할 파일 이름
     */
    public static void deleteDirectory (String rootPath, String directory) {
    	try {
	        File file =  new File(rootPath, directory);

	        //대상파일이 존재하는 지 확인
	        if (file.exists()) {
	        	deleteDirectory(file);
	        }
    	} catch (Exception ex) {
    		logger.error("");
    	}
    }

    /**
     * 디렉토리의 하위데이터들을 모두 삭제한다
     * @param file 삭제할 디렉토리파일객체
     */
    public static void deleteDirectory(File file) {

        if (!file.exists()) {
    	    return;
        }
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
           if (files[i].isDirectory()) {
        	   deleteDirectory(files[i]);
    	   } else {
    	       files[i].delete();
    	   }
    	}
    	file.delete();
    }

    /**
     * 기존에 등록되어있는 첨부파일을 삭제한다
     * @param fileName 삭제할 파일 이름
     */
    public static void deleteFile (String realPath, String fileName) {
    	try {
	        File tempFile =  new File(realPath);

	        //대상파일이 존재하는 지 확인
	        if (tempFile.exists()) {
	        	tempFile.delete();
	        }
    	} catch (Exception ex) {
    		logger.error("파일 삭제 처리에서 에러가 발생했습니다.");
    	}
    }
    
    /**
     * 기존에 등록되어있는 첨부파일을 삭제한다
     * @param 
     */
    public static void deleteFile (String realPath) {
    	try {
	        File tempFile =  new File(realPath);

	        //대상파일이 존재하는 지 확인
	        if (tempFile.exists()) {
	        	tempFile.delete();
	        }
    	} catch (Exception ex) {
    		logger.error("파일 삭제 처리에서 에러가 발생했습니다.");
    	}
    }

    /**
     * Prefix로 시작하는 파일명의 리스트를 반환한다
     * @param dirPath 디렉토리패스
     * @param prefix 파일명 선두문자
     */
    public static List<String> getFileList (String dirPath, String prefix) {
		List<String> fileNameList = new ArrayList<String>();

		try {

	        File dir =  new File(dirPath);

	        if (!dir.exists()) {
        	    return null;
            }

	        File[] files = dir.listFiles();
	        for (int i = 0; i < files.length; i++) {
	           if (files[i].isDirectory() == false
	        	   && files[i].getName().startsWith(prefix)) {
	        	   fileNameList.add(files[i].getName());
	    	   }
	    	}

    	} catch (Exception ex) {
    		logger.error("파일 삭제 처리에서 에러가 발생했습니다.");
    	}

    	return fileNameList;
    }

    /**
     * Prefix로 시작하는 파일명의 리스트를 반환한다
     * @param dirPath 디렉토리패스
     * @param except 예외파일명
     * @param prefix 파일명 선두문자
     */
    public static List<String> getFileList (String dirPath, String prefix, String except) {
		List<String> fileNameList = new ArrayList<String>();

		try {
			fileNameList = getFileList(dirPath, prefix);
	        for (int i = 0; i < fileNameList.size(); i++) {
	           if (except.equals(fileNameList.get(i))) {
	        	   fileNameList.remove(i);
	        	   break;
	    	   }
	    	}

    	} catch (Exception ex) {
    		logger.error("파일 삭제 처리에서 에러가 발생했습니다.");
    	}

    	return fileNameList;
    }

    /**
     * 파일 이름이 디렉토리에 이미 존재하는지 체크한다
     * @param realPath 업로드디렉토리
     * @param fileName 업로드파일이름
     * @return String 파일이름
     */
    public static String getUniqueFileName(String realPath, String fileName) {
    	 //파일이 디렉토리에 존재하는지 확인
    	fileName = StringUtil.replace(fileName, " ", "");
        File tempFile =  new File(realPath, fileName);
        int idx = 0;
        int cnt = 1;
        String temp = fileName;

        //이미존재하는 파일인지 확인
        while(tempFile.exists()) {
            //기존에 존재하면 확장자랑 파일명을 분리해서 [숫자]를 붙여서 파일이름을 짓는다
            if((idx=fileName.indexOf(".")) != -1){
                String left = fileName.substring(0, idx);
                String right = fileName.substring(idx);
                temp = left + "[" + cnt + "]" + right;
            }else{
                temp = fileName + "[" + cnt + "]";
            }

            tempFile = new File(realPath, temp);
            cnt++;
        }
        //실제 저장될 파일이름 저장
        fileName = temp;

        return fileName;
    }

    //파일을 이동하는 메소드
    public static void fileMove(String inFileName, String outFileName) {
    	FileInputStream fis = null;
    	FileOutputStream fos = null;
    	try {
		      fis = new FileInputStream(inFileName);
		      fos = new FileOutputStream(outFileName);

		      int data = 0;
		      while((data=fis.read())!=-1) {
		       fos.write(data);
		      }
		      //복사한뒤 원본파일을 삭제함
		      File I = new File(inFileName);
		      I.delete();

     } catch (Exception e) {
      // TODO Auto-generated catch block
    	 logger.error(StringUtil.stackTraceToString(e));
     }finally {
         try {
			fis.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(StringUtil.stackTraceToString(e));
		}         
     }
    }

    /**
     * path 디렉토리에 위치한 확장자가 extention의 파일을 리턴한다.。
     * 확장자에 *을 지정하면 폴더를 포함하는 모든파일을 리턴한다.。
     *
     * @param path 검색한 위치(디렉토리)
     * @param extention 확장자
     * @return File 오브젝트의 리스트
     */
    public ArrayList listInDir(String path, String extention) {
        File dir = new File(path);
        if (!dir.isDirectory()) return null;
        File[] allList = dir.listFiles();
        ArrayList list = new ArrayList();
        String name = null;
        String ext = null;
        int extLength = extention.length();
        for (int i = 0; i < allList.length; i++) {
            if (allList[i].isDirectory()){
                list.addAll(listInDir(allList[i].getPath(), extention));
            }
            name = allList[i].getName();
            if (name.length()<=extLength) continue;
            ext = name.substring(name.length() - extLength, name.length());
            if (ext.equalsIgnoreCase(extention)||extention=="*")
                list.add(allList[i]);
        }
        return list;
    }
    
    /**
     * 지정한 위치에 파일을 생성 함
     * @param path 생성할 파일 이름
     * @param data 생성할 파일데이터
     * @throws IOException
     */
    public static void writeBinaryFile(String path, byte[] data) {
		FileOutputStream file = null;
		
		try{
			file = new FileOutputStream(path);
			file.write(data);
		} catch (Exception ex) {
    		logger.error("파일 생성 처리에서 에러가 발생했습니다." + ex.getMessage());
    	} finally{
    		try {
				if(file != null){
					file.close();
				}
    		}catch (Exception e) {
    			
    		}
		}
	}
    
    /**
     *  파일데이터의 Byte 정보를 취득한다
     * @param path 취득할파일패스
     * @return byte[] 데이터
     */
    public static byte[] readBinaryFile(String path) {
		FileInputStream file = null;
		byte[] data = null;
		try{
			file = new FileInputStream(path);
			data = new byte[(int)new File(path).length()];
			file.read(data);
			
			
		} catch (Exception ex) {
    		logger.error("파일 생성 처리에서 에러가 발생했습니다." + ex.getMessage());
		}finally{
			try {
				if(file != null){
					file.close();
				}
    		}catch (Exception e) {    			
    		}
		}
		
		return data;
	}
    
    /**
	 * 시리얼 넘버 생성을위한 Hex처리
	 * @param byteArray
	 * @return Striing 시리얼넘버
	 */
    public static String getHexString(byte[] byteArray){
        int i;
        StringBuilder result = new StringBuilder();
        
        for(i = 0; i < byteArray.length; i++){
            result.append(String.format("%02x", byteArray[i]));
        }
        
        return result.toString();
    }
	
    /**	
     * 압축풀기 메소드	
     * @param zipFileName 압축파일	
     * @param directory 압축 풀 폴더	
     */	
    public static void decompress(String zipFileName, String directory) throws Throwable {	
      File zipFile = new File(zipFileName);	
      FileInputStream fis = null;	
      ZipInputStream zis = null;	
      ZipEntry zipentry = null;	
      try {	
        //파일 스트림	
        fis = new FileInputStream(zipFile);	
        //Zip 파일 스트림	
        zis = new ZipInputStream(fis);	
        //entry가 없을때까지 뽑기	
        while ((zipentry = zis.getNextEntry()) != null) {	
          String filename = zipentry.getName();	
          File file = new File(directory, filename);	
          //entiry가 폴더면 폴더 생성	
          if (zipentry.isDirectory()) {	
            file.mkdirs();	
          } else {	
            //파일이면 파일 만들기	
            createFile(file, zis);	
          }	
        }	
      } catch (Throwable e) {	

      } finally {	
        if (zis != null)	
          zis.close();	
        if (fis != null)	
          fis.close();	
      }	
    }	
    /**	
     * 파일 만들기 메소드	
     * @param file 파일	
     * @param zis Zip스트림	
     */	
    private static void createFile(File file, ZipInputStream zis) throws Throwable {	
      //디렉토리 확인	
      File parentDir = new File(file.getParent());	
      //디렉토리가 없으면 생성하자	
      if (!parentDir.exists()) {	
        parentDir.mkdirs();	
      }
      if(file.exists()) {
	      //파일 스트림 선언	
	      try (FileOutputStream fos = new FileOutputStream(file)) {	
	        byte[] buffer = new byte[256];	
	        int size = 0;	
	        //Zip스트림으로부터 byte뽑아내기	
	        while ((size = zis.read(buffer)) > 0) {	
	          //byte로 파일 만들기	
	          fos.write(buffer, 0, size);	
	        }
	      } catch (Exception e) {
	
	      }
      }
    }
    
	/**
     * 파일을 zip으로 압축한다.
     * @param zipFileName 풀패스의 zip 파일이름(절대경로)
     * @param files 압축할 파일이름(절대경로)
     * @throws Exception
     */
	public static void makeZipFiles(String zipFileName, List<String> files) throws UserSysException{
				
		FileInputStream in = null;
		ZipOutputStream out = null;
		List zipFileList = new ArrayList();        
		byte[] buf = new byte[4096];
		try {
		    out = new ZipOutputStream(new FileOutputStream(zipFileName));
		    for (int i=0; i<files.size(); i++) {
		    	// 파일 존재 확인
		    	File dFile = new File(files.get(i));
		    	if(dFile.exists()) {
		    		in = new FileInputStream(files.get(i));
			        Path p = Paths.get(files.get(i));
			        String fileName = p.getFileName().toString();
		    		if(!zipFileList.contains(fileName)) {
				        zipFileList.add(fileName);
				        ZipEntry ze = new ZipEntry(fileName);
				        out.putNextEntry(ze);
		    		}else {
		    			fileName = "RE" +"_"+ fileName;
		    			zipFileList.add(fileName);
				        ZipEntry ze = new ZipEntry(fileName);
				        out.putNextEntry(ze);
		    		}
			        int len;
			        while ((len = in.read(buf)) > 0) {
			            out.write(buf, 0, len);
			        }			          
			        out.closeEntry();
		    	}		        
		    }
		    
		} catch (IOException e) {
			throw new UserSysException(CLASS_NAME, "getZipFile", e);
		} finally {
        	try {
        		
				if(in!=null)	in.close();
				if(out!=null)	out.close();
			} catch (IOException e) {
				throw new UserSysException(CLASS_NAME, "getZipFile", e);
			}
             
		}
		
	}
	/**
	 * 파일을 zip으로 압축한다.
	 * @param zipFileName 풀패스의 zip 파일이름(절대경로)
	 * @param files 압축할 파일이름(절대경로)
	 * @throws Exception
	 */
	public static String makeZipFileAndReturnPath(String zipFileName, List<String> files) throws UserSysException{
		
		FileInputStream in = null;
		ZipOutputStream out = null;
		List zipFileList = new ArrayList();        
		byte[] buf = new byte[4096];
		try {
			out = new ZipOutputStream(new FileOutputStream(zipFileName));
			for (int i=0; i<files.size(); i++) {
				String addFile = files.get(i).replace("/", File.separator);

				// 파일 존재 확인
				File dFile = new File(addFile);
				if(dFile.exists()) {
					in = new FileInputStream(addFile);
					Path p = Paths.get(addFile);
					String fileName = p.getFileName().toString();
					if(!zipFileList.contains(fileName)) {
						zipFileList.add(fileName);
						ZipEntry ze = new ZipEntry(fileName);
						out.putNextEntry(ze);
					}else {
						fileName = "RE" +"_"+ fileName;
						zipFileList.add(fileName);
						ZipEntry ze = new ZipEntry(fileName);
						out.putNextEntry(ze);
					}
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}			          
					out.closeEntry();
				}		        
			}
			
		} catch (IOException e) {
			logger.debug(e.toString());
			throw new UserSysException(CLASS_NAME, "getZipFile", e);
		} finally {
			try {
				
				if(in!=null)	in.close();
				if(out!=null)	out.close();
			} catch (IOException e) {
				throw new UserSysException(CLASS_NAME, "getZipFile", e);
			}
			
		}
		return zipFileName;
	}
	
    public static void unzip(String source, String destination, char[] password){
        try {
             ZipFile zipFile = new ZipFile(source);
             if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
             }
             zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
    
    public static String readFileToString(String source){
    	String content = "";
    	try {
        	Path path = Paths.get(source);
			Stream<String> lines = Files.lines(path);
			content = lines.collect(Collectors.joining(System.lineSeparator()));
			lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	return content;
    }
}


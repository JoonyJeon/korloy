/*****************************************************************************
 * 프로그램명  : MemoryProviderServer.java
 * 설     명  : 메모리데이터 로딩 Command 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.09  LYS      1.0
 *****************************************************************************/
package com.eaction.framework.business.system.memory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eaction.framework.business.common.code.OtherCodeTableMng;
import com.eaction.framework.common.util.StringUtil;

public class MemoryProviderServer {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(OtherCodeTableMng.class);    
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	private static HashMap serverData;

	//임시서버 포트 정보
	private static int hostPort = 2024;
	
	MemoryProviderServer() {
	}

	void run() {
		try {
			// 1. creating a server socket
			providerSocket = new ServerSocket(hostPort, 10);
			// 2. Wait for connection
			logger.debug("Waiting for connection");
			connection = providerSocket.accept();
			logger.debug("Connection received from " + connection.getInetAddress().getHostName());
			// 3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			
			sendData();
			
			// 4. The two parts communicate via the input and output streams
			try {
				HashMap clientData = (HashMap) in.readObject();
				
				// 서버 데이터 없으면 Client에 요청후 정보 저장
				if (serverData == null || MemoryConstant.MEM_STATE_RELOAD.equals(clientData.get(MemoryConstant.MEM_STATE_KEY))){
					logger.debug("server> Client 상태정보 : "+ clientData.get(MemoryConstant.MEM_STATE_KEY));
					logger.debug("server> 메모리 데이터 저장....");
					serverData = clientData;
				// 서버데이터가 없으면 데이터 전송
				}else if(MemoryConstant.MEM_STATE_END.equals(clientData.get(MemoryConstant.MEM_STATE_KEY))){
					logger.debug("server> Client 상태정보 : "+ clientData.get(MemoryConstant.MEM_STATE_KEY));
				}else{
					logger.debug("server> 메모리 데이터 전송....");
					sendData();
				}
			} catch (ClassNotFoundException classnot) {
				logger.error("Data received in unknown format");
			}
		} catch (IOException ioException) {
			logger.error(StringUtil.stackTraceToString(ioException));
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				providerSocket.close();
			} catch (IOException ioException) {
				logger.error(ioException.getMessage());
			}
		}
	}

	void sendData() {
		try {
			out.writeObject(serverData);
			out.flush();
			
			//서버데이터 없을때
			if(serverData == null){
				logger.debug("server> 서버 저장 데이터 없음...");
			}
			else{
				logger.debug("server> 서버 저장 데이터 전송!!");
			}
		} catch (IOException ioException) {
			logger.error(StringUtil.stackTraceToString(ioException));
		}
	}

	public static void main(String args[]) {
		MemoryProviderServer server = new MemoryProviderServer();
		while (true) {
			server.run();
		}
	}
}
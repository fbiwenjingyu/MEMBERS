package com.daboo.core.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;

/**
 *      
  * @ClassName: FileUtil
  * @Description: 文件操作公共方法
  * @author 马正正
  * @date 2015年6月24日
 */
public class FileUtil {
	private static Log logger=LogFactory.getLog(FileUtil.class);
	/**
	 * OSSClient对象
	 */
	private static OSSClient client;
	static{
		init();
	}
	
	/**
	 * 
	  * @Description: 初始化
	  * @param ms		配置信息
	  * @author 马正正
	  * @date 2015年7月8日
	 */
	private static void init(){
		ClientConfiguration conf = new ClientConfiguration();
		conf.setMaxConnections(500);		//设置HTTP最大连接数为500
		conf.setConnectionTimeout(20000);	//设置TCP连接超时为20秒
		conf.setMaxErrorRetry(5);			//设置最大的重试次数为5
		conf.setSocketTimeout(100000);		//设置Socket传输数据超时的时间为100秒

		String endpoint = ConfigProperty.getProperty("oss.endpoint") ; 
		String accessKeyId =ConfigProperty.getProperty("oss.accessKeyId") ;
		String accessKeySecret = ConfigProperty.getProperty("oss.accessKeySecret");
		client=new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
	}
	
	/**
	 * 
	  * @Description: 文件上传
	  * @param input		输入流
	  * @param fileName		文件名(可以是url形式a/b/c.txt,开头不能是/)
	  * @param lenth		文件大小(字节数)
	  * @return				文件url
	  * @author 马正正
	  * @date 2015年6月24日
	 */
	public static String uploadFile(InputStream input, String fileName, long lenth) {
		logger.info("文件上传到阿里云");
		
		String bucketName=  ConfigProperty.getProperty("oss.bucketName") ;
		String bucketUrl= ConfigProperty.getProperty("oss."+bucketName) ;
		String result = bucketUrl+fileName;					//返回OSS文件地址
		
		ObjectMetadata objectMeta = new ObjectMetadata();	//OSS上传
		objectMeta.setContentLength(lenth);
		
		long startTime = System.currentTimeMillis();
		
		PutObjectResult put=client.putObject(bucketName, fileName, input, objectMeta);
		
		logger.info("返回的结果"+put.getETag()+"---文件上传到oss的时间 :"+(System.currentTimeMillis()- startTime));
		
		return result;
	}
	
	/**
	 * 
	  * @Description: 文件上传
	  * @param file			文件
	  * @param fileName		文件名(可以是url形式a/b/c.txt,开头不能是/)
	  * @return
	  * @author 马正正
	  * @date 2015年6月24日
	 */
	public static String uploadFile(byte[] content, String fileName){
		return uploadFile(new ByteArrayInputStream(content), fileName, content.length);
	}
	
	public static String uploadFile(File file,String fileName){
		String path=null;
		InputStream is=null;
		try{
			is=new FileInputStream(file);
			path=uploadFile(is, fileName, file.length());
		} catch (FileNotFoundException e) {
			logger.error("",e);
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					logger.error("",e);
				}
			}
		}
		return path;
	}
	
	/**
	 * 
	  * @Description: 文件删除
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param fileUrl	文件路径
	  * @author 马正正
	  * @date 2015年6月24日
	 */
	public void deleteFileOSS(String fileUrl){
		 String bucketName= ConfigProperty.getProperty("oss.bucketName");
		 client.deleteObject(bucketName, fileUrl);
	}
}

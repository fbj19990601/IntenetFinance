package com.lechou.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lechou.user.UserMgr;
import com.lechou.user.UserVO;

// Servlet 文件上传  
public class HeadIconUploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filePath; // 文件存放目录
	private String tempPath; // 临时文件目录

	// 初始化
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 从配置文件中获得初始化参数
		filePath = config.getInitParameter("filepath");
		tempPath = config.getInitParameter("temppath");

		ServletContext context = getServletContext();

		filePath = context.getRealPath(filePath);
		tempPath = context.getRealPath(tempPath);
		System.out.println("文件存放目录、临时文件目录准备完毕 ...");
	}

	// doPost
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		res.setContentType("text/plain;charset=gbk");
	
		PrintWriter pw = res.getWriter();
		try {
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
			// threshold 极限、临界值，即硬盘缓存 1M
			diskFactory.setSizeThreshold(4 * 1024);
			// repository 贮藏室，即临时文件目录
			diskFactory.setRepository(new File(tempPath));

			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			// 设置允许上传的最大文件大小 4M
			upload.setSizeMax(100 * 1024 * 1024);
			// 解析HTTP请求消息头
			List fileItems = upload.parseRequest(req);
			Iterator iter = fileItems.iterator();
			

			int pk_user = -1;
			String requestURL = "";
		
			
			UserVO uservo = new UserVO();
			int status = 0;

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (item.isFormField()) {
					if (item.getFieldName().equals("pk_user")) {
						pk_user = Integer.parseInt(item.getString());
					}
					if (item.getFieldName().equals("requestURL")) {
						requestURL = item.getString();
					}
				}

				else {
					System.out.println("处理上传的文件 ...");
					 String headicon = null;
                     if(item.getFieldName().equals("headicon")){
                    		String filename = item.getName();

                    		System.out.println("完整的文件名：" + filename);
                    		int index = filename.lastIndexOf(".");
                    		String filetype = filename.substring(index + 1, filename.length());
                    		long fileSize = item.getSize();
                    		if ("".equals(filename) && fileSize == 0 ) {
                    			System.out.println("文件名为空 ...");
                    			res.getWriter().print(
             							"<script>alert('请选择文件!');window.location.href='"
             									+ requestURL + "'</script>");
                    			
                    		}else if ((!filetype.equals("jpg")) && (!filetype.equals("jpeg"))
                    				&& (!filetype.equals("png"))) {
                    			System.out.println("上传格式有误 ...");
                    			res.getWriter().print(
             							"<script>alert('文件格式不正确!');window.location.href='"
             									+ requestURL + "'</script>");
                    		}else{

                    		File uploadFile = new File(filePath + "/"
                    				+ PasswordEncryptor.GetMD5Code("" + pk_user) + "." + filetype);
                    		item.write(uploadFile);
                    		
                    		if(filetype != null && !filetype.trim().equals("")){
                    			headicon = PasswordEncryptor.GetMD5Code("" + pk_user) + "." + filetype;
                    		}
                    		else headicon = null;
                    		System.out.println(filename + " 文件保存完毕 ...");
                    		System.out.println("保存的完整路径：" + filePath + "/"
                    				+ PasswordEncryptor.GetMD5Code("" + pk_user) + "." + filetype);
                    		System.out.println("文件大小为 ：" + fileSize + "字节\r\n");
                    		
                    		res.sendRedirect(requestURL);

             				if (pk_user != -1) {
             					uservo = UserMgr.getInstance().getUserVOById(pk_user);
             					uservo.setIconurl(headicon);
             					UserMgr.getInstance().updateUser(uservo);
             				}
                    		}
                      }
					


				}
			}// end while()
			pw.close();
		} catch (Exception e) {
			System.out.println("使用 fileupload 包时发生异常 ...");
			e.printStackTrace();
		}// end try ... catch ...
	}// end doPost()

	

	// doGet
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}
}
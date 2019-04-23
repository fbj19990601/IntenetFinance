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

// Servlet �ļ��ϴ�  
public class HeadIconUploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filePath; // �ļ����Ŀ¼
	private String tempPath; // ��ʱ�ļ�Ŀ¼

	// ��ʼ��
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// �������ļ��л�ó�ʼ������
		filePath = config.getInitParameter("filepath");
		tempPath = config.getInitParameter("temppath");

		ServletContext context = getServletContext();

		filePath = context.getRealPath(filePath);
		tempPath = context.getRealPath(tempPath);
		System.out.println("�ļ����Ŀ¼����ʱ�ļ�Ŀ¼׼����� ...");
	}

	// doPost
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		res.setContentType("text/plain;charset=gbk");
	
		PrintWriter pw = res.getWriter();
		try {
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
			// threshold ���ޡ��ٽ�ֵ����Ӳ�̻��� 1M
			diskFactory.setSizeThreshold(4 * 1024);
			// repository �����ң�����ʱ�ļ�Ŀ¼
			diskFactory.setRepository(new File(tempPath));

			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			// ���������ϴ�������ļ���С 4M
			upload.setSizeMax(100 * 1024 * 1024);
			// ����HTTP������Ϣͷ
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
					System.out.println("�����ϴ����ļ� ...");
					 String headicon = null;
                     if(item.getFieldName().equals("headicon")){
                    		String filename = item.getName();

                    		System.out.println("�������ļ�����" + filename);
                    		int index = filename.lastIndexOf(".");
                    		String filetype = filename.substring(index + 1, filename.length());
                    		long fileSize = item.getSize();
                    		if ("".equals(filename) && fileSize == 0 ) {
                    			System.out.println("�ļ���Ϊ�� ...");
                    			res.getWriter().print(
             							"<script>alert('��ѡ���ļ�!');window.location.href='"
             									+ requestURL + "'</script>");
                    			
                    		}else if ((!filetype.equals("jpg")) && (!filetype.equals("jpeg"))
                    				&& (!filetype.equals("png"))) {
                    			System.out.println("�ϴ���ʽ���� ...");
                    			res.getWriter().print(
             							"<script>alert('�ļ���ʽ����ȷ!');window.location.href='"
             									+ requestURL + "'</script>");
                    		}else{

                    		File uploadFile = new File(filePath + "/"
                    				+ PasswordEncryptor.GetMD5Code("" + pk_user) + "." + filetype);
                    		item.write(uploadFile);
                    		
                    		if(filetype != null && !filetype.trim().equals("")){
                    			headicon = PasswordEncryptor.GetMD5Code("" + pk_user) + "." + filetype;
                    		}
                    		else headicon = null;
                    		System.out.println(filename + " �ļ�������� ...");
                    		System.out.println("���������·����" + filePath + "/"
                    				+ PasswordEncryptor.GetMD5Code("" + pk_user) + "." + filetype);
                    		System.out.println("�ļ���СΪ ��" + fileSize + "�ֽ�\r\n");
                    		
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
			System.out.println("ʹ�� fileupload ��ʱ�����쳣 ...");
			e.printStackTrace();
		}// end try ... catch ...
	}// end doPost()

	

	// doGet
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}
}
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

import com.lechou.project.ProjectMgr;
import com.lechou.project.ProjectVO;
import com.lechou.user.UserMgr;

// Servlet 文件上传  
public class InitialProjectServlet extends HttpServlet {
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
		req.setCharacterEncoding("UTF-8");
		
	
		PrintWriter pw = res.getWriter();
		try {
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
			// threshold 极限、临界值，即硬盘缓存 1M
			diskFactory.setSizeThreshold(100 * 1024);
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
			
			String action = "";

			//处理
			ProjectVO projectvo = new ProjectVO();
			
			String title = null;
			String fundamountstr = null;
			String funddatestr = null;
			String investreward = null;
			String selectableprice1str = null;
			String selectableprice2str = null;
			String selectableprice3str = null;
			String catagoryidstr = null;
			String projectaddress = null;
			String province = null;
			String city = null;
			String titleimageurl = "";
			String movieurl = "";
			String description = null;
			String detaildescription = null;
			String descriptionimages = "";


			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (item.isFormField()) {
					if (item.getFieldName().equals("pk_user")) {
						pk_user = Integer.parseInt(item.getString());
					}
					if (item.getFieldName().equals("requesturl")) {
						requestURL = item.getString();
					}
					
					if (item.getFieldName().equals("action")) {
						action = item.getString();
					}
					if (item.getFieldName().equals("title")) {
						title= new String(item.getString().getBytes("iso-8859-1"),"UTF-8");
//						
					}
					if (item.getFieldName().equals("fundamount")) {
						fundamountstr = item.getString();
					}
					if (item.getFieldName().equals("funddate")) {
						funddatestr = item.getString();
					}
					if (item.getFieldName().equals("investreward")) {
						investreward = new String(item.getString().getBytes("iso-8859-1"),"UTF-8");
					}
					if (item.getFieldName().equals("selectableprice1")) {
						selectableprice1str = item.getString();
					}
					if (item.getFieldName().equals("selectableprice2")) {
						selectableprice2str = item.getString();
					}
					if (item.getFieldName().equals("selectableprice3")) {
						selectableprice3str = item.getString();
					}
					if (item.getFieldName().equals("catagoryid")) {
						catagoryidstr = item.getString();
					}
					if (item.getFieldName().equals("city")) {
						city = new String(item.getString().getBytes("iso-8859-1"),"UTF-8");
					}
					if (item.getFieldName().equals("province")) {
						province = new String(item.getString().getBytes("iso-8859-1"),"UTF-8");
					}
					if (item.getFieldName().equals("description")) {
						description = new String(item.getString().getBytes("iso-8859-1"),"UTF-8");
					}
					if (item.getFieldName().equals("detaildescription")) {
						detaildescription = new String(item.getString().getBytes("iso-8859-1"),"UTF-8");
					}
				}

				else {
					System.out.println("处理上传的文件 ...");
					String afersavename = "";
					if(item.getFieldName().equals("titleimageurl"))
					{
						titleimageurl = processUploadFile(item, pw);
					}
					if(item.getFieldName().equals("movieurl"))
					{
						movieurl = processUploadFile(item, pw);
					}
					if(item.getFieldName().equals("descriptionimage1")){
						afersavename = processUploadFile(item, pw);
						if(afersavename != null){
						descriptionimages += afersavename+"&";
						}
					}
					if(item.getFieldName().equals("descriptionimage2")){
						afersavename = processUploadFile(item, pw);
						if(afersavename != null){
						descriptionimages += afersavename+"&";
						}
					} 
					if(item.getFieldName().equals("descriptionimage3")){
						afersavename = processUploadFile(item, pw);
						if(afersavename != null){
						descriptionimages += afersavename+"&";
						}
					}
					if(item.getFieldName().equals("descriptionimage4")){
						afersavename = processUploadFile(item, pw);
						if(afersavename != null){
						descriptionimages += afersavename+"&";
						}
					}
				
                    
				}
			}// end while()
			
			
			    if(fundamountstr != null && !fundamountstr.trim().equals("")){
			    	projectvo.setFundamount(Integer.parseInt(fundamountstr));
			    }
			    if(funddatestr != null && !funddatestr.trim().equals("")){
			    	projectvo.setFunddate(Integer.parseInt(funddatestr));
			    }
			    if(selectableprice1str != null && !selectableprice1str.trim().equals("")){
			    	projectvo.setSelectableprice1(Integer.parseInt(selectableprice1str));
			    }
			    if(selectableprice2str != null && !selectableprice2str.trim().equals("")){
			    	projectvo.setSelectableprice2(Integer.parseInt(selectableprice2str));
			    }
			    if(selectableprice3str != null && !selectableprice3str.trim().equals("")){
			    	projectvo.setSelectableprice3(Integer.parseInt(selectableprice3str));
			    }
			    if(catagoryidstr != null && !catagoryidstr.trim().equals("all")){
      		    	projectvo.setCatagoryid(Integer.parseInt(catagoryidstr));
			    }
				projectvo.setTitle(title);
				projectvo.setInvestreward(investreward);
				projectvo.setInitiatorid(pk_user);
			/*	 if(province != null && !province.trim().equals("0")){
	      		    	projectaddress =Integer.parseInt(province) +"&";
				    }
				if(city != null && !city.trim().equals("0")){
					projectaddress +=Integer.parseInt(city);
      		    	projectvo.setProjectaddress(projectaddress);
			    }*/
				if(province.equals(city)){
					projectaddress = city;
				}else
				{
					projectaddress = province+city;
				}
				
  		    	projectvo.setProjectaddress(projectaddress);
				projectvo.setDescription(description);
				projectvo.setDetaildescription(detaildescription);
				projectvo.setModifier(UserMgr.getInstance().getUserVOById(pk_user).getUsername());
				projectvo.setTitleimageurl(titleimageurl);
				projectvo.setMovieurl(movieurl);
				projectvo.setDescriptionimages(descriptionimages);
		
				if (action != null && action.trim().equals("initailnow") ) {
					if (ProjectMgr.getInstance().initialproject(
							projectvo) == 0) {
						res.sendRedirect(""+requestURL+"?result=success");
						
						
					}
				}
			
			

			pw.close();
		} catch (Exception e) {
			System.out.println("使用 fileupload 包时发生异常 ...");
			e.printStackTrace();
		}// end try ... catch ...
	}// end doPost()

	// 处理上传的文件
	private String  processUploadFile(FileItem item, PrintWriter pw) throws Exception {
		// 此时的文件名包含了完整的路径，得注意加工一下
		String filename = item.getName();

		System.out.println("完整的文件名：" + filename);
		int index = filename.lastIndexOf(".");
		String filetype = filename.substring(index + 1, filename.length());
		long fileSize = item.getSize();
		if (/*(!filetype.equals("jpg")) && (!filetype.equals("jpeg"))
				&& (!filetype.equals("png")) &&*/ (!filetype.equals("flv"))) {
			
			System.out.println("上传格式有误 ...");
		}

		if ("".equals(filename) && fileSize == 0) {

			System.out.println("文件名为空 ...");
			
		}
		String afersavename ;
		if(filetype != null && !filetype.trim().equals("")){
			afersavename = PasswordEncryptor.GetMD5Code("" + filename) + "." + filetype;
			File uploadFile = new File(filePath + "/"
					+ PasswordEncryptor.GetMD5Code("" + filename) + "." + filetype);
			item.write(uploadFile);
		}
		else afersavename = null;
		System.out.println(filename + " 文件保存完毕 ...");
		System.out.println("保存的完整路径：" +afersavename);
		System.out.println("文件大小为 ：" + fileSize + "字节\r\n");
		return afersavename;
	}

	// doGet
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}
}
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.JavaClassExecuter;
import utils.ByteUtils;

public class ExecuteByteCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);

		List<FileItem> list;
		try {
			list = fileUpload.parseRequest(request);

			// 获取登录用户的用户名

			for (FileItem item : list) {
				System.out.println(item.toString());
				if (item.isFormField()) {
					String filedname = item.getFieldName();
					String value = item.getString("utf-8");
					System.out.println(filedname + ":" + value);
				} else {
					InputStream is = item.getInputStream();
					byte[] b = ByteUtils.Inputstream2Bytes(is);
					String result = JavaClassExecuter.execute(b);
					request.setAttribute("result", result);
					request.getRequestDispatcher("/index.jsp").forward(request,
							response);
				}
				item.delete();
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

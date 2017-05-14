package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.JavaClassExecuter;
import utils.ByteUtils;
import utils.CompilerUtils;

public class ExecuteSourceCode extends HttpServlet {

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

			// ��ȡ��¼�û����û���

			for (FileItem item : list) {
				System.out.println(item.toString());
				if (item.isFormField()) {
					String filedname = item.getFieldName();
					String value = item.getString("utf-8");
					System.out.println(filedname + ":" + value);
				} else {
					InputStream is = item.getInputStream();

					byte[] b = ByteUtils.Inputstream2Bytes(is);

					String path = CompilerUtils.CompileSource(b);
					if (null != path) {
						is = new FileInputStream(path);
						b = ByteUtils.Inputstream2Bytes(is);
						String result = JavaClassExecuter.execute(b);
						request.setAttribute("result", result);
						request.getRequestDispatcher("/index.jsp").forward(
								request, response);
						File file = new File(path);
						file.delete();
					}
				}
				item.delete();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

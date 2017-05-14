package utils;

import java.net.URISyntaxException;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class CompilerUtils {
	public static String CompileSource(byte[] b) throws URISyntaxException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);

		StringSourceJavaObject sourceObject = new StringSourceJavaObject(
				"Main", new String(b));

		Iterable<? extends JavaFileObject> fileObjects = Arrays
				.asList(sourceObject);

		// 获取工程存放临时class文件目录

		String strRealPath = System.getProperty("user.dir");
		strRealPath = strRealPath.substring(0, strRealPath.indexOf("\\") + 1)
				.trim();

		Iterable options = Arrays.asList("-d", strRealPath);

		CompilationTask task = compiler.getTask(null, fileManager, null,
				options, null, fileObjects);
		boolean success = task.call();
		if (success) {
			return strRealPath + "Main.class";
		} else {
			return null;
		}
	}
}

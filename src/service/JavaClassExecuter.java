package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import utils.ClassModifier;
import utils.HackSystem;
import utils.HotSwapClassLoader;

public class JavaClassExecuter {
	public static void main(String[] args) {
		byte[] b;
		try {
			InputStream is = new FileInputStream("c:/TestClass.class");
			b = new byte[is.available()];
			is.read(b);
			is.close();
			execute(b);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String execute(byte[] classByte) {
		HackSystem.clearBuffer();
		ClassModifier cm = new ClassModifier(classByte);
		byte[] modibytes = cm.modifyUTF8Constant("java/lang/System",
				"utils/HackSystem");

		HotSwapClassLoader loader = new HotSwapClassLoader();

		Class clazz = loader.loadByte(modibytes);
		try {
			Method method = clazz.getMethod("main",
					new Class[] { String[].class });
			method.invoke(null, new String[] { null });
		} catch (Throwable e) {
			e.printStackTrace(HackSystem.out);
		}
		return HackSystem.getBufferString();
	}
}

package utils;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import javax.tools.SimpleJavaFileObject;

/**
 * 这个类用于简化得到java字节码的内容可以让我们从任意地方得到需要编译的字节码内容 ：
 * 
 * @author: Hunter Luo
 * @date： 日期：2016年11月7日 时间：下午8:19:42
 * @version 1.0
 */
public class StringSourceJavaObject extends SimpleJavaFileObject {
	private String content = null;

	public StringSourceJavaObject(String name, String content)
			throws URISyntaxException {
		super(URI.create("string:///" + name.replace('.', '/')
				+ Kind.SOURCE.extension), Kind.SOURCE);
		this.content = content;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors)
			throws IOException {
		// TODO Auto-generated method stub
		return content;
	}
}
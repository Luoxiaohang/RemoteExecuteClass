package utils;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import javax.tools.SimpleJavaFileObject;

/**
 * ��������ڼ򻯵õ�java�ֽ�������ݿ��������Ǵ�����ط��õ���Ҫ������ֽ������� ��
 * 
 * @author: Hunter Luo
 * @date�� ���ڣ�2016��11��7�� ʱ�䣺����8:19:42
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
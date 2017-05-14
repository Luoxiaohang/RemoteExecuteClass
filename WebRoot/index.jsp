<%@ page pageEncoding="UTF-8"%>
<html>

<span>选择字节码文件：</span>
<br>
<br>
<form
	action="${pageContext.request.contextPath}/servlet/executeByteCode"
	method="post" enctype="multipart/form-data">
	<input type="file" name="file"> <input type="submit" value="执行">
</form>
<span>选择源文件：</span>
<br>
<br>
<form
	action="${pageContext.request.contextPath}/servlet/executeSourceCode"
	method="post" enctype="multipart/form-data">
	<input type="file" name="file"> <input type="submit" value="执行">
</form>
<span>执行结果：</span>
</br>
<textarea style="width:1000;height:400">${result}</textarea>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원가입</h2>
<form:form 
         modelAttribute="Member"
         method="POST"
         action="/normal/join">
     id : <form:input path="member_id" /> <br />
         <form:errors path="member_id" /> <br />
         이름 : <form:input path="member_name" /> <br />
         <form:errors path="member_name" /> <br />
         이메일 : <form:input path="member_email" /> <br />
         <form:errors path="member_email" /> <br />
         비밀번호 : <form:password path="member_pwd" /> <br />
         <form:errors path="member_pwd" /> <br />
         권한 : <form:input path="member_level" /> <br />
         <form:errors path="member_level" /> <br />
         주소 : <form:input path="member_adress" /> <br />
         <form:errors path="member_adress" /> <br />
         전화번호 : <form:input path="member_tel" /> <br />
         <form:errors path="member_tel" /> <br />
         전화번호 : <form:input path="member_birth" /> <br />
         <form:errors path="member_birth" /> <br />
         <input type="submit" value="가입하기">
</form:form>
</body>
</html>
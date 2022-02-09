<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원가입</h2>
<form method="POST" action="/members/join" >
     id : <input type="text" name="memberId" /> <br />
         이름 : <input type="text" name="memberName" /> <br />
         이메일 : <input type="text" name="memberEmail" /> <br />
         비밀번호 : <input type="password" name="memberPwd" /> <br />
         권한 : <input type="text" name="memberLevel" /> <br />
         주소 : <input type="text" name="memberAdress" /> <br />
         전화번호 : <input type="text" name="memberTel" /> <br />
         생년월일 : <input type="date" name="memberBirth" /> <br />
         <input type="submit" value="가입하기">
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- histoty Modal-->
<div class="modal-header">
    <h5 class="modal-title" id="historyModalLabel">ID - Modal</h5>
    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">×</span>
    </button>
</div>
<div class="modal-body">
    <div class="table-responsive">
        <div class="container">
        </div>
        <table class="table table-hover">
            <thead class="text-center">
            <tr class="content">
                <th class="text-center">
                    ID
                </th>
                <th class="text-center">
                    PassWord
                </th>
            </tr>
            </thead>
            <tbody class="text-center">
                <tr class="content" style="font-size: 12px;">
                <% 
                	System.out.print("dfdsafdsafds");
           		%>
                
                    <td class="text-center">${memberId }</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="modal-footer">
    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
</div>

<%@page import="model.bean.Contact"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý liên hệ</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
	        String msg=request.getParameter("msg");
	    	if("1".equals(msg)){
	    		out.print("<span style='color: blue;font-weight: bold;' >Xóa danh mục thành công!</span>");
	    	}
	    	
	    	String err=request.getParameter("err");
	    	if("1".equals(err)){
	    		out.print("<span style='color: red;font-weight: bold;' >ID không tồn tại!</span>");
	    	}
	    	if("2".equals(err)){
	    		out.print("<span style='color: red;font-weight: bold;' >Có lỗi xảy ra!</span>");
	    	}
        	
        %>
        <hr />
        
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath() %>/admin/contacts/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="text" class="form-control input-sm" placeholder="Nhập tên liên hệ" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>Message</th>
                                        <th width="100px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	ArrayList<Contact> contactList = (ArrayList<Contact>)request.getAttribute("contactList");
                                	if(contactList != null && contactList.size() > 0) {
                                		for(Contact item : contactList) {
                                	
                                %>
                                    <tr>
                                        <td><%=item.getId() %></td>
                                        <td class="center"><%=item.getName() %></td>
                                        <td class="center"><%=item.getEmail() %></td>
                                        <td class="center"><%=item.getWebsite() %></td>
                                        <td class="center"><%=item.getMessage() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/contacts/delete?id=<%=item.getId() %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa?')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                <%
                                		}}
                                %>
                                </tbody>
                            </table>
                            <%
                            	int contactAmount = (Integer) request.getAttribute("contactAmount");
                            	int pages = (Integer) request.getAttribute("pages");
                            	int currentPage = (Integer) request.getAttribute("currentPage");
                            	if(contactList != null && contactList.size() > 0){
                            		
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                	
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <!-- <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="#">Trang trước</a></li> -->
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="javascript:void(0)" style="font-weight: bold;">Trang </a></li>
                                            <%
                                            	String active = "";
                                            	for(int i = 1; i <= pages; i++) {
                                            		if(currentPage == i) {
                                            			active = " active";
                                            		} else {
                                            			active = "";
                                            		}
                                            		
                                            %>
                                            <li class="paginate_button<%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contacts?page=<%=i%>"><%=i %></a></li>
                                            <%
                                            	}
                                            %>
                                            <!-- <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="#">Trang tiếp</a></li> -->
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <%
                            	}
                            %>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
<%@page import="model.dao.CatDAO"%>
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
                <h2>Thêm bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
	        String name = request.getParameter("name");
			String preview = request.getParameter("preview");
			String detail = request.getParameter("detail");
			String catId=request.getParameter("category");
	        String err=request.getParameter("err");
	    	if("1".equals(err)){
	    		out.print("<span style='color: red;font-weight: bold;' >Có lỗi xảy ra!</span>");
	    	}
	    	ArrayList<Category> categoryList=(ArrayList<Category>)request.getAttribute("categoryList");
        %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="" role="form" method="post" enctype="multipart/form-data" id="form" class="form">
                                    <div class="form-group">
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="<%if(name!=null) out.print(name); %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="category" class="form-control">
                                        	<%
                                        		if(categoryList!=null&&categoryList.size()>0){
                                        			for(Category category : categoryList){
                                        	%>
	                                       	 			<option <%if(catId != null && catId.equals(String.valueOf(category.getId()))) out.print(" selected"); %> value="<%=category.getId()%>"><%=category.getName() %></option>
											<%
                                        			}
                                        		}
											%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview"><%if(preview!=null) out.print(preview); %></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" id="detail" rows="5" name="detail"><%if(detail!=null) out.print(detail); %></textarea>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
                                </form>
                                <style>
									.error {
										color: red;
									}
								</style>
								
								<script type="text/javascript">
									$(document).ready(function() {
										$('.form').validate({
											rules: {
												"name": {
													required: true
												},
												"preview": {
													required: true
												},
												"detail": {
													required: true
												}
											},
											messages: {
												"name": {
													required: "Vui lòng nhập thông tin!"
												},
												"preview": {
													required: "Vui lòng nhập thông tin!"
												},
												"detail": {
													required: "Vui lòng nhập thông tin!"
												}
											}
										});
									});
								</script>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script type="text/javascript">
	CKEDITOR.replace('detail');
</script>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
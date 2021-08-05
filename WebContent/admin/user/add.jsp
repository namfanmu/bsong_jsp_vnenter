<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
	    	String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
        	String err=request.getParameter("err");
        	if("1".equals(err)){
        		out.print("<span style='color: red;font-weight: bold;' >Có lỗi xảy ra!</span>");
        	}
        	if("2".equals(err)){
        		out.print("<span style='color: red;font-weight: bold;' >Username đã tồn tại!</span>");
        	}
        %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="" role="form" method="post" id="form" class="form">
                                    <div class="form-group">
                                        <label for="name">Username</label>
                                        <input type="text" id="username" value="<%if(username!=null) out.print(username); %>" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Password</label>
                                        <input type="password" id="password" value="<%if(password!=null) out.print(password); %>" name="password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Fullname</label>
                                        <input type="text" id="fullname" value="<%if(fullname!=null) out.print(fullname); %>" name="fullname" class="form-control" />
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
												"username": {
													required: true
												},
												"password": {
													required: true
												},
												"fullname": {
													required: true
												}
											},
											messages: {
												"username": {
													required: "Vui lòng nhập thông tin!"
												},
												"password": {
													required: "Vui lòng nhập thông tin!"
												},
												"fullname": {
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
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
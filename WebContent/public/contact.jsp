<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <h1><span>Liên hệ</span></h1>
      <div class="clr"></div>
      	<%
	      	String name = request.getParameter("name");
			String email = request.getParameter("email");
			String website = request.getParameter("website");
			String message = request.getParameter("message");
        	String err=request.getParameter("err");
        	if("1".equals(err)){
        		out.print("<span style='color: red;font-weight: bold;' >Có lỗi xảy ra!</span>");
        	}
        	String msg=request.getParameter("msg");
        	if("1".equals(msg)){
        		out.print("<span style='color: blue;font-weight: bold;' >Thêm liên hệ thành công!</span>");
        	}
        %>
      <p style="color: blue;padding-left: 0px;font-weight: bold;">Khi bạn có thắc mắc, vui lòng gửi liên hệ, chúng tôi sẽ phản hồi trong thời gian sớm nhất.</p>
    </div>
    <div class="article">
      <h2>Gửi liên hệ đến chúng tôi</h2>
      <div class="clr"></div>
      <form action="" method="post" id="sendemail" class="form">
        <ol>
          <li>
            <label for="name">Họ tên</label>
            <input id="name" value="<%if(name!=null) out.print(name); %>" name="name" class="text" />
          </li>
          <li>
            <label for="email">Email</label>
            <input id="email" value="<%if(email!=null) out.print(email); %>" name="email" class="text" />
          </li>
          <li>
            <label for="website">Website</label>
            <input id="website" value="<%if(website!=null) out.print(website); %>" name="website" class="text" />
          </li>
          <li>
            <label for="message">Nội dung</label>
            <textarea id="message"  name="message" rows="8" cols="50" ><%if(message!=null) out.print(message); %></textarea>
          </li>
          <li>
            <button id="button2899" type="submit" name="submit" class="button2899">Gửi</button>
            <div class="clr"></div>
          </li>
        </ol>
      </form>
      <style>
			.error {
				color: red;
				padding-top: 5px;
			}
		</style>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$('.form').validate({
					rules: {
						"name": {
							required: true
						},
						"email": {
                            required: true,
                            email: true,
                        },
                        "website": {
                            required: true,
                            url: true,
                        },
                        "message": {
							required: true
						}
					},
					messages: {
						"name": {
							required: "Vui lòng nhập đầy đủ thông tin!"
						},
						"email": {
                            required: "Vui lòng nhập đầy đủ thông tin!",
                            email: "Vui lòng nhập email!",
                        },
                        "website": {
                            required: "Vui lòng nhập đầy đủ thông tin!",
                            url: "Vui lòng nhập URL!",
                        },
                        "message": {
							required: "Vui lòng nhập đầy đủ thông tin!"
						}
					}
				});
			});
		</script>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>

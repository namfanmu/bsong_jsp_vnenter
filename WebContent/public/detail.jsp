<%@page import="model.bean.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>

<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    <%
    	Song song=(Song)request.getAttribute("song");
    %>
      <h1><%=song.getName() %></h1>
      <div class="clr"></div>
      <p>Ngày đăng: <%=song.getDateCreate() %>. Lượt xem: <%=song.getCounter() %></p>
      <div class="vnecontent">
          <%=song.getDetailText() %>
      </div>
    </div>
    
    <div class="article"  >
      <h2 id="relative">Bài viết liên quan</h2>
      <div class="clr"></div>
      <%
      	ArrayList<Song> songs=(ArrayList<Song>)request.getAttribute("songList");
      	if(songs!=null&&songs.size()>0){
      		for(Song song2 : songs){
 
      %>
      <div class="comment"> <a href="<%=request.getContextPath()%>/song?id=<%=song2.getId() %>"><img src="<%=request.getContextPath() %>/files/<%=song2.getPicture() %>" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a href="<%=request.getContextPath()%>/song?id=<%=song2.getId() %>"><%=song2.getName() %></a></h2>
        <p><%=song2.getPreviewText() %></p>
      </div>
      <%
				}
			}
      %>
    </div>
    
    <!-- Comment -->
    <div class="article">
			<div class="panel-primary">
				<div class="panel-heading">
					<h2 id="relative">Bình luận</h2>
				</div>
				<form class="form-cmt">
					<div class="form-item">
						<input type="text" name="song_id" id="song_id" value="<%=song.getId() %>" disabled="disabled" />
					</div>
					<br>
					<div class="form-item">
						<input type="text" name="fullname" id="fullname" value="" placeholder="Nhập tên" required="required" />
					</div>
					<br>
					<div class="form-item">
						<input type="text" name="cmt" id="cmt" value="" placeholder="Nhập bình luận" required="required" />
						
					</div>
					<br>
					<a href="javascript:void(0)" title="" class="btn" onclick="return getComment()">Bình luận</a>
				</form>
			</div>
			<p id="print"></p>
		</div>

		<script type="text/javascript">
		
		function getComment() {
			var name=$("#fullname").val();
			var cmt=$("#cmt").val();
			var song_id=$("#song_id").val();
			
			$.ajax({
				url: '<%=request.getContextPath()%>/comment',
				type: 'POST',
				cache: false,
				data: {
					//Dữ liệu gửi đi
					name: name,
					cmt: cmt,
					song_id: song_id
				},
				success: function(data){
					// Xử lý thành công
					$(".ajax-data").html(data);
				},
				error: function (){
					// Xử lý nếu có lỗi
					alert("Error!");
				}
				
			});
			return false;
		}
		
		</script>

		<div class="ajax-data">
				<%
					ArrayList<Comment> cmtList=(ArrayList<Comment>)request.getAttribute("cmtList");
					if(cmtList!=null&&cmtList.size()>0){
						for(Comment comment : cmtList){
				%>
						<div class="item-cmt">
							<p class="name-cmt"><%=comment.getName() %></p>
							<p class="content-cmt"><%=comment.getComment() %></p>
							<p class="time-cmt"><%=comment.getDatePost() %></p>
						</div>
				<%
						}} else{
				%>
				<p style="color: red;">Chưa có bình luận nào cả! </p>
				<%
					}
				%>
				
		</div>
    
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>

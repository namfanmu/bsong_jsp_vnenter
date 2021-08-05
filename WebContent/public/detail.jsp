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
    
    <div class="article">
      <h2>Bài viết liên quan</h2>
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
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>

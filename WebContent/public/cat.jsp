<%@page import="util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>

<div class="content_resize">
  <div class="mainbar">
  <%
  	Category cat=(Category)request.getAttribute("category");
  %>
    <div class="article">
		<h1><%=cat.getName() %></h1>
    </div>
    <%
	  	ArrayList<Song> songs=(ArrayList<Song>)request.getAttribute("songs");
	  	if(songs!=null&&songs.size()>0){
	  		int count=0;
	  		for(Song song : songs){
	  			count++;
	  %>
    <div class="article">
      <h2><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(cat.getName()) %>/<%=StringUtil.makeSlug(song.getName()) %>-<%=song.getId() %>.html" title="<%=song.getName() %>"><%=song.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=song.getDateCreate() %>. Lượt xem: <%=song.getCounter() %> <a href="<%=request.getContextPath()%>/song?id=<%=song.getId() %>" class="com"><span><%=count %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/files/<%=song.getPicture() %>" width="200" height="200" alt="" class="fl" /></div>
      <div class="post_content">
        <p><%=song.getPreviewText() %></p>
        <p class="spec"><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(cat.getName()) %>/<%=StringUtil.makeSlug(song.getName()) %>-<%=song.getId() %>.html" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%
			}
		}
    %>
    <%
    	int songAmount = (Integer) request.getAttribute("songAmount");
    	int pages = (Integer) request.getAttribute("pages");
    	int currentPage = (Integer) request.getAttribute("currentPage");
    	if(songs != null && songs.size() > 0){
    		
    %>
    <div class="row">
        <div class="col-sm-6">
        	
        </div>
        <div class="col-sm-6" style="text-align: left;">
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
                    <li class="paginate_button<%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(cat.getName()) %>-<%=cat.getId()%>/page/<%=i%>"><%=i %></a></li>
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
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
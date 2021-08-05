<%@page import="model.dao.SongDAO"%>
<%@page import="model.bean.Song"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CatDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
  <form id="formsearch" name="formsearch" method="post" action="<%=request.getContextPath() %>/index/search">
    <span>
    <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="" type="text" placeholder="Nhập tên bài hát" />
    </span>
    <input name="button_search" src="<%=request.getContextPath() %>/templates/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  	<%
  		CatDAO catDAO=new CatDAO();
  		ArrayList<Category> categoryList=catDAO.getAllCategories();
  		if(categoryList!=null&&categoryList.size()>0){
  			for(Category category : categoryList){

  	%>
    <li><a href="<%=request.getContextPath()%>/cat?cat_id=<%=category.getId()%>"><%=category.getName() %></a></li>
    <%
			}
		}
    %>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
  	<%
  		SongDAO songDAO = new SongDAO();
  		ArrayList<Song> songList = songDAO.getAllSong();
  		if(songList != null && songList.size() > 0) {
  			for(int i = 0; i < 5; i++){

  	%>
    <li><a href="<%=request.getContextPath()%>/song?id=<%=songList.get(i).getId()%>"><%=songList.get(i).getName() %></a><br />
      <%if(songList.get(i).getPreviewText().length() > 50) out.print(songList.get(i).getPreviewText().substring(0, 50)+"..."); else out.print(songList.get(i).getPreviewText()); %></li>
    
      <%
			}
		}
      %>
  </ul>
</div>
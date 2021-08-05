<%@page import="model.bean.Category"%>
<%@page import="model.dao.CatDAO"%>
<%@page import="model.bean.Song"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
	        String err=request.getParameter("err");
	    	if("1".equals(err)){
	    		out.print("<span style='color: red;font-weight: bold;' >ID không tồn tại!</span>");
	    	}
	    	if("2".equals(err)){
	    		out.print("<span style='color: red;font-weight: bold;' >Có lỗi xảy ra!</span>");
	    	}
	    	String msg=request.getParameter("msg");
	    	if("1".equals(msg)){
	    		out.print("<span style='color: blue;font-weight: bold;' >Thêm bài hát thành công!</span>");
	    	}
	    	if("2".equals(msg)){
	    		out.print("<span style='color: blue;font-weight: bold;' >Sửa bài hát thành công!</span>");
	    	}
	    	if("3".equals(msg)){
	    		out.print("<span style='color: blue;font-weight: bold;' >Xóa bài hát thành công!</span>");
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
                                    <a href="<%=request.getContextPath() %>/admin/songs/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath() %>/admin/songs/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="text" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên bài hát</th>
                                        <th>Danh mục</th>
                                        <th>Lượt đọc</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	CatDAO catDAO=new CatDAO();
                                	ArrayList<Song> songList=(ArrayList<Song>)request.getAttribute("songList");
                                	if(songList!=null&&songList.size()>0){
                                		for(Song song : songList){
                                			Category category=catDAO.getCatById(song.getCatId());
                                	
                                %>
                                    <tr>
                                        <td><%=song.getId() %></td>
                                        <td class="center"><%=song.getName() %></td>
                                        <td class="center"><%=category.getName() %></td>
                                        <td class="center"><%=song.getCounter() %></td>
                                        <td class="center">
											<img width="200px" height="200px" src="<%=request.getContextPath() %>/files/<%=song.getPicture() %>" alt=""/>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/songs/edit?id=<%=song.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/songs/delete?id=<%=song.getId() %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa?')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
								<%
									}}
								%>
                                </tbody>
                            </table>
                            <%
                            	int songAmount = (Integer) request.getAttribute("songAmount");
                            	int pages = (Integer) request.getAttribute("pages");
                            	int currentPage = (Integer) request.getAttribute("currentPage");
                            	if(songList != null && songList.size() > 0){
                            		
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
                                            <li class="paginate_button<%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/songs?page=<%=i%>"><%=i %></a></li>
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
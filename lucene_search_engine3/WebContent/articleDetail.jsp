<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="article.ArticleDTO" %>
<%@ page import="article.ArticleDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.net.URLEncoder" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta name="viewsport" content="width=device-width, intial-scale=1, shrink-to-fit=no">
	<title>Hello World?</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css">
	<link rel="stylesheet" href="./css/custom.css">
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	int ScoreDoc = 0;
	String searchEngine = "Default";
	if(request.getParameter("ScoreDoc")!= null){
		try{
			ScoreDoc = Integer.parseInt(request.getParameter("ScoreDoc"));
		} catch(Exception e){
			System.out.println("pagenum error");
		}
	}
	if(request.getParameter("searchEngine")!= null){
		try{
			searchEngine = request.getParameter("searchEngine");
		} catch(Exception e){
			System.out.println("pagenum error");
		}
	}
	
	ArticleDTO article = new ArticleDAO().getArticle(ScoreDoc, searchEngine);
%>


		<div class="modal-content">
			<div class="modal-header">
				<label class="modal-title" id="modal"><%=article.getTitle()%></label>
			</div>
			<div class="modal-body">
				<div class="form-group">
						<label><b>英文篇名:</b></label>
						<a><%=article.getEngTitle() %></a>
				</div>
				<div class="form-group">
						<label><b>第一责任人:</b></label>
						<a><%=article.getFirstPerson() %></a>
				</div>
				<div class="form-group">
						<label><b>作者:</b></label>
						<a><%=article.getAuthor() %></a>
				</div>
				<div class="form-group">
						<label><b>英文作者:</b></label>
						<a><%=article.getEngAuthor() %></a>
				</div>
				<div class="form-group">
						<label><b>单位:</b></label>
						<a><%=article.getOrganization() %></a>
				</div>
				<div class="form-group">
						<label><b>来源:</b></label>
						<a><%=article.getSource() %></a>
				</div>
				<div class="form-group">
						<label><b>出版单位:</b></label>
						<a><%=article.getPublisher() %></a>
				</div>
				<div class="form-group">
						<label><b>关键词:</b></label>
						<a><%=article.getKeyword() %></a>
				</div>
				<div class="form-group">
						<label><b>英文关键词:</b></label>
						<a><%=article.getEngKeyword() %></a>
				</div>
				<div class="form-group">
						<label><b>摘要:</b></label>
						<a><%=article.getSummary() %></a>
				</div>
				<div class="form-group">
						<label><b>英文摘要:</b></label>
						<a><%=article.getEngSummary() %></a>
				</div>
				<div class="form-group">
						<label><b>基金:</b></label>
						<a><%=article.getFund() %></a>
				</div>
				<div class="form-group">
						<label><b>年:</b></label>
						<a><%=article.getYear() %></a>
				</div>
				<div class="form-group">
						<label><b>期:</b></label>
						<a><%=article.getIssue() %></a>
				</div>
				<div class="form-group">
						<label><b>专辑代码:</b></label>
						<a><%=article.getAlbumCode() %></a>
				</div>
				<div class="form-group">
						<label><b>专题代码:</b></label>
						<a><%=article.getThemaCode() %></a>
				</div>
				<div class="form-group">
						<label><b>专题子栏目代码:</b></label>
						<a><%=article.getThemaChildCode() %></a>
				</div>
				<div class="form-group">
						<label><b>专题名称:</b></label>
						<a><%=article.getThemaName() %></a>
				</div>
				<div class="form-group">
						<label><b>分类号:</b></label>
						<a><%=article.getClassNum() %></a>
				</div>
				<div class="form-group">
						<label><b>分类名称:</b></label>
						<a><%=article.getClassName() %></a>
				</div>
				<div class="form-group">
						<label><b>文件名:</b></label>
						<a><%=article.getFile() %></a>
				</div>
				<div class="form-group">
						<label><b>语种:</b></label>
						<a><%=article.getLanguage() %></a>
				</div>
				<div class="form-group">
						<label><b>被引年:</b></label>
						<a><%=article.getCitedYear() %></a>
				</div>
				<div class="form-group">
						<label><b>参考文献:</b></label>
						<a><%=article.getReference() %></a>
				</div>
				<div class="form-group">
						<label><b>引证文献:</b></label>
						<a><%=article.getYinZheng() %></a>
				</div>
				<div class="form-group">
						<label><b>共引文献:</b></label>
						<a><%=article.getGongYin() %></a>
				</div>
				<div class="form-group">
						<label><b>同被引文献:</b></label>
						<a><%=article.getTongBeiYin() %></a>
				</div>
				<div class="form-group">
						<label><b>二级引证文献:</b></label>
						<a><%=article.getSecondYinZheng() %></a>
				</div>
				<div class="form-group">
						<label><b>二级参考文献:</b></label>
						<a><%=article.getSecondReference() %></a>
				</div>
				<div class="form-group">
						<label><b>表名:</b></label>
						<a><%=article.getTableName() %></a>
				</div>
				<div class="form-group">
						<label><b>出版日期:</b></label>
						<a><%=article.getPublishTime() %></a>
				</div>
				<div class="form-group">
						<label><b>引证文献数量:</b></label>
						<a><%=article.getYinZhengAmount() %></a>
				</div>
				<div class="form-group">
						<label><b>二级参考文献数量:</b></label>
						<a><%=article.getSecondReferenceAmount() %></a>
				</div>
				<div class="form-group">
						<label><b>二级引证文献数量:</b></label>
						<a><%=article.getYinZhengAmount() %></a>
				</div>
				<div class="form-group">
						<label><b>共引文献数量:</b></label>
						<a><%=article.getGongYinAmount() %></a>
				</div>
				<div class="form-group">
						<label><b>同被引文献数量:</b></label>
						<a><%=article.getTongBeiYinAmount() %></a>
				</div>
				<div class="form-group">
						<label><b>英文刊名:</b></label>
						<a><%=article.getJournal() %></a>
				</div>
				<div class="form-group">
						<label><b>ISSN:</b></label>
						<a><%=article.getIssn() %></a>
				</div>
				<div class="form-group">
						<label><b>CN:</b></label>
						<a><%=article.getCn() %></a>
				</div>
			</div>
		</div>

	
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
		Copyright &copy 2018 All Rights Reserved.
	</footer>
	
	
	<script src="./js/jquery.min.js"></script>
	<script src="./js/pooper.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>






</html>
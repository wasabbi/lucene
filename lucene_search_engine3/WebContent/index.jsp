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
	String searchEngine = "Default";
	String searchType = "All";
	String search = "";
	int pageNumber = 0;
	if(request.getParameter("searchEngine")!= null){
		searchEngine = request.getParameter("searchEngine");
	}
	if(request.getParameter("searchType")!= null){
		searchType = request.getParameter("searchType");
	}
	if(request.getParameter("search")!= null){
		search = request.getParameter("search");
	}
	if(request.getParameter("pageNumber")!= null){
		try{
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		} catch(Exception e){
			System.out.println("pagenum error");
		}
	}
%>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="index.jsp">Lucene搜索引擎</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
			<span class="navbar-toggler-icon"> 
			</span>
		</button>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="index.jsp">Main</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="dropdown" data-toggle="dropdown">
						Manage
					</a>
					<div class="dropdown-menu" aria-labelledby="dropdown">
						<a class="dropdown-item" href="#">Login</a>
						<a class="dropdown-item" href="#">Logout</a>
						<a class="dropdown-item" href="#">Register</a>
					</div>
				</li>
			</ul>
			<form action="./index.jsp" method="get" class="form-inline my-2 my-lg-0">
				<input type="text" name="search" class="form-control mr-sm-2" type="search" placeholder="input term" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	<section class="container">
		<form method="get" action="./index.jsp" class="form-inline mt-3">
			<select name="searchEngine" class="form-control mx-1 mt-2">
				<option value="Default">Default</option>
				<option value="BM25" <%if(searchEngine.equals("BM25")) out.println("selected"); %>>BM25</option>
				<option value="LMD" <%if(searchEngine.equals("LMD")) out.println("selected"); %>>LMD</option>
				<option value="LMJM" <%if(searchEngine.equals("LMJM")) out.println("selected"); %>>LMJM</option>
				<option value="Custom" <%if(searchEngine.equals("Custom")) out.println("selected"); %>>Custom</option>				
			</select>
			<select name="searchType" class="form-control mx-1 mt-2">
				<option value="All" <%if(searchType.equals("All")) out.println("selected"); %>>All</option>
				<option value="Title" <%if(searchType.equals("Title")) out.println("selected"); %>>Title</option>
				<option value="EngTitle" <%if(searchType.equals("EngTitle")) out.println("selected"); %>>EngTitle</option>
				<option value="Author" <%if(searchType.equals("Author")) out.println("selected"); %>>Author</option>
				<option value="EngAuthor" <%if(searchType.equals("EngAuthor")) out.println("selected"); %>>EngAuthor</option>
				<option value="FirstPerson" <%if(searchType.equals("FirstPerson")) out.println("selected"); %>>FirstPerson</option>
				<option value="Organization" <%if(searchType.equals("Organization")) out.println("selected"); %>>Organization</option>
				<option value="Source" <%if(searchType.equals("Source")) out.println("selected"); %>>Source</option>
				<option value="Publisher" <%if(searchType.equals("Publisher")) out.println("selected"); %>>Publisher</option>
				<option value="Keyword" <%if(searchType.equals("Keyword")) out.println("selected"); %>>Keyword</option>
				<option value="EngKeyword" <%if(searchType.equals("EngKeyword")) out.println("selected"); %>>EngKeyword</option>
				<option value="Summary" <%if(searchType.equals("Summary")) out.println("selected"); %>>Summary</option>
				<option value="Fund" <%if(searchType.equals("Fund")) out.println("selected"); %>>Fund</option>
				<option value="EngSummary" <%if(searchType.equals("EngSummary")) out.println("selected"); %>>EngSummary</option>
				<option value="Year" <%if(searchType.equals("Year")) out.println("selected"); %>>Year</option>
				<option value="Issue" <%if(searchType.equals("Issue")) out.println("selected"); %>>Issue</option>
				<option value="AlbumCode" <%if(searchType.equals("AlbumCode")) out.println("selected"); %>>AlbumCode</option>
				<option value="ThemaCode" <%if(searchType.equals("ThemaCode")) out.println("selected"); %>>ThemaCode</option>
				<option value="ThemaChildCode" <%if(searchType.equals("ThemaChildCode")) out.println("selected"); %>>ThemaChildCode</option>
				<option value="ThemaName" <%if(searchType.equals("ThemaName")) out.println("selected"); %>>ThemaName</option>
				<option value="ClassNum" <%if(searchType.equals("ClassNum")) out.println("selected"); %>>ClassNum</option>
				<option value="ClassName" <%if(searchType.equals("ClassName")) out.println("selected"); %>>ClassName</option>
				<option value="File" <%if(searchType.equals("File")) out.println("selected"); %>>File</option>
				<option value="Language" <%if(searchType.equals("Language")) out.println("selected"); %>>Language</option>
				<option value="CitedYear" <%if(searchType.equals("CitedYear")) out.println("selected"); %>>CitedYear</option>
				<option value="Reference" <%if(searchType.equals("Reference")) out.println("selected"); %>>Reference</option>
				<option value="YinZheng" <%if(searchType.equals("YinZheng")) out.println("selected"); %>>YinZheng</option>
				<option value="GongYin" <%if(searchType.equals("GongYin")) out.println("selected"); %>>GongYin</option>
				<option value="TongBeiYin" <%if(searchType.equals("TongBeiYin")) out.println("selected"); %>>TongBeiYin</option>
				<option value="SecondYinZheng" <%if(searchType.equals("SecondYinZheng")) out.println("selected"); %>>SecondYinZheng</option>
				<option value="SecondReference" <%if(searchType.equals("SecondReference")) out.println("selected"); %>>SecondReference</option>
				<option value="TableName" <%if(searchType.equals("TableName")) out.println("selected"); %>>TableName</option>
				<option value="PublishTime" <%if(searchType.equals("PublishTime")) out.println("selected"); %>>PublishTime</option>
				<option value="YinZhengAmount" <%if(searchType.equals("YinZhengAmount")) out.println("selected"); %>>YinZhengAmount</option>
				<option value="SecondReferenceAmount" <%if(searchType.equals("SecondReferenceAmount")) out.println("selected"); %>>SecondReferenceAmount</option>
				<option value="SecondYinZhengAmount" <%if(searchType.equals("SecondYinZhengAmount")) out.println("selected"); %>>SecondYinZhengAmount</option>
				<option value="GongYinAmount" <%if(searchType.equals("GongYinAmount")) out.println("selected"); %>>GongYinAmount</option>
				<option value="TongBeiYinAmount" <%if(searchType.equals("TongBeiYinAmount")) out.println("selected"); %>>TongBeiYinAmount</option>
				<option value="Journal" <%if(searchType.equals("Journal")) out.println("selected"); %>>Journal</option>
				<option value="ISSN" <%if(searchType.equals("ISSN")) out.println("selected"); %>>ISSN</option>
				<option value="CN" <%if(searchType.equals("CN")) out.println("selected"); %>>CN</option>				
			</select>
			<input type="text" name="search" class="form-control mx-1 mt-2" placeholder="input content">
			<button type="submit" class="btn btn-primary mx-1 mt-2">search</button>
			<a class="btn btn-primary mx-1 mt-2" data-toggle="modal" href="#registerModal">Advanced</a>
			<a class="btn btn-danger mx-1 mt-2" data-toggle="modal" href="#reportModal">Report</a>
		</form>
		
<%//!!!!!!!!!!!!!!!!!!!!Build Path 에 jar 파일들을 꼭 넣어주자!!
	ArrayList<ArticleDTO> articleList =  new ArrayList<ArticleDTO>();
	if(search == "") search = "a";
	articleList = new ArticleDAO().getList(search, searchEngine, searchType, pageNumber);
	if(articleList != null){
		for(int i = 0; i < articleList.size(); i++){
			if(i == 10) break;
			ArticleDTO article = articleList.get(i);
		
%>
		
		<div class="card bg-light mt-3">
			<div class="card-header bg-light">
				<div class="row">
					<a class="col-8 text-left" href="./articleDetail.jsp?ScoreDoc=<%=article.getScoreDoc()%>&searchEngine=<%=searchEngine%>"><%=article.getTitle() %></a>
					<div class="col-4 text-right">
						<%= article.getFirstPerson() %> 
					</div>
				</div>
			</div>
			<div class="card-body">
				<label class="card-title">
					<p><% if(article.getEngTitle() == null){out.print("无英文题目");}else{out.print(article.getEngTitle());} %>&nbsp&nbsp&nbsp&nbsp<small><%= article.getAuthor() %></small></p>
				</label>
				<p class="card-text"><%=article.getSummary() %></p>
				<div class="row">
					<div class="col-9 text-left">

					</div>
				</div>
			</div>
		</div>
<%
		}
	}
%>
	</section>
	<ul class="pagination justify-content-center mt-3">
		<li class="page-item">
<%
	if(pageNumber <= 0){
%>
	<a class="page-link disabled">previous</a>
<%
	} else{
%>
	<a class="page-link" href="./index.jsp?searchEngine=<%= URLEncoder.encode(searchEngine, "UTF-8") %>
	&searchType=<%= URLEncoder.encode(searchType, "UTF-8") %>
	&search=<%= URLEncoder.encode(search, "UTF-8") %>
	&pageNumber=<%= pageNumber-1 %>">previous</a>
<%
	}
%>
		</li>
		<li>
<%
	if(articleList.size() < 11){
%>
	<a class="page-link disabled">next</a>
<%
	} else{
%>
	<a class="page-link" href="./index.jsp?searchEngine=<%= URLEncoder.encode(searchEngine, "UTF-8") %>
	&searchType=<%= URLEncoder.encode(searchType, "UTF-8") %>
	&search=<%= URLEncoder.encode(search, "UTF-8") %>
	&pageNumber=<%= pageNumber+1 %>">next</a>
<%
	}
%>
		</li>
	</ul>"
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">Appraisal</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="./evalutionRegisterAction.jsp" method="post">
						<div class="form-row">
							<div class="form-group col-sm-6">
								<label>Author</label>
								<input type="text" name="LectureName" class="form-control" maxLength="20">
							</div>
							<div class="form-group col-sm-6">
								<label>EngAuthor</label>
								<input type="text" name="LectureName" class="form-control" maxLength="20">
							</div>
						</div>
						<div class="form-row">
							<div class="form-froup col-sm-4">
								<label>year</label>
								<select name="lectureYear" class="form-control">
									<option value="2010">2011></option>
									<option value="2011" selected>2011></option>
									<option value="2012">2011></option>
								</select>
							</div>
							<div class="form-group col-sm-4">
								<label>Organization</label>
								<select name="semesterDivide" class="form-control">
									<option value="1sem" selected>1st</option>
									<option value="2sem" selected>2nd</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="evaluationTime" class="form-control" maxlength="30">
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea name="evaluationContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>
						</div>
						<div class="form-row">
							<div class="form-group col-sm-3">
								<label>searchEngine</label>
								<select name="totalScore" class="form-control">
									<option value="A" selected>A</option>
									<option value="B">B</option>
									<option value="C">C</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>searchType</label>
								<select name="comfortableScore" class="form-control">
									<option value="A" selected>A</option>
									<option value="B">B</option>
									<option value="C">C</option>
								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
							<button type="submit" class="btn btn-primary">OK</button>
						</div>"
					</form>
				</div>
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
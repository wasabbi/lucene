package article;

public class ArticleDTO {
	String title;
	String engTitle;
	String author;
	String engAuthor;
	String firstPerson;
	String organization;
	String source;
	String publisher;
	String keyword;
	String engKeyword;
	String summary;
	String engSummary;
	String fund;
	String year;
	String issue;
	String albumCode;
	String themaCode;
	String themaChildCode;
	String themaName;
	String classNum;
	String className;
	String file;
	String language;
	String citedYear;
	String reference;
	String yinZheng;
	String gongYin;
	String tongBeiYin;
	String secondYinZheng;
	String secondReference;
	String tableName;
	String publishTime;
	String yinZhengAmount;
	String secondReferenceAmount;
	String secondYinZhengAmount;
	String gongYinAmount;
	String tongBeiYinAmount;
	String journal;
	String issn;
	String cn;
	int scoreDoc;
	
	public ArticleDTO() {
	}
	public ArticleDTO(String title, String engTitle, String author, String engAuthor, String firstPerson,
			String organization, String source, String publisher, String keyword, String engKeyword, String summary,
			String engSummary, String fund, String year, String issue, String albumCode, String themaCode, String themaChildCode,
			String themaName, String classNum, String className, String file, String language, String citedYear,
			String reference, String yinZheng, String gongYin, String tongBeiYin, String secondYinZheng,
			String secondReference, String tableName, String publishTime, String yinZhengAmount,
			String secondReferenceAmount, String secondYinZhengAmount, String gongYinAmount, String tongBeiYinAmount,
			String journal, String issn, String cn, int scoreDoc) {
		super();
		this.title = title;
		this.engTitle = engTitle;
		this.author = author;
		this.engAuthor = engAuthor;
		this.firstPerson = firstPerson;
		this.organization = organization;
		this.source = source;
		this.publisher = publisher;
		this.keyword = keyword;
		this.engKeyword = engKeyword;
		this.summary = summary;
		this.engSummary = engSummary;
		this.fund = fund;
		this.year = year;
		this.issue = issue;
		this.albumCode = albumCode;
		this.themaCode = themaCode;
		this.themaChildCode = themaChildCode;
		this.themaName = themaName;
		this.classNum = classNum;
		this.className = className;
		this.file = file;
		this.language = language;
		this.citedYear = citedYear;
		this.reference = reference;
		this.yinZheng = yinZheng;
		this.gongYin = gongYin;
		this.tongBeiYin = tongBeiYin;
		this.secondYinZheng = secondYinZheng;
		this.secondReference = secondReference;
		this.tableName = tableName;
		this.publishTime = publishTime;
		this.yinZhengAmount = yinZhengAmount;
		this.secondReferenceAmount = secondReferenceAmount;
		this.secondYinZhengAmount = secondYinZhengAmount;
		this.gongYinAmount = gongYinAmount;
		this.tongBeiYinAmount = tongBeiYinAmount;
		this.journal = journal;
		this.issn = issn;
		this.cn = cn;
		this.scoreDoc = scoreDoc;
	}
	
	public String getTitle() {
		return title;
	}
	public String getEngTitle() {
		return engTitle;
	}
	public String getAuthor() {
		return author;
	}
	public String getEngAuthor() {
		return engAuthor;
	}
	public String getFirstPerson() {
		return firstPerson;
	}
	public String getOrganization() {
		return organization;
	}
	public String getSource() {
		return source;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getKeyword() {
		return keyword;
	}
	public String getEngKeyword() {
		return engKeyword;
	}
	public String getSummary() {
		return summary;
	}
	public String getEngSummary() {
		return engSummary;
	}
	public String getFund() {
		return fund;
	}
	public String getYear() {
		return year;
	}
	public String getIssue() {
		return issue;
	}
	public String getAlbumCode() {
		return albumCode;
	}
	public String getThemaCode() {
		return themaCode;
	}
	public String getThemaChildCode() {
		return themaChildCode;
	}
	public String getThemaName() {
		return themaName;
	}
	public String getClassNum() {
		return classNum;
	}
	public String getClassName() {
		return className;
	}
	public String getFile() {
		return file;
	}
	public String getLanguage() {
		return language;
	}
	public String getCitedYear() {
		return citedYear;
	}
	public String getReference() {
		return reference;
	}
	public String getYinZheng() {
		return yinZheng;
	}
	public String getGongYin() {
		return gongYin;
	}
	public String getTongBeiYin() {
		return tongBeiYin;
	}
	public String getSecondYinZheng() {
		return secondYinZheng;
	}
	public String getSecondReference() {
		return secondReference;
	}
	public String getTableName() {
		return tableName;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public String getYinZhengAmount() {
		return yinZhengAmount;
	}
	public String getSecondReferenceAmount() {
		return secondReferenceAmount;
	}
	public String getSecondYinZhengAmount() {
		return secondYinZhengAmount;
	}
	public String getGongYinAmount() {
		return gongYinAmount;
	}
	public String getTongBeiYinAmount() {
		return tongBeiYinAmount;
	}
	public String getJournal() {
		return journal;
	}
	public String getIssn() {
		return issn;
	}
	public String getCn() {
		return cn;
	}
	public int getScoreDoc() {
		return scoreDoc;
	}
}

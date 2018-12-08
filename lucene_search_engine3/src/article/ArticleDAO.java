package article;

import java.io.File;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.LMDirichletSimilarity;
import org.apache.lucene.search.similarities.LMJelinekMercerSimilarity;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class ArticleDAO {
	public ArticleDAO(){
		
	}
	public ArrayList<ArticleDTO> getList (String search, String searchEngine, String searchType, int pageNum) throws Exception{

		ArrayList<ArticleDTO> articleList = new ArrayList<ArticleDTO>();
		
		Analyzer analyzer = null;
		analyzer = new IKAnalyzer();
		
		String indexPath = "";
		indexPath = toIndexPath(searchEngine);
				
		File indexDir = new File(indexPath);
		org.apache.lucene.store.Directory fsDir = FSDirectory.open(indexDir);
		IOContext context = new IOContext();
		org.apache.lucene.store.Directory directory = new RAMDirectory(fsDir, context);
		
		//Test Searching
		IndexReader indexReader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(indexReader);
		String[] Allkeywords = {"Title","EngTitle","Author","EngAuthor","FirstPerson","Organization","Source","Publisher","Keyword","EngKeyword","Summary","Fund","EngSummary","Year","Issue","AlbumCode","ThemaCode","ThemaChildCode","ThemaName","ClassNum","ClassName","File","Language","CitedYear","Reference","YinZheng","GongYin","TongBeiYin","SecondYinZheng","SecondReference","TableName","PublishTime","YinZhengAmount","SecondReferenceAmount","SecondYinZhengAmount","GongYinAmount","TongBeiYinAmount","Journal","ISSN","CN"};
		String[] keyword = {""};
		if(searchType.equals("All"))
			keyword = Allkeywords;
		else
			keyword[0] = searchType;
		
		@SuppressWarnings("deprecation")
		QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_4_10_3, keyword, analyzer);
		//Term t = new Term("EngSummary", "many");
		//Query query = new TermQuery(t); 
		Query query = parser.parse(search);
		if(searchEngine.equals("Default"))								//设置排序规则
			searcher.setSimilarity(new DefaultSimilarity());			
		else if(searchEngine.equals("BM25"))
			searcher.setSimilarity(new BM25Similarity());
		else if(searchEngine.equals("LMD"))
			searcher.setSimilarity(new LMDirichletSimilarity());
		else if(searchEngine.equals("LMJM"))
			searcher.setSimilarity(new LMJelinekMercerSimilarity(0.2f));
		else if(searchEngine.equals("Custom"))
			searcher.setSimilarity(new CustomSimilarity());
		else
			searcher.setSimilarity(new DefaultSimilarity());
		
		TopDocs docs = searcher.search(query, 100);
		for(int i = 0; i < 11 && pageNum*10 + i < docs.scoreDocs.length; i++) {
			Document d = searcher.doc(docs.scoreDocs[pageNum*10+i].doc);
			ArticleDTO a = new ArticleDTO(d.get("Title"), d.get("EngTitle"), d.get("Author"), d.get("EngAuthor"), d.get("FirstPerson"), d.get("Organization"), d.get("Source"), d.get("Publisher"), d.get("Keyword"), d.get("EngKeyword"), d.get("Summary"), d.get("Fund"), d.get("EngSummary"), d.get("Year"), d.get("Issue"), d.get("AlbumCode"), d.get("ThemaCode"), d.get("ThemaChildCode"), d.get("ThemaName"), d.get("ClassNum"), d.get("ClassName"), d.get("File"), d.get("Language"), d.get("CitedYear"), d.get("Reference"), d.get("YinZheng"), d.get("GongYin"), d.get("TongBeiYin"), d.get("SecondYinZheng"), d.get("SecondReference"), d.get("TableName"), d.get("PublishTime"), d.get("YinZhengAmount"), d.get("SecondReferenceAmount"), d.get("SecondYinZhengAmount"), d.get("GongYinAmount"), d.get("TongBeiYinAmount"), d.get("Journal"), d.get("ISSN"), d.get("CN"), docs.scoreDocs[pageNum*10+i].doc);
			articleList.add(a);
			System.out.println(a.getTitle());
		}
		analyzer.close();
		
		return articleList;
	}
	
	private String toIndexPath(String searchEngine) {
		String indexPath;
		if(searchEngine.equals("Defalut")) 							//设置对应的索引文件
			indexPath = "C:\\Users\\YeB\\Desktop\\lucene\\lucene_index";
		else if(searchEngine.equals("BM25"))
			indexPath = "C:\\Users\\YeB\\Desktop\\lucene\\lucene_index_BM25";
		else if(searchEngine.equals("LMD"))
			indexPath = "C:\\Users\\YeB\\Desktop\\lucene\\lucene_index_LMD";
		else if(searchEngine.equals("LMJM"))
			indexPath = "C:\\Users\\YeB\\Desktop\\lucene\\lucene_index_LMJM";
		else if(searchEngine.equals("Custom"))
			indexPath = "C:\\Users\\YeB\\Desktop\\lucene\\lucene_index_custom";
		else 
			indexPath = "C:\\Users\\YeB\\Desktop\\lucene\\lucene_index";
		return indexPath;
	}
	
	public ArticleDTO getArticle(int scoreDoc, String searchEngine) throws Exception {
		String indexPath = "";
		indexPath = toIndexPath(searchEngine);
		File indexDir = new File(indexPath);
		org.apache.lucene.store.Directory fsDir = FSDirectory.open(indexDir);
		IOContext context = new IOContext();
		org.apache.lucene.store.Directory directory = new RAMDirectory(fsDir, context);
		
		//Test Searching
		IndexReader indexReader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(indexReader);

		Document d = searcher.doc(scoreDoc);
		if(d == null)
			return null;
		ArticleDTO a = new ArticleDTO(d.get("Title"), d.get("EngTitle"), d.get("Author"), d.get("EngAuthor"), d.get("FirstPerson"), d.get("Organization"), d.get("Source"), d.get("Publisher"), d.get("Keyword"), d.get("EngKeyword"), d.get("Summary"), d.get("EngSummary"), d.get("Fund"), d.get("Year"), d.get("Issue"), d.get("AlbumCode"), d.get("ThemaCode"), d.get("ThemaChildCode"), d.get("ThemaName"), d.get("ClassNum"), d.get("ClassName"), d.get("File"), d.get("Language"), d.get("CitedYear"), d.get("Reference"), d.get("YinZheng"), d.get("GongYin"), d.get("TongBeiYin"), d.get("SecondYinZheng"), d.get("SecondReference"), d.get("TableName"), d.get("PublishTime"), d.get("YinZhengAmount"), d.get("SecondReferenceAmount"), d.get("SecondYinZhengAmount"), d.get("GongYinAmount"), d.get("TongBeiYinAmount"), d.get("Journal"), d.get("ISSN"), d.get("CN"), scoreDoc);
		return a;
	}
}

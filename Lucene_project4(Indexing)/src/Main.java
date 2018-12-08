import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.LMJelinekMercerSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
  
public class Main {
 
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		/*
		 * Indexing!!
		 */   
		int bufferSize = 20 * 1024 * 1024;  
		
		File file = new File("C:\\Users\\YeB\\Desktop\\CNKI_journal_957701522_605801097\\CNKI_journal_v2.txt");  
		FileInputStream fileInputStream = new FileInputStream(file);  
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);  
		InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);  
		BufferedReader input = new BufferedReader(inputStreamReader, bufferSize);  

		Analyzer analyzer = null;
		analyzer = new IKAnalyzer();
		
		String indexPath = "C:\\Users\\YeB\\Desktop\\lucene\\lucene_index_custom";
		File indexDir = new File(indexPath);
		Directory fsDir = FSDirectory.open(indexDir);
		Directory directory = new RAMDirectory(fsDir, null);
		Similarity similarity = new CustomSimilarity();
		
/*	Indexing!!	
 */	
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		config.setSimilarity(similarity);
		IndexWriter writer = new IndexWriter(directory, config);
		
		Document doc = new Document();

		String line = null;
        int docNum = 0;
		for(long lineCounter=0;(line = input.readLine()) != null; lineCounter++) {
        	if(line.equals("<REC>")) {
        		if(doc.getFields() != null) {
        			writer.addDocument(doc);
        			doc = new Document();
        		}
        		docNum += 1;
        		if(docNum % 100 == 0) {
        			System.out.println("docNum: "+docNum);
        		}
        	}else if(line.startsWith("<题名>")){
        		doc.add(new Field("Title", line.replace("<题名>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<英文篇名>")){
        		doc.add(new Field("EngTitle", line.replace("<英文篇名>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<作者>")){
        		doc.add(new Field("Author", line.replace("<作者>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<英文作者>")){
        		doc.add(new Field("EngAuthor", line.replace("<英文作者>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<第一责任人>")){
        		doc.add(new Field("FirstPerson", line.replace("<第一责任人>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<单位>")){
        		doc.add(new Field("Organization", line.replace("<单位>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<来源>")){
        		doc.add(new Field("Source", line.replace("<来源>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<出版单位>")){
        		doc.add(new Field("Publisher", line.replace("<出版单位>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<关键词>")){
        		doc.add(new Field("Keyword", line.replace("<关键词>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<英文关键词>")){
        		doc.add(new Field("EngKeyword", line.replace("<英文关键词>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<摘要>")){
        		doc.add(new Field("Summary", line.replace("<摘要>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<基金>")){
        		doc.add(new Field("Fund", line.replace("<基金>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<英文摘要>")){
        		doc.add(new Field("EngSummary", line.replace("<英文摘要>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<年>")){ 
        		doc.add(new Field("Year", line.replace("<年>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<期>")){
        		doc.add(new Field("Issue", line.replace("<期>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<专辑代码>")){
        		doc.add(new Field("AlbumCode", line.replace("<专辑代码>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<专题代码>")){
        		doc.add(new Field("ThemaCode", line.replace("<专题代码>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<专题子栏目代码>")){
        		doc.add(new Field("ThemaChildCode", line.replace("<专题子栏目代码>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<专题名称>")){
        		doc.add(new Field("ThemaName", line.replace("<专题名称>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<分类号>")){
        		doc.add(new Field("ClassNum", line.replace("<分类号>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<分类名称>")){
        		doc.add(new Field("ClassName", line.replace("<分类名称>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<文件名>")){
        		doc.add(new Field("File", line.replace("<文件名>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<语种>")){
        		doc.add(new Field("Language", line.replace("<语种>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<被引年>")){
        		doc.add(new Field("CitedYear", line.replace("<被引年>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<参考文献>")){
        		doc.add(new Field("Reference", line.replace("<参考文献>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<引证文献>")){
        		doc.add(new Field("YinZheng", line.replace("<引证文献>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<共引文献>")){
        		doc.add(new Field("GongYin", line.replace("<共引文献>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<同被引文献>")){
        		doc.add(new Field("TongBeiYin", line.replace("<同被引文献>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<二级引证文献>")){
        		doc.add(new Field("SecondYinZheng", line.replace("<二级引证文献>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<二级参考文献>")){
        		doc.add(new Field("SecondReference", line.replace("<二级参考文献>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<表名>")){
        		doc.add(new Field("TableName", line.replace("<表名>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<出版日期>")){
        		doc.add(new Field("PublishTime", line.replace("<出版日期>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<引证文献数量>")){
        		doc.add(new Field("YinZhengAmount", line.replace("<<引证文献数量>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<二级参考文献数量>")){
        		doc.add(new Field("SecondReferenceAmount", line.replace("<二级参考文献数量>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<二级引证文献数量>")){
        		doc.add(new Field("SecondYinZhengAmount", line.replace("<二级引证文献数量>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<共引文献数量>")){
        		doc.add(new Field("GongYinAmount", line.replace("<共引文献数量>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<同被引文献数量>")){
        		doc.add(new Field("TongBeiYinAmount", line.replace("<同被引文献数量>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<英文刊名>")){
        		doc.add(new Field("Journal", line.replace("<英文刊名>=", ""),Field.Store.YES, Field.Index.ANALYZED));
        	}else if(line.startsWith("<ISSN>")){
        		doc.add(new Field("ISSN", line.replace("<ISSN>=", ""),Field.Store.YES, Field.Index.NOT_ANALYZED));
        	}else if(line.startsWith("<CN>")){
        		doc.add(new Field("CN", line.replace("<CN>=", ""),Field.Store.YES, Field.Index.NOT_ANALYZED));
        	}
        }
		writer.addDocument(doc);
		writer.close();
       
		
		
		
		/*
		 *  Save Index.https://www.cnblogs.com/linjiqin/archive/2011/03/31/2001574.html 
		 */  
		IndexWriterConfig config2 = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		IndexWriter fsWriter = new IndexWriter(fsDir, config2);
		fsWriter.addIndexes(new Directory[] {directory});
		fsWriter.commit();
		fsWriter.close();
		
		analyzer.close();
		
		
		
		
		
		
		
		
		
		//Test Searching
		IndexReader indexReader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(indexReader);
		QueryParser parser = new QueryParser(Version.LUCENE_4_10_3, "Summary", new SimpleAnalyzer());
		//Term t = new Term("EngSummary", "many");
		//Query query = new TermQuery(t); 
		Query query = parser.parse("+本文 +国内 +实际");
		TopDocs docs = searcher.search(query, 10);
		
		for(int i = 0; i < 10; i++) {
			Document d = searcher.doc(docs.scoreDocs[i].doc);
			
			System.out.println(d.get("Title"));
			System.out.println(d.get("Summary"));
			System.out.println(d.get("EngSummary"));
		}
	}
}
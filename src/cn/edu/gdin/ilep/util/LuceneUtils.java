package cn.edu.gdin.ilep.util;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneUtils {

	private static Directory newsDirectory = null;
	
	private static Directory resourceDirectory = null;
	
	private static IndexWriterConfig config = null;

	private static Version matchVersion = null;

	private static Analyzer analyzer = null;

	static {

		try {
			newsDirectory = FSDirectory.open(new File(Constant.NEWS_INDEXURL));
			
			resourceDirectory = FSDirectory.open(new File(Constant.RESOURCE_INDEXURL));
			
			matchVersion = Version.LUCENE_44;

			analyzer = new IKAnalyzer(true);

			config = new IndexWriterConfig(matchVersion, analyzer);
			config.setOpenMode(OpenMode.CREATE_OR_APPEND);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private LuceneUtils(){}
	
	/**
	 * 获取文章的索引写入器
	 * @return
	 * @throws IOException
	 */
	public static IndexWriter getNewsIndexWriter() throws IOException {
		IndexWriter indexWriter = new IndexWriter(newsDirectory, config);
		return indexWriter;
	}
	/**
	 * 获取资源的所以写入器
	 * @return
	 * @throws IOException
	 */
	public static IndexWriter getResourceIndexWriter() throws IOException{
		return new IndexWriter(resourceDirectory, config);
	}

	/**
	 * 
	 * 获取文章的索引搜索入口
	 * 
	 * @return
	 * @throws IOException
	 */
	public static IndexSearcher getNewsIndexSearcher() throws IOException {

		IndexReader indexReader = DirectoryReader.open(newsDirectory);

		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		return indexSearcher;

	}
	/**
	 * 获取资源的索引搜索入口
	 * @return
	 * @throws IOException
	 */
	public static IndexSearcher getResourceIndexSearcher() throws IOException {
		return new IndexSearcher(DirectoryReader.open(resourceDirectory));
	}

	/**
	 * 
	 * 获取版本信息
	 * @return
	 */
	public static Version getMatchVersion() {
		return matchVersion;
	}

	/**
	 * 获取分词器--IKAnalyzer
	 * @return
	 */
	public static Analyzer getIKAnalyzer() {
		return analyzer;
	}

}

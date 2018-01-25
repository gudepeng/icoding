package top.icoding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author 我是金角大王
 * @date 2018年1月15日 下午1:43:42
 */
public class ReptileMain {
	public static final String REPTILE_WEB = "http://blog.csdn.net";
	public static final String REPTILE_URL = "/u014459326/article/list/1";

	public static void main(String[] args) throws IOException {
		ReptileMain reptile=new ReptileMain();
		
		Element body = reptile.getBody(REPTILE_WEB+REPTILE_URL);
		//将爬取出来的文章封装到Artcle中，并放到ArrayList里面去
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        
        Element articleListDiv = body.select(".blog-units-box").first();
        Elements articleList = articleListDiv.select("li");
        for(Element article:articleList){
        	Map<String, Object> map = new HashMap();
        	Element url=article.select("a").first();
        	Element title=article.select("h3").first();
        	Element summary=article.select("p").first();
        	Element createTime=article.select(".unit-control .left-dis-24").first();
        	
        	Element articleBody = reptile.getBody(REPTILE_WEB+url.attr("href"));
        	Element article_content = articleBody.getElementById("article_content");
        	
        	map.put("url", REPTILE_WEB+url.attr("href"));
        	map.put("title", title.text().replace("置顶", ""));
        	map.put("summary", summary.text());
        	map.put("createTime", createTime.text());
        	map.put("content", article_content.text());
        	resultList.add(map);
        }
        
        for(Map<String,Object> map:resultList){
        	System.out.println("url："+map.get("url"));
        	System.out.println("标题："+map.get("title"));
        	System.out.println("简介："+map.get("summary"));
        	System.out.println("创建时间："+map.get("createTime"));
        	System.out.println("文章内容："+map.get("content"));
        }
        
	}
	
	public Element getBody(String url) throws IOException{
		Connection urlConnection = Jsoup.connect(url) // 博客首页的url地址
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:47.0) Gecko/20100101 Firefox/47.0") // http请求的浏览器设置
				.timeout(5000) // http连接时长
				.method(Connection.Method.GET); // 请求类型是get请求，http请求还是post,delete等方式

		// 获取页面的html文档
		Document doc = urlConnection.get();
		return doc.body();
	}
}

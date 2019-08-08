package club.sdll.ptc.spring.controller;

import java.io.IOException;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import club.sdll.ptc.spring.pojo.Province;
import club.sdll.ptc.spring.service.ICourseService;
import club.sdll.ptc.spring.service.impl.CourseServiceImpl;
import club.sdll.ptc.spring.util.DBFactory;

public class LagouController {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:conf/applicationContext-spring.xml");
		ICourseService courseService = (ICourseService) ac.getBean("courseService");
		String url = "https://www.lagou.com/jobs/list_java?px=default&gj=3%E5%B9%B4%E5%8F%8A%E4%BB%A5%E4%B8%8B&city=%E6%AD%A6%E6%B1%89#filterBox";
		url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2015/index.html";
		Document doc = getDocumentOfProvinceByURL(url);
		System.out.println(doc);
		Elements trs = doc.select(".provincetr td a");
		int i = 1;
		Province province = null;
		for (Element element : trs) {
			String provinceName = element.text();
			String proHref = element.attr("href");
			province = new Province();
			province.setId(UUID.randomUUID().toString().replace("-", ""));
			province.setProvinceName(provinceName);
			if(provinceName.indexOf("自治区") == -1) {
				province.setType("1");
			}else {
				province.setType("2");
			}
			courseService.saveProvince(province);
			System.out.println("i = " + i++ + " || " + provinceName + " || href = " + proHref);
			int endIndex = url.lastIndexOf("/");
			String cityHref = url.substring(0, endIndex + 1) + proHref;
			//System.out.println("cityHref = " + cityHref);
			Document citydoc = getDocumentOfCityByUR(cityHref);
			//System.out.println(citydoc);
			Elements citys = citydoc.select(".citytr");
			for (Element elem : citys) {
			/* <tr class="citytr">
                 <td><a href="13/1308.html">130800000000</a></td>
                 <td><a href="13/1308.html">承德市</a></td>
                </tr>*/
				Elements cityCodeAndNames = elem.select("td");
				String cityCode = cityCodeAndNames.get(0).text();
				String cityName = cityCodeAndNames.get(1).text();
				String districtHref = cityCodeAndNames.get(0).select("a").attr("href");
				String countyUrl = url.substring(0, endIndex + 1) + districtHref;
				System.out.println("cityCode = " + cityCode + " || cityName = " + cityName + "  || href = " + districtHref);
				Document docCounty = Jsoup.connect(countyUrl).get();
				Elements countys = docCounty.select(".countytr"); 
//				for (Element county : countys) {
//					Elements countCodeAndNames = elem.select("td");
//					String countyCode = countCodeAndNames.get(0).text();
//					String countyName = countCodeAndNames.get(1).text();
//					String countyHref = countCodeAndNames.get(0).select("a").attr("href");
//					System.out.println("countyCode = " + countyCode + " || countyName = " + countyName + "  || countyHref = " + countyHref);
//				}
			}
		}
		//DBFactory.getBean("");
		

	}
	
	/**
	 * 获取 省份 province 的html文档doc文档对象
	 * @createUser shandianlala
	 * @createDate 2017年10月26日
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Document getDocumentOfProvinceByURL(String url) throws IOException {
		Document doc = Jsoup.connect(url).cookie("15697130000", "shandianlala").post();
		return doc;
	}
	
	/**
	 * 获取 城市 city 的html文档doc文档对象
	 * @createUser shandianlala
	 * @createDate 2017年10月26日
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Document getDocumentOfCityByUR(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		return doc;
	}

}

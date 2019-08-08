package club.sdll.ptc.imageocr.download;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * 下载图片
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年08月15日 16:00
 */
public class DownLoadImage {

    public static void downloadImageCodeOfJiaoTongBank (String imageName) {
        try {
            System.out.println("==== 图片 "+ imageName +" 下载开始 ====");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = "https://creditcardapp.bankcomm.com/member/creimg?v=" + Math.random();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader(new BasicHeader("Host", "creditcardapp.bankcomm.com"));
            httpGet.setHeader(new BasicHeader("Referer", "https://creditcardapp.bankcomm.com/member/apply/status/inquiry.html"));
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
//            String imageName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
            File file = new File("/Users/chengxiwang/IdeaProjects/learn/imageocr/images/" + imageName);

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte[] bytes = new byte[1024];
            int tempByte = 0;
            while ((tempByte = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes);
            }
            fileOutputStream.flush();
            inputStream.close();
            httpResponse.close();
            httpClient.close();
            System.out.println("==== 图片 "+ imageName + " 下载完毕 ====");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {

            downloadImageCodeOfJiaoTongBank(i + "b.jpeg");
            Thread.sleep(1000);
        }
    }

}

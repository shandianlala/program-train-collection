package club.sdll.ptc.imageocr.download;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年08月17日 11:05
 */
public class Test {

    public static String DOWNLOAD_DIR = "/Users/chengxiwang/IdeaProjects/learn/imageocr/images/";

    // 1.下载验证码：将多个验证码图片下载到指定目录，要求各种可能的验证码（单个数字）都应该有，比如：0-9。
    private static void downloadImage() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        for (int i = 0; i < 10; i++) {
            String url = "http://www.yoursite.com/yz.php";
            HttpGet getMethod = new HttpGet(url);
            try {
                HttpResponse response = httpClient.execute(getMethod, new BasicHttpContext());
                HttpEntity entity = response.getEntity();
                InputStream instream = entity.getContent();
                OutputStream outstream = new FileOutputStream(new File(DOWNLOAD_DIR, i + ".png"));
                int l = -1;
                byte[] tmp = new byte[2048];
                while ((l = instream.read(tmp)) != -1) {
                    outstream.write(tmp);
                }
                outstream.close();
            } finally {
                getMethod.releaseConnection();
            }
        }

        System.out.println("下载验证码完毕！");
    }


    public static void main(String[] args) {


    }


}

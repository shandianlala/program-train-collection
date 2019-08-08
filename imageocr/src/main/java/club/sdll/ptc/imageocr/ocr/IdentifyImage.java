package club.sdll.ptc.imageocr.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 识别图片
 * 参考：https://blog.csdn.net/qq_38113432/article/details/79363963
 * @author chengxiwang
 * @version v0.1
 * @data 2018年08月15日 16:03
 */
public class IdentifyImage {


    public static void main(String[] args) {
        System.out.println("==== 图片识别开始 ====");
        String imagePath = "/Users/chengxiwang/IdeaProjects/learn/imageocr/images/1.jpg";
        File fileDir = new File("/Users/chengxiwang/IdeaProjects/learn/imageocr/images/");
        if (fileDir.isDirectory()) {
            File[] listFile = fileDir.listFiles();
            for (File file: listFile) {
                System.out.println("fileName = " + file.getName());
                identifyImage(file.getAbsolutePath());
            }
        }
        System.out.println("==== 图片识别结束 ====");

    }


    /**
     * 识别图片
     * @param imagePath 图片路径
     * @return
     */
    public static String identifyImage(String imagePath) {
        try {
            File file = new File(imagePath);
            ITesseract iTesseract = new Tesseract();

            String result = iTesseract.doOCR(file);
            System.out.println("result = " + result);

        } catch (Exception e) {
            if (e instanceof IIOException) {
                System.out.println("读取失败：" + e.getMessage());
            } else {
                e.printStackTrace();
            }
        }


        return "";
    }


}

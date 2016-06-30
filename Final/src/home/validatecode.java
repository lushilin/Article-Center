package home;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
 
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class validatecode extends HttpServlet {
       //将来存放验证码值的session的key值
       public static final String VALIDATE_CODE_KEY = "ValidateCode";
 
       //请求servlet的时的被调方法
       public void doGet(HttpServletRequest request, HttpServletResponse response)
                     throws ServletException, IOException {
 
//设置文档的内容类型为jpeg类型，
//从而在浏览的时候告知浏览器这是一个图片类型
response.setContentType("image/jpeg");
              //生成验证码，保存在session中
              HttpSession session = request.getSession();
             
//调用下面的generateString方法，
//生成一个4位长的每位为0-9的随机字符串
 
              String code = generateString();
              session.setAttribute(VALIDATE_CODE_KEY, code);
 
              //生出内存图片
              BufferedImage image = generateImage(code);
 
              //输出图片
              outputImage(image, response.getOutputStream());
       }
 
 
       //往页面输出的方法
       private void outputImage(BufferedImage image, ServletOutputStream out)
                     throws IOException {
              ImageWriter writer = null;
              //下面进行对图片格式的一些修改
              ImageTypeSpecifier type =
ImageTypeSpecifier.createFromRenderedImage(image);
              Iterator iter = ImageIO.getImageWriters(type, "jpg");
              if (iter.hasNext()) {
                     writer = (ImageWriter) iter.next();
              }
             
              IIOImage iioImage = new IIOImage(image, null, null);
              ImageWriteParam param = writer.getDefaultWriteParam();
 
              param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
              //控制图片质量，1.0最高
              param.setCompressionQuality(1.0F);
              //创建输出流
              ImageOutputStream outputStream = ImageIO.createImageOutputStream(out);
              //将构建好的图片输出流写入到页面中
              writer.setOutput(outputStream);
              writer.write(null, iioImage, param);
       }
 
      
       //用来生成随机字符串的方法
private String generateString() {
              int a = (int) (Math.random() * 10);
              int b = (int) (Math.random() * 10);
              int c = (int) (Math.random() * 10);
              int d = (int) (Math.random() * 10);
              return "" + a + b + c + d;
       }
      
       //生成图片的方法
       private BufferedImage generateImage(String code) {
              //设置图片信息，宽，高，具有 8 位 RGB 颜色分量的图像
              BufferedImage image = new BufferedImage(100, 30,
                            BufferedImage.TYPE_INT_RGB);
              //得到画笔
              Graphics g = image.getGraphics();
              //产生背景图片
              g.setColor(Color.white);
              //画一个矩形框
              g.fillRect(1, 1, 98, 28);
              //添加一些干扰的线条
              for (int i = 0; i < 20; i++) {
                     g.setColor(generateColor());
                     int x1 = (int) (Math.random() * 100);
                     int y1 = (int) (Math.random() * 30);
                     int x2 = (int) (Math.random() * 100);
                     int y2 = (int) (Math.random() * 30);
                     g.drawLine(x1, y1, x2, y2);
              }
              //画数字
              //为了得到不同效果的随机字符串，这里采用一个一个字符串的画。
              //这样可以使其颜色或者其他信息有所不同
 
              g.setFont(new Font("IMPACT", Font.PLAIN,
                            20 + (int) (Math.random() * 10)));
              g.setColor(generateColor());
              g.drawString(code.charAt(0) + "", 5, 28);
 
              g.setFont(new Font("IMPACT", Font.PLAIN,
                            20 + (int) (Math.random() * 10)));
              g.setColor(generateColor());
              g.drawString(code.charAt(1) + "", 30, 28);
 
              g.setFont(new Font("IMPACT", Font.PLAIN,
                            20 + (int) (Math.random() * 10)));
              g.setColor(generateColor());
              g.drawString(code.charAt(2) + "", 55, 28);
 
              g.setFont(new Font("IMPACT", Font.PLAIN,
                            20 + (int) (Math.random() * 10)));
              g.setColor(generateColor());
              g.drawString(code.charAt(3) + "", 80, 28);
 
              return image;//返回制作好的图像
       }
      
       //生成随机的颜色
       private Color generateColor() {
              int r = (int) (Math.random() * 180);
              int g = (int) (Math.random() * 180);
              int b = (int) (Math.random() * 180);
              return new Color(r, g, b);
       }
}
package com.exam.demo.utils;

import com.exam.demo.model.MyX509TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * HTTP工具类
 * @author Administrator
 *
 */
public class Http_Utils {

    static CloseableHttpClient httpclient = HttpClients.createDefault();
    static String CHARSET = "UTF-8";

    /**
     * 发送
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    public static String  npost(String url, Map<String, String> param) throws IOException {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        //拼接参数
        List <NameValuePair> nvps = new ArrayList();
        for (String key : param.keySet()) {
            nvps.add(new BasicNameValuePair(key, param.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps,CHARSET));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            HttpEntity entity = response.getEntity();
             result = EntityUtils.toString(entity, CHARSET);
             response.close();
            EntityUtils.consume(entity);
        } catch (Exception e) {
//            log.error(">>>>post error"+e.getMessage());
        } finally {
            response.close();
        }
        return result;
    }

    /**
     * get
     * @param url
     * @return
     * @throws IOException
     */
    public static String  nget(String url) throws IOException {
        String result = null;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, CHARSET);
            response.close();
            EntityUtils.consume(entity);
        } catch (Exception e) {
//            log.error(">>>>get error"+e.getMessage());
        } finally {
            response.close();
        }
        return result;
    }

    public static String post(String url, Map<String, String> params) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

//        log.info("create httppost:" + url);
        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    public static String get(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

//        log.info("create httppost:" + url);
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);

        httpclient.getConnectionManager().shutdown();

        return body;
    }


    private static String invoke(DefaultHttpClient httpclient,
                                 HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static String paseResponse(HttpResponse response) {
//        log.info("get response from http server..");
        HttpEntity entity = response.getEntity();

//        log.info("response status: " + response.getStatusLine());
        String charset = EntityUtils.getContentCharSet(entity);
//        log.info(charset);

        String body = null;
        try {
            body = EntityUtils.toString(entity);
//            log.debug(body);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static HttpResponse sendRequest(@SuppressWarnings("deprecation") DefaultHttpClient httpclient,
                                            HttpUriRequest httpost) {
//        log.info("execute post...");
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> params){

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();

        Set<String> keySet = params.keySet();
        for(String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
//            log.info("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }

    /**
     * 转换字节数组
     * @param content
     * @param charset
     * @return
     * @throws Exception
     */
    public static byte[] getContentBytes(String content, String charset) throws Exception {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 建立实际的连接
            conn.connect();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = conn.getHeaderFields();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 处理https GET请求
     * 请求地址
     * @param url
     * @return
     */
    public static String httpsPost (String url) {
        return httpsRequest(url,"POST",null);
    }
    /**
     * 处理https GET请求
     * 请求地址
     * @param url
     * @return
     */
    public static String httpsGet (String url) {
        return httpsRequest(url,"GET",null);
    }

    /**
     * 处理https GET/POST请求
     * 请求地址、请求方法、参数
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static String httpsRequest(String requestUrl,String requestMethod,String outputStr){
        StringBuffer buffer=null;
        InputStream is = null;
        OutputStream os = null;
        try{
            //创建SSLContext
            SSLContext sslContext=SSLContext.getInstance("SSL");
            TrustManager[] tm={new MyX509TrustManager()};
            //初始化
            sslContext.init(null, tm, new java.security.SecureRandom());;
            //获取SSLSocketFactory对象
            SSLSocketFactory ssf=sslContext.getSocketFactory();
            URL url=new URL(requestUrl);
            HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            //往服务器端写内容
            if(null!=outputStr){
                os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            is =conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (os!=null) {
                    os.close();
                }
                if (is!=null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}

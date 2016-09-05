//package com.hydra.tool.web.http;
//
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.params.CoreConnectionPNames;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class HttpUtil {
//
//    private static Logger LOG = LoggerFactory.getLogger(HttpUtil.class);
//
//    public static String getRequest(String url) throws Exception {
//        LOG.info("get " + url);
//        HttpClient httpClient = new DefaultHttpClient();
//        httpClient.getParams().setIntParameter(
//                CoreConnectionPNames.CONNECTION_TIMEOUT, 3500);
//        httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
//        HttpGet get = new HttpGet(url);
//        HttpResponse httpResponse = httpClient.execute(get);
//        if (httpResponse.getStatusLine().getStatusCode() == 200) {
//            return EntityUtils.toString(httpResponse.getEntity());
//        }
//        return null;
//    }
//
//    public static String postRequest(String url, Map<String, String> rawParams)
//            throws Exception {
//
//        HttpClient httpClient = new DefaultHttpClient();
//        httpClient.getParams().setIntParameter(
//                CoreConnectionPNames.CONNECTION_TIMEOUT, 3500);
//        httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
//
//        HttpPost post = new HttpPost(url);
//        LOG.info(url);
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        for (String key : rawParams.keySet()) {
//            params.add(new BasicNameValuePair(key, rawParams.get(key)));
//        }
//        post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//        HttpResponse httpResponse = httpClient.execute(post);
//        if (httpResponse.getStatusLine().getStatusCode() == 200) {
//            String result = EntityUtils.toString(httpResponse.getEntity());
//            return result;
//        }
//        return null;
//    }
//
//    public static String postRequest(String url, Map<String, String> rawParams, Map<String, String> headerParams)
//            throws Exception {
//
//        HttpClient httpClient = new DefaultHttpClient();
//        httpClient.getParams().setIntParameter(
//                CoreConnectionPNames.CONNECTION_TIMEOUT, 3500);
//        httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
//
//        HttpPost post = new HttpPost(url);
//        for (Map.Entry<String, String> entry : headerParams.entrySet()) {
//            post.addHeader(entry.getKey(), entry.getValue());
//        }
//        System.out.println(url);
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        for (String key : rawParams.keySet()) {
//            params.add(new BasicNameValuePair(key, rawParams.get(key)));
//        }
//        post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//        HttpResponse httpResponse = httpClient.execute(post);
//        if (httpResponse.getStatusLine().getStatusCode() == 200) {
//            return decode(EntityUtils.toString(httpResponse.getEntity()));
//        }
//        return null;
//    }
//
//    public static String postRequest(String url, Map<String, String> rawParams, Map<String, String> headerParams, Map<String, File> fileParams)
//            throws Exception {
//        HttpPost request = new HttpPost(url);
//
//        HttpClient httpClient = new DefaultHttpClient();
//        MultipartEntity entity = new MultipartEntity();
//
//        for (Map.Entry<String, String> entry : rawParams.entrySet()) {
//            entity.addPart(entry.getKey(), new StringBody(entry.getValue(), Charset.forName("UTF-8")));
//        }
//
//        for (Map.Entry<String, String> entry : headerParams.entrySet()) {
//            request.addHeader(entry.getKey(), entry.getValue());
//        }
//
//        for (Map.Entry<String, File> entry : fileParams.entrySet()) {
//            entity.addPart(entry.getKey(), new FileBody(entry.getValue()));
//        }
//
//        request.setEntity(entity);
//
//        HttpResponse response = httpClient.execute(request);
//        if (response.getStatusLine().getStatusCode() == 200) {
//            String result = EntityUtils.toString(response.getEntity());
//            LOG.info(result);
//            return result;
//        }
//
//        return null;
//    }
//
//    static final Pattern reUnicode = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");
//
//    public static String decode(String s) {
//        Matcher m = reUnicode.matcher(s);
//        StringBuffer sb = new StringBuffer(s.length());
//        while (m.find()) {
//            m.appendReplacement(sb,
//                    Character.toString((char) Integer.parseInt(m.group(1), 16)));
//        }
//        m.appendTail(sb);
//        return sb.toString();
//    }
//}

//package com.hydra.tool.web.http;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.collect.Maps;
//import com.hydra.tool.string.StrUtil;
//import org.apache.log4j.Logger;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by GongZheng on 15/6/13 下午8:41.
// * Describe: 图片上传组件
// */
//public class PictureUploadUtil {
//    private static final Logger LOG = Logger.getLogger(PictureUploadUtil.class);
//    private static final String TOKEN = "58eade1e6c921fa06e739c01564e5ca652954cef:SzVWZ3J6WHZ4eUNQXzJ6NGF5eVdVWnBEVkk0PQ==:eyJkZWFkbGluZSI6MTQzNDIzOTYzNywiYWN0aW9uIjoiZ2V0IiwidWlkIjoiNTA3MTIyIiwiYWlkIjoiMTA1ODM3MiJ9";
//
//    public static Picture uploadPicture(File file) {
//        Map<String, String> params = Maps.newHashMap();
//        params.put("Token", TOKEN);
//
//        Map<String, File> fileParams = Maps.newHashMap();
//        fileParams.put("file", file);
//
//        Picture picture = null;
//
//        try {
//            String result = HttpUtil.postRequest("http://up.tietuku.com", params, new HashMap<String, String>(), fileParams);
//
//            if (StrUtil.isNotBlank(result)) {
//                picture = JSONObject.parseObject(result, Picture.class);
//            }
//        } catch (Exception e) {
//            LOG.error("upload picture error", e);
//        }
//
//        return picture;
//    }
//
//    public static class Picture {
//        private int width;
//        private int height;
//        private String type;
//        private int size;
//        private String ubburl;
//        private String linkurl;
//        private String htmlurl;
//        private String s_url;
//        private String t_url;
//
//        public int getWidth() {
//            return width;
//        }
//
//        public void setWidth(int width) {
//            this.width = width;
//        }
//
//        public int getHeight() {
//            return height;
//        }
//
//        public void setHeight(int height) {
//            this.height = height;
//        }
//
//        public String getType() {
//            return type;
//        }
//
//        public void setType(String type) {
//            this.type = type;
//        }
//
//        public int getSize() {
//            return size;
//        }
//
//        public void setSize(int size) {
//            this.size = size;
//        }
//
//        public String getUbburl() {
//            return ubburl;
//        }
//
//        public void setUbburl(String ubburl) {
//            this.ubburl = ubburl;
//        }
//
//        public String getLinkurl() {
//            return linkurl;
//        }
//
//        public void setLinkurl(String linkurl) {
//            this.linkurl = linkurl;
//        }
//
//        public String getHtmlurl() {
//            return htmlurl;
//        }
//
//        public void setHtmlurl(String htmlurl) {
//            this.htmlurl = htmlurl;
//        }
//
//        public String getS_url() {
//            return s_url;
//        }
//
//        public void setS_url(String s_url) {
//            this.s_url = s_url;
//        }
//
//        public String getT_url() {
//            return t_url;
//        }
//
//        public void setT_url(String t_url) {
//            this.t_url = t_url;
//        }
//
//        @Override
//        public String toString() {
//            return "Picture{" +
//                    "width=" + width +
//                    ", height=" + height +
//                    ", type='" + type + '\'' +
//                    ", size=" + size +
//                    ", ubburl='" + ubburl + '\'' +
//                    ", linkurl='" + linkurl + '\'' +
//                    ", htmlurl='" + htmlurl + '\'' +
//                    ", s_url='" + s_url + '\'' +
//                    ", t_url='" + t_url + '\'' +
//                    '}';
//        }
//    }
//}

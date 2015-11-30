package com.canglong.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.springframework.util.Base64Utils;

public final class HttpUtils {
    public static int getResponseCode(String url) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpclient.execute(httpGet);
            return response.getStatusLine().getStatusCode();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    public static String getRequestBody(HttpServletRequest request, String charsetName) throws ServletException {
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = requestWrapper.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charsetName));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw new ServletException("Error reading the request payload", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException iox) {
                    // ignore
                }
            }
        }
        return stringBuilder.toString();
    }

    public static HttpResponse sendDelete(String url) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            return httpclient.execute(httpDelete);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    public static HttpResponse sendDelete(String url, String username, String password) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            String s = username + ":" + password;
            String basicHeader = "Basic " + Base64.encodeBase64String(s.getBytes());
            httpDelete.setHeader(HttpHeaders.AUTHORIZATION, basicHeader);
            return httpclient.execute(httpDelete);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    public static boolean isMultiPartRequest(HttpServletRequest request) {
        return request.getHeader(HTTP.CONTENT_TYPE) != null && request.getHeader(HTTP.CONTENT_TYPE).contains("multipart/form-data");
    }

    public static String encodeUTF8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            return str;
        }
    }

    public static String decodeUTF8(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            return str;
        }
    }
}

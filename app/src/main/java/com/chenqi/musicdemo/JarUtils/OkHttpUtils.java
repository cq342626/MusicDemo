package com.chenqi.musicdemo.JarUtils;


import android.annotation.SuppressLint;
import android.os.Handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@SuppressLint("DefaultLocale")
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    private Handler mHandler = new Handler();
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private OkHttpUtils() {
        okhttp3.OkHttpClient.Builder ClientBuilder = new okhttp3.OkHttpClient.Builder();
        ClientBuilder.readTimeout(30, TimeUnit.SECONDS);// 读取超时
        ClientBuilder.connectTimeout(10, TimeUnit.SECONDS);// 连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);// 写入超时
        /**
         * 构建OkHttpClient
         */
        okHttpClient = ClientBuilder.build();
    }

    public static OkHttpUtils getInstance() {
        if (null == okHttpUtils) {
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }

    /**
     * 构造get 普通请求
     *
     * @param url
     *            请求的url
     * @param callBack
     *            上传回调
     */
    public void getRequest(String url, final DataCallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        deliveryResult(request, callBack);
    }


    /**
     * 构造post 普通请求
     *
     * @param url        请求的url
     * @param BodyParams 请求参数
     * @param callBack   上传回调
     */
    public void postRequest(String url, Map<String, String> BodyParams, final DataCallBack callBack) {
        Request request = new Request.Builder().url(url).post(SetRequestBody(BodyParams)).build();
        deliveryResult(request, callBack);
    }

    /**
     * 构造post 上传文件
     *
     * @param url           上传url
     * @param file          上传的文件的本地路径
     * @param headersParams 上传头部信息
     * @param BodyParams    上传参数
     * @param callBack      上传回调
     */
    public void postRequestUpLoadFile(String url, String file, Map<String, String> headersParams,
                                      Map<String, String> BodyParams, final DataCallBack callBack) {

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        builder.addPart(SetHeaders(headersParams), getRequestBody(file));//上传的头部信息和文件信息

        for (String key : BodyParams.keySet()) {
            builder.addFormDataPart(key, BodyParams.get(key));//上传的参数
        }

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder().url(url).post(requestBody).build();
        deliveryResult(request, callBack);
    }

    /**
     * 处理请求结果的回调
     *
     * @param request
     */
    private void deliveryResult(final Request request, final DataCallBack callBack) {
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String success = response.body().string();
                deliverDataSuccess(success, callBack);
            }
        });

    }

    /**
     * 设置请求头
     *
     * @param headersParams
     * @return
     */
    private Headers SetHeaders(Map<String, String> headersParams) {
        okhttp3.Headers.Builder headersbuilder = new okhttp3.Headers.Builder();
        if (headersParams != null) {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                headersbuilder.add("Content-Disposition", "form-data; name=\"" + key + "\"; fileName=\"" + headersParams.get(key) + "\"");
            }
        }
        return headersbuilder.build();
    }

    /**
     * post请求参数
     *
     * @param BodyParams
     * @return
     */
    private RequestBody SetRequestBody(Map<String, String> BodyParams) {
        okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
        if (BodyParams != null) {
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, BodyParams.get(key));
            }
        }
        return formEncodingBuilder.build();

    }

    /**
     * 表单中进行文件上传时，就需要使用该格式
     *
     * @param file
     */

    public RequestBody getRequestBody(String file) {
        String type = file.substring(file.lastIndexOf(".") + 1).toUpperCase();
        String fileType = "application/octet-stream";
        if (type.equals("PNG")) {
            fileType = "image/png";
        } else if (type.equals("JPG") || type.equals("JPEG")) {
            fileType = "image/jpeg";
        } else if (type.equals("GIF")) {
            fileType = "image/gif";
        }
        return RequestBody.create(MediaType.parse(fileType), new File(file));
    }


    /**
     * 下载文件
     *
     * @param url
     * @param desDir
     * @param callBack
     */
    public void getDownLoad(final String url, final String desDir, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buffer = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    File file = null;
                    if (!desDir.endsWith("/") && desDir.contains("."))
                        file = new File(desDir);
                    else
                        file = getReallyUrlFileName(desDir, url);
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                    fos.flush();
                    deliverDataSuccess(file.getAbsolutePath(), callBack);
                } catch (Exception e) {
                    deliverDataFailure(request, e, callBack);
                    e.printStackTrace();
                } finally {
                    if (is != null)
                        is.close();
                    if (fos != null)
                        fos.close();
                }
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }
        });
    }

    /**
     * 分发成功的时候调用
     *
     * @param result
     * @param callBack
     */
    private void deliverDataSuccess(final String result, final DataCallBack callBack) {
        /**
         * 在这里使用异步线程处理
         */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.requestSuccess(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 分发失败的时候调用
     *
     * @param request
     * @param e
     * @param callBack
     */
    private void deliverDataFailure(final Request request, final Exception e, final DataCallBack callBack) {
        /**
         * 在这里使用异步处理
         */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestFailure(request, e);
                }
            }
        });
    }

    /**
     * 数据回调接口
     */
    public interface DataCallBack {
        // 请求成功
        void requestSuccess(String result) throws Exception;

        // 请求失败
        void requestFailure(Request request, Exception e);
    }

    /**
     * 获取完整的文件名
     */
    public File getReallyUrlFileName(String desDir, String url) {
        File file = new File(desDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file_jar = new File(file, getReallyFileName(url));
        if (!file_jar.exists()) {
            try {
                file_jar.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file_jar;
    }

    /**
     * 从下载的url地址中获取文件名
     */
    public String getReallyFileName(String url) {
        String filename = "";
        URL myURL;
        HttpURLConnection conn = null;
        if (url == null || url.length() < 1) {
            return null;
        }
        try {
            myURL = new URL(url);
            conn = (HttpURLConnection) myURL.openConnection();
            conn.connect();
            conn.getResponseCode();
            URL absUrl = conn.getURL();// 获得真实Url
            // 通过Content-Disposition获取文件名，这点跟服务器有关，需要灵活变通
            filename = conn.getHeaderField("Content-Disposition");
            if (filename == null || filename.length() < 1) {
                filename = conn.getHeaderField("response-content-disposition");
            }
            if (filename == null || filename.length() < 1) {
                filename = absUrl.getFile();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        String[] sss = filename.split(";");
        if (sss != null) {
            for (String a : sss) {
                String b = a.toUpperCase();
                if (b.startsWith("FILENAME")) {
                    filename = a.substring(a.lastIndexOf("=") + 1);
                }
            }
        }
        filename = filename.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        try {
            filename = URLDecoder.decode(filename, "UTF-8");
            if (filename.contains("\""))
                filename = filename.replaceAll("\"", "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return filename;
    }
}
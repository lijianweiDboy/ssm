package com.zhgd.shenzhenmetro.vehicle.platform;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SignaturePolicys {

    public static void main(String[] args) {
        String accessId = "2c1Uu9YbS9ITZcS9yF33";
        String secretKey = "LZH6SEaw5Q7tDAsqX5zzF2GBPb0sp6O9TqPdnUSZ";
        String method = "post";
        String contentType = "application/json;charset=UTF-8";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDate = sdf.format(new Date());
        String api = "http://zhgd-test.ccccltd.cn/api/dw/labor/teams/v2/";
        String authorizationStr = SignaturePolicys.generalAuthorization(accessId, secretKey, method, contentType, strDate, api);
        System.out.println(authorizationStr);
        JSONObject object = JSONUtil.createObj();
        JSONObject objectModel = JSONUtil.createObj();
        objectModel.put("id", "123456");
        objectModel.put("name", "xxx劳务队");
        objectModel.put("isRemoved", false);
        object.put("data", Arrays.asList(objectModel));
        object.put("orgId",656922130202672L);
        object.put("systemKey","jiuxiang-labor-teams");
        System.err.println("object =    " +object);
        HttpResponse httpRequestLiftRealTime = HttpRequest.post(api).
                header("Authorization", authorizationStr).
                header("Content-Type", "application/json;charset=UTF-8").
                header("Date", strDate)
                .body(object.toString()).execute();
        String resBodyRealTime = httpRequestLiftRealTime.body();
        System.out.println(resBodyRealTime);
    }

    public static HttpRequest getPlatformHttpRequest(String api){
            String accessId = "2c1Uu9YbS9ITZcS9yF33";
            String secretKey = "LZH6SEaw5Q7tDAsqX5zzF2GBPb0sp6O9TqPdnUSZ";
            String method = "post";
            String contentType = "application/json;charset=UTF-8";
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String strDate = sdf.format(new Date());
            String authorizationStr = SignaturePolicys.generalAuthorization(accessId, secretKey, method, contentType, strDate, api);
            HttpRequest httpRequest = HttpRequest.post(api).
                    header("Authorization", authorizationStr).
                    header("Content-Type", "application/json;charset=UTF-8").
                    header("Date", strDate);
            return httpRequest;
    }


    public static String generalAuthorization(String accessId, String secretKey, String method, String contentType, String strDate, String api) {
        try {
            String[] splitUrl = api.split("\\?");
            String sortedQueryString = "";
            if (splitUrl.length > 1) {
                sortedQueryString = sortQueryParam2ASC(splitUrl[1]);
            }

            StringBuilder policy = new StringBuilder(method.toUpperCase());
            policy.append("\n");
            if (null != contentType && contentType.length() > 0) {
                policy.append(contentType).append("\n");
            }

            policy.append(strDate).append("\n").append(splitUrl[0]).append(sortedQueryString);
            String signature = generalSignature(secretKey, policy.toString());
            return "IWOP " + accessId + ":" + signature;
        } catch (Throwable var11) {
            throw var11;
        }
    }

    public static String getGMTNowDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(new Date());
    }

    private static String generalSignature(String secretKey, String content) {
        byte[] data = new byte[0];
        try {
            data = encryptHmacSha1(secretKey, content);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return (new BASE64Encoder()).encode(data);
    }

    private static byte[] encryptHmacSha1(String secretKey, String content) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey sk = new SecretKeySpec(StrUtil.bytes(secretKey), "HmacSHA1");
        Mac mac = Mac.getInstance(sk.getAlgorithm());
        mac.init(sk);
        return mac.doFinal(StrUtil.bytes(content));
    }

    private static String sortQueryParam2ASC(String queryString) {
        StringBuilder sortedQuerySB = new StringBuilder("?");
        List<String> paramList = new ArrayList();
        paramList.addAll(Arrays.asList(queryString.split("&")));
        Collections.sort(paramList);

        for (int i = 0; i < paramList.size(); ++i) {
            String paramItem = (String) paramList.get(i);
            sortedQuerySB.append(encodeQueryParamValue(paramItem));
            if (i < paramList.size() - 1) {
                sortedQuerySB.append("&");
            }
        }

        return sortedQuerySB.toString();
    }

    private static String encodeQueryParamValue(String paramItem) {
        StringBuilder encodeSB = new StringBuilder();
        String[] array = paramItem.split("=");
        encodeSB.append(array[0]);
        if (array.length > 1) {
            encodeSB.append("=");
            try {
                encodeSB.append(URLEncoder.encode(array[1], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return encodeSB.toString();
    }
}

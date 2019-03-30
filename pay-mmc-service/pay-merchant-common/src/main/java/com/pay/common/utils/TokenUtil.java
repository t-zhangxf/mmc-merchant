package com.pay.common.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.pay.common.enums.TokenState;
import net.minidev.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    /**
     * 秘钥
     */
    private static final byte[] SECRET="qxfhtghtuda@163fac04467df11fff26d".getBytes();
    /**
     * 初始化head部分的数据为
     * {
     *         "alg":"HS256",
     *         "type":"JWT"
     * }
     */
    private static final JWSHeader header=new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);

    /**
     * 生成token，该方法只在用户登录成功后调用
     *
     * //@param Map集合，可以存储用户id，token生成时间，token过期时间等自定义字段
     * @return token字符串,若失败则返回null
     */
    public static String createToken(Map<String, Object> payload) {
        String tokenString=null;
        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
        try {
            // 将jwsObject 进行HMAC签名
            jwsObject.sign(new MACSigner(SECRET));
            tokenString=jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("签名失败:" + e.getMessage());
            e.printStackTrace();
        }
        return tokenString;
    }

    /**
     * 校验token是否合法，返回Map集合,集合中主要包含    state状态码   data鉴权成功后从token中提取的数据
     * 该方法在过滤器中调用，每次请求API时都校验
     * @param token
     * @return  Map<String, Object>
     */
    public static Map<String, Object> validToken(String token) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier verifier = new MACVerifier(SECRET);

            if (jwsObject.verify(verifier)) {
                JSONObject jsonOBj = payload.toJSONObject();
                // token校验成功（此时没有校验是否过期）
                resultMap.put("state", TokenState.VALID.toString());
                // 若payload包含ext字段，则校验是否过期
                if (jsonOBj.containsKey("exp")) {
                    long extTime = Long.valueOf(jsonOBj.get("exp").toString());
                    long curTime = new Date().getTime();
                    // 过期了
                    if (curTime > extTime) {
                        resultMap.clear();
                        resultMap.put("state", TokenState.EXPIRED.toString());
                    }
                }
                resultMap.put("data", jsonOBj);

            } else {
                // 校验失败
                resultMap.put("state", TokenState.INVALID.toString());
            }

        } catch (Exception e) {
            //e.printStackTrace();
            // token格式不合法导致的异常
            resultMap.clear();
            resultMap.put("state", TokenState.INVALID.toString());
        }
        return resultMap;
    }

    //生成token的业务逻辑
    public static String TokenTest(String uid) {
        //获取生成token

        Map<String, Object> map = new HashMap<>();

        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", uid);
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime()+6);
        try {
            String token = createToken(map);
            System.out.println("token="+token);
            return token;
        } catch (Exception e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
        return null;

    }

    //处理解析的业务逻辑
    public static void ValidTokenTest(String token) {
        //解析token
        try {
            if (token != null) {

                Map<String, Object> validMap = validToken(token);
                String i = validMap.get("state").toString();
                if (TokenState.VALID.toString().equals(i)) {
                    System.out.println("token解析成功");
                    JSONObject jsonObject = (JSONObject) validMap.get("data");
                    System.out.println("uid是" + jsonObject.get("uid"));
                    System.out.println("sta是"+jsonObject.get("sta"));
                    System.out.println("exp是"+jsonObject.get("exp"));
                } else if (TokenState.EXPIRED.toString().equals(i)) {
                    System.out.println("token已经过期");
                }else if (TokenState.INVALID.toString().equals(i)) {
                    System.out.println("token不合法");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] ages) {
//        //获取token
//        String uid = "lianghl";
//        String token = TokenTest(uid);
//        //解析token
//        ValidTokenTest(token);
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTMyNDk0NDA2NTIsInR5cGUiOiJtMDA1IiwiZW1haWwiOiIxMzM0NTYyMzgzQHFxLmNvbSJ9.K1zLQFfvxrNu7jSNfL4JPJ2SI341tQQiz6LMUZIA0LI";
        System.out.println(validToken(token));
    }
}

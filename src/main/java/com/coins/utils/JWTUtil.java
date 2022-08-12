package com.coins.utils;

import com.coins.configure.CryptProperties;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class JWTUtil {
    /**
     * 生成签名,hour后过期
     *
     * @param username 用户Id
     * @param hour   时效
     * @return 加密的token
     */
    public static String sign(String username, Integer hour,String prefix) throws Exception {
        LocalDateTime times = LocalDateUtils.plus(LocalDateTime.now(),hour * 3600, ChronoUnit.SECONDS);
        String timeStr = LocalDateUtils.format(times,"yyyyMMddHHmmss");
//        System.out.println("JWT time ------------------------" + timeStr);
        return CryptUtils.encryptToAES(CryptProperties.getAes(),prefix + "." + username + "." + timeStr);
    }

    /**
     * 校验token是否正确
     *
     * @param token
     * @return 成功则返回解析结果
     */
    public static String verify(String token) {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            // 正常解密
            String tokenAsc = CryptUtils.decryptByAES(CryptProperties.getAes(),token);
            String[] tokenArr = tokenAsc.split("\\.");
            // 验证时间
            LocalDateTime tokenTime = LocalDateUtils.parseLocalDateTime(tokenArr[2],"yyyyMMddHHmmss");
            Long timeS = LocalDateUtils.getChronoUnit(currentTime,tokenTime,ChronoUnit.SECONDS);
//            System.out.println("JWT Token timeS ------------------------" + token);
//            System.out.println(timeS);
            if (timeS <= 0){
                return "false";
            }
            return tokenArr[1];
        }catch (Exception ex){
            return "false";
        }
    }
}

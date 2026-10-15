import cn.hutool.json.JSONObject;
import com.qk.common.util.QKJwts;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MrZhao
 *  演示
 *  1、如何使用java 代码生成JWT 令牌
 *  2、如何解析JWT
 */
public class JWTTest {

    public static void main(String[] args) {

        // 1. 生成令牌
        JwtBuilder builder = Jwts.builder();
        // 1.1处理头
        JwtBuilder.BuilderHeader header = builder.header();
        // 1.2处理载荷
        JwtBuilder.BuilderClaims claims = builder.claims();
        // 1.3签名
        // builder.signWith();

        /// 简化写法
        // 生成签名密钥
        SecretKey key = Jwts.SIG.HS256.key().build();
        // 生成令牌
        // 属性：id 令牌的唯一标识
        // issuer 签发人
        // issuedAt 签发时间
        // expiration 有效时间 一般7天
        // subject 主题
        // claims 自定义内容
//        Map<String,Object> map= new HashMap<>();
//        map.put("id",123);
//        map.put("username","张三");
//        String jwtToken = Jwts.builder()
//                .id("11111")
//                .issuer("itheima")
//                .issuedAt(new Date())
//                //.expiration(new Date())
//                .subject("Joe")
//                .claims(map)
//                .signWith(key).compact();
//        System.out.println(jwtToken);
//        // 2. 解析校验
//        Map<String,Object> payload = (Map<String, Object>) Jwts.parser().verifyWith(key).build().parse(jwtToken).getPayload();
//        System.out.println(payload);

        Map<String,Object> map= new HashMap<>();
        map.put("id",123);
        map.put("username","张三");
        String jwtToken = QKJwts.createJWTToken(map);
        System.out.println(jwtToken);
        JSONObject entries = QKJwts.parseJWTToken(jwtToken);
        System.out.println(entries);
        System.out.println(QKJwts.verifyJWTToken(jwtToken));
        Boolean b = QKJwts.verifyJWTToken("123131313");
        System.out.println(b);

    }

}

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.HMacJWTSigner;
import cn.hutool.jwt.signers.JWTSigner;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MrZhao
 *
 */
public class HutoolJWTs {




    public static void main(String[] args) {

        Map<String,Object> map= new HashMap<>();
        map.put("id",123);
        map.put("username","张三");
        String token = JWTUtil.createToken(map, "1231".getBytes());
        System.out.println(token);

        JWT jwt = JWTUtil.parseToken(token);
        JSONObject payloads = jwt.getPayloads();
        System.out.println(payloads);


    }

}

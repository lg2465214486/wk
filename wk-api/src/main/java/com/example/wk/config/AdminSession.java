package com.example.wk.config;

import cn.hutool.core.util.ObjectUtil;
import com.example.wk.entity.WkUser;
import com.example.wk.mapper.WkUserMapper;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Data
@Component
public class AdminSession {

    @Autowired
    private RedisTemplate<String, WkUser> redisTemplate;
    @Autowired
    private WkUserMapper userMapper;
    private static final String SESSION_KEY = "session:user:";
    private static AdminSession instance;

    public AdminSession() {
        instance = this;
    }

    public static AdminSession getInstance() {
        return instance;
    }

    public WkUser admin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("X-Token");
        if (ObjectUtil.isEmpty(token)) {
            token = request.getParameter("X-Token");
        }
        return redisTemplate.opsForValue().get(SESSION_KEY + token);
    }

    public String setAdmin(WkUser user) {
        if (user == null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            String token = request.getHeader("X-Token");
            redisTemplate.delete(SESSION_KEY + token);
            return "";
        } else {
            WkUser userSession = new WkUser();
            BeanUtils.copyProperties(user, userSession);
            userSession.setPwd(null);
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(SESSION_KEY + token, userSession, 30, TimeUnit.MINUTES);
            return token;
        }
    }

    public String logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String token = request.getHeader("X-Token");
        WkUser user = redisTemplate.opsForValue().get(SESSION_KEY + token);
        if (null != user)
            redisTemplate.delete(SESSION_KEY + token);
        return "logout success";
    }

}
package com.project_sena.spring_boot.Util.Service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ThreadLocalService {

    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public void setData(Map<String, Object> jwtData) {
        threadLocal.set(jwtData);
    }

    public Map<String, Object> getData() {
        return threadLocal.get();
    }

    public void clearData() {
        threadLocal.remove();
    }

}

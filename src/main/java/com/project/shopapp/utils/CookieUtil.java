package com.project.shopapp.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class CookieUtil {

    // Tạo cookie mới
    public void createCookie(String name,
                                    String value,
                                    int maxAge,
                                    boolean isHttpOnly,
                                    boolean isSecure,
                                    String path) {
        if (cookieExists(name))
            return;

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge); // Thời gian sống của cookie (giây)
        cookie.setHttpOnly(isHttpOnly); // Ngăn XSS tấn công
        cookie.setSecure(isSecure); // Chỉ gửi qua HTTPS nếu true
        cookie.setPath(path); // Phạm vi đường dẫn của cookie

        WebUtils.getCurrentHttpServletResponse().addCookie(cookie);
    }

    // Lấy giá trị của cookie theo tên
    public Optional<String> getCookieValue(String name) {
        HttpServletRequest request = WebUtils.getCurrentHttpServletRequest();

        if (request.getCookies() == null)
            return Optional.empty();

        return Arrays.stream(request.getCookies())
                .filter(cookie -> name.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }

    // Xóa cookie (set maxAge = 0)
    public void deleteCookie(String name, String path) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0); // Đặt maxAge = 0 để xóa cookie
        cookie.setPath(path); // Cần đảm bảo path giống khi tạo cookie
        WebUtils.getCurrentHttpServletResponse().addCookie(cookie);
    }

    // Kiểm tra cookie có tồn tại hay không
    public boolean cookieExists(String name) {
        HttpServletRequest request = WebUtils.getCurrentHttpServletRequest();

        if (request.getCookies() == null)
            return false;

        return Arrays.stream(request.getCookies())
                .anyMatch(cookie -> name.equals(cookie.getName()));
    }


}

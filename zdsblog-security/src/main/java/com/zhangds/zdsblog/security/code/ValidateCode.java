package com.zhangds.zdsblog.security.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Create by zhangds
 * 2020-03-04 18:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}

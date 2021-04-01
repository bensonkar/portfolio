package com.ben.portforlio.utils;

import lombok.Data;

/**
 * @author bkariuki
 */
@Data
public final class Extra {
    public String code;

    public Extra() {
    }

    public Extra(String code) {
        this.code = code;
    }
}

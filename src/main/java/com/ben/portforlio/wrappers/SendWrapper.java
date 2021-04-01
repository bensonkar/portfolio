package com.ben.portforlio.wrappers;

import lombok.Data;

import java.util.List;

/**
 * @author bkariuki
 */
@Data
public class SendWrapper {
    public List<Long> ids;
    public String title;
    public String message;
}

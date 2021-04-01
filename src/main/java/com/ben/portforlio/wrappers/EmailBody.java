package com.ben.portforlio.wrappers;

import lombok.Data;

/**
 * @author bkariuki
 */
@Data
public class EmailBody {
    private String title;
    private String message;
    private Long familyId;
}

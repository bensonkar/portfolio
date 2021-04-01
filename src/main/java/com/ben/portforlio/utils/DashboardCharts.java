package com.ben.portforlio.utils;

import lombok.Data;

/**
 * @author bkariuki
 */
@Data
public final class DashboardCharts {
    public String name;
    public long value;
    public Extra extra;

    public DashboardCharts() {
    }

    public DashboardCharts(String name, long value, Extra extra) {
        this.name = name;
        this.value = value;
        this.extra = extra;
    }
}

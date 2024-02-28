package com.seed_planner.schedule_center.common;

import java.util.UUID;

public class Utils {
    public static String customUUID() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 15);
    }

}

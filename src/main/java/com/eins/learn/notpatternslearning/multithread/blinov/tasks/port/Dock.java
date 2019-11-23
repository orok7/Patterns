package com.eins.learn.notpatternslearning.multithread.blinov.tasks.port;

import lombok.Data;

@Data
public class Dock {
    private final String dockId;
    private Ship docked;
}

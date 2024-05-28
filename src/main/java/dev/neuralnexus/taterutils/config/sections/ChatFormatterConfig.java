package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** ChatFormatter configuration. */
@ConfigSerializable
public class ChatFormatterConfig {
    @Setting private String format;

    public String format() {
        return format;
    }
}

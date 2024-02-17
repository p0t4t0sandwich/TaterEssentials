package dev.neuralnexus.taterutils;

import dev.neuralnexus.taterlib.plugin.ModuleLoader;
import dev.neuralnexus.taterlib.plugin.PluginModule;

import java.util.HashSet;
import java.util.Set;

/** TaterUtils implementation of the ModuleLoader. */
public class TaterUtilsModuleLoader implements ModuleLoader {
    private static final Set<PluginModule> modules = new HashSet<>();

    /** {@inheritDoc} */
    @Override
    public Set<PluginModule> modules() {
        return modules;
    }
}

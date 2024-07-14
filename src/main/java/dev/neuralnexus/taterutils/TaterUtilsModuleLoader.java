/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils;

import dev.neuralnexus.taterloader.plugin.ModuleLoader;
import dev.neuralnexus.taterloader.plugin.PluginModule;

import java.util.ArrayList;
import java.util.List;

/** TaterUtils implementation of the ModuleLoader. */
public class TaterUtilsModuleLoader implements ModuleLoader {
    private static final List<PluginModule> modules = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public List<PluginModule> modules() {
        return modules;
    }
}

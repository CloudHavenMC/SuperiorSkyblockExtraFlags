# SuperiorSkyblockExtraFlags
Plugin adding extra island flags and privileges for [SuperiorSkyblock2](https://github.com/BG-Software-LLC/SuperiorSkyblock2).

# List of extra island flags and privileges
## Flags (settings)
- **NATURAL_MONSTER_SPAWN_NORMAL**
- **NATURAL_MONSTER_SPAWN_NETHER**
- **NATURAL_MONSTER_SPAWN_THE_END**

These flags enables or disables natural monster spawn separately for each world.

## Privileges (permissions)
- **USE_BUTTONS** - Allows to use buttons
- **USE_DOORS** - Allows to use doors
- **USE_TRAPDOORS** - And so on...
- **USE_FENCE_GATES**
- **USE_BEDS**
- **USE_DRIPLEAF**
- **USE_ANVILS**
- **USE_BELLS**
- **USE_BARRELS**
- **USE_COMPOSTERS**
- **USE_ENDER_CHESTS**
- **USE_ENCHANTING_TABLES**
- **USE_HOPPERS**
- **USE_DISPENSERS**
- **USE_DROPPERS**
- **USE_PRESSURE_PLATES**
- **USE_FURNACES** - Allows to use furnace, blast furnace and smoker
- **USE_CHESTS** - Allows to use chest and trapped chest
- **USE_ARMOR_STANDS** - Allows to manipulate armor stand equipment
- **USE_ITEM_FRAMES** - Allows to take item from and put item in item frame
- **COLLECT_SWEET_BERRIES** - Allows to collect sweet berries from bush
- **COLLECT_GLOW_BERRIES** - Allows to collect glow berries from cave vines
- **ROTATE_ITEM_FRAME_ITEMS** - Allows to rotate item in item frame

**Some privileges also deny from destroy block/entity even if player has `BREAK` privilege. These privileges are: `USE_ANVILS`, `USE_BELLS`, `USE_BARRELS`, `USE_COMPOSTERS`, `USE_ENDER_CHESTS`, `USE_ENCHANTING_TABLES`, `USE_HOPPERS`, `USE_DISPENSERS`, `USE_DROPPERS`, `USE_PRESSURE_PLATES`, `USE_FURNACES`, `USE_CHESTS`, `USE_ARMOR_STANDS`, `USE_ITEM_FRAMES`**

# How to use
## Default flags and privileges
Most of extra flags and privileges are replacement for few default ones. So the best way is to enable those by default and to remove those from /is _settings/perms_, so players can't change these.

Those flags are:
- NATURAL_MONSTER_SPAWN

And privileges:
- USE
- CHEST_ACCESS
- ITEM_FRAME

For privilege `INTERACT` I recommend to just edit file `interactables.yml`, so it has other blocks that aren't covered by extra privileges, like loom, stonecutter, smithing table and whatever you want.

## Extra flags and privileges
You must enable by default those extra flags, for proper monsters spawn in each world:
- NATURAL_MONSTER_SPAWN_NORMAL
- NATURAL_MONSTER_SPAWN_NETHER
- NATURAL_MONSTER_SPAWN_THE_END

Default island flags (settings) can be set in `config.yml` of SuperiorSkyblock2 in section `default-settings`

Default island privileges (permissions) for different roles you can set in section `island-roles` also in `config.yml` and I recommend to do it.

If you want to give players access to change these flags and privileges, and I assume you want to, you have to edit files `menus/settings.yml` and `menus/permissions.yml`, and add extra flags/privileges just like default ones. For flag/privilege name use name from list in lower case.

# Default config
```yml
# SuperiorSkyblockExtraFlags by Ynfuien

# Prefix for hex colors
# Default: '&#'
hex-prefix: '&#'

# Message that will appear when the player isn't allowed to do something on island
# Default: '&c&lError | &7This island is protected.'
island-protected-message: '&c&lError | &7This island is protected.'
```
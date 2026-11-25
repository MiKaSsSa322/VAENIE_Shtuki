# Frontier Military

Minecraft **1.20.1**, Forge **47.2.0**, Java **17**.

This repository is the original project with its existing registrations and game behavior retained. This update refreshes the resource pack assets and fixes block model/state definitions.

## Updated in this pass
- Reworked pixel textures for the metal fence, security bars, concrete barrier, reinforced fiber, field ration, and pulse sidearm.
- Textures are hand-authored pixel art at 32x32 and use transparent pixels where appropriate.
- Corrected model texture slots for vanilla fence, iron-bars, and wall parents.
- Metal fence now uses multipart blockstate connections and proper post/side models.
- Kept both Russian and English localization keys and item/block registrations.
- The ration remains edible. The sidearm's existing projectile/cooldown/durability behavior is retained; its firing sound uses a vanilla crossbow sound event.
- Pinned ForgeGradle to 6.0.24 and stabilized GitHub Actions major versions.

## Scope / limitations
This is still the original lightweight milestone. It does **not** add a custom 3D animated renderer, reload animation, custom projectile entity, new sound files, structures, NPCs, armor, or vehicles. OptiFine is not required: the updated assets use vanilla JSON block/item models and textures, so they should work without it. OptiFine compatibility has not been runtime-tested.

## Build
Use Java 17 and Gradle 8.8:

```bash
gradle build --no-daemon
```

The GitHub Actions workflow installs Gradle 8.8 and runs the same build. Output JARs are placed in `build/libs/`.


### Recent fixes
- Fixed block texture/model slots for concrete barrier, security bars, and metal fence.
- Added rifle-ammo supply item and simplified reload interaction (Shift + right-click).
- Added a static 3D voxel item model. The projectile remains a fictional game effect.

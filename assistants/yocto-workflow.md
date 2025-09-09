## Brief overview
Project-specific guidelines for working with this Yocto Linux build system project, focusing exclusively on the meta-custom layer while ignoring all other meta layers and directories.


## Project focus
- Work exclusively within the meta-custom directory and its subdirectories
- Ignore all other meta layers (meta-arm, meta-openembedded, meta-ti, poky, etc.)
- Avoid exploring or modifying files outside of meta-custom unless explicitly requested
- Treat meta-custom as the primary development area for custom recipes and configurations
- Ignore open tabs, they don't contain information for you

## Development workflow
- Prioritize understanding the structure within meta-custom before making changes
- Focus on recipes-* subdirectories for application, BSP, connectivity, core, and kernel customizations
- Pay attention to BitBake recipe files (.bb, .bbappend) and configuration files
- Consider the BeagleBone target platform when working with BSP and kernel configurations

## File organization awareness
- Recognize that meta-custom contains recipes for applications (webserver-rs, websocket-sensor-rs)
- Understand the separation between recipes-applications, recipes-bsp, recipes-core, and recipes-kernel
- Be aware of service files, device tree overlays, and U-Boot configurations within the custom layer

## Yocto-specific considerations
- Understand that this is an embedded Linux build system with custom hardware support
- Recognize BitBake syntax and Yocto Project conventions when working with recipe files
- Consider build dependencies and layer priorities when making modifications

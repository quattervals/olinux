SUMMARY = "Custom BeagleBone Black image with systemd networking"
DESCRIPTION = "Minimal image for BeagleBone Black with systemd-networkd"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL:append = " kernel-modules"
IMAGE_INSTALL:remove = " sysvinit"

# Enable systemd networking components
PACKAGECONFIG:append:pn-systemd = " networkd resolved"

# Remove conflicting network management
BAD_RECOMMENDATIONS += "busybox-syslog"

# Add our custom configurations
IMAGE_INSTALL:append = " \
    packagegroup-beaglebone-network \
    packagegroup-beaglebone-utils \
"

# Remove network manager packages that might conflict
IMAGE_INSTALL:remove = " \
    connman \
    connman-client \
"

IMAGE_INSTALL += " \
    packagegroup-core-boot \
    packagegroup-core-ssh-openssh \
"

# Add applications
IMAGE_INSTALL:append = " hello-world"
IMAGE_INSTALL:append = " hello-world-rs"
IMAGE_INSTALL:append = " webserver-rs"
IMAGE_INSTALL:append = " service-webserver-rs"
IMAGE_INSTALL:append = " ssh-host-cert"

IMAGE_INSTALL:append = " dt-overlay-test"


IMAGE_INSTALL:append = " dtc"

# Post-process commands to copy overlay to boot partition
python copy_overlay_to_boot_partition() {
    import os
    import shutil

    # Get the path to the root filesystem staging location
    rootfs_path = d.getVar('IMAGE_ROOTFS')

    # Verify and create overlays directory in the root filesystem
    overlays_dir = os.path.join(rootfs_path, 'boot', 'overlays')
    os.makedirs(overlays_dir, exist_ok=True)

    # Dynamically locate the source path for the overlay file
    src_overlay = os.path.join(
        d.getVar('TMPDIR'),
        f"work/{d.getVar('MULTIMACH_TARGET_SYS')}/dt-overlay-test/{d.getVar('PV')}/package/boot/overlays/testoverlay.dtbo"
    )

    # Destination path in the root filesystem
    dst_overlay = os.path.join(overlays_dir, 'testoverlay.dtbo')

    # Copy the overlay file if it exists
    if os.path.exists(src_overlay):
        shutil.copy(src_overlay, dst_overlay)
        bb.note(f"Copied overlay {src_overlay} to {dst_overlay}")
    else:
        bb.warn(f"Overlay file not found: {src_overlay}")
}

ROOTFS_POSTPROCESS_COMMAND += "copy_overlay_to_boot_partition; "

DESCRIPTION = "Device tree overlay for test ADC"
SECTION = "kernel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit devicetree

COMPATIBLE_MACHINE = "beaglebone"

# Source files - devicetree class expects .dtso extension
SRC_URI = "file://testoverlay.dtso"

# The devicetree class will handle compilation automatically
# It compiles .dtso files to .dtbo files

# Install to both /boot/overlays and deploy directory
do_install:append() {
    # Create overlays directory in rootfs
    install -d ${D}/boot/overlays
    # Install the compiled overlay
    install -m 0644 ${B}/testoverlay.dtbo ${D}/boot/overlays/
}

# Deploy for boot partition integration
do_deploy:append() {
    # Create overlays directory in deploy
    install -d ${DEPLOYDIR}/overlays
    # Deploy the compiled overlay
    install -m 0644 ${B}/testoverlay.dtbo ${DEPLOYDIR}/overlays/
}

# Package the overlay files
FILES:${PN} += "/boot/overlays/testoverlay.dtbo"

# Ensure this runs after kernel is built
DEPENDS += "virtual/kernel"

DESCRIPTION = "Device tree overlay for test ADC"
SECTION = "kernel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit devicetree

COMPATIBLE_MACHINE = "beaglebone"

SRC_URI = "file://testoverlay.dtso"

# Add the -@ flag to Device Tree Compiler (DTC) flags
DTC_FLAGS:append = " -@"

#Install and deploy tasks done by devicetree

FILES:${PN} += "/boot/overlays/testoverlay.dtbo"

DEPENDS += "virtual/kernel"

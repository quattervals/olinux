DESCRIPTION = "Device tree overlay for test ADC"
SECTION = "kernel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit devicetree
# inherit kernel-devicetree
# inherit ref-classes-kernel-devicetree

COMPATIBLE_MACHINE = "beaglebone"

SRC_URI = "file://testoverlay.dtso"

# Add the -@ flag to Device Tree Compiler (DTC) flags
DTC_FLAGS:append = " -@"

# do_install:append() {
#     install -d ${D}/boot/overlays
#     install -m 0644 ${B}/testoverlay.dtbo ${D}/boot/overlays/
# }

# do_deploy:append() {
#     install -d ${DEPLOYDIR}/overlays
#     install -m 0644 ${B}/*.dtbo ${DEPLOYDIR}/overlays/

# }
# addtask deploy  after do_compile
# before do_build
FILES:${PN} += "/boot/overlays/testoverlay.dtbo"

# IMAGE_BOOT_FILES += "overlays/*.dtbo;overlays/"

DEPENDS += "virtual/kernel"


# KERNEL_DEVICETREE:append = " testoverlay.dtbo"

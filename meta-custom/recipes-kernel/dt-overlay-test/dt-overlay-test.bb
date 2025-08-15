DESCRIPTION = "Device tree overlay for test ADC"
SECTION = "kernel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit devicetree

COMPATIBLE_MACHINE = "beaglebone"

# Source files
SRC_URI = "file://testoverlay.dts"

# Install path
S = "${WORKDIR}"

# Build the overlay
do_compile() {
    # Compile the device tree overlay
    dtc -@ -I dts -O dtb -o ${B}/testoverlay.dtbo ${WORKDIR}/testoverlay.dts
}

do_install() {
    install -d ${D}/boot/overlays
    install -m 0644 ${B}/testoverlay.dtbo ${D}/boot/overlays/
}

# Deploy to appropriate location
do_deploy() {
    install -d ${DEPLOYDIR}/overlays
    install -m 0644 ${B}/testoverlay.dtbo ${DEPLOYDIR}/overlays/
}

addtask deploy before do_build after do_compile

FILES:${PN} = "/boot/overlays/testoverlay.dtbo"

# Package the overlay
PACKAGES = "${PN}"

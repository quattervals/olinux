SUMMARY = "Device Tree Overlay for BeagleBone Black I2C2"
DESCRIPTION = "Enables I2C2 on BeagleBone Black P9.19 (SCL) and P9.20 (SDA)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit devicetree

COMPATIBLE_MACHINE = "beaglebone"

SRC_URI = "file://BB-I2C2-00A0.dts"

# Specify the output filename
DEVICETREE_OVERLAYS = "BB-I2C2-00A0"

do_install() {
    # Create the firmware directory
    install -d ${D}${nonarch_base_libdir}/firmware

    # Install the compiled overlay
    install -m 0644 ${B}/BB-I2C2-00A0.dtbo ${D}${nonarch_base_libdir}/firmware/
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/"

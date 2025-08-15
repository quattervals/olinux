DESCRIPTION = "Device Tree Overlay for kernel configuration"
LICENSE = "CLOSED"


FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "file://testoverlay.dtbo"

FILES:${PN} += "/boot/dtbs/"


do_install() {
    install -d ${D}/boot/dtbs/
    install -m 0644 ${WORKDIR}/testoverlay.dtbo ${D}/boot/dtbs/
}

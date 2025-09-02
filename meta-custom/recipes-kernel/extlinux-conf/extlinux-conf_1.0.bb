
SUMMARY = "Custom extlinux.conf for BeagleBone. Needed when there is a device tree overlay"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://extlinux.conf"

inherit deploy

S = "${WORKDIR}"


do_install() {
    install -d ${D}/boot/extlinux/
    install -m 0644 ${S}/extlinux.conf ${D}/boot/extlinux/
}

do_deploy() {
    install -d ${DEPLOYDIR}/extlinux
    install -m 0644 ${S}/extlinux.conf ${DEPLOYDIR}/extlinux
}

addtask deploy before do_build after do_compile

FILES:${PN} += " /boot/extlinux/extlinux.conf"

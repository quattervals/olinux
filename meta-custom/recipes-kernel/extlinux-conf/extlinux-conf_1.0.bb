# meta-custom/recipes-bsp/extlinux-conf/extlinux-conf_1.0.bb
SUMMARY = "Custom extlinux.conf for BeagleBone"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://extlinux.conf"

inherit deploy

S = "${WORKDIR}"

do_install() {
    bbwarn "useless install in ${D}"
    install -d ${D}/boot/extlinux/
    install -m 0644 ${S}/extlinux.conf ${D}/boot/extlinux/
}

do_deploy() {
    bbwarn "deploy dir is ${DEPLOYDIR}"
    bbwarn "B is ${B}"
    bbwarn "D is ${D}"
    bbwarn "S is ${S}"
    install -d ${DEPLOYDIR}/extlinux
    install -m 0644  ${S}/extlinux.conf ${DEPLOYDIR}/extlinux
}

addtask deploy before do_build after do_compile

FILES:${PN} += " /boot/extlinux/extlinux.conf"

# IMAGE_BOOT_FILES += "extlinux.conf;extlinux/"

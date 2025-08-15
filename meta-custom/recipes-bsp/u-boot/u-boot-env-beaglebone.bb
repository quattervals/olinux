DESCRIPTION = "Custom U-Boot environment for BeagleBone with overlay support"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "beaglebone"

SRC_URI = "file://uEnv.txt"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/boot
    install -m 0644 ${WORKDIR}/uEnv.txt ${D}/boot/uEnv.txt
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/uEnv.txt ${DEPLOYDIR}/uEnv.txt
}

addtask deploy before do_build after do_install

FILES:${PN} = "/boot/uEnv.txt"

RDEPENDS:${PN} = "dt-overlay-test"

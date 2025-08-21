DESCRIPTION = "Custom U-Boot environment for BeagleBone with overlay support"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
COMPATIBLE_MACHINE = "beaglebone"
SRC_URI = "file://uEnv.txt"
S = "${WORKDIR}"

inherit deploy

# do_install() {
#     install -d ${D}/boot
#     install -m 0644 ${WORKDIR}/uEnv.txt ${D}/boot/uEnv.txt
# }

# do_install() {
#     # Create an empty directory or install a small marker file
#     install -d ${D}${sysconfdir}
#     echo "U-Boot environment deployed to boot partition" > ${D}${sysconfdir}/uboot-env-deployed
# }

do_deploy() {
    bbwarn "workdir ${WORKDIR}"
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/uEnv.txt ${DEPLOYDIR}/uEnv.txt
}

addtask deploy before do_build after do_install

# FILES:${PN} = "/boot/uEnv.txt"
# FILES:${PN} = "${sysconfdir}/uboot-env-deployed"
RDEPENDS:${PN} = "dt-overlay-test"

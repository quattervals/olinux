SUMMARY = "systemd-networkd configuration for BeagleBone Black"
DESCRIPTION = "Static network configuration using systemd-networkd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://10-eth0.network \
"

inherit systemd
S = "${WORKDIR}"

RDEPENDS:${PN} = "systemd"

do_install() {
    install -d ${D}/${systemd_system_unitdir}/network
    install -m 0644 ${WORKDIR}/10-eth0.network ${D}/${systemd_system_unitdir}/network/
}

FILES:${PN} = " \
    ${systemd_system_unitdir}/network/10-eth0.network \
"

SUMMARY = "BeagleBone Black networking packages"
DESCRIPTION = "Networking packages and configuration for BeagleBone Black with systemd-networkd"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

PACKAGES = "\
    ${PN} \
"

RDEPENDS:${PN} = "\
    systemd-networkd-config \
    systemd-extra-utils \
    iproute2 \
    iputils \
    ethtool \
"

# Ensure systemd networking is properly configured
RRECOMMENDS:${PN} = "\
    kernel-module-smsc95xx \
"

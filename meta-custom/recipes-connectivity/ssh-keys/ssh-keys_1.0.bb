SUMMARY = "SSH configuration and key management"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://sshd_config_custom"

S = "${WORKDIR}"

RDEPENDS:${PN} = "openssh"

do_install() {
    # Custom SSH daemon configuration
    install -d ${D}${sysconfdir}/ssh
    install -m 0644 ${WORKDIR}/sshd_config_custom ${D}${sysconfdir}/ssh/sshd_config_custom
}

pkg_postinst:${PN}() {
    #!/bin/sh
    # Generate SSH host keys if they don't exist
    if [ ! -f $D${sysconfdir}/ssh/ssh_host_rsa_key ]; then
        ssh-keygen -t rsa -f $D${sysconfdir}/ssh/ssh_host_rsa_key -N ""
    fi
    if [ ! -f $D${sysconfdir}/ssh/ssh_host_ecdsa_key ]; then
        ssh-keygen -t ecdsa -f $D${sysconfdir}/ssh/ssh_host_ecdsa_key -N ""
    fi
    if [ ! -f $D${sysconfdir}/ssh/ssh_host_ed25519_key ]; then
        ssh-keygen -t ed25519 -f $D${sysconfdir}/ssh/ssh_host_ed25519_key -N ""
    fi
}

FILES:${PN} = "${sysconfdir}/ssh/sshd_config_custom"

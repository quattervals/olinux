SUMMARY = "SSH Host Certificate for BeagleBone"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

RDEPENDS:${PN} = "openssh-sshd openssh-ssh openssh-keygen"


S = "${WORKDIR}"

KEYS = "${TOPDIR}/../meta-custom/ssh_device"
CA = "${TOPDIR}/../meta-custom/ssh-ca"
HOST_KEY_NAME = "ssh_host_ed25519_key"
CA_KEY_NAME = "ssh_ca_key"

do_install() {

    install -d ${D}${sysconfdir}/ssh

    # Install host private key (needed for certificate-based host authentication)
    install -m 600 ${KEYS}/${HOST_KEY_NAME} ${D}${sysconfdir}/ssh/

    # Install host certificate (signed public key)
    install -m 644 ${KEYS}/${HOST_KEY_NAME}-cert.pub ${D}${sysconfdir}/ssh/

    # Install CA public key for verifying user certificates
    # This is the SAME CA public key used to sign both host and user certificates
    install -m 644 ${CA}/keys/${CA_KEY_NAME}.pub ${D}${sysconfdir}/ssh/

}

FILES:${PN} = "${sysconfdir}/ssh/${HOST_KEY_NAME} \
               ${sysconfdir}/ssh/${HOST_KEY_NAME}-cert.pub \
               ${sysconfdir}/ssh/${CA_KEY_NAME}.pub"

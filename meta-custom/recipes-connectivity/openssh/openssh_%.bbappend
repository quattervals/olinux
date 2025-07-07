FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://sshd_config.append"
RDEPENDS:${PN}:append = " ssh-host-cert"

do_install:append () {
    # Append certificate configuration to sshd_config
    cat ${WORKDIR}/sshd_config.append >> ${D}${sysconfdir}/ssh/sshd_config
}

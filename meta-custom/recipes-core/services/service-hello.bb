LICENSE = "CLOSED"
DESCRIPTION = "Run hello_systemd script"

SRC_URI = " \
    file://hello-systemd.sh \
    file://hello-systemd.service \
"

SERVICE_NAME = "hello-systemd"
inherit systemd



SYSTEMD_SERVICE:${PN} = "${SERVICE_NAME}.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/${SERVICE_NAME}.sh ${D}${bindir}

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/${SERVICE_NAME}.service ${D}${systemd_system_unitdir}
}

FILES:${PN} = " \
    ${bindir}/* \
    ${systemd_system_unitdir}/* \
"

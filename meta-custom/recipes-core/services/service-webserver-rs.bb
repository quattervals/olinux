SUMMARY = "Run basic webserver"
DESCRIPTION = "Run basic webserver"
LICENSE = "CLOSED"


SERVICE_NAME = "webserver-rs"

SRC_URI = " \
    file://${SERVICE_NAME}.service \
"

inherit systemd

RDEPENDS:${PN} = "systemd"
SYSTEMD_SERVICE:${PN} = "${SERVICE_NAME}.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/${SERVICE_NAME}.service ${D}${systemd_system_unitdir}
}

FILES:${PN} = " \
    ${bindir}/* \
    ${systemd_system_unitdir}/* \
"

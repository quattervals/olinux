SUMMARY = "Simple Webserver in async Rust"
DESCRIPTION = "A simple webserver in async rust"
HOMEPAGE = "https://github.com/quattervals/simple_srv_rs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit cargo
inherit cargo-update-recipe-crates

require ${BPN}-crates.inc

TAG = "0.1.6"
SRC_URI += "git://github.com/quattervals/simple_srv_rs.git;branch=async_server;tag=v${TAG};protocol=https;name=webserver"

S = "${WORKDIR}/git"


TARGET_BIN_NAME = "webserver-rs"
HTML_DIR = "${S}/html"
TARGET_HTML_DIR = "${localstatedir}/www/${BPN}/html"

do_fetch[network] = "1"
do_compile[network] = "0"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/target/${RUST_TARGET_SYS}/release/${TARGET_BIN_NAME} ${D}${bindir}/${TARGET_BIN_NAME}

    install -d ${D}${TARGET_HTML_DIR}
    cp -r ${S}/html/* ${D}${TARGET_HTML_DIR}/
    chown -R root:root ${D}${TARGET_HTML_DIR}
    chmod -R 644 ${D}${TARGET_HTML_DIR}
    find ${D}${TARGET_HTML_DIR} -type d -exec chmod 755 {} \;
}

FILES:${PN} += "${bindir}/${TARGET_BIN_NAME} ${TARGET_HTML_DIR}"

SUMMARY = "Simple Webserver in async Rust"
DESCRIPTION = "A simple webserver in async rust"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


inherit cargo_bin

do_compile[network] = "1"

SRC_URI = "git://github.com/quattervals/simple_srv_rs.git;branch=async_server;tag=v0.1.5;protocol=https"


S = "${WORKDIR}/git"


HTML_DIR = "${S}/html"
TARGET_HTML_DIR = "${localstatedir}/www/${BPN}/html"


# Use variable for the binary name. Must match the name in Cargo.toml
TARGET_BIN_NAME= "webserver-rs"
# Specify the target architecture for cross-compilation
TARGET_CC_ARCH += "${LDFLAGS}"
# Set the Rust target triple for ARM which is different from Yocto
RUST_TARGET_SYS= "armv7-unknown-linux-gnueabihf"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/${RUST_TARGET_SYS}/release/${TARGET_BIN_NAME} ${D}${bindir}/${TARGET_BIN_NAME}

    install -d ${D}${TARGET_HTML_DIR}
    cp -r ${S}/html/* ${D}${TARGET_HTML_DIR}/
    chown -R root:root ${D}${TARGET_HTML_DIR}
    chmod -R 644 ${D}${TARGET_HTML_DIR}
    find ${D}${TARGET_HTML_DIR} -type d -exec chmod 755 {} \;
}

FILES:${PN} += "${bindir}/${TARGET_BIN_NAME} ${TARGET_HTML_DIR}"

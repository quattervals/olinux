LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/yourusername/hello_world_rust.git;branch=main;protocol=https"
SRCREV = "v0.1.0"

S = "${WORKDIR}/git"

inherit cargo

# Specify the target architecture for cross-compilation
TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/target/${TARGET_SYS}/release/hello-world-rust ${D}${bindir}/hello-world-rust
}

FILES:${PN} = "${bindir}/hello-world-rust"

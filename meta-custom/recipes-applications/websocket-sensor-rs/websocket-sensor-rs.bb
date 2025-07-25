SUMMARY = "Rust Sensor Application"
DESCRIPTION = "Provid Sensor Data via Websocket"
HOMEPAGE = "https://github.com/quattervals/sensor_rs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit cargo
inherit rust

SRC_URI = "git://github.com/quattervals/sensor_rs.git;branch=main;rev=da86baaae41cb65aa8d8e3b206b3b0484a4ca9ad;protocol=https"

S = "${WORKDIR}/git"

do_fetch[network] = "1"
do_compile[network] = "1"

# Must match the name in Cargo.toml
TARGET_BIN_NAME = "sensor-rs"
# Specify the target architecture for cross-compilation
TARGET_CC_ARCH += "${LDFLAGS}"
# Set the Rust target triple for ARM which is different from Yocto
RUST_TARGET_SYS = "armv7-poky-linux-gnueabihf"

CARGO_BUILD_FLAGS:remove = "--frozen"
# Use cargo class variables to control behavior
CARGO_DISABLE_BITBAKE_VENDORING = "1"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/target/${RUST_TARGET_SYS}/release/${TARGET_BIN_NAME} ${D}${bindir}/${TARGET_BIN_NAME}
}

FILES:${PN} += "${bindir}/${TARGET_BIN_NAME}"

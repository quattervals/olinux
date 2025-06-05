SUMMARY = "Hello World application written in Rust"
DESCRIPTION = "A simple Hello World application written in Rust for demonstration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


inherit cargo_bin

do_compile[network] = "1"

SRC_URI = "git://github.com/quattervals/hello_rs_world.git;branch=main;protocol=https"
SRCREV = "v0.1.0"

S = "${WORKDIR}/git"


# Use variable for the binary name. Must match the name in Cargo.toml
TARGET_BIN_NAME= "hello-world-rs"
# Specify the target architecture for cross-compilation
TARGET_CC_ARCH += "${LDFLAGS}"
# Set the Rust target triple for ARM which is different from Yocto
RUST_TARGET_SYS= "armv7-unknown-linux-gnueabihf"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/${RUST_TARGET_SYS}/release/hello-world-rs ${D}${bindir}/hello-world-rs
}

FILES:${PN} = "${bindir}/hello-world-rs"


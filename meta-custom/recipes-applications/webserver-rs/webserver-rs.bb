SUMMARY = "Simple Webserver in async Rust"
DESCRIPTION = "A simple webserver in async rust"
HOMEPAGE = "https://github.com/quattervals/simple_srv_rs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


inherit cargo
inherit rust

SRC_URI = "git://github.com/quattervals/simple_srv_rs.git;branch=async_server;tag=v0.1.6;protocol=https"

S = "${WORKDIR}/git"

do_fetch[network] = "1"
do_compile[network] = "1"



# Must match the name in Cargo.toml
TARGET_BIN_NAME= "webserver-rs"
# Specify the target architecture for cross-compilation
TARGET_CC_ARCH += "${LDFLAGS}"


# Automatically figured out
# RUST_TARGET_SYS= "armv7-unknown-linux-gnueabihf"
# RUST_TARGET_SYS= "armv7-poky-linux-gnueabihf"

CARGO_BUILD_FLAGS:remove = "--frozen"
# Use cargo class variables to control behavior
CARGO_DISABLE_BITBAKE_VENDORING = "1"
# CARGO_BUILD_FLAGS = "--target ${RUST_TARGET_SYS} --release" ## automatically done


python do_print_variables() {
    bb.warn("=== Printing Variables Before Compile ===")

    variables_to_print = [
        'TARGET_CC_ARCH',
        'RUST_TARGET_SYS',
        'CARGO_BUILD_FLAGS'
    ]

    for var in variables_to_print:
        value = d.getVar(var)
        bb.warn(f"{var} = {value}")

    bb.warn("\n=== End Variables ===")
}

HTML_DIR = "${S}/html"
TARGET_HTML_DIR = "${localstatedir}/www/${BPN}/html"

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
addtask print_variables before do_compile

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "git://github.com/quattervals/hello_world.git;branch=main;protocol=https"
SRCREV = "v0.1.0"
# alternatively, use a git hash: SRCREV = "ea3c84e90830b792bf859146017ce4bd2573a838"


S = "${WORKDIR}/git"

inherit cmake


EXTRA_OECMAKE = ""

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Define the installation steps
do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/HelloWorld ${D}${bindir}/HelloWorld
}

# Specify the files to be included in the package
FILES_${PN} = "${bindir}/HelloWorld"

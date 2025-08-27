DESCRIPTION = "Extlinux configuration for BeagleBone with device tree overlay support"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "beaglebone"

inherit deploy

# No source files needed - we generate the config
SRC_URI = ""

S = "${WORKDIR}"

# Generate the extlinux.conf file
do_compile() {
    cat > ${WORKDIR}/extlinux.conf << EOF
default Yocto
label Yocto
   kernel /zImage
   fdtdir /
   fdtoverlays /overlays/testoverlay.dtbo
   append root=PARTUUID=076c4a2a-02 rootwait console=ttyS0,115200
EOF
}

# Deploy to the correct extlinux directory structure
do_deploy() {
    install -d ${DEPLOYDIR}/extlinux
    install -m 0644 ${WORKDIR}/extlinux.conf ${DEPLOYDIR}/extlinux/extlinux.conf
}

addtask deploy before do_build after do_compile

# Ensure this runs after device tree overlays are built
DEPENDS += "dt-overlay-test"

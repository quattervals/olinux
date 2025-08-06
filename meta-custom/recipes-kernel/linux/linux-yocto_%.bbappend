# Add custom uEnv.txt configuration for I2C2 overlay
FILESEXTRAPATHS:prepend := "${THISDIR}/../../recipes-kernel/device-tree/files:"

SRC_URI += "file://uEnv.txt.append"

do_install:append() {
    # Get the path to the uEnv.txt file
    if [ -e ${D}/boot/uEnv.txt ]; then
        # Append our custom configuration
        cat ${WORKDIR}/uEnv.txt.append >> ${D}/boot/uEnv.txt
    elif [ -e ${D}${KERNEL_IMAGEDEST}/uEnv.txt ]; then
        # Append our custom configuration
        cat ${WORKDIR}/uEnv.txt.append >> ${D}${KERNEL_IMAGEDEST}/uEnv.txt
    else
        # Create a new uEnv.txt file in the boot directory
        install -d ${D}/boot
        install -m 0644 ${WORKDIR}/uEnv.txt.append ${D}/boot/uEnv.txt
    fi
}

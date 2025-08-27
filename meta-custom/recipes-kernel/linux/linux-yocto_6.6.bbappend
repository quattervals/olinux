FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://cfg-of-overlay.cfg"


KERNEL_DTC_FLAGS:append = " -@"

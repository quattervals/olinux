SUMMARY = "Custom BeagleBone Black image with systemd networking"
DESCRIPTION = "Minimal image for BeagleBone Black with systemd-networkd"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL:append = " kernel-modules"
IMAGE_INSTALL:remove = " sysvinit"

# Use systemd as init system
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"

# Enable systemd networking components
PACKAGECONFIG:append:pn-systemd = " networkd resolved"

# Remove conflicting network management
BAD_RECOMMENDATIONS += "busybox-syslog"

# Add our custom configurations
IMAGE_INSTALL:append = " \
    packagegroup-beaglebone-network \
    packagegroup-beaglebone-utils \
"

# Remove network manager packages that might conflict
IMAGE_INSTALL:remove = " \
    connman \
    connman-client \
"

IMAGE_INSTALL += " \
    packagegroup-core-boot \
    packagegroup-core-ssh-openssh \
    openssh-scp \
    "

# Add applications
IMAGE_INSTALL:append = " hello-world"
IMAGE_INSTALL:append = " hello-world-rs"

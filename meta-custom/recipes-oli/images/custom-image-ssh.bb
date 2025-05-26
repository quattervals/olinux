require recipes-core/images/core-image-minimal.bb

DESCRIPTION = "My custom minimal image with additional packages e.g. SSH"


LICENSE = "MIT"


# Switch to systemd
DISTRO_FEATURES:append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_initscripts = ""

# Add what you need
IMAGE_INSTALL:append = " \
    openssh \
    openssh-sshd \
    openssh-keygen \
    systemd \
    systemd-serialgetty \
    systemd-networkd \
"

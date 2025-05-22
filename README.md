# Minimal BeagleBone Black Linux

Ultra-minimal Yocto setup that boots Linux with serial console access only.

## Quick Start

```bash
source poky/oe-init-build-env build

echo 0 | sudo tee /proc/sys/kernel/apparmor_restrict_unprivileged_userns

bitbake core-image-minimal
```
## Login

- Username: `root`
- Password: (none - just press Enter)

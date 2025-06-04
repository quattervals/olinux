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


## Host PC
- Networking: ipv4, manual:
  - enter own address e.g. 192.168.1.101, Netmask 255.255.255.0 and *no* Gateway to avoid interference with the wireless network.

- serial connection
  - `picocom -b 115200 /dev/ttyUSB0`
- ssh
  - `ssh root@192.168.1.100`

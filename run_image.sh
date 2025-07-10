#!/bin/bash



SSH_PORT=2222     # Lokaler Port für den SSH-Zugriff

cd build/tmp/deploy/images/qemuarm


qemu-system-arm \
   -machine virt,highmem=off \
    -cpu cortex-a15 \
    -m 512 \
    -smp 4 \
    -kernel zImage \
    -drive id=disk0,file=beaglebone-image-qemuarm.rootfs.ext4,if=none,format=raw \
    -device virtio-blk-device,drive=disk0 \
    -netdev user,id=net0,net=192.168.1.100/24,host=192.168.1.2,hostfwd=tcp::2222-:22 \
    -device virtio-net-device,netdev=net0 \
    -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 \
    -device virtio-rng-device,rng=rng0 \
    -append "root=/dev/vda rw  swiotlb=0 net.ifnames=0" \
    -nographic \

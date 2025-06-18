#!/bin/bash

MACHINE="BeagleBone"

PROJECT_DIR="$(dirname "$0")/.."
CA_KEYS_DIR="$PROJECT_DIR/ssh-ca/keys"
CA_KEY="ssh_ca_key"

DEVICE_KEY_DIR="$PROJECT_DIR/ssh_device"
DEVICE_KEY="ssh_host_ed25519_key"


rm -rf ${DEVICE_KEY_DIR}
mkdir -p ${DEVICE_KEY_DIR}

echo "Make device public and private key pair"
ssh-keygen -t ed25519 \
           -f ${DEVICE_KEY_DIR}/${DEVICE_KEY} -N "" \
           -C "beaglebone-${MACHINE}-$(date +%Y%m%d)"


echo "Sign with certificate"
ssh-keygen  -s "${CA_KEYS_DIR}/${CA_KEY}" \
            -I "beaglebone-${MACHINE}-$(date +%Y%m%d%H%M)" \
            -h \
            -V +365d \
            -n "beaglebone,${MACHINE},${MACHINE}.local,localhost,127.0.0.1,192.168.1.100" \
            ${DEVICE_KEY_DIR}/${DEVICE_KEY}.pub

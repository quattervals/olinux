SUMMARY = "SSH Host Certificate for BeagleBone"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# CA key must be available during build
CA_KEY_FILE ??= "${TOPDIR}/../meta-custom/ssh-ca/keys/ssh_ca_key"

# RDEPENDS:${PN} = "openssh"


SSH_KEYGEN = "/usr/bin/ssh-keygen"

do_compile() {
    # Check if ssh-keygen is available
    if ! command -v ${SSH_KEYGEN} >/dev/null 2>&1; then
        bbfatal "ssh-keygen not found. Please install openssh-client"
    fi

    # Check if CA key exists
    if [ ! -f "${CA_KEY_FILE}" ]; then
        bbfatal "CA private key not found at ${CA_KEY_FILE}. Run generate-ssh-ca.sh first!"
    fi

    echo "Generating host certificate for ${MACHINE}..."

    rm -rf ${B}/host-keys
    mkdir -p ${B}/host-keys

    # Generate BeagleBone's host private key
    ${SSH_KEYGEN} -t ed25519 -f ${B}/host-keys/ssh_host_ed25519_key -N "" \
               -C "beaglebone-${MACHINE}-$(date +%Y%m%d)" \


    echo "sign with certificate ${MACHINE}..."

    # Sign the host public key with CA private key
    ${SSH_KEYGEN} -s "${CA_KEY_FILE}" \
               -I "beaglebone-${MACHINE}-$(date +%Y%m%d%H%M)" \
               -h \
               -V +365d \
               -n "beaglebone,${MACHINE},${MACHINE}.local,localhost,127.0.0.1,192.168.1.100" \
               ${B}/host-keys/ssh_host_ed25519_key.pub

    echo "Host certificate generated:"
    ${SSH_KEYGEN} -L -f ${B}/host-keys/ssh_host_ed25519_key-cert.pub
}

do_install() {



    install -d ${D}${sysconfdir}/ssh

    # Install host private key
    install -m 600 ${B}/host-keys/ssh_host_ed25519_key ${D}${sysconfdir}/ssh/

    # Install host certificate (signed public key)
    install -m 644 ${B}/host-keys/ssh_host_ed25519_key-cert.pub ${D}${sysconfdir}/ssh/

    # Install CA public key for verifying user certificates
    # This is the SAME CA public key used to sign both host and user certificates
    install -m 644 ${CA_KEY_FILE}.pub ${D}${sysconfdir}/ssh/trusted_user_ca_keys.pub

    # Verify the CA public key exists
    if [ ! -f "${CA_KEY_FILE}.pub" ]; then
        bbfatal "CA public key not found at ${CA_KEY_FILE}.pub"
    fi

    echo "Installed CA public key for user certificate verification:"
    cat ${CA_KEY_FILE}.pub
}

FILES_${PN} = "${sysconfdir}/ssh/ssh_host_ed25519_key \
               ${sysconfdir}/ssh/ssh_host_ed25519_key-cert.pub \
               ${sysconfdir}/ssh/trusted_user_ca_keys.pub"

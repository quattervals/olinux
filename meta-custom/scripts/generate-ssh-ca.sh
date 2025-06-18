#!/bin/bash


PROJECT_DIR="$(dirname "$0")/.."
CA_DIR="$PROJECT_DIR/ssh-ca"
KEYS_DIR="$CA_DIR/keys"

mkdir -p "$KEYS_DIR" 

# Generate CA key pair (do this once)
if [ ! -f "$KEYS_DIR/ssh_ca_key" ]; then
    echo "Generating SSH CA key pair..."
    ssh-keygen -t ed25519 -f "$KEYS_DIR/ssh_ca_key" -N "" -C "BeagleBone-Project-CA"
    chmod 600 "$KEYS_DIR/ssh_ca_key"
    chmod 644 "$KEYS_DIR/ssh_ca_key.pub"
fi

echo "CA setup complete. CA public key:"
cat "$KEYS_DIR/ssh_ca_key.pub"

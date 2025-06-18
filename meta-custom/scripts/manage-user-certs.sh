#!/bin/bash
# Script to sign user certificates

PROJECT_DIR="$(dirname "$0")/.."
CA_DIR="$PROJECT_DIR/ssh-ca"
CA_KEY="$CA_DIR/keys/ssh_ca_key"
USERS_DIR="$PROJECT_DIR/ssh_device/users"


mkdir -p "$USERS_DIR"

sign_user_key() {
    local username="$1"
    local user_pubkey="$2"
    local validity_days="${3:-30}"
    
    if [ ! -f "$user_pubkey" ]; then
        echo "Error: Public key file not found: $user_pubkey"
        return 1
    fi
    
    local cert_file="${user_pubkey%-cert.pub}-cert.pub"
    if [[ "$user_pubkey" != *-cert.pub ]]; then
        cert_file="${user_pubkey%.pub}-cert.pub"
    fi
    
    echo "Signing certificate for user: $username"
    echo "Public key: $user_pubkey"
    echo "Certificate: $cert_file"
    echo "Valid for: $validity_days days"
    
    ssh-keygen -s "$CA_KEY" \
               -I "user-${username}-$(date +%Y%m%d)" \
               -n "$username" \
               -V "+${validity_days}d" \
               "$user_pubkey"
    
    echo "Certificate created: $cert_file"
    ssh-keygen -L -f "$cert_file"
    
    # Copy to users directory
    cp "$cert_file" "$USERS_DIR/${username}-cert.pub"
    echo "Certificate saved to: $USERS_DIR/${username}-cert.pub"
}

revoke_user() {
    local username="$1"
    echo "To revoke user $username, you need to:"
    echo "1. Remove their certificate from $USERS_DIR"
    echo "2. Add their certificate serial to a revocation list (not implemented)"
    echo "3. Or rotate the CA key (nuclear option)"
}

case "$1" in
    sign)
        if [ $# -lt 3 ]; then
            echo "Usage: $0 sign <username> <public_key_file> [validity_days]"
            echo "Example: $0 sign alice ~/.ssh/id_ed25519.pub 30"
            exit 1
        fi
        sign_user_key "$2" "$3" "$4"
        ;;
    revoke)
        if [ $# -lt 2 ]; then
            echo "Usage: $0 revoke <username>"
            exit 1
        fi
        revoke_user "$2"
        ;;
    *)
        echo "Usage: $0 {sign|revoke}"
        echo ""
        echo "Commands:"
        echo "  sign <user> <pubkey> [days]  - Sign a user's public key"
        echo "  revoke <user>                - Revoke a user's access"
        echo ""
        echo "Examples:"
        echo "  $0 sign alice ~/.ssh/id_ed25519.pub 30"
        echo "  $0 sign bob /tmp/bob_key.pub 7"
        exit 1
        ;;
esac 

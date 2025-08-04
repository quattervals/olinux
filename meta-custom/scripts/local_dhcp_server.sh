#!/bin/bash

INTERFACE="enp0s31f6"  
DEVICE_MAC="54:4A:16:BB:D3:56"  # Connected device's MAC
ASSIGNED_ADDRESS="192.168.100.10"


sudo pkill dnsmasq
sudo rm -f /tmp/dnsmasq.log

# Set static IP on laptop interface
sudo ip addr replace 192.168.100.1/24 dev $INTERFACE

# Start dnsmasq for this session only
sudo dnsmasq --interface=$INTERFACE \
             --dhcp-range=192.168.100.200,192.168.100.255,24h \
             --dhcp-host=$DEVICE_MAC,$ASSIGNED_ADDRESS,yocto-host \
            --port=5353 \
            --log-dhcp \
            --log-facility=/tmp/dnsmasq.log \
            #  --no-daemon


# Brutally stop the process: sudo pkill dnsmasq
# Check log
# sudo tail -f /tmp/dnsmasq.log
# sudo pkill dnsmasq
# sudo rm  /tmp/dnsmasq.log

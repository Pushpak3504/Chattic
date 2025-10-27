#!/bin/bash
sudo apt update && sudo apt upgrade -y
wget https://dl.min.io/server/minio/release/linux-amd64/minio -O minio
chmod +x minio 
sudo mv minio /usr/local/bin/
mkdir ~/minio-data
minio server ~/minio-data --console-address ":9001"
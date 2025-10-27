#!/bin/bash
sudo apt update && sudo apt upgrade -y
wget https://dl.min.io/server/minio/release/linux-amd64/minio -O minio
chmod +x minio 
sudo mv minio /usr/local/bin/
mkdir ~/minio-data

minio server ~/minio-data --console-address ":9001"

#Client
#wget https://dl.min.io/client/mc/release/linux-amd64/mc -O mc
#chmod +x mc
#sudo mv mc /usr/local/bin/

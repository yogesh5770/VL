# Virtualization Model Lab — Super Easy Step-by-Step Guide

This folder contains easy-to-follow, plain English guides for every single experiment in your Model Lab Exam.
Even if you are doing this for the first time, just follow **Step 1, Step 2, Step 3** and you will get 100% success!

---

## 📑 All Experiment Text Files

1. **[`EXP_02A_02B.txt`](./EXP_02A_02B.txt)**
   - **2a:** How to create a VM in VirtualBox (RAM, Hard Disk, Network) + Run Java speed test (Windows vs Ubuntu).
   - **2b:** How to ping between two VMs and ping from VM to Windows Host with 0% packet loss.
   - **Java Code:** [`Benchmark.java`](./Benchmark.java)

2. **[`EXP_03.txt`](./EXP_03.txt)**
   - **Cold Migration:** How to move a VM from Computer 1 to Computer 2 using an `.ova` file and a pendrive with zero data loss.

3. **[`EXP_04.txt`](./EXP_04.txt)**
   - **4a:** How to install Citrix XenServer on a physical server computer.
   - **4b:** How to do Live Migration of a running VM from Server 1 to Server 2 using XenCenter with 0 downtime.

4. **[`EXP_05.txt`](./EXP_05.txt)**
   - **5a:** How to install KVM on Linux and create a virtual machine instance with `virt-manager`.
   - **5b:** How to create a 10 GB disk with `qemu-img`, resize it to 15 GB, and convert it into RAW, VDI, and VHD.

5. **[`EXP_06.txt`](./EXP_06.txt)**
   - **File Transfer:** How to send and receive files between Ubuntu and Kali using NFS share, Shared Folders, SSH/SCP, and FTP.

---

## 🚀 Quick Command Summary (Copy-Paste Cheatsheet)

### 1. File Transfer (NFS Share)
```bash
# On Ubuntu (Server):
sudo apt install nfs-kernel-server -y
sudo mkdir -p /srv/nfs/sharedfolder && sudo chmod 777 /srv/nfs/sharedfolder
echo "/srv/nfs/sharedfolder *(rw,sync,no_subtree_check,no_root_squash)" | sudo tee -a /etc/exports
sudo exportfs -a && sudo systemctl restart nfs-kernel-server

# On Kali (Client):
sudo apt install nfs-common -y
sudo mkdir -p /mnt/nfs/sharedfolder
sudo mount <UBUNTU_IP>:/srv/nfs/sharedfolder /mnt/nfs/sharedfolder
```

### 2. Ping Test (VM-to-VM)
```bash
# On both VMs:
sudo ufw disable
ip a
ping -c 4 <OTHER_VM_IP>
```

### 3. Java Benchmark
```bash
javac Benchmark.java
java Benchmark
```

### 4. KVM Image Operations
```bash
# Create 10GB disk:
qemu-img create -f qcow2 mydisk.qcow2 10G

# Resize (+5GB):
qemu-img resize mydisk.qcow2 +5G

# Convert to VDI, RAW, VHD:
qemu-img convert -f qcow2 -O raw mydisk.qcow2 mydisk.raw
qemu-img convert -f qcow2 -O vdi mydisk.qcow2 mydisk.vdi
qemu-img convert -f qcow2 -O vpc mydisk.qcow2 mydisk.vhd
```

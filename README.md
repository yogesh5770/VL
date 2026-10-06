# Virtualization & Cloud Computing Lab — Model Exam Reference Repository

This repository contains complete, tested, step-by-step procedures, terminal commands, configurations, Java benchmark code, and Viva Voce questions for all Model Lab virtualization experiments.

---

## 📑 Experiment Quick Index

| Exp No | Topic & Objective | Key Tools / Hypervisors | File Link |
|---|---|---|---|
| **2a** | **VM Creation, Resource Allocation & Host vs VM Java Benchmark** | Oracle VirtualBox, Ubuntu, Java JDK | [`EXP_02A_02B.txt`](./EXP_02A_02B.txt) |
| **2b** | **VM-to-VM Ping & VM-to-Host (Native) ICMP Ping Testing** | VirtualBox Host-Only Adapter, ICMP, ufw | [`EXP_02A_02B.txt`](./EXP_02A_02B.txt) |
| **3** | **Cold Migration of VM using Export/Import Appliance (OVA)** | Oracle VirtualBox, OVA/OVF, Storage Transfer | [`EXP_03.txt`](./EXP_03.txt) |
| **4a** | **Citrix XenServer Installation (Bare-Metal Type-1)** | Citrix Hypervisor ISO, BIOS VT-x, xsconsole | [`EXP_04.txt`](./EXP_04.txt) |
| **4b** | **Live Migration of Running VM using XenCenter GUI** | XenCenter, Shared NFS/iSCSI Storage, Zero Downtime | [`EXP_04.txt`](./EXP_04.txt) |
| **5a** | **KVM Installation & Virtual Instance Creation** | KVM, QEMU, libvirt, virt-manager, virsh, CirrOS | [`EXP_05.txt`](./EXP_05.txt) |
| **5b** | **KVM Image Creation from ISO, Image Resizing & Conversion** | `qemu-img`, `growpart`, `resize2fs`, QCOW2/RAW/VDI/VHD | [`EXP_05.txt`](./EXP_05.txt) |
| **6** | **File Transfer Between VMs (NFS, Shared Folders, SSH/SCP, FTP)** | NFS kernel server, VirtualBox Guest Additions, SCP, vsftpd | [`EXP_06.txt`](./EXP_06.txt) |

---

## 🚀 Quick Execution Cheatsheet for Lab Exam

### Experiment 2a & 2b: VM Creation, Benchmark & Ping
```bash
# Compile and run Java benchmark in Ubuntu VM:
javac Benchmark.java
java Benchmark
# Alternatively with timing:
time java Benchmark

# Ping Host and Peer VM:
sudo ufw disable
ip a
ping -c 4 192.168.56.1    # Ping Host Windows
ping -c 4 192.168.56.103  # Ping Second VM
```

### Experiment 3: Cold Migration (OVA)
1. In VM: `echo "Test data" > test.txt` && `sudo shutdown -h now`
2. VirtualBox: `File -> Export Appliance...` -> Save as `.ova`.
3. Transfer `.ova` to destination PC via USB.
4. On Destination: `File -> Import Appliance...` -> Generate new MAC addresses.
5. Start VM -> `cat test.txt`.

### Experiment 4: XenServer & Live Migration
- **Hypervisor type**: Xen is Type-1 bare-metal.
- **Dom0**: Management domain with physical hardware drivers.
- **Live Migration rule**: Requires Shared Storage (NFS/iSCSI) and identical CPU architecture.
- In XenCenter: Right-click VM -> `Move VM...` -> Select Destination Host -> Zero downtime ping verification.

### Experiment 5: KVM & Image Operations
```bash
# 1. Check CPU hardware virtualization support:
egrep -c '(vmx|svm)' /proc/cpuinfo

# 2. Install KVM packages & enable daemon:
sudo apt update && sudo apt install -y qemu-kvm libvirt-daemon-system libvirt-clients virt-manager qemu-utils
sudo systemctl enable --now libvirtd
sudo usermod -aG libvirt $USER && sudo usermod -aG kvm $USER

# 3. Create 10GB QCOW2 image:
qemu-img create -f qcow2 ubuntu_vm.qcow2 10G
qemu-img info ubuntu_vm.qcow2

# 4. Resize image (+5GB):
qemu-img resize ubuntu_vm.qcow2 +5G

# 5. Convert QCOW2 to VDI, RAW, VHD:
qemu-img convert -f qcow2 -O raw ubuntu_vm.qcow2 ubuntu_vm.raw
qemu-img convert -f qcow2 -O vdi ubuntu_vm.qcow2 ubuntu_vm.vdi
qemu-img convert -f qcow2 -O vpc ubuntu_vm.qcow2 ubuntu_vm.vhd
```

### Experiment 6: File Transfer (NFS, SSH/SCP, FTP)
```bash
# Method 1: NFS Server (Ubuntu)
sudo apt install -y nfs-kernel-server
sudo mkdir -p /srv/nfs/sharedfolder && sudo chmod 777 /srv/nfs/sharedfolder
echo "/srv/nfs/sharedfolder 192.168.56.0/24(rw,sync,no_subtree_check,no_root_squash)" | sudo tee -a /etc/exports
sudo exportfs -a && sudo systemctl restart nfs-kernel-server

# Method 1: NFS Client (Kali)
sudo apt install -y nfs-common
sudo mkdir -p /mnt/nfs/sharedfolder
sudo mount 192.168.56.103:/srv/nfs/sharedfolder /mnt/nfs/sharedfolder

# Method 3: SCP (Secure Copy)
scp testfile.txt user@192.168.56.103:/home/user/
```

---

## 🎯 Viva High-Yield Top 5 Questions

1. **Type-1 vs Type-2 Hypervisors**:
   - Type-1 (Bare-Metal): Runs directly on physical hardware (Xen, KVM, ESXi). High performance.
   - Type-2 (Hosted): Runs inside a host OS (VirtualBox, VMware Workstation). Higher overhead.
2. **Cold vs Live Migration**:
   - Cold: VM is shut down; disks/configs copied. Incurs downtime.
   - Live: VM remains running; pre-copies RAM pages over network with shared storage. Zero downtime.
3. **QCOW2 vs RAW**:
   - QCOW2: Thin provisioning, copy-on-write, snapshots, compression.
   - RAW: Flat binary, fastest performance, no native snapshots.
4. **NFS Exports Options**:
   - `rw`: Read/write access.
   - `sync`: Synchronous write commit before reply.
   - `no_root_squash`: Allows remote root client to maintain root privileges on share.
5. **Why is Bare-Metal Host faster than VM for compute loops?**
   - VM incurs hypervisor context switching, CPU virtualization traps, and nested page-table translation overhead.

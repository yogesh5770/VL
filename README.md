# Virtualization & Cloud Computing Model Lab — Master Reference

This repository contains complete, crystal-clear, step-by-step guides for **ALL** Model Lab experiments (2 through 8).
Each file is written in plain, beginner-friendly English with exact commands, GUI steps, and Viva Voce answers.

---

## 📑 Complete Experiment Directory

| Experiment | Title & Objective | Key Technology | File Link |
|---|---|---|---|
| **Exp 2a & 2b** | VM Creation, RAM/Disk Allocation, Host vs VM Java Benchmark & Ping Test | Oracle VirtualBox, Ubuntu, Java | [`EXP_02A_02B.txt`](./EXP_02A_02B.txt) |
| **Java Code** | Speed Benchmark Program for Exp 2a | Java JDK | [`Benchmark.java`](./Benchmark.java) |
| **Exp 3** | Cold Migration of VM using Export/Import Appliance (OVA) | VirtualBox, OVA/OVF, USB | [`EXP_03.txt`](./EXP_03.txt) |
| **Exp 4a & 4b** | Citrix XenServer Bare-Metal Install & Live Migration via XenCenter | Citrix XenServer, XenCenter, NFS | [`EXP_04.txt`](./EXP_04.txt) |
| **Exp 5a & 5b** | KVM Setup, Virt-Manager, QCOW2 Disk Creation, Resizing & Format Conversion | KVM, QEMU, libvirt, `qemu-img` | [`EXP_05.txt`](./EXP_05.txt) |
| **Exp 6** | File Transfer Between VMs (NFS Share, Shared Folders, SSH/SCP, FTP) | NFS kernel server, SCP, vsftpd | [`EXP_06.txt`](./EXP_06.txt) |
| **Exp 7a & 7b** | Hyper-V Configuration on Windows & VM Automation with PowerShell | Microsoft Hyper-V, PowerShell | [`EXP_07.txt`](./EXP_07.txt) |
| **Exp 8** | OpenStack (DevStack) Installation & VM Cloud Provisioning via CLI | DevStack, Nova, Glance, Neutron | [`EXP_08.txt`](./EXP_08.txt) |

---

## ⚡ 10-Second Quick Cheatsheet for Exam

### 1. Exp 2: Java Benchmark & Ping
```bash
javac Benchmark.java && java Benchmark
sudo ufw disable && ping -c 4 <OTHER_VM_IP>
```

### 2. Exp 3: Cold Migration (OVA)
- `echo "Proof" > ~/proof.txt && sudo shutdown -h now`
- VirtualBox: File $\rightarrow$ Export Appliance (`.ova`) $\rightarrow$ Import on Computer 2.

### 3. Exp 4: XenServer Live Migration
- Xen is Type-1 (bare-metal) $\rightarrow$ Dom0 is management domain.
- Requires Shared Storage (NFS) $\rightarrow$ Right click VM $\rightarrow$ Move VM $\rightarrow$ 0 downtime.

### 4. Exp 5: KVM & Disk Operations
```bash
# Create 10GB disk:
qemu-img create -f qcow2 mydisk.qcow2 10G
# Resize (+5GB):
qemu-img resize mydisk.qcow2 +5G
# Convert:
qemu-img convert -f qcow2 -O raw mydisk.qcow2 mydisk.raw
qemu-img convert -f qcow2 -O vdi mydisk.qcow2 mydisk.vdi
qemu-img convert -f qcow2 -O vpc mydisk.qcow2 mydisk.vhd
```

### 5. Exp 6: NFS File Transfer
```bash
# Ubuntu (Server):
sudo apt install nfs-kernel-server -y
echo "/srv/nfs/sharedfolder *(rw,sync,no_subtree_check,no_root_squash)" | sudo tee -a /etc/exports
sudo exportfs -a && sudo systemctl restart nfs-kernel-server

# Kali (Client):
sudo apt install nfs-common -y
sudo mount <UBUNTU_IP>:/srv/nfs/sharedfolder /mnt/nfs/sharedfolder
```

### 6. Exp 7: Hyper-V PowerShell Commands
```powershell
Get-VM
Start-VM -Name "PS_Ubuntu_VM"
Stop-VM -Name "PS_Ubuntu_VM"
```

### 7. Exp 8: OpenStack CLI Commands
```bash
source openrc admin admin
openstack image list
openstack flavor list
openstack server create --image <image_name> --flavor m1.tiny --nic net-id=private --key-name mykey my-first-vm
openstack server list
```

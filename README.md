# Virtualization & Cloud Computing Model Lab — Complete Master Reference (Experiments 2 to 10)

This repository contains complete, crystal-clear, step-by-step guides for **ALL 9 experiments (Experiments 2 through 10)**.
Each file is written in plain, beginner-friendly English with exact commands, GUI steps, and Viva Voce answers.

---

## 📑 Complete Experiment Directory

| Experiment | Title & Objective | Key Technology | File Link |
|---|---|---|---|
| **Exp 2a & 2b** | VM Creation, RAM/Disk Allocation, Host vs VM Java Benchmark & Ping Test | Oracle VirtualBox, Ubuntu, Java | [`EXP_02A_02B.txt`](./EXP_02A_02B.txt) |
| **Java Code** | Speed Benchmark Program for Exp 2a | Java JDK | [`Benchmark.java`](./Benchmark.java) |
| **Exp 3** | Cold Migration of VM using Export/Import Appliance (OVA) | VirtualBox, OVA/OVF, IP / USB | [`EXP_03.txt`](./EXP_03.txt) |
| **Exp 4a & 4b** | Citrix XenServer Bare-Metal Install & Live Migration via XenCenter | Citrix XenServer, XenCenter, NFS | [`EXP_04.txt`](./EXP_04.txt) |
| **Exp 5a & 5b** | KVM Setup, Virt-Manager, QCOW2 Disk Creation, Resizing & Format Conversion | KVM, QEMU, libvirt, `qemu-img` | [`EXP_05.txt`](./EXP_05.txt) |
| **Exp 6** | File Transfer Between VMs (NFS Share, Shared Folders, SSH/SCP, FTP) | NFS kernel server, SCP, vsftpd | [`EXP_06.txt`](./EXP_06.txt) |
| **Exp 7a & 7b** | Hyper-V Configuration on Windows & VM Automation with PowerShell | Microsoft Hyper-V, PowerShell | [`EXP_07.txt`](./EXP_07.txt) |
| **Exp 8** | OpenStack (DevStack) Installation & VM Cloud Provisioning via CLI | DevStack, Nova, Glance, Neutron | [`EXP_08.txt`](./EXP_08.txt) |
| **Exp 9** | Network Virtualization: Open vSwitch (OVS) & Linux Bridge | Open vSwitch, Linux Bridge, veth, Namespaces | [`EXP_09.txt`](./EXP_09.txt) |
| **Exp 10** | VHD on Windows & Restoring on Different Host + Storage Virtualization (LVM) | Windows VHD/VHDX, LVM (`pvcreate`, `vgextend`, `lvextend`) | [`EXP_10.txt`](./EXP_10.txt) |

---

## ⚡ 10-Second Quick Cheatsheet for Exam

### 1. Exp 2: Java Benchmark & Ping
```bash
javac Benchmark.java && java Benchmark
sudo ufw disable && ping -c 4 <OTHER_VM_IP>
```

### 2. Exp 3: Cold Migration (OVA)
- `echo "Proof" > ~/proof.txt && sudo shutdown -h now`
- VirtualBox: File $\rightarrow$ Export Appliance (`.ova`) $\rightarrow$ Send via IP/USB $\rightarrow$ Import on Computer 2.

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

### 8. Exp 9: Open vSwitch & Linux Bridge Commands
```bash
# OVS setup with namespaces:
sudo ip netns add VRF1 && sudo ip netns add VRF2
sudo ip link add veth1 type veth peer name veth2
sudo ip link add veth3 type veth peer name veth4
sudo ip link set veth1 netns VRF1 && sudo ip link set veth3 netns VRF2
sudo ovs-vsctl add-br br-ovs
sudo ovs-vsctl add-port br-ovs veth2 && sudo ovs-vsctl add-port br-ovs veth4
sudo ip link set veth2 up && sudo ip link set veth4 up && sudo ip link set br-ovs up
sudo ip netns exec VRF1 ip addr add 10.0.0.1/24 dev veth1 && sudo ip netns exec VRF1 ip link set veth1 up
sudo ip netns exec VRF2 ip addr add 10.0.0.2/24 dev veth3 && sudo ip netns exec VRF2 ip link set veth3 up
sudo ip netns exec VRF1 ping -c 4 10.0.0.2
```

### 9. Exp 10: Storage Virtualization (LVM on Linux)
```bash
# Add second disk /dev/sdb and extend LVM root partition:
sudo pvcreate /dev/sdb
sudo vgextend ubuntu-vg /dev/sdb
sudo lvextend -L +3G /dev/ubuntu-vg/root
sudo resize2fs /dev/ubuntu-vg/root
df -h /
```

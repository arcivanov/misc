#!/bin/bash -eEu

yumdownloader system-release
rm -rf /amzn-root || :
mkdir -p /amzn-root/var/lib
touch /amzn-root/var/lib/random-seed
rpm --rebuilddb --root=/amzn-root
rpm -i --root=/amzn-root --nodeps system-release*.rpm
#yum --installroot=/amzn-root reinstall -y system-release
yum --installroot=/amzn-root install -y epel-release rootfiles basesystem yum yum-utils sysfsutils binutils net-tools \
    openssh-clients openssh-server man wget sudo tar vim \
    xz rsync ca-certificates ethtool e2fsprogs \
    sendmail serial authconfig zip

yum --installroot=/amzn-root list installed | sed 's/@amzn-main\|@amzn-updates\|installed//'> amzn-root.list
yum list installed | sed 's/@amzn-main\|@amzn-updates\|installed//' > current.list

yum --installroot=/amzn-root clean all

rm -f /amzn-root/etc/mtab
ln -s /proc/mounts /amzn-root/etc/mtab
rm -f /amzn-root/dev/null
mknod -m 600 /amzn-root/dev/console c 5 1

cat > /amzn-root/etc/fstab <<EOF
tmpfs       /dev/shm    tmpfs   defaults        0   0
devpts      /dev/pts    devpts  gid=5,mode=620  0   0
EOF

cat > /amzn-root/etc/udev/rules.d/51-udev.nodes <<EOF
# These device have to be created manually
tty0
tty1
tty2
tty3
tty4
tty5
tty6

ttyp0
ttyp1
ttyp2
ttyp3
ttyp4
ttyp5
ttyp6

ptyp0
ptyp1
ptyp2
ptyp3
ptyp4
ptyp5
ptyp6
EOF

sed -r -i 's/Defaults\s+requiretty//' /amzn-root/etc/sudoers

rm -rf /vz/template/cache/centos-7-x86_64-azmn.tar.gz || :
tar zcf /vz/template/cache/centos-7-x86_64-amzn.tar.gz -C /amzn-root .

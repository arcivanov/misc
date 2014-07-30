#!/bin/bash -eEu

TINYCORE=${1:-}

if [ -z "$TINYCORE" ]; then
	echo "Usage: sg_flash_firmware.sh <path to tinycore_Seagate.gz>" >&2
	exit 1
fi

TMP_DIR="$(mktemp -d /tmp/sg_flash_firmwareXXXXXXXXXX)"
echo "Extracting into '$TMP_DIR'..."

( cd "$TMP_DIR" && gzip -c -d "$TINYCORE" | cpio --quiet -di 2>/dev/null || true )

ROOT_DIR="$(readlink -nf "$TMP_DIR/root")"
STELOGS_DIR="$(readlink -nf "$ROOT_DIR/stelogs")"

(
  cd "$STELOGS_DIR" && sudo "$TMP_DIR/usr/bin/STECon" -exitpause -colorizePassFail -USBSTELogs "$STELOGS_DIR" -scriptcab "$ROOT_DIR/FDLSG_LTO.cab" FDLSG_LTO.xml
  sync
)


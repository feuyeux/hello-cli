#!/usr/bin/env bash
set -e
SCRIPT_PATH="$(
    cd "$(dirname "$0")" >/dev/null 2>&1 || exit
    pwd -P
)"
cd "$SCRIPT_PATH" || exit

sh target/ici/bin/ici.sh
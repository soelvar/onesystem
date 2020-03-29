#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
curl -X POST -F "file=@$DIR/Input.txt" http://127.0.0.1:8080/onesystem/inputs
#!/bin/bash


until $(curl -o /dev/null -s -I -f http://localhost:$PORT); do
  sleep 5
done

while (( --count > 0 )); do
  for ROUTE in $WARMUP_ROUTES; do
    echo "[warmup] calling $ROUTE, Counting down: $count"
    curl -L "http://localhost:$PORT$ROUTE" >/dev/null 2>&1
    sleep 1
  done
done

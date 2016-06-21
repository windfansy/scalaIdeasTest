#!/bin/bash
touch result.csv
while IFS=',' read -ra line; do
    DATE=$(echo "${line[0]}" | tr -d '"')
    milliseconds=$(date -d "$DATE" '+%s%3N')
    gsdelayTime=$(echo "${line[1]}" | grep -oP 'gsdelay=[\d]*(?=&)')
    echo "${milliseconds},${gsdelayTime}" >> result.csv
done < "servertime_gsdelay.csv"
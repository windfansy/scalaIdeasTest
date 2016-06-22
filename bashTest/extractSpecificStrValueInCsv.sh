#!/bin/bash
touch profile_servertime_gsdelay_result.csv
while IFS=',' read -ra line; do
    profileId="${line[0]}"
    DATE=$(echo "${line[1]}" | tr -d '"')
    milliseconds=$(date -d "$DATE" '+%s%3N')
    gsdelayTime=$(echo "${line[2]}" | grep -oP 'gsdelay=[\d]*(?=&)') #如果gsdelay是负数就取不出来了，后面有时间可以优化
    echo "${profileId},${milliseconds},${gsdelayTime}" >> profile_servertime_gsdelay_result.csv
done < "profile_servertime_gsdelay.csv"
#!/bin/bash
if [ ! -d "tmp" ]; then
        mkdir "tmp"
fi

nameWithSuffix=${1##*/} #截取最后一个"/"后面的内容，即文件名
nameWithoutSuffix=${nameWithSuffix%.*}
suffix=${nameWithSuffix#*.}
resultName="${nameWithoutSuffix}Result.${suffix}"

sort -t ',' -k 1,1  $1 > "tmp/$nameWithoutSuffix.tmp"
{ while IFS=',' read -ra line; do
    if [ ! -e "${nameWithoutSuffix}Result.${suffix}" ]; then
       touch "${nameWithoutSuffix}Result.${suffix}"
       lastKey=${line[0]}
       outputLine=$lastKey
    fi
    if [ "$lastKey" == "${line[0]}" ]; then
        outputLine=$(echo $outputLine,${line[1]})
    else
       echo $outputLine >> "${nameWithoutSuffix}Result.${suffix}"
       lastKey=${line[0]}
       outputLine=$(echo $lastKey,${line[1]})
    fi
done < "tmp/$nameWithoutSuffix.tmp"
echo $outputLine >> "${nameWithoutSuffix}Result.${suffix}"
}

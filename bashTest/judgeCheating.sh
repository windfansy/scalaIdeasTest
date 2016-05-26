#!/bin/bash
# 计算duration的结果并存储到文件中，函数四个参数分别为   $1 startTime  $2 endTime  $3 count $4 resultFile
function saveDurationResult {
    startTime=$1
    endTime=$2
    count=$3
    resultFile=$4
    duration=$(( endTime-startTime + 1 ))
    speed=$(( count / duration ))
    startSecond=$(( startTime % 60 ))
    startMinute=$(( (startTime / 60 ) % 60))
    startHour=$(( startTime / 3600 ))
    endSecond=$(( endTime % 60 ))
    endMinute=$(( (endTime / 60 ) % 60))
    endHour=$(( endTime / 3600 ))
    printf "%2d:%2d:%2d    %2d:%2d:%2d    %8d    %8d\n " $startHour $startMinute $startSecond $endHour $endMinute $endSecond $duration $speed>> $resultFile
}
if [ ! -d "tmp" ]; then
        mkdir "tmp"
fi
originFileName=${1##*/} #截取最后一个"/"后面的内容，即文件名
cat  $1 | cut -d ' ' -f2  | awk -F':' '{print $1 * 60 * 60 + $2 * 60 + $3}' > "tmp/$originFileName.tmp" #截取日志中的时间并转换成Int的秒数
sort "tmp/$originFileName.tmp" | { while read timeValue; do
    if [ ! -e "$originFileName.result" ]; then
            printf "startTime    endTime    duration(s)    speed(per/s)\n" > "$originFileName.result"
            count=0
    fi
    if [ $count -eq 0 ]; then   # 整数判断用-eq
        startTime=${timeValue}
        lastTime=${timeValue}
        count=$(( count + 1))
    elif [ $(( timeValue-lastTime )) -gt 600 ]; then
        saveDurationResult $startTime $lastTime $count "$originFileName.result"
        count=1
        startTime=${timeValue}
        lastTime=${timeValue}
    else
        count=$(( count + 1))
        lastTime=${timeValue}
    fi
done
# 循环结束后只有两种情况:
# 1. count=1, 最后一条记录是新的开始
# 2. count>1, 最后一条记录跟前面属于同一个区间
# 这两种情况的代码实现是一致的。
saveDurationResult $startTime $lastTime $count "$originFileName.result"
}
#! /bin/bash
for i in $(seq -w 02 07)
do
hadoop fs -mkdir -p "/user/chenxirong/rawdata/wd/year=2016/month=201609/day=201609${i}"
hadoop distcp "hdfs://10.202.40.10:8020/user/hive/warehouse/rawdata.db/wd/year=2016/month=201609/day=201609${i}/*" "hdfs://10.200.40.60:8020/user/chenxirong/rawdata/wd//year=2016/month=201609/day=201609${i}/" >> "hadoop_distcp_$(date +%Y-%m-%d:%H:%M:%S).log"

done

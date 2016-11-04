#!/bin/bash

#TABLES="Session PageView DailyUserEvent ECommerce Conversion AbTest ConversionPathStep Except Exposure Heartbeat InternalLinkClick JunctionPoint MouseClick MouseScroll Product ScreenFocus SiteSearch SiteSearchClick"
TABELS="Session"
#PARTITION_DATE_TABLES="EqidMapping LogParserStatistic"

#NON_PARTITION_TABLES="PageCategory Profile2Solution"
NON_PARTITION_TABLES="Profile2Solution"
#PARTITION_PROFILEID_TABLES="Retention Revisit"

#PGS="commercial isp newmedia legacyMd gov zombie"
PGS="commercial"

#impala-shell -q "use wd3; create external table session_bounce like parquet '/user/chenxirong/etl/Session/year=2016/month=201609/day=20160901/profile_group=commercial/part-r-0.parquet' partitioned by (year int , month int , day int , profile_group string) stored as parquet location '/user/chenxirong/etl/test/Session/year=2016/month=201609/day=20160901/profile_group=commercial';"

#impala-shell -q "use wd3; create external table Profile2Solution_bounce like parquet '/user/chenxirong/etl/Profile2Solution/part-r-00001.parquet' stored as parquet location '/user/chenxirong/etl/Profile2Solution/';"

for DATE in $(seq 20160904 20160907)
do
YEAR=${DATE:0:4}
MONTH=${DATE:0:6}
impala-shell -q "use wd3; alter table session_bounce add partition(year=${YEAR}, month=${MONTH}, day=${DATE}, profile_group='${PGS}')location '/user/chenxirong/etl/Session/year=2016/month=201609/day=${DATE}/profile_group=commercial'"
done

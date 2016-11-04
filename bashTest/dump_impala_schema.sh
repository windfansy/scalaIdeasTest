#!/bin/bash

# Parameters :
  # $1   : impala db name
  # $2   : table name we should dump schema, for example "pageview session heartbeat"
  # $3   : view name we should dump schema, for example "ECommerceView conversioneventview"

db=$1

for table in  $2
do
  impala-shell -q "use $1; show create table $table" 2>/dev/null | awk -F '\\|' 'NR>3{print $2}/STORED AS PARQUET/{print ";";exit}'
done

for view in $3
do
  impala-shell -q "use $1; describe formatted $view;" 2>/dev/null | awk -F\| 'BEGIN{v="create view '"$view"' as"}/View Expanded Text/{ print v,$3";" }'
done

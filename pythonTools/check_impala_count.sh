#!/bin/bash

# Parameters :
  # $1   : impala db name
  # $2   : table name we should dump schema, for example "pageview session heartbeat"
  # $3   : view name we should dump schema, for example "ECommerceView conversioneventview"

db=$1
if [ -z "$2" ]; then
  tables="Session PageView DailyUserEvent ECommerce Conversion AbTest ConversionPathStep Except Exposure Heartbeat InternalLinkClick JunctionPoint MouseClick MouseScroll Product ScreenFocus SiteSearch SiteSearchClick EqidMapping LogParserStatistic dimPageCategory Profile2Solution Retention Revisit"
else
  tables="$2"
fi

for table in  $tables
do
  impala-shell -q "use $1; select count(*) from $table"
done

for view in $3
do
  impala-shell -q "use $1; describe formatted $view;" 2>/dev/null | awk -F\| 'BEGIN{v="create view '"$view"' as"}/View Expanded Text/{ print v,$3";" }'
done

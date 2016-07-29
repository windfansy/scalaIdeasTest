#!/bin/bash
MONGOADDRESS=10.200.200.157
AdministratorRegex="^WdV3Metadata_Administrator"
GsServerRegex="^WdV3Metadata_GS-SERVER"
T440PRegex="^WdV3Metadata_T440P"
T4501Regex="^WdV3Metadata_T4501"
WindfansyRegex="^WdV3Metadata_windfansy"
CyzRegex="^cyz"

mongo ${MONGOADDRESS} --eval "db.getMongo().getDBNames()" > originMongoDbs.txt
cat originMongoDbs.txt | head -n -1  | tail -n +4 | cut -d ',' -f1 | tr -d '"' | while read dbName;
do
    if [[ ${dbName} =~ ${AdministratorRegex} ]] || [[ ${dbName} =~ ${GsServerRegex} ]] || [[ ${dbName} =~ ${CyzRegex} ]] ||
    [[ ${dbName} =~ ${T440PRegex} ]] || [[ ${dbName} =~ ${T4501Regex} ]] || [[ ${dbName} =~ ${WindfansyRegex} ]]; then
        echo "dropping $MONGOADDRESS/$dbName"
        mongo "$MONGOADDRESS/$dbName" --eval "db.dropDatabase()"
    fi
done
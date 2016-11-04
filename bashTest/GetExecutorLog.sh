#! /bin/bash
if [ ! -d "log" ]; then
        mkdir "log"
fi

if [ ! -d "tmp" ]; then
        mkdir "tmp"
fi
logName=$(date +%Y-%m-%d:%H:%M:%S)
touch "log/$logName.log"

function parselog {
    element=$1
    username=$2
    # extract hostname
    host=$(echo $element | cut -d '/' -f3 | cut -d ':' -f1)

    wget "${element% #*}" -O tmp/executor.tmp
    # extract date like '20161026' from web
    dateLine=$(sed -n '/<title>/p' tmp/executor.tmp)
    theDate=$(echo "$dateLine" | sed -e 's/^[[:space:]]*//' | cut -d ' ' -f4)
    if [ ! -d "$theDate" ]; then
        mkdir $theDate
        # get executor link from web
        sed -n '/<a href="http:\/\/gs-server-.*stderr/p' tmp/executor.tmp > $theDate/Url.tmp
		echo "${theDate} job's urls have all been extracted, cat ${theDate}/Url.tmp for details" >> log/$logName.log
        # make up real executor link
        empty="true"
		while read origin_href; do
            tmp=${origin_href#*//}
            server=${tmp%:*}
            tmp=${origin_href#*containerlogs/}
            container=${tmp%/${username}*}
            real_href="http://${host}:19888/jobhistory/logs/${server}:8041/${container}/${container}/${username}/stderr?start=0"
            # log like exception also not contains date format `20161026`, so save all html to our log
            # wget -O- $real_href | sed -n '/[0-9][0-9]\/[0-9][0-9]\/[0-9][0-9]/p' > $theDate/$server.log
            wget -O- $real_href  > $theDate/$server.log
			if [ -s $theDate/$server.log ]; then
				empty="false"
			fi
        done < $theDate/Url.tmp
		if [ "$empty" == "true" ]; then
			echo "${theDate} executor logs are all empty, rm -rf ${theDate} directory " >> log/$logName.log
			rm -rf "${theDate}"
		elif [ "$empty" == "false" ]; then
			echo "${theDate} executor logs have all been downloaded, ll ${theDate} for details: " >> log/$logName.log
			# awk 'FNR==1{ printf ("\n\n ################################### %s ##################################\n\n ", ARGV[ARGIND]) }1' $theDate/gs-server-* > $theDate/gs-server-combineLog.log
			# grep limit in logs and save result to limitResult.txt
			grep -n -H 'has reaching the limit' $theDate/gs-server*.log > $theDate/$theDate_limitResult.txt
			echo "${theDate} logs have finish grep 'has reaching the limit' analysis, less ${theDate}/limitResult.txt for details" >> log/$logName.log
		fi
    else
        echo "${theDate} directory exist, skip" >> log/$logName.log
    fi
}

if [ "$1" == "-all" ]; then
	echo "Run in all Mode" >> log/$logName.log
	pre=${2%page=*}
	post="&${2#*&}"
	for ((i=$3;i<=$4;i++));
	do
	     web="${pre}page=$i$post"
		 wget -O- $web | awk '/WD ETL for/ { print x }; { x=$0 }'  >> "tmp/$logName-urlList.tmp"
	done
	cat tmp/${logName}-urlList.tmp | awk -F '[<>]' '{print "http://gs-server-1000:18088/history/" $5 "/executors/"}' > "tmp/$logName-urlList.txt"
	echo "Finish creating $logName-urlList.txt, saved in tmp/$logName-urlList.txt" >> log/$logName.log
	readarray urlArray < "tmp/$logName-urlList.txt"
	for element in "${urlArray[@]}"
	do      
        	parselog $element $3
	done	
elif [ "$1" == "-list" ]; then
	echo "Run in List Mode" >> log/$logName.log
	readarray urlArray < $2
	for element in "${urlArray[@]}" 
	do
		parselog $element $3
	done
elif [ "$1" == "-link" ]; then
	echo "Run in link Mode" >> log/$logName.log
	parselog $2 $3
else
   echo "error argument $@"
   exit 1
fi

# grep -n -H -R 'has reaching the limit' ./20*/*.log | sort -u -t' ' -k9,10 > "log/${logName}-totalLimitResult.txt"
# echo "all logs grep 'has reaching the limit' result is saved to log/${logName}-totalLimitResult.txt " >> log/$logName.log
echo "Job is done"
# echo "cat log/${logName}-totalLimitResult.txt for result"
echo "cat log/$logName.log for running state"

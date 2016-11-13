import subprocess
import batch_maintain_impala as impala
import utils


DEFAULT_LOCAL_BASE_PATH = '/home/chenxirong/etl'
DEFAULT_HDFS_BASE_PATH = '/user/chenxirong'


def deco(func):
    def wrapped():
        print("******************************************************************************************************")
        return_code = func()
        print("******************************************************************************************************")
        return return_code
    return wrapped


def prepare_local_path(local_base_path=None):
    if local_base_path is None:
        local_base_path = DEFAULT_LOCAL_BASE_PATH
    currentjars_path = "{0}/currentjars ".format(local_base_path)
    scripts_path = "{0}/scripts ".format(local_base_path)
    postjobs_log_path = "{0}/logs/postjobs ".format(local_base_path)
    daily_logs_path = "{0}/logs/daily ".format(local_base_path)
    hourly_logs_path = "{0}/logs/hourly ".format(local_base_path)
    all_local_path = "mkdir -p " + currentjars_path + scripts_path + postjobs_log_path + daily_logs_path + hourly_logs_path
    # print(all_local_path)
    subprocess.call(all_local_path.split())


def prepare_hdfs_path(hdfs_base_path=None, date=None):
    if hdfs_base_path is None:
        hdfs_base_path = DEFAULT_HDFS_BASE_PATH
    output_path = "{0}/etl ".format(hdfs_base_path)
    prepare_path = "{0}/logs ".format(hdfs_base_path)
    etl_logs_path = "{0}/etl-logs/ ".format(hdfs_base_path)
    daily_succ_path = "{0}/etl-logs/daily/succ ".format(hdfs_base_path)
    all_hdfs_path = "hadoop fs -mkdir -p " + output_path + prepare_path + etl_logs_path + daily_succ_path
    # print(all_hdfs_path)
    subprocess.call(all_hdfs_path.split())
    if date is not None:
        touch_prepare_flag = "hadoop fs -touchz {0}/logs/wd-weblog-preparser-{1}.txt ".format(hdfs_base_path, date)
        subprocess.call(touch_prepare_flag.split())


def remove_etl_did_flag(hdfs_base_path=None, date=None):
    if hdfs_base_path is None:
        hdfs_base_path = DEFAULT_HDFS_BASE_PATH
    remove_flag_cmd = "hadoop fs -rm {0}/etl-logs/*.txt ".format(hdfs_base_path).split() if date is None else \
        "hadoop fs -rm {0}/etl-logs/wd-etl-{1}.txt ".format(hdfs_base_path).split()
    # print(remove_flag_cmd)
    subprocess.call(remove_flag_cmd)


def create_hbase_table():
    create_wd_user_cmd = ["echo", "create 'wd-user', {NAME => 'f', COMPRESSION => 'SNAPPY',BLOCKSIZE=>'16384', VERSIONS=> 1}, {SPLITS=>['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']}"]
    create_wd_userid_mapping_cmd = ["echo", "create 'wd-userId-mapping', {NAME => 'u', COMPRESSION => 'SNAPPY',BLOCKSIZE=>'16384', VERSIONS=> 1}, {SPLITS=>['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f']}"]
    create_wd_ecom_id_cmd = ["echo", "create 'wd-ecom-id', {NAME => 'f', COMPRESSION => 'SNAPPY', BLOCKSIZE=>'16384', VERSIONS=> 10, TTL => '2592000'}, {SPLITS=>['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']}"]
    hbase_shell_cmd = ["hbase", "shell", "-n"]

    create_wd_user = subprocess.Popen(create_wd_user_cmd, stdout=subprocess.PIPE)
    subprocess.Popen(hbase_shell_cmd, stdin=create_wd_user.stdout).communicate()

    create_wd_userid_mapping = subprocess.Popen(create_wd_userid_mapping_cmd, stdout=subprocess.PIPE)
    subprocess.Popen(hbase_shell_cmd, stdin=create_wd_userid_mapping.stdout).communicate()

    create_wd_ecom_id = subprocess.Popen(create_wd_ecom_id_cmd, stdout=subprocess.PIPE)
    subprocess.Popen(hbase_shell_cmd, stdin=create_wd_ecom_id.stdout).communicate()


def attention():
    _jar_attention_()
    _rawdata_attention_()


@deco
def _jar_attention_():
    print("Should prepare spark-wd-etl-X.X.X-jar-with-dependencies.jar")
    print("Should prepare spark-wd-post-process-X.X.X-jar-with-dependencies.jar")
    print("Should prepare spark-wd-filters-X.X-SNAPSHOT.jar, insure it could be fetched by all cluster, "
          "recommended path is /usr/share/nfs_share_dir/")


@deco
def _rawdata_attention_():
    print("Should prepare rawdata.db/wd and rawdata.db/legacy_md_streaming")
    print("Recommended profileId in wd are 702(UA Web), 2915(Home Inn App), 100324(China Blue TV Legacy_Md)")
    print("Recommended path like /user/hive/warehouse/rawdata.db/legacy_md_streaming, "
          "/user/hive/warehouse/rawdata.db/wd")


def create_impala_table(force_flag=False):
    if force_flag:
        impala.drop_tables()
    create_table_sql = impala.show_create_tables(hostname='impala.internal.gridsumdissector.com')
    tmp_sql = open("_tmp_.sql", "wb")
    tmp_sql.write(create_table_sql)
    tmp_sql.close()
    impala.create_tables_from_file("_tmp_.sql")
    # os.remove(tmp_sql)


def main():
    parser = utils.setup_argparse()
    args = parser.parse_args()

    prepare_local_path(args.local_base_path)
    prepare_hdfs_path(args.hdfs_base_path, args.date)
    remove_etl_did_flag(args.hdfs_base_path)
    create_hbase_table()
    create_impala_table(args.force_flag)
    attention()


if __name__ == "__main__":
    main()

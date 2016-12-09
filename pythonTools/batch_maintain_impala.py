import subprocess


PARTITION_PROFILE_GROUP_TABLES = ['Session', 'PageView', 'DailyUserEvent', 'ECommerce', 'Conversion', 'AbTest',
                                  'ConversionPathStep', 'Except', 'Exposure', 'Heartbeat', 'InternalLinkClick',
                                  'JunctionPoint', 'MouseClick', 'MouseScroll', 'Product',
                                  'ScreenFocus', 'SiteSearch', 'SiteSearchClick']
PARTITION_DATE_TABLES = ['EqidMapping', 'LogParserStatistic']
NON_PARTITION_TABLES = ['PageCategory', 'Profile2Solution']
PARTITION_PROFILEID_TABLES = ['Retention', 'Revisit']
PRE_AGGREGATION_TABLES = ['preagg_sessionsmalldims', 'preagg_sessionpaiddims']
PGS = ['commercial', 'isp', 'newmedia', 'legacyMd', 'gov', 'zombie']


def _execute_impala_sql_(sql, target_db="wd3", extra_cmd=None, hostname=None):
    if hostname is None:
        hostname = 'localhost'
    cmd = '''impala-shell -i {0} -q'''.format(hostname).split(' ')
    sql = "Use {0};".format(target_db) + sql
    if extra_cmd is None:
        cmd += [sql]
        return subprocess.Popen(cmd, stdout=subprocess.PIPE).communicate()
    else:
        cmd += [sql]
        p1 = subprocess.Popen(cmd, stdout=subprocess.PIPE)
        p2 = subprocess.Popen(extra_cmd, stdin=p1.stdout, stdout=subprocess.PIPE)
        return p2.communicate()


def _describe_table_sql_(tb):
    return "describe {0};".format(tb)


def _show_create_table_sql_(tb):
    return "show create table {0}".format(tb)


def _drop_table_sql_(tb):
    return "drop table {0}".format(tb)


def _map_table_(tb):
    if tb == 'PageCategory':
        return "dimpagecategory"
    else:
        return tb


def describe_tables(all_table=True, hostname=None):
    if all_table:
        for tb in PARTITION_PROFILE_GROUP_TABLES + PARTITION_DATE_TABLES + NON_PARTITION_TABLES + PARTITION_PROFILEID_TABLES:
            tb = _map_table_(tb)
            desc_sql = _describe_table_sql_(tb)
            _execute_impala_sql_(desc_sql, hostname=hostname)


def show_create_tables(all_table=True, hostname=None, *tbs):
    create_table_sql = ''
    if all_table:
        for tb in PARTITION_PROFILE_GROUP_TABLES + PARTITION_DATE_TABLES + NON_PARTITION_TABLES + PARTITION_PROFILEID_TABLES:
            tb = _map_table_(tb)
            desc_sql = _show_create_table_sql_(tb)
            out, err = _execute_impala_sql_(desc_sql, extra_cmd=['awk', '-F', '|', 'NR>3{print $2}/STORED AS PARQUET/{print \";\";exit}'], hostname=hostname)
            create_table_sql += out
    else:
        for tb in tbs:
            tb = _map_table_(tb)
            desc_sql = _show_create_table_sql_(tb)
            out, err = _execute_impala_sql_(desc_sql, extra_cmd=['awk', '-F', '|', 'NR>3{print $2}/STORED AS PARQUET/{print \";\";exit}'], hostname=hostname)
            create_table_sql += out
    return create_table_sql


def drop_tables(all_table=True, hostname=None):
    if all_table:
        for tb in PARTITION_PROFILE_GROUP_TABLES + PARTITION_DATE_TABLES + NON_PARTITION_TABLES + PARTITION_PROFILEID_TABLES:
            tb = _map_table_(tb)
            drop_sql = _drop_table_sql_(tb)
            _execute_impala_sql_(drop_sql, hostname=hostname)


def create_tables_from_file(create_table_file):
    cmd = '''impala-shell -f {0}'''.format(create_table_file).split(' ')
    subprocess.call(cmd)


def main():
    # describe_tables()
    show_create_tables(all_table=False, hostname=None, MouseClick)
    #drop_tables()


if __name__ == "__main__":
    main()

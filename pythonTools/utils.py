import argparse


def setup_argparse():
    parser = argparse.ArgumentParser(
        formatter_class=argparse.RawTextHelpFormatter,
        description='WD ETL Test Helper Script.')
    parser.add_argument('-lbp', '-local_base_path', dest='local_base_path', help='like /home/chenxirong/etl')
    parser.add_argument('-hbp', '-hdfs_base_path', dest='hdfs_base_path', help='like /user/chenxirong')
    parser.add_argument('-d', '--date', dest='date', help='date format: YYYYmmdd')
    parser.add_argument('-f', '--force', dest='force_flag', help='Force create impala table. '
                                                                 'Set true to drop all wd3 table then create')
    return parser

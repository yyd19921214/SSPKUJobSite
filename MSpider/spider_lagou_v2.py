__author__ = 'yangyudong'
import requests
from requests.auth import HTTPBasicAuth
import json
import pymysql
import time
import re
import random


class DBHelper(object):
    dbName = 'jobDB'
    user = 'root'
    pwd = 'root'

    def __init__(self):
        self.db = pymysql.connect("localhost", self.user, self.pwd, self.dbName, charset="utf8")

    def insertCompanyBatch(self, companyList):
        cursor = self.db.cursor()
        sql = "INSERT INTO company(name,source,sourceId,alias,city,type,keyWord,financeStage,industry,description,createTime,refLink\
         VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
        params = []
        for company in companyList:
            params.append((company['name'], company['source'], company['sourceId'], company['alias'], company['city'] \
                               , company['type'], company['keyWord'], company['financeStage'], \
                           company['industry'], company['description'], company['createTime'], company['refLink']))
        try:
            cursor.executemany(sql, params)
            # 执行sql语句
            self.db.commit()
            return id
        except Exception as e:
            print(e)
            self.db.rollback()

    def insertCompany(self, company):
        cursor = self.db.cursor()
        sql = "INSERT INTO company(name,source,sourceId,alias,city,type,keyWord,financeStage,industry,description,createTime,refLink) VALUES('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}','{8}','{9}','{10}','{11}')".format(
            company['name'], company['source'], company['sourceId'], company['alias'], company['city'] \
            , company['type'], company['keyWord'], company['financeStage'], \
            company['industry'], company['description'], company['createTime'], company['refLink'])
        print(sql)
        try:
            cursor.execute(sql)
            id = int(cursor.lastrowid)
            self.db.commit()
            return id
        except Exception as e:
            print(e)
            self.db.rollback()

    def insertJob(self, job):
        cursor = self.db.cursor()
        sql = 'select id,name,city from company where sourceId=\'{0}\''.format(job['companyId'])
        cursor.execute(sql)
        result = cursor.fetchone()
        if result==None:
            return
        cid = result[0]
        cname = result[1]
        city = result[2]
        sql= "INSERT INTO jobInfo(name,source,sourceId,companyId,city,location,education,\
            minExperience,maxExperience,minSalary,maxSalary,state,workType,createTime,refLink,searchIndex)\
             VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"

        param=list([job['name'], job['source'], job['sourceId'], cid, city, job['location'], job['education'],
                   job.get('minExperience', None), job.get('maxExperience', None),
                   job.get('minSalary', None), job.get('maxSalary', None), job['state'], job['workType'],
                   job['createTime'], job['refLink'], '{0} {1}'.format(job['name'], cname)
                   ])
        print(sql)
        try:
            cursor.execute(sql,param)
            id = int(cursor.lastrowid)
            self.db.commit()
            return id
        except Exception as e:
            print(e)
            self.db.rollback()

    def checkCompanyExist(self,sourceId):
        cursor = self.db.cursor()
        sql = 'select id from company where sourceId=\'{0}\''.format(sourceId)
        cursor.execute(sql)
        result = cursor.fetchone()
        if result:
            return True
        else:
            return False

    def selectCompanyList(self,limit):
        cursor = self.db.cursor()
        sql = 'select id,name,sourceId from company limit {0}'.format(limit)
        cursor.execute(sql)
        result = cursor.fetchall()
        return result

    def checkJobExist(self,sourceId):
        cursor = self.db.cursor()
        sql = 'select id from jobinfo where sourceId=\'{0}\''.format(sourceId)
        cursor.execute(sql)
        result = cursor.fetchone()
        if result:
            return True
        else:
            return False

def request_with_proxy(url,data):
    proxies_list=['116.255.162.107',
                  '121.41.8.23',
                  '112.74.108.33',
                  '114.215.174.98',
                  '116.255.162.135',
                  '120.76.142.46',
                  '182.254.247.118',
                  '115.159.145.200',
                  '115.159.1.47',
                  '127.0.0.1']
    for _ in range(3):
        proxy=random.choice(proxies_list)
        try:
            if proxy!='127.0.0.1':
                proxy_map={'http':'http://duoduo999:tfc41zc5@{0}:16816/'.format(proxy)}
                print(proxy_map)
                r=requests.session().post(url=url,proxies=proxy_map,data=data,timeout=5)
            else:
                r=requests.session().post(url=url,data=data,timeout=5)
            return r
        except Exception as e:
            print(e)
            print('proxy error:{}'.format(proxy))


def parser_company_in_city(city_config):
    city_code = city_config['code']
    mcookie = {"user_trace_token": "20170524174617-d5bc9e2b-4065-11e7-8db6-5254005c3644","LGUID":"20170524174617-d5bca29f-4065-11e7-8db6-5254005c3644"}
    wait_insert = []
    for i in range(1, 10):
        print('page is {0}'.format(i))
        try:
            r=request_with_proxy(url='https://www.lagou.com/gongsi/{0}-0-0.json'.format(city_code),data={'pn': i, 'sortField': 0, 'havemark': 0})
            # r = requests.post(url='https://www.lagou.com/gongsi/{0}-0-0.json'.format(city_code),
            #                   data={'pn': i, 'sortField': 0, 'havemark': 0}, cookies=mcookie)
            res = json.loads(str(r.content, encoding='utf-8'))
            if 'result' in res and len(res['result']) > 0:
                print(res)
                for e in res['result']:
                    wait_insert.append(e)
        except Exception:
            print('error!')
    return wait_insert



def parser_job_random():
    dbh = DBHelper()
    url = 'https://www.lagou.com/gongsi/searchPosition.json'
    # mcookie = {"user_trace_token": "20170423133133-0a59a66b266649b2996f0592fb9f5e08",
    #            "LGUID": "20170524174617-d5bca29f-4065-11e7-8db6-5254005c3644"}
    wait_insert = []
    companyList=dbh.selectCompanyList(50)
    for e in companyList:
        data = {'companyId': e[2].split('_')[1], 'pageNo': 1, 'pageSize': 50}
        try:
            import time
            time.sleep(1)
            # proxies = {'http': 'http://duoduo999:tfc41zc5@116.255.162.107:16816/'}
            # r = requests.post(url=url,data=data,proxies=proxies)
            r=request_with_proxy(url,data=data)
            res = json.loads(str(r.content, encoding='utf-8'))
            if res.get('message') == '操作成功':
                for e in res['content']['data']['page']['result']:
                    print(e)
                    wait_insert.append(e)
                    if len(wait_insert)>10:
                        break
        except:
            print('error!')
            pass
    return wait_insert

# def parser_job_simple_info_city(city_name):
#     url = 'https://www.lagou.com/gongsi/searchPosition.json'
#     mcookie = {"user_trace_token": "20170423133133-0a59a66b266649b2996f0592fb9f5e08","LGUID":"20170524174617-d5bca29f-4065-11e7-8db6-5254005c3644"}
#     wait_insert = []
#     row = 0
#     with open('lagou_{0}.txt'.format(city_name), 'r', encoding='utf-8') as fin:
#         for line in fin:
#             row += 1
#             print(row)
#             t = json.loads(line)
#             try:
#                 data = {'companyId': t['companyId'], 'pageNo': 1, 'pageSize': 50}
#                 r = requests.post(url=url, data=data, cookies=mcookie)
#                 res = json.loads(str(r.content, encoding='utf-8'))
#                 if res.get('message') == '操作成功':
#                     for e in res['content']['data']['page']['result']:
#                         wait_insert.append(e)
#             except e:
#                 print(e)
#                 pass
#
#     with open('lagou_{0}_job.txt'.format(city_name), 'w', encoding='utf-8') as fout:
#         for e in wait_insert:
#             fout.write('{0}\n'.format(json.dumps(e, ensure_ascii=False)))


def save_company_db(wait_insert):
    dbh = DBHelper()
    dateTime = time.strftime('%Y-%m-%d', time.localtime(time.time()))
    insert_num=0
    for t in wait_insert:
        sourceId='lagou_{0}'.format(t['companyId'])
        if dbh.checkCompanyExist(sourceId):
            continue
        com = dict()
        insert_num+=1
        com['name'] = t['companyShortName']
        com['source'] = 'lagou'
        com['sourceId'] = sourceId
        com['alias'] = t['companyFullName']
        com['city'] = t['city']
        com['type'] = 1
        com['keyWord'] = ''
        com['financeStage'] = t['financeStage']
        com['industry'] = t['industryField']
        com['description'] = t['companyFeatures']
        com['createTime'] = dateTime
        com['refLink'] = 'https://www.lagou.com/gongsi/{0}.html'.format(t['companyId'])
        dbh.insertCompany(com)
    print(insert_num)


def save_job_db(wait_insert):
    dbh = DBHelper()
    dateTime = time.strftime('%Y-%m-%d', time.localtime(time.time()))
    for t in wait_insert:
        sourceId='lagou_{0}'.format(t['positionId'])
        if dbh.checkJobExist(sourceId):
            continue
        job = dict()
        job['name'] = t['positionName']
        job['source'] = 'lagou'
        job['sourceId'] = sourceId
        job['companyId'] = 'lagou_{0}'.format(t['companyId'])
        job['location'] = t.get('district',None)
        e = t['education']
        if '大专' in e:
            job['education'] = 1
        elif '本科' in e:
            job['education'] = 2
        elif '硕士' in e:
            job['education'] = 3
        elif '博士' in e:
            job['education'] = 4
        else:
            job['education'] = None

        workyear = t['workYear']
        workyearRegex_1 = '(\d+)-(\d+)年'
        workyearRegex_2 = '(\d+)年以下'
        workyearRegex_3 = '(\d+)年以上'
        if '应届' in workyear:
            job['minExperience'] = -1
            job['maxExperience'] = -1
        elif re.match(workyearRegex_1, workyear):
            m = re.match(workyearRegex_1, workyear).groups()
            job['minExperience'] = int(m[0])
            job['maxExperience'] = int(m[1])
        elif re.match(workyearRegex_2, workyear):
            m = re.match(workyearRegex_2, workyear).groups()
            job['maxExperience'] = int(m[0])
        elif re.match(workyearRegex_3, workyear):
            m = re.match(workyearRegex_3, workyear).groups()
            job['minExperience'] = int(m[0])
        salary = t['salary']
        salaryRegex_1 = '(\d+)k-(\d+)k'
        salaryRegex_2 = '(\d+)k以上'
        if re.match(salaryRegex_1, salary):
            m = re.match(salaryRegex_1, salary).groups()
            job['minSalary'] = int(m[0])
            job['maxSalary'] = int(m[1])
        elif re.match(salaryRegex_2, salary):
            m = re.match(salaryRegex_2, salary).groups()
            job['minSalary'] = int(m[0])
        job['state'] = 1
        if t['jobNature'] == '全职':
            job['workType'] = 1
        elif t['jobNature'] == '实习':
            job['workType'] = 2
        elif t['jobNature'] == '兼职':
            job['workType'] = 3

        job['createTime'] = dateTime
        job['refLink'] = 'https://www.lagou.com/jobs/{0}.html'.format(t['positionId'])
        dbh.insertJob(job)


# wait_insert=parser_company_in_city({'code':2,'name':'beijing'})
# print(len(wait_insert))
# save_company_db(wait_insert)


wait_insert=parser_job_random()
print(len(wait_insert))
save_job_db(wait_insert)


# job = {'companyId': 'lagou_1712'}
# dbh = DBHelper()
# dbh.insertJob(job)
# save_job_db('lagou_shengzheng_job.txt')
# parser_company_in_city({'code':5,'name':'chongqing'})
# parser_job_simple_info()
# s='suzhou'
# parser_job_simple_info_city(s)
# save_job_db('lagou_{0}_job.txt'.format(s))

# print(dbh.checkCompanyExist('lagou_157255555'))
# # url='http://www.baidu.com'
# import urllib.request as req
# proxy = req.ProxyHandler({'http': r'http://duoduo999:tfc41zc5@116.255.162.107:16816'})
# auth = req.HTTPBasicAuthHandler()
# opener = req.build_opener(proxy, auth, req.HTTPHandler)
# req.install_opener(opener)
# conn = req.urlopen('http://www.baidu.com')
# return_str = conn.read()
# print(return_str)
# myproxies={}
# r = requests.post(url=url,proxies={'http':'222.161.56.166:9000'},timeout=2000)
# print(r.content)


# proxies = {'http': 'http://duoduo999:tfc41zc5@116.255.162.107:16816/'}
# # auth = HTTPBasicAuth('duoduo999', 'tfc41zc5')
# r = requests.post(url='http://www.baidu.com', proxies=proxies)
# print(r)

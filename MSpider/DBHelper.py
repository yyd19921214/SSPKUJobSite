import pymysql
import util


class DBHelper(object):
    dbName = 'blog'
    user = 'root'
    pwd = 'root'

    def __init__(self):
        self.db = pymysql.connect("localhost", self.user, self.pwd, "blog", charset="utf8")

    def insertCompany(self, company):
        id = -1
        cursor = self.db.cursor()
        sql = "INSERT INTO company(companyName,companyType,companyLocation,keyWord,companyIntro) VALUES('{0}','{1}','{2}','{3}','{4}')".format(
            company['ShortName'], company['type'], company['city'], company['industry'], company['desc'])
        try:
            cursor.execute(sql)
            id = int(cursor.lastrowid)
            # 执行sql语句
            self.db.commit()
        except Exception as e:
            print(e)
            self.db.rollback()
        return id

    def insertJob(self, jobList):
        id = -1
        cursor = self.db.cursor()
        sql = "INSERT INTO jobInfo(companyId,jobName,location,requireDegree,\
        minExperience,maxExperience,minSalary,maxSalary,workType,postTime,jobDesc)\
         VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
        params=[]
        for job in jobList:
            params.append((job['companyId'],job['jobName'],job['jobaddress'],job.get('requireDegree',None),job.get('minExperience',None)\
                           ,job.get('maxExperience',None),job.get('minSalary',None),job.get('maxSalary',None),\
                           job.get('workType',None),job.get('postTime',None),job.get('desc',None)))
        try:
            cursor.executemany(sql,params)
            # 执行sql语句
            self.db.commit()
        except Exception as e:
            print(e)
            self.db.rollback()

    def selectCompany(self,name=None,limitNum=200):
        cursor = self.db.cursor()
        if name==None:
            sql='select companyId,companyName,companyLocation from company limit {0}'.format(limitNum)
        else:
            sql='select companyId,companyName,companyLocation from company where companyName like \'%{0}%\''.format(name)

        cursor.execute(sql)
        result = cursor.fetchall()
        return result;

    def checkJobIfExist(self,companyId,jobName):
        cursor=self.db.cursor()
        sql= 'select jobInfoId from jobInfo where companyId={0} and jobName=\'{1}\''.format(companyId,jobName)
        cursor.execute(sql)
        rs=cursor.fetchall()
        if len(rs)>0:
            return True;
        else:
            return False;

    def selectJobs(self,jobName=None,num=200):
        cursor = self.db.cursor()
        if jobName!=None:
            sql='select jobInfoId,jobName from jobInfo where jobName like \'%{0}%\' limit {1}'.format(jobName,num)
        else:
            sql = 'select jobInfoId,jobName from jobInfo limit {0}'.format(num)
        cursor.execute(sql)
        rs=cursor.fetchall()
        return rs


    def selectCompanyByName(self,name):
        cursor=self.db.cursor()
        sql='select companyId,companyName,companyLocation from company where companyName like \'%{0}%\''.format(name)
        cursor.execute(sql)
        rs=cursor.fetchone()
        return rs


    def insertUsers(self,userList):
        cursor = self.db.cursor()
        sql = "INSERT INTO userinfo(nickName,passwd,email,\
            telPhone,userType) VALUES(%s,%s,%s,%s,%s)"

        params = []
        for user in userList:
            params.append((user['nickName'], user['passwd'], user['email'], user['telPhone'],user['userType']))

        try:
            cursor.executemany(sql, params)
            # 执行sql语句
            self.db.commit()
        except Exception as e:
            print(e)
            self.db.rollback()











if __name__ == '__main__':
    count=0
    dbh = DBHelper()
    import random
    print(random.shuffle(list(dbh.selectCompany())))


            # print(line.strip('\r\n'))


    # dbh = DBHelper()
    # print(dbh.selectCompanyByName('微软'))
    # print(len(dbh.selectCompany(limitNum=1000)));
    # print(dbh.selectCompanyByName('搜狗'))
    # print(dbh.checkJobIfExist(671,'ios高级开发工程师'))
    # rs=dbh.selectCompany(name='阿里巴巴集团')
    # print (rs[0][1])

    # jobList = util.read_list('lagou_com_jobs.txt')
    # job=jobList[0]
    # job['companyId']=4024
    # import time
    # job['postTime']=time.strftime('%Y-%m-%d', time.localtime(time.time()))
